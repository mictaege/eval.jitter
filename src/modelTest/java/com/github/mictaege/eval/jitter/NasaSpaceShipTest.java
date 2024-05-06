package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.SpaceShipType.MERCURY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.OnlyIf;

@OnlyIf(NASA)
class NasaSpaceShipTest {

    private SpaceShip nasaShip;

    @BeforeEach
    void context() {
        this.nasaShip = new SpaceShip(MERCURY);
    }

    @Test
    void shouldProvideNasaType() {
        assertThat(nasaShip.getType(), is(MERCURY));
    }

}