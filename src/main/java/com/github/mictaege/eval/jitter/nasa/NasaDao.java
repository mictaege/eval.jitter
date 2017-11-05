package com.github.mictaege.eval.jitter.nasa;

import com.github.mictaege.eval.jitter.BearerType;
import com.github.mictaege.eval.jitter.DaoIF;

import java.util.List;

import static com.github.mictaege.eval.jitter.BearerType.ATLAS;
import static com.github.mictaege.eval.jitter.BearerType.TITAN;
import static java.util.Arrays.asList;

public class NasaDao implements DaoIF {
    public List<BearerType> findBearers() {
        return asList(ATLAS, TITAN);
    }
}
