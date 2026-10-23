package com.github.mictaege.eval.jitter;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.github.mictaege.jitter.api.OnlyIf;

/**
 * Demo of the flavour gates inside XML resources: the unified
 * {@code Telemetry.xsd} is resolved per flavour while the resources are
 * copied, so the test runtime sees exactly the active flavour's variant
 * of the document - without any gate residue.
 */
class TelemetryXsdTest {

    private final String xsd = read("com/github/mictaege/eval/jitter/Telemetry.xsd");

    @Test
    void theResolvedSchemaCarriesNoGateResidue() {
        // the residual xmlns:jitter declaration on the schema root is
        // harmless and ignored by every XML tool, the gate elements are gone
        assertThat(xsd, not(containsString("<jitter:")));
        assertThat(xsd, not(containsString(":appinfo")));
    }

    @OnlyIf(ESA)
    @Test
    void esaSeesTheEsaMissionCodePattern() {
        assertThat(xsd, containsString("value=\"[A-Z]{3}\""));
        assertThat(xsd, not(containsString("value=\"\\d{3}\"")));
        assertThat(xsd, not(containsString("value=\"[A-Z]{2}\\d\"")));
        assertThat(xsd, not(containsString("abort_signal")));
    }

    @OnlyIf(NASA)
    @Test
    void nasaSeesTheNasaMissionCodePatternAndTheAbortSignal() {
        assertThat(xsd, containsString("value=\"\\d{3}\""));
        assertThat(xsd, not(containsString("value=\"[A-Z]{3}\"")));
        assertThat(xsd, containsString("abort_signal"));
        // the crew element keeps its own cardinality outside ROSKOSMOS
        assertThat(xsd, containsString("name=\"crew\""));
        assertThat(xsd, not(containsString("minOccurs=\"1\"")));
    }

    @OnlyIf(ROSKOSMOS)
    @Test
    void roskosmosSeesTheRoskosmosPatternAndTheMandatoryCrew() {
        assertThat(xsd, containsString("value=\"[A-Z]{2}\\d\""));
        assertThat(xsd, not(containsString("abort_signal")));
        assertThat(xsd, containsString("minOccurs=\"1\""));
    }

    private static String read(final String path) {
        try (InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(path)) {
            assertThat("the resolved schema is on the test classpath", in != null, is(true));
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
