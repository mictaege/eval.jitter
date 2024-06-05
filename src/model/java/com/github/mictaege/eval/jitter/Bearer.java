package com.github.mictaege.eval.jitter;

public final class Bearer implements Component {

    private final BearerType type;

    public Bearer(final BearerType type) {
        super();
        this.type = type;
    }

    public BearerType getType() {
        return type;
    }

    @Override
    public String getName() {
        return getType().getName();
    }
}
