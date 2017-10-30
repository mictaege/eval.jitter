package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;
import org.junit.Test;

import static com.github.mictaege.eval.jitter.BearerType.ATLAS;
import static com.github.mictaege.eval.jitter.BearerType.TITAN;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

@OnlyIf(NASA)
public class NasaDaoTest {

    @Test
    public void shouldProvideNasaBearers() {
        assertThat(dao().findBearers(), hasItems(ATLAS, TITAN));
    }

    @Fork(ifActive = NASA, to = "nasaDao")
    private DaoIF dao() {
        return new Dao.NasaDao();
    }

    private DaoIF nasaDao() {
        return new Dao();
    }

}