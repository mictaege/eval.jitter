package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.OnlyIf;

/**
 * Demo of the flavour variance of a constant's value, declared with
 * {@code @AlterConstValue} (see {@link TableNames}): the flavour build
 * re-bakes the inlined placeholder anchor of the String constant - the
 * annotation below carries the active flavour's value - and patches the
 * wrapper constant's assignment, so the reference reads the active
 * flavour's value.
 *
 * <p>Each assertion is gated to its own flavour, like the usages of the
 * flavour-variant constants in {@link OnlyIfTest}.
 */
class AlterConstValueTest {

    @OnlyIf(ESA)
    @Test
    void esaShouldSeeTheEsaValues() {
        assertThat(table(), is("ESA_JOB"));
        assertThat(nodeIndex(), is(1));
    }

    @OnlyIf(NASA)
    @Test
    void nasaShouldSeeTheNasaValues() {
        assertThat(table(), is("NASA_JOB"));
        assertThat(nodeIndex(), is(2));
    }

    @OnlyIf(ROSKOSMOS)
    @Test
    void roskosmosShouldSeeTheRoskosmosValues() {
        assertThat(table(), is("ROSKOSMOS_JOB"));
        assertThat(nodeIndex(), is(2));
    }

    private String table() {
        try {
            return PeemJobs.class.getDeclaredField("JOB")
                    .getAnnotation(Table.class).name();
        } catch (final NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    private int nodeIndex() {
        return PeemJobs.nodeIndex();
    }

}
