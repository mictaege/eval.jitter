package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static com.github.mictaege.eval.jitter.SpaceShipType.GEMINI;
import static com.github.mictaege.eval.jitter.SpaceShipType.HERMES;
import static com.github.mictaege.eval.jitter.SpaceShipType.MERCURY;
import static com.github.mictaege.eval.jitter.SpaceShipType.SOJUS;
import static com.github.mictaege.eval.jitter.SpaceShipType.WOSTOK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.OnlyIf;

class SpaceShipTypeTest {

    @Test
    @OnlyIf(ESA)
    void shouldAssertHermes() {
        assertThat(HERMES.getName(), is("Hermes"));
    }

    @Test
    @OnlyIf(NASA)
    void shouldAssertMercury() {
        assertThat(MERCURY.getName(), is("Mercury"));
    }

    @Test
    @OnlyIf(NASA)
    void shouldAssertGemini() {
        assertThat(GEMINI.getName(), is("Gemini"));
    }

    @Test
    @OnlyIf(ROSKOSMOS)
    void shouldAssertWostok() {
        assertThat(WOSTOK.getName(), is("Wostok"));
    }

    @Test
    @OnlyIf(ROSKOSMOS)
    void shouldAssertSojus() {
        assertThat(SOJUS.getName(), is("Sojus"));
    }

}