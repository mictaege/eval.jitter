package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.OnlyIf;

import java.util.List;

import static com.github.mictaege.eval.jitter.BearerType.SOJUS;
import static com.github.mictaege.eval.jitter.BearerType.WOSTOK;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static java.util.Arrays.asList;

@OnlyIf(ROSKOSMOS)
class RoskosmosDao implements DaoIF {
    public List<BearerType> findBearers() {
        return asList(WOSTOK, SOJUS);
    }
}
