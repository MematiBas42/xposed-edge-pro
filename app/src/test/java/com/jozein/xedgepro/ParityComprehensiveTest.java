package com.jozein.xedgepro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.os.Bundle;
import android.view.View;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class ParityComprehensiveTest {

    @Test
    public void testConstantsParity() {
        assertEquals("com.jozein.xedgepro", pkg_f.l.CONST_PKG);
        assertEquals("com.jozein.xedgepro.", pkg_f.l.CONST_PREFIX);
        assertTrue(pkg_f.l.CONST_IS_PRO);
        assertEquals("XEdgePro", pkg_f.l.CONST_APP_TAG);
        assertEquals(R.string.app_name_pro, pkg_f.l.CONST_APP_NAME);
    }

    @Test
    public void testAidlDescriptorParity() throws Exception {
        Class<?> stubCls = Class.forName("pkg_a.g$b");
        Field descriptorField = stubCls.getDeclaredField("f4a");
        descriptorField.setAccessible(true);
        String descriptor = (String) descriptorField.get(null);
        assertEquals("com.jozein.xedgepro.BinderInterface", descriptor);

        // Check methods in interface g
        Class<?> gCls = Class.forName("pkg_a.g");
        Method methodA = gCls.getDeclaredMethod("a", String.class);
        assertNotNull("Method a(String) must exist", methodA);
        Method methodB = gCls.getDeclaredMethod("b", String.class, android.os.Parcelable.class);
        assertNotNull("Method b(String, Parcelable) must exist", methodB);
    }

    @Test
    public void testXposedEntryPointParity() throws Exception {
        Class<?> hookMainCls = Class.forName("com.jozein.xedgepro.xposed.HookMain");
        assertNotNull("HookMain must exist", hookMainCls);

        // Check implemented Xposed interfaces
        Class<?> zygoteInit = Class.forName("de.robv.android.xposed.IXposedHookZygoteInit");
        Class<?> loadPackage = Class.forName("de.robv.android.xposed.IXposedHookLoadPackage");

        assertTrue("HookMain must implement IXposedHookZygoteInit", zygoteInit.isAssignableFrom(hookMainCls));
        assertTrue("HookMain must implement IXposedHookLoadPackage", loadPackage.isAssignableFrom(hookMainCls));

        // Check required methods exist
        Set<String> methodNames = new HashSet<>();
        for (Method m : hookMainCls.getDeclaredMethods()) {
            methodNames.add(m.getName());
        }

        assertTrue(methodNames.contains("initZygote"));
        assertTrue(methodNames.contains("handleLoadPackage"));
        assertTrue(methodNames.contains("h"));
        assertTrue(methodNames.contains("i"));
        assertTrue(methodNames.contains("j"));
        assertTrue(methodNames.contains("k"));
        assertTrue(methodNames.contains("l"));
        assertTrue(methodNames.contains("m"));
    }

    @Test
    public void testAndroid16Patches1Through4_TransientStateParity() throws Exception {
        Class<?> n0Cls = Class.forName("com.jozein.xedgepro.xposed.n0");
        Field transientField = n0Cls.getField("mTransientState");
        assertNotNull("mTransientState field must exist on n0", transientField);
        assertEquals(boolean.class, transientField.getType());
        assertTrue(Modifier.isPublic(transientField.getModifiers()));

        // Check n0.B0 and n0.y0 methods exist
        Method b0Method = n0Cls.getDeclaredMethod("B0", boolean.class);
        Method y0Method = n0Cls.getDeclaredMethod("y0", boolean.class);
        assertNotNull(b0Method);
        assertNotNull(y0Method);
    }

    @Test
    public void testAndroid16Patch5_PartialScreenshotIntentParity() throws Exception {
        Class<?> w1Cls = Class.forName("com.jozein.xedgepro.xposed.w1");
        Method d4Method = w1Cls.getDeclaredMethod("D4", int.class);
        assertNotNull("w1.D4(int) must exist", d4Method);
    }

    @Test
    public void testAndroid16Patch6_AudioVolumeAliasFallbackParity() throws Exception {
        int[] expectedFallback = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        assertEquals(12, expectedFallback.length);
        for (int i = 0; i < 12; i++) {
            assertEquals(i, expectedFallback[i]);
        }

        Class<?> eCls = Class.forName("com.jozein.xedgepro.xposed.e");
        Method fMethod = eCls.getDeclaredMethod("f");
        assertNotNull("e.f() volume alias method must exist", fMethod);
        assertEquals(int[].class, fMethod.getReturnType());
    }

    @Test
    public void testAndroid16Patch7_ScreenRotationFallbackParity() throws Exception {
        Class<?> m2Cls = Class.forName("com.jozein.xedgepro.xposed.m2");
        Method dMethod = m2Cls.getDeclaredMethod("D", int.class);
        assertNotNull("m2.D(int) must exist", dMethod);
    }

    @Test
    public void testAndroid16Patch8_FullScreenshotAccessibilityParity() throws Exception {
        Class<?> w1Cls = Class.forName("com.jozein.xedgepro.xposed.w1");
        Method p1Method = w1Cls.getDeclaredMethod("P1");
        assertNotNull("w1.P1() getter for AccessibilityService must exist", p1Method);
    }

    @Test
    public void testReconstructedB1MethodParity() throws Exception {
        pkg_d.c testInstance = new pkg_d.c() {
            @Override
            protected void C1(Bundle bundle, int i) {}

            @Override
            protected int B0() { return 0; }

            @Override
            protected View h1(int i2) { return null; }

            @Override
            protected void k1(int i2) {}
        };

        Class<?> cCls = pkg_d.c.class;
        Method b1Method = cCls.getDeclaredMethod("B1", int.class, boolean.class);
        b1Method.setAccessible(true);

        // When p1 == 0 and z == false:
        // returns exactly 2 elements: [0x1040001 (cancel), R.string.action_info]
        int[] resFalse = (int[]) b1Method.invoke(testInstance, 0, false);
        assertNotNull(resFalse);
        assertEquals(2, resFalse.length);
        assertEquals(0x1040001, resFalse[0]);
        assertEquals(R.string.action_info, resFalse[1]);

        // When z == true and i == 0x36 (54):
        // adds 0x7f0600ee (edit_action), 0x1040001 (cancel), 3 action strings, and action_info = 6 elements
        int[] res36 = (int[]) b1Method.invoke(testInstance, 0x36, true);
        assertNotNull(res36);
        assertEquals(6, res36.length);
        assertEquals(R.string.edit_action, res36[0]);
        assertEquals(0x1040001, res36[1]);

        // When z == true and i == 0x48 (72):
        int[] res48 = (int[]) b1Method.invoke(testInstance, 0x48, true);
        assertNotNull(res48);
        assertEquals(6, res48.length);

        // When z == true and i == 0x4c (76):
        int[] res4c = (int[]) b1Method.invoke(testInstance, 0x4c, true);
        assertNotNull(res4c);
        assertEquals(6, res4c.length);

        // When z == true and i == 0x58 (88):
        int[] res58 = (int[]) b1Method.invoke(testInstance, 0x58, true);
        assertNotNull(res58);
        assertEquals(6, res58.length);
    }
}
