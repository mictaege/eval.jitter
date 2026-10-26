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
 * Demo of the flavour handling of compile-time constants.
 *
 * <p>javac inlines constant variables into their usages, so the flavour
 * stripping cannot apply an {@code @OnlyIf} gate at the usage sites - the
 * build therefore fails for a flavour variant constant unless it is marked
 * {@code @InlinedConstantApproved}, the review assertion that every usage
 * sits on a member gated with a subset of the constant's flavours. The
 * opt-in {@code jitterVerifyInlinedConstants} check verifies that assertion
 * mechanically.
 *
 * <p>The usages below are own-flavour only on purpose: a usage in a member of
 * a wider gate would keep the inlined full model value and is the leak
 * pattern the check rejects.
 */
class OnlyIfTest {

    @OnlyIf(NASA)
    @InlinedConstantApproved
    public final int NASA_CONSTANT = 42;
    @OnlyIf(ESA)
    @InlinedConstantApproved
    public final int ESA_CONSTANT = 43;
    @OnlyIf(ROSKOSMOS)
    @InlinedConstantApproved
    public final int ROSKOSMOS_CONSTANT = 44;

    @OnlyIf(NASA)
    @Test
    void nasaShouldSeeNasa() {
        assertThat(NASA_CONSTANT, is(42));
    }

    @OnlyIf(ESA)
    @Test
    void esaShouldSeeEsa() {
        assertThat(ESA_CONSTANT, is(43));
    }

    @OnlyIf(ROSKOSMOS)
    @Test
    void roskosmosShouldSeeRoskosmos() {
        assertThat(ROSKOSMOS_CONSTANT, is(44));
    }

}
