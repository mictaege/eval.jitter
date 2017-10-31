package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;

@OnlyIf(ROSKOSMOS)
@RunWith(ConcordionRunner.class)
@SuppressWarnings("squid:S2187")
public class RoskosmosDaoTest {

    public List<BearerType> shouldProvideRoskosmosBearers() {
        return dao().findBearers();
    }

    @Fork(ifActive = ROSKOSMOS, to = "roskosmosDao")
    private DaoIF dao() {
        return new RoskosmosDao();
    }

    private DaoIF roskosmosDao() {
        return new Dao();
    }

}