package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;

public interface Component {

    String getName();

    @Fork(ifActive = Flavour.ESA, to="describeESA")
    default String describe() {
        return switch(this) {
            case Bearer b -> b.getName() + " bearer";
            case SpaceShip s -> s.getName() + " spaceship";
            default -> "Unknown component";
        };
    }

    @OnlyIf(Flavour.ESA)
    default String describeESA() {
        return switch(this) {
            case Bearer b -> b.getName() + " bearer, constructed " + b.getType().getConstructionYear();
            case SpaceShip s -> s.getName() + " spaceship";
            default -> "Unknown component";
        };
    }

}
