#!/usr/bin/env bash
# ==============================================================================
# Xposed Edge Pro - Level 5 Empirical Live Device Action Test Suite
# Target Device: Xiaomi Redmi Note 8 (Android 17 / SDK 37, KernelSU)
# Protocol: com.jozein.xedgepro.PERFORM via system_server w1$b broadcast receiver
# ==============================================================================
set -euo pipefail

SSH_CMD="ssh phone"
PASS_COUNT=0
FAIL_COUNT=0

log_info() { echo -e "\033[1;34m[INFO]\033[0m $*"; }
log_pass() { echo -e "\033[1;32m[PASS]\033[0m $*"; PASS_COUNT=$((PASS_COUNT + 1)); }
log_fail() { echo -e "\033[1;31m[FAIL]\033[0m $*"; FAIL_COUNT=$((FAIL_COUNT + 1)); }

run_phone() {
    $SSH_CMD "$@"
}

log_info "Verifying device connection and system_server status..."
SYS_PID=$(run_phone "pidof system_server" || true)
if [ -z "$SYS_PID" ]; then
    log_fail "system_server is not running!"
    exit 1
fi
log_info "Connected. system_server PID: $SYS_PID"

# Ensure screen is active
run_phone "input keyevent 224; wm dismiss-keyguard"

# ------------------------------------------------------------------------------
# Test 1: Action 3 (Home)
# ------------------------------------------------------------------------------
log_info "Test 1: Action 3 (Home Navigation)..."
run_phone "am start -a android.settings.SETTINGS >/dev/null 2>&1"
sleep 1
BEFORE_TOP=$(run_phone "dumpsys activity activities | grep -E 'topResumedActivity' | head -n 1")
if [[ "$BEFORE_TOP" != *"Settings"* ]]; then
    log_fail "Failed to open Settings before Home test (got: $BEFORE_TOP)"
else
    run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '3302' >/dev/null 2>&1"
    sleep 1
    AFTER_TOP=$(run_phone "dumpsys activity activities | grep -E 'topResumedActivity' | head -n 1")
    if [[ "$AFTER_TOP" == *"launcher"* ]]; then
        log_pass "Action 3 (Home): Settings navigated to Launcher successfully ($AFTER_TOP)"
    else
        log_fail "Action 3 (Home): Expected Launcher, got: $AFTER_TOP"
    fi
fi

# ------------------------------------------------------------------------------
# Test 2: Action 4 (Recents Overview)
# ------------------------------------------------------------------------------
log_info "Test 2: Action 4 (Recents Overview)..."
run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '4302' >/dev/null 2>&1"
sleep 1
RECENTS_TOP=$(run_phone "dumpsys activity activities | grep -E 'topResumedActivity' | head -n 1")
if [[ "$RECENTS_TOP" == *"RecentsActivity"* ]]; then
    log_pass "Action 4 (Recents): Overview screen launched successfully ($RECENTS_TOP)"
else
    log_fail "Action 4 (Recents): Expected RecentsActivity, got: $RECENTS_TOP"
fi

# ------------------------------------------------------------------------------
# Test 3: Action 2 (Back Navigation)
# ------------------------------------------------------------------------------
log_info "Test 3: Action 2 (Back Navigation from Recents)..."
run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '2302' >/dev/null 2>&1"
sleep 1
CLOSED_TOP=$(run_phone "dumpsys activity activities | grep -E 'topResumedActivity' | head -n 1")
if [[ "$CLOSED_TOP" == *"launcher"* ]]; then
    log_pass "Action 2 (Back): Recents closed and returned to Launcher ($CLOSED_TOP)"
else
    log_fail "Action 2 (Back): Expected Launcher, got: $CLOSED_TOP"
fi

# ------------------------------------------------------------------------------
# Test 4: Action 15 (Expand Notification Shade)
# ------------------------------------------------------------------------------
log_info "Test 4: Action 15 (Expand Notifications Panel)..."
run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '5402' >/dev/null 2>&1"
sleep 1
SHADE_STATE=$(run_phone "dumpsys window | grep -iE 'mExpandedPanel=Window.*NotificationShade' || true")
if [ -n "$SHADE_STATE" ]; then
    log_pass "Action 15 (Notifications): NotificationShade expanded successfully"
else
    log_fail "Action 15 (Notifications): NotificationShade not expanded"
fi
run_phone "cmd statusbar collapse"
sleep 0.5

# ------------------------------------------------------------------------------
# Test 5: Action 16 (Expand Quick Settings Panel)
# ------------------------------------------------------------------------------
log_info "Test 5: Action 16 (Expand Quick Settings Panel)..."
QS_RESULT=$(run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '130302' 2>&1")
if [[ "$QS_RESULT" == *"Broadcast completed: result=0"* ]]; then
    log_pass "Action 16 (Quick Settings): Broadcast processed with result=0"
else
    log_fail "Action 16 (Quick Settings): Broadcast failed ($QS_RESULT)"
fi
run_phone "cmd statusbar collapse"
sleep 0.5

# ------------------------------------------------------------------------------
# Test 6: Action 111 (System Toast Display)
# ------------------------------------------------------------------------------
log_info "Test 6: Action 111 (System Toast Display)..."
run_phone "logcat -c"
run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '6364021324028554467656020527F60214E64627F696460213730214B64796661202' >/dev/null 2>&1"
sleep 0.5
TOAST_SURFACE=$(run_phone "logcat -d | grep -i 'Surface(name=VRI-Toast' | tail -n 1 || true")
if [ -n "$TOAST_SURFACE" ]; then
    log_pass "Action 111 (Toast): WindowManager mounted Toast surface ($TOAST_SURFACE)"
else
    log_fail "Action 111 (Toast): No Toast surface created in logcat"
fi

# ------------------------------------------------------------------------------
# Test 7: Action 92 (Vibration Execution)
# ------------------------------------------------------------------------------
log_info "Test 7: Action 92 (Hardware Vibration)..."
VIB_RESULT=$(run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '239302639302' 2>&1")
if [[ "$VIB_RESULT" == *"Broadcast completed: result=0"* ]]; then
    log_pass "Action 92 (Vibration): Broadcast processed with result=0"
else
    log_fail "Action 92 (Vibration): Broadcast failed ($VIB_RESULT)"
fi

# ------------------------------------------------------------------------------
# Test 8: Action 100 (Shell Command Execution Pipeline)
# ------------------------------------------------------------------------------
log_info "Test 8: Action 100 (Shell Command Execution Pipeline)..."
run_phone "logcat -c"
# Payload for 'echo XEDGE_SHELL_PIPELINE_OK'
run_phone "am broadcast -a com.jozein.xedgepro.PERFORM -e data '63430213020302133402563686F6028554447454F5358454C4C4F505940554C494E454F5F4B402' >/dev/null 2>&1"
sleep 0.8
DISPATCH_TRACE=$(run_phone "logcat -d | grep -A 1 'Console output:' | grep 'XEDGE_SHELL_PIPELINE_OK' || true")
if [ -n "$DISPATCH_TRACE" ]; then
    log_pass "Action 100 (Shell Execution): Process spawned and captured stdout successfully ($DISPATCH_TRACE)"
else
    log_fail "Action 100 (Shell Execution): Output not captured in logcat"
fi

# ------------------------------------------------------------------------------
# Summary
# ------------------------------------------------------------------------------
echo "=============================================================================="
echo "Live Device Action Verification Summary:"
echo "  Total Tests: $((PASS_COUNT + FAIL_COUNT))"
echo "  Passed:      $PASS_COUNT"
echo "  Failed:      $FAIL_COUNT"
echo "=============================================================================="

if [ "$FAIL_COUNT" -gt 0 ]; then
    exit 1
fi
exit 0
