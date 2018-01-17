package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;

import com.github.mictaege.jitter.api.OnlyIf;
import com.github.mictaege.jitter.api.Tags;

@OnlyIf(ROSKOSMOS)
@Tags({Feature.SPACESHIP, Feature.BEARER})
public class RoskosmosResult {

}
