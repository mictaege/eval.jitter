package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.OnlyIf;
import org.junit.Before;
import org.junit.Test;

import static com.github.mictaege.eval.jitter.SpaceShipType.MERCURY;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@OnlyIf(NASA)
public class NasaSpaceShipTest {

    private SpaceShip nasaShip;

    @Before
    public void context() {
        this.nasaShip = new SpaceShip(MERCURY);
    }

    @Test
    public void shouldProvideNasaType() {
        assertThat(nasaShip.getType(), is(MERCURY));
    }

}