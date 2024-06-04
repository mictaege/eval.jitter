package com.github.mictaege.eval.jitter;

public record SpaceShip(SpaceShipType type) implements Component {
    @Override
    public String getName() {
        return type.getName();
    }
}
