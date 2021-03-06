package com.github.mictaege.eval.jitter.nasa;

import com.github.mictaege.eval.jitter.BearerType;
import com.github.mictaege.eval.jitter.Dao;
import com.github.mictaege.eval.jitter.DaoIF;
import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.mictaege.eval.jitter.Flavour.NASA;

@OnlyIf(NASA)
@RunWith(ConcordionRunner.class)
@SuppressWarnings("squid:S2187")
public class NasaDaoTest {

    public List<BearerType> shouldProvideNasaBearers() {
        return dao().findBearers();
    }

    @Fork(ifActive = NASA, to = "nasaDao")
    private DaoIF dao() {
        return new NasaDao();
    }

    private DaoIF nasaDao() {
        return new Dao();
    }

}