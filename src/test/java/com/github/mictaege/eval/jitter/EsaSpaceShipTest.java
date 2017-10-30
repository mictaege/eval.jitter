package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.OnlyIf;
import org.junit.Before;
import org.junit.Test;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.SpaceShipType.HERMES;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@OnlyIf(ESA)
public class EsaSpaceShipTest {

    private SpaceShip esaShip;

    @Before
    public void context() {
        this.esaShip = new SpaceShip(HERMES);
    }

    @Test
    public void shouldProvideEsaType() {
        assertThat(esaShip.getType(), is(HERMES));
    }

}