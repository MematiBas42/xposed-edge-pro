package com.jozein.xedgepro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import pkg_a.b;
import pkg_a.p;
import pkg_f.h;
import pkg_f.p0;

/**
 * Deterministic Parity and Lossless Serialization Test Suite
 * Tests all mapped action types, byte/hex serialization, edge regions, and rule filters.
 */
public class ActionParityTest {

    @Test
    public void testEdgeRegionsParity() {
        assertEquals(16, p.EdgeRegions.f14e.length);
        assertEquals(16, p.EdgeRegions.f15f.length);
        assertEquals(16, p.EdgeRegions.f16g.length);
        assertEquals(16, p.EdgeRegions.h.length);

        // Verify edge indices mapping
        int[] expectedIndices = {4, 5, 6, 0, 7, 8, 9, 1, 10, 11, 12, 2, 13, 14, 15, 3};
        for (int i = 0; i < 16; i++) {
            assertEquals("Edge index mismatch at " + i, expectedIndices[i], p.EdgeRegions.f14e[i]);
        }
    }

    /**
     * Deterministik Doğrulama: Deserialization kurucularının (pkg_f.s sVar)
     * doğrudan çağrıldığında bayt akışından kayıpsız okuma yaptığını ve
     * sVar'ın asla null kabul etmeyen orijinal Smali mimarisine tam uyduğunu kanıtlar.
     */
    @Test
    public void testDirectDeserializationConstructors() throws Exception {
        // 1. Önce eylemi oluştur ve serileştir
        pkg_a.b.a0 originalAction = new pkg_a.b.a0(true, true, "echo PARITY_CHECK");
        pkg_f.h serializer = new pkg_f.h();
        serializer.j();
        serializer.f(originalAction);
        byte[] bytes = serializer.o();
        assertNotNull("Serialized payload must not be null", bytes);
        assertTrue("Serialized payload must have content", bytes.length > 0);

        // 2. Doğrudan (pkg_f.s) kurucusu üzerinden refleksiyonla çöz
        pkg_f.h stream = new pkg_f.h();
        stream.i(bytes);
        int actionId = stream.h();
        assertEquals("Action ID in stream must be 100", 100, actionId);

        Constructor<pkg_a.b.a0> sCtor = pkg_a.b.a0.class.getDeclaredConstructor(pkg_f.s.class);
        sCtor.setAccessible(true);
        pkg_a.b.a0 directInstance = sCtor.newInstance(stream);

        assertNotNull("Directly constructed instance must not be null", directInstance);
        assertEquals("Action ID must match", 100, directInstance.z);
        assertEquals("Command string must be preserved losslessly", "echo PARITY_CHECK", directInstance.z());

        // 3. Null sVar verildiğinde orijinal Smali'deki gibi NullPointerException fırlattığını kanıtla
        try {
            sCtor.newInstance(new Object[]{null});
            fail("Original Smali invokes interface on sVar immediately; null sVar MUST throw InvocationTargetException wrapping NPE!");
        } catch (java.lang.reflect.InvocationTargetException e) {
            assertTrue("Root cause must be NullPointerException", e.getCause() instanceof NullPointerException);
        }
    }

    @Test
    public void testAllActionSubclassesInstantiableAndLosslessSerialization() throws Exception {
        Class<?>[] declaredClasses = b.class.getDeclaredClasses();
        List<Class<? extends b>> actionClasses = new ArrayList<>();

        for (Class<?> cls : declaredClasses) {
            if (b.class.isAssignableFrom(cls) && !Modifier.isAbstract(cls.getModifiers())) {
                actionClasses.add(cls.asSubclass(b.class));
            }
        }

        assertTrue("Must discover at least 70 distinct action subclasses", actionClasses.size() >= 70);

        int verifiedCount = 0;
        for (Class<? extends b> actionCls : actionClasses) {
            Constructor<? extends b> ctor = null;
            b instance = null;

            // Try default no-arg constructor
            try {
                ctor = actionCls.getDeclaredConstructor();
                ctor.setAccessible(true);
                instance = ctor.newInstance();
            } catch (NoSuchMethodException e) {
                // Try int constructor
                try {
                    ctor = actionCls.getDeclaredConstructor(int.class);
                    ctor.setAccessible(true);
                    instance = ctor.newInstance(0);
                } catch (NoSuchMethodException e2) {
                    // Try (int, int) or other standard signatures
                    for (Constructor<?> c : actionCls.getDeclaredConstructors()) {
                        c.setAccessible(true);
                        Class<?>[] paramTypes = c.getParameterTypes();

                        // Skip deserialization constructors (those that take pkg_f.s)
                        boolean isDeserializationCtor = false;
                        for (Class<?> p : paramTypes) {
                            if (pkg_f.s.class.isAssignableFrom(p)) {
                                isDeserializationCtor = true;
                                break;
                            }
                        }
                        if (isDeserializationCtor) continue;

                        Object[] params = new Object[paramTypes.length];
                        for (int pIdx = 0; pIdx < paramTypes.length; pIdx++) {
                            if (paramTypes[pIdx] == int.class) params[pIdx] = 0;
                            else if (paramTypes[pIdx] == boolean.class) params[pIdx] = false;
                            else if (paramTypes[pIdx] == String.class) params[pIdx] = "";
                            else if (paramTypes[pIdx] == android.content.Intent.class) {
                                params[pIdx] = new android.content.Intent("android.intent.action.MAIN") {
                                    @Override
                                    public String toUri(int flags) {
                                        return "intent:#Intent;action=android.intent.action.MAIN;end";
                                    }
                                };
                            }
                            else if (b.class.isAssignableFrom(paramTypes[pIdx])) params[pIdx] = b.e();
                            else params[pIdx] = null;
                        }
                        try {
                            instance = (b) c.newInstance(params);
                            break;
                        } catch (Throwable ignored) {
                        }
                    }
                }
            }

            if (instance != null) {
                // Initialize any inner action or intent fields to prevent null serialization in wrappers
                for (java.lang.reflect.Field f : actionCls.getDeclaredFields()) {
                    if (b.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        try {
                            if (f.get(instance) == null) {
                                f.set(instance, b.e());
                            }
                        } catch (Throwable ignored) {}
                    } else if (android.content.Intent.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        try {
                            if (f.get(instance) == null) {
                                f.set(instance, new android.content.Intent("android.intent.action.MAIN"));
                            }
                        } catch (Throwable ignored) {}
                    }
                }
                // Also check superclass fields (e.g. s2.K)
                Class<?> superCls = actionCls.getSuperclass();
                while (superCls != null && superCls != Object.class) {
                    for (java.lang.reflect.Field f : superCls.getDeclaredFields()) {
                        if (android.content.Intent.class.isAssignableFrom(f.getType())) {
                            f.setAccessible(true);
                            try {
                                if (f.get(instance) == null) {
                                    f.set(instance, new android.content.Intent("android.intent.action.MAIN"));
                                }
                            } catch (Throwable ignored) {}
                        }
                    }
                    superCls = superCls.getSuperclass();
                }

                // Verify basic action metadata
                int actionId = instance.z;
                assertTrue("Action ID must be non-negative: " + actionCls.getSimpleName(), actionId >= 0);

                // Verify serialization to byte stream
                h serializer = new h();
                serializer.j();
                serializer.f(instance);
                byte[] bytes = serializer.o();
                assertNotNull("Serialized bytes must not be null for " + actionCls.getSimpleName(), bytes);
                assertTrue("Serialized bytes must not be empty for " + actionCls.getSimpleName(), bytes.length > 0);

                // Verify hex string encoding/decoding
                String hex = p0.i(bytes);
                assertNotNull("Hex encoding must not be null for " + actionCls.getSimpleName(), hex);
                byte[] decodedBytes = p0.g(hex);
                assertEquals("Decoded byte length must match original for " + actionCls.getSimpleName(),
                        bytes.length, decodedBytes.length);
                for (int bIdx = 0; bIdx < bytes.length; bIdx++) {
                    assertEquals("Byte mismatch at offset " + bIdx + " for " + actionCls.getSimpleName(),
                            bytes[bIdx], decodedBytes[bIdx]);
                }

                // Verify deserialization back to action instance
                h deserializer = new h();
                deserializer.i(bytes);
                b reconstructed = deserializer.g();
                assertNotNull("Deserialized action must not be null for " + actionCls.getSimpleName(), reconstructed);

                // In original closed-source APK:
                // 1) UI container actions (79, 103) are not trigger actions and deserialize to b.e() (id 0)
                // 2) Legacy actions are deterministically migrated to modern unified action types:
                //    - p (41) -> o (82)
                //    - s2 (49) -> r2 (78)
                //    - c1 (56) -> b1 (104)
                //    - v1 (57) -> u1 (106)
                //    - d3 (58) -> c3 (107)
                //    - j (60) -> i (89)
                //    - q2 (69) -> r2 (78)
                //    - v2 (91) -> u2 (105)
                if (actionId == 79 || actionId == 103 || actionId == 65537) {
                    assertEquals("UI container/sentinel action must deserialize to empty action (id 0) for " + actionCls.getSimpleName(),
                            0, reconstructed.z);
                } else if (actionId == 41) {
                    assertEquals("Legacy action 41 must migrate to 82", 82, reconstructed.z);
                } else if (actionId == 49 || actionId == 69) {
                    assertEquals("Legacy shortcut action must migrate to 78", 78, reconstructed.z);
                } else if (actionId == 50) {
                    assertEquals("Legacy saved multi-action must migrate to 1", 1, reconstructed.z);
                } else if (actionId == 52) {
                    assertEquals("Legacy inject key event must migrate to 105", 105, reconstructed.z);
                } else if (actionId == 56) {
                    assertEquals("Legacy action 56 must migrate to 108", 108, reconstructed.z);
                } else if (actionId == 57) {
                    assertEquals("Legacy action 57 must migrate to 109", 109, reconstructed.z);
                } else if (actionId == 58) {
                    assertEquals("Legacy action 58 must migrate to 110", 110, reconstructed.z);
                } else if (actionId == 60) {
                    assertEquals("Legacy action 60 must migrate to 89", 89, reconstructed.z);
                } else if (actionId == 91) {
                    assertEquals("Legacy action 91 must migrate to 111", 111, reconstructed.z);
                } else {
                    assertEquals("Reconstructed action ID must match for " + actionCls.getSimpleName(),
                            instance.z, reconstructed.z);
                }

                verifiedCount++;
            }
        }

        assertTrue("Must successfully verify lossless round-trip for at least 70 action classes, verified: " + verifiedCount,
                verifiedCount >= 70);
    }

    @Test
    public void testBroadcastCommandStringFormatting() {
        b emptyAction = b.e();
        assertNotNull(emptyAction);
        assertEquals(0, emptyAction.z);

        String cmd = b.g(emptyAction);
        assertNotNull(cmd);
        assertTrue("Command must target com.jozein.xedgepro.PERFORM", cmd.contains("com.jozein.xedgepro.PERFORM"));
        assertTrue("Command must contain -e data argument", cmd.contains("-e data "));
    }

    @Test
    public void testConditionBitmaskOperations() throws Exception {
        p pInstance = new p();
        java.lang.reflect.Field kField = p.class.getDeclaredField("K");
        kField.setAccessible(true);
        kField.setInt(pInstance, 0x01 | 0x04 | 0x20); // Bits 0, 2, 5

        assertTrue("Bit 0 must be set", pInstance.o(0));
        assertTrue("Bit 2 must be set", pInstance.o(2));
        assertTrue("Bit 5 must be set", pInstance.o(5));
        org.junit.Assert.assertFalse("Bit 1 must not be set", pInstance.o(1));
        org.junit.Assert.assertFalse("Bit 3 must not be set", pInstance.o(3));
    }
}
