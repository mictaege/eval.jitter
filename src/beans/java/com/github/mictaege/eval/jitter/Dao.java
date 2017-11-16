package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Alter;
import com.github.mictaege.jitter.api.OnlyIf;

import java.util.List;

import static com.github.mictaege.eval.jitter.BearerType.ARIANE5;
import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static java.util.Arrays.asList;

@Alter(ifActive = ESA, with = "EsaDao", nested = true)
@Alter(ifActive = NASA, with = "com.github.mictaege.eval.jitter.nasa.NasaDao")
@Alter(ifActive = ROSKOSMOS, with = "RoskosmosDao")
public class Dao implements DaoIF {

    @Override
    public List<BearerType> findBearers() {
        throw new IllegalStateException("Not implemented.");
    }

    @OnlyIf(ESA)
    static class EsaDao implements DaoIF {
        public List<BearerType> findBearers() {
            return asList(ARIANE5);
        }
    }

}

