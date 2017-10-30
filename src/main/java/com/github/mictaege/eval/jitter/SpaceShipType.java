package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.OnlyIf;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;

public enum SpaceShipType {

    @OnlyIf(ESA)
    HERMES("Hermes"),

    @OnlyIf(NASA)
    MERCURY("Mercury"),
    @OnlyIf(NASA)
    GEMINI("Gemini"),

    @OnlyIf(ROSKOSMOS)
    WOSTOK("Wostok"),
    @OnlyIf(ROSKOSMOS)
    SOJUS("Sojus");



    private String name;

    SpaceShipType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
