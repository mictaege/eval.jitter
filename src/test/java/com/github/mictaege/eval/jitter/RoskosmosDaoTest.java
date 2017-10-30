package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;
import org.junit.Test;

import static com.github.mictaege.eval.jitter.BearerType.SOJUS;
import static com.github.mictaege.eval.jitter.BearerType.WOSTOK;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

@OnlyIf(ROSKOSMOS)
public class RoskosmosDaoTest {

    @Test
    public void shouldProvideRoskosmosBearers() {
        assertThat(dao().findBearers(), hasItems(WOSTOK, SOJUS));
    }

    @Fork(ifActive = ROSKOSMOS, to = "roskosmosDao")
    private DaoIF dao() {
        return new RoskosmosDao();
    }

    private DaoIF roskosmosDao() {
        return new Dao();
    }

}