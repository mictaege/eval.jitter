package com.github.mictaege.eval.jitter.nasa;

import com.github.mictaege.eval.jitter.AbstractDao;
import com.github.mictaege.eval.jitter.BearerType;

import java.util.List;

import static com.github.mictaege.eval.jitter.BearerType.ATLAS;
import static com.github.mictaege.eval.jitter.BearerType.TITAN;
import static java.util.Arrays.asList;

final class NasaDao extends AbstractDao {

    private static List<BearerType> nasaBearer = asList(ATLAS, TITAN);

    public NasaDao() {
        super();
    }

    @Override
    protected List<BearerType> init() {
        return this.nasaBearer;
    }

}
