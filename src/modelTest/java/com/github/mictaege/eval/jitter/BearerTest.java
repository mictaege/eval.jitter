package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.BearerType.ARIANE5;
import static com.github.mictaege.eval.jitter.BearerType.SOJUS;
import static com.github.mictaege.eval.jitter.BearerType.TITAN;
import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;

class BearerTest {

    @Test
    void shouldProvideBearerType() {
        final BearerType bearerType = bearerType();

        final Bearer bearer = new Bearer(bearerType);

        assertThat(bearer.getType(), is(bearerType));
    }

    @Fork(ifActive = ESA, to = "bearerTypeEsa")
    @Fork(ifActive = NASA, to = "bearerTypeNasa")
    @Fork(ifActive = ROSKOSMOS, to = "bearerTypeRoskosmos")
    private BearerType bearerType() {
        return bearerTypeEsa();
    }

    @OnlyIf(ESA)
    private BearerType bearerTypeEsa() {
        return ARIANE5;
    }

    @OnlyIf(NASA)
    private BearerType bearerTypeNasa() {
        return TITAN;
    }

    @OnlyIf(ROSKOSMOS)
    private BearerType bearerTypeRoskosmos() {
        return SOJUS;
    }

}