package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.OnlyIf;
import org.junit.Test;

import static com.github.mictaege.eval.jitter.SpaceShipType.GEMINI;
import static com.github.mictaege.eval.jitter.SpaceShipType.HERMES;
import static com.github.mictaege.eval.jitter.SpaceShipType.MERCURY;
import static com.github.mictaege.eval.jitter.SpaceShipType.SOJUS;
import static com.github.mictaege.eval.jitter.SpaceShipType.WOSTOK;
import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SpaceShipTypeTest {

    @Test
    @OnlyIf(ESA)
    public void shouldAssertHermes() {
        assertThat(HERMES.getName(), is("Hermes"));
    }

    @Test
    @OnlyIf(NASA)
    public void shouldAssertMercury() {
        assertThat(MERCURY.getName(), is("Mercury"));
    }

    @Test
    @OnlyIf(NASA)
    public void shouldAssertGemini() {
        assertThat(GEMINI.getName(), is("Gemini"));
    }

    @Test
    @OnlyIf(ROSKOSMOS)
    public void shouldAssertWostok() {
        assertThat(WOSTOK.getName(), is("Wostok"));
    }

    @Test
    @OnlyIf(ROSKOSMOS)
    public void shouldAssertSojus() {
        assertThat(SOJUS.getName(), is("Sojus"));
    }

}