package com.github.mictaege.eval.jitter;

import org.junit.jupiter.api.Test;
import sim.SimFixture;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Demonstrates the custom api source set of the simulator: the test
 * COMPILES against the full 150% model of the sharedFixtures (the
 * {@code sharedFixturesApiElements} variant carries the full jar), but
 * RUNS against the stripped model of the active flavour (the stripped
 * {@code jitterSharedFixturesJar} replaced the runtime variant) - only
 * the members of the active flavour survive at runtime.
 *
 * <p>Because the test itself is not flavour specific, it touches the
 * gated members reflectively: static references would make the build's
 * reference verification fail in the flavours that remove them.
 */
public class SimFixtureFlavourTest {

    @Test
    void carriesOnlyTheActiveFlavoursBoosters() throws Exception {
        final SimFixture fixture = new SimFixture();
        assertEquals("any mission", fixture.plain());

        final String flavour = System.getProperty("jitter.active.flavour", "");
        switch (flavour) {
            case "ESA" -> {
                assertEquals("ariane", booster(fixture, "esaBooster"));
                assertNull(booster(fixture, "nasaBooster"));
                assertNull(booster(fixture, "roskosmosBooster"));
            }
            case "NASA" -> {
                assertNull(booster(fixture, "esaBooster"));
                assertEquals("falcon", booster(fixture, "nasaBooster"));
                assertNull(booster(fixture, "roskosmosBooster"));
            }
            case "ROSKOSMOS" -> {
                assertNull(booster(fixture, "esaBooster"));
                assertNull(booster(fixture, "nasaBooster"));
                assertEquals("sojus", booster(fixture, "roskosmosBooster"));
            }
            default -> {
                // no flavour active: nothing is stripped, the full model runs
                assertEquals("ariane", booster(fixture, "esaBooster"));
                assertEquals("falcon", booster(fixture, "nasaBooster"));
                assertEquals("sojus", booster(fixture, "roskosmosBooster"));
            }
        }
    }

    private String booster(final SimFixture fixture, final String name) throws Exception {
        try {
            final Method method = SimFixture.class.getDeclaredMethod(name);
            return (String) method.invoke(fixture);
        } catch (final NoSuchMethodException e) {
            return null;
        } catch (final InvocationTargetException e) {
            throw new AssertionError("invoking " + name + " failed: " + e.getCause(), e);
        }
    }

}
