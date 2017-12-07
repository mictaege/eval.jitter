package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.BearerType.ARIANE5;
import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static java.util.Arrays.asList;

import java.util.List;

import com.github.mictaege.jitter.api.Alter;
import com.github.mictaege.jitter.api.OnlyIf;

@Alter(ifActive = ESA, with = "EsaDao")
@Alter(ifActive = NASA, with = "com.github.mictaege.eval.jitter.nasa.NasaDao")
@Alter(ifActive = ROSKOSMOS, with = "RoskosmosDao")
public class Dao implements DaoIF {

    @Override
    public List<BearerType> findBearers() {
        throw new IllegalStateException("Not implemented.");
    }

}

@OnlyIf(ESA)
@SuppressWarnings("X")
final class EsaDao implements DaoIF {

    private List<BearerType> esaBearer;

    EsaDao() {
        super();
        this.esaBearer = asList(ARIANE5);
    }

    public List<BearerType> findBearers() {
        return getEsaBearer();
    }

    private List<BearerType> getEsaBearer() {
        return esaBearer;
    }

}

