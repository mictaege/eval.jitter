package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ESA;

import java.util.List;

import org.concordion.api.ConcordionFixture;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;

@OnlyIf(ESA)
@ConcordionFixture
@SuppressWarnings("squid:S2187")
public class EsaDaoTest {

    public List<BearerType> shouldProvideEsaBearers() {
        return dao().findBearers();
    }

    @Fork(ifActive = ESA, to = "esaDao")
    private DaoIF dao() {
        return new EsaDao();
    }

    private DaoIF esaDao() {
        return new Dao();
    }

}