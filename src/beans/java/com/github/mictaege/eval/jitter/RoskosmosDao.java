package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.OnlyIf;
import com.github.mictaege.jitter.api.Tags;

import java.util.List;

import static com.github.mictaege.eval.jitter.BearerType.SOJUS;
import static com.github.mictaege.eval.jitter.BearerType.WOSTOK;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static java.util.Arrays.asList;

@OnlyIf(ROSKOSMOS)
@Tags({Feature.SPACESHIP, Feature.BEARER})
class RoskosmosDao extends AbstractDao {

    @Override
    protected List<BearerType> init() {
        return asList(WOSTOK, SOJUS);
    }

}
