package com.github.mictaege.eval.jitter.nasa;

import static com.github.mictaege.eval.jitter.Flavour.NASA;

import java.util.List;

import org.concordion.api.ConcordionFixture;

import com.github.mictaege.eval.jitter.BearerType;
import com.github.mictaege.eval.jitter.Dao;
import com.github.mictaege.eval.jitter.DaoIF;
import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;

@OnlyIf(NASA)
@ConcordionFixture
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