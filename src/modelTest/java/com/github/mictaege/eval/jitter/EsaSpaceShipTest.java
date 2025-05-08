package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.SpaceShipType.HERMES;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.OnlyIf;

@OnlyIf(ESA)
class EsaSpaceShipTest {

    private SpaceShip esaShip;

    @BeforeEach
    void context() {
        this.esaShip = new SpaceShip(HERMES);
    }

    @Test
    void shouldProvideEsaType() {
        assertThat(esaShip.type(), is(HERMES));
    }

}