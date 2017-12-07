package com.github.mictaege.eval.jitter;

import java.util.List;

public abstract class AbstractDao implements DaoIF {

    protected List<BearerType> bearer;

    public AbstractDao() {
        super();
        this.bearer = init();
    }

    protected abstract List<BearerType> init();

    @Override
    public List<BearerType> findBearers() {
        return bearer;
    }

}