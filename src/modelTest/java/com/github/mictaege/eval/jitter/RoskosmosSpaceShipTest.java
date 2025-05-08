package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static com.github.mictaege.eval.jitter.SpaceShipType.WOSTOK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.OnlyIf;

@OnlyIf(ROSKOSMOS)
class RoskosmosSpaceShipTest {

    private SpaceShip roskosmosShip;

    @BeforeEach
    void context() {
        this.roskosmosShip = new SpaceShip(WOSTOK);
    }

    @Test
    void shouldProvideRoskosmosType() {
        assertThat(roskosmosShip.type(), is(WOSTOK));
    }

}