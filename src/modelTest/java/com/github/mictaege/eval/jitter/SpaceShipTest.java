package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static com.github.mictaege.eval.jitter.SpaceShipType.HERMES;
import static com.github.mictaege.eval.jitter.SpaceShipType.MERCURY;
import static com.github.mictaege.eval.jitter.SpaceShipType.WOSTOK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;

class SpaceShipTest {

    @OnlyIf(ESA)
    private SpaceShip esaShip;

    @OnlyIf(NASA)
    private SpaceShip nasaShip;

    @OnlyIf(ROSKOSMOS)
    private SpaceShip roskosmosShip;

    @BeforeEach
    @Fork(ifActive = ESA, to = "esaContext")
    @Fork(ifActive = NASA, to = "nasaContext")
    @Fork(ifActive = ROSKOSMOS, to = "roskosmosContext")
    void context() {
        esaContext();
        nasaContext();
        roskosmosContext();
    }

    @OnlyIf(ESA)
    private void esaContext() {
        this.esaShip = new SpaceShip(HERMES);
    }

    @OnlyIf(NASA)
    private void nasaContext() {
        this.nasaShip = new SpaceShip(MERCURY);
    }


    @OnlyIf(ROSKOSMOS)
    private void roskosmosContext() {
        this.roskosmosShip = new SpaceShip(WOSTOK);
    }

    @Test
    @OnlyIf(ESA)
    void shouldProvideEsaType() {
        assertThat(esaShip.type(), is(HERMES));
    }

    @Test
    @OnlyIf(NASA)
    void shouldProvideNasaType() {
        assertThat(nasaShip.type(), is(MERCURY));
    }

    @Test
    @OnlyIf(ROSKOSMOS)
    void shouldProvideRoskosmosType() {
        assertThat(roskosmosShip.type(), is(WOSTOK));
    }

}