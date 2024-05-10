package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.BearerType.ARIANE5;
import static com.github.mictaege.eval.jitter.BearerType.ATLAS;
import static com.github.mictaege.eval.jitter.BearerType.SOJUS;
import static com.github.mictaege.eval.jitter.BearerType.TITAN;
import static com.github.mictaege.eval.jitter.BearerType.WOSTOK;
import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static com.github.mictaege.eval.jitter.SpaceShipType.GEMINI;
import static com.github.mictaege.eval.jitter.SpaceShipType.HERMES;
import static com.github.mictaege.eval.jitter.SpaceShipType.MERCURY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.OnlyIf;

class BearerTypeTest {

    @Test
    @OnlyIf(ESA)
    void shouldAssertAriane5() {
        assertThat(ARIANE5.getName(), is("Ariane5"));
        assertThat(ARIANE5.getConstructionYear(), is(1996));
        assertThat(ARIANE5.getSpaceShip().type(), is(HERMES));
        assertThat(ARIANE5.getImg(), is("com/github/mictaege/eval/jitter/Ariane5.jpg"));
    }

    @Test
    @OnlyIf(NASA)
    void shouldAssertAtlas() {
        assertThat(ATLAS.getName(), is("Atlas"));
        assertThat(ATLAS.getSpaceShip().type(), is(MERCURY));
        assertThat(ATLAS.getImg(), is("com/github/mictaege/eval/jitter/Atlas.jpg"));
    }

    @Test
    @OnlyIf(NASA)
    void shouldAssertTitan() {
        assertThat(TITAN.getName(), is("Titan"));
        assertThat(TITAN.getSpaceShip().type(), is(GEMINI));
        assertThat(TITAN.getImg(), is("com/github/mictaege/eval/jitter/Titan.jpg"));
    }

    @Test
    @OnlyIf(ROSKOSMOS)
    void shouldAssertWostok() {
        assertThat(WOSTOK.getName(), is("Wostok"));
        assertThat(WOSTOK.getSpaceShip().type(), is(SpaceShipType.WOSTOK));
        assertThat(WOSTOK.getImg(), is("com/github/mictaege/eval/jitter/Wostok.jpg"));
    }

    @Test
    @OnlyIf(ROSKOSMOS)
    void shouldAssertSojus() {
        assertThat(SOJUS.getName(), is("Sojus"));
        assertThat(SOJUS.getSpaceShip().type(), is(SpaceShipType.SOJUS));
        assertThat(SOJUS.getImg(), is("com/github/mictaege/eval/jitter/Sojus.jpg"));
    }

}