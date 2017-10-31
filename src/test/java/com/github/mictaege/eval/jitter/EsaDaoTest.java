package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.mictaege.eval.jitter.Flavour.ESA;

@OnlyIf(ESA)
@RunWith(ConcordionRunner.class)
@SuppressWarnings("squid:S2187")
public class EsaDaoTest {

    public List<BearerType> shouldProvideEsaBearers() {
        return dao().findBearers();
    }

    @Fork(ifActive = ESA, to = "esaDao")
    private DaoIF dao() {
        return new Dao.EsaDao();
    }

    private DaoIF esaDao() {
        return new Dao();
    }

}