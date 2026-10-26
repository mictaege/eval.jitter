package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.InlinedConstantApproved;
import com.github.mictaege.jitter.api.OnlyIf;

/**
 * Demo of the flavour handling of compile-time {@code String} constants -
 * the same mechanism as {@link OnlyIfTest}, which demonstrates the primitive
 * type variant: javac inlines them just the same, so the flavour variant
 * constants need the {@code @InlinedConstantApproved} review marker, and the
 * usages must sit on members gated with a subset of the constant's flavours.
 */
class OnlyIfStringTest {

    @OnlyIf(NASA)
    @InlinedConstantApproved
    public final String NASA_CONSTANT = "42";
    @OnlyIf(ESA)
    @InlinedConstantApproved
    public final String ESA_CONSTANT = "43";
    @OnlyIf(ROSKOSMOS)
    @InlinedConstantApproved
    public final String ROSKOSMOS_CONSTANT = "44";

    @OnlyIf(NASA)
    @Test
    void nasaShouldSeeNasa() {
        assertThat(NASA_CONSTANT, is("42"));
    }

    @OnlyIf(ESA)
    @Test
    void esaShouldSeeEsa() {
        assertThat(ESA_CONSTANT, is("43"));
    }

    @OnlyIf(ROSKOSMOS)
    @Test
    void roskosmosShouldSeeRoskosmos() {
        assertThat(ROSKOSMOS_CONSTANT, is("44"));
    }

}
