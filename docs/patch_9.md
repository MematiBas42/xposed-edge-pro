# Patch 9: Modern Android InputMethodManagerService (IMMS) Compatibility (Action 85)

## Overview & Scope
* **Target Action:** Action 85 (`action_soft_keyboard` / Toggle / Show / Hide / Picker / Switch IME)
* **Affected Classes:** `com.jozein.xedgepro.xposed.p0`, `com.jozein.xedgepro.xposed.j2`
* **Root Causes:**
  1. AOSP Android 11+ moved `InputMethodManagerService$LocalServiceImpl` from an independent class with field `mService` to a non-static inner class of `InputMethodManagerService` with synthetic outer reference `this$0`. Old reflection calling `XposedHelpers.getObjectField(..., "mService")` threw `NoSuchFieldError`.
  2. AOSP Android 15+ (commit `a438ce172b441c8297eadde8d990ab292f5aa7d1`) encapsulated `InputMethodManagerService` behind `IInputMethodManagerImpl.Stub` for IPC. Calling `ServiceManager.getService("input_method")` no longer returned an instance of `InputMethodManagerService`, causing `j2.s()` to throw `RuntimeException: Object ... not instance of ...`.
  3. In Android 14+ / 15+, per-user states (`mCurInputContext`, `mCurInputConnection`, `mFocusedWindow`, `mBindingController`) were relocated into `com.android.server.inputmethod.UserData` accessed via `getUserData(userId)`.
  4. Method signatures for `hideCurrentInputLocked` and `showCurrentInputLocked` changed to require `ImeTracker.Token` and omit `ResultReceiver`.

## Architectural Solution: Multi-Tier Cascading Fallback

### Tier 1: IMMS Instance Resolution (`p0.x()` & `p0.s()`)
* **SDK 29..37+:**
  1. Attempt reading `InputMethodManagerInternal.mService` (Android 9-10).
  2. On failure, extract enclosing instance `XposedHelpers.getSurroundingThis(internal)` (`this$0` in Android 11-17+).
* **Fallback to ServiceManager Binder:**
  1. If binder is direct instance of `InputMethodManagerService` (Android 5-14), return directly.
  2. If binder is `IInputMethodManagerImpl` (Android 15-17+), extract `mCallback` field which holds the inner `InputMethodManagerService` instance.
* **Fallback for Android < 29:**
  * Read `w1.g2().u("mInputMethodManager")`.

### Tier 2: IME Window & State Resolution (`getFocusedWindowToken()`, `F()`, `G()`)
* **Window Token:** Checks `mLastImeTargetWindow` -> `mCurFocusedWindow` -> `getUserData(userId).mImeBindingState.mFocusedWindow`.
* **Input Connection (`F()`):** Checks `mCurInputContext` -> `getUserData(userId).mCurInputConnection`.
* **Selected Method ID (`G()`):** Checks `getSelectedMethodIdLocked` -> `getUserData(userId).mBindingController.getSelectedMethodId()` -> `mCurMethodId`.

### Tier 3: IME Operations (`J()`, `Q()`, `P()`, `R()`)
* **Hide IME (`J()`):**
  * Android 14+: Invokes `InputMethodManagerInternal.hideInputMethod(3, 0)` directly via handler message dispatch without requiring `ImfLock`.
  * Fallback: Invokes `hideCurrentInputLocked` under `ImfLock` with best-match reflection.
* **Show IME (`Q()`):**
  * Invokes `showCurrentInputLocked` with resolved window token and active user ID.
* **Picker Menu (`P()`):**
  * Android 14+: Invokes `IInputMethodManager.showInputMethodPickerFromSystem(0, 0)` via service binder.
  * Fallback: Invokes legacy `mMenuController.showInputMethodMenu(...)`.
* **Switch IME (`R()`):**
  * Android 14+: Invokes `IInputMethodManager.onImeSwitchButtonClickFromSystem(0)`.
  * Fallback: Invokes legacy `switchToPreviousInputMethod` / `switchToLastInputMethod`.
