package com.github.mictaege.eval.jitter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Demonstrates the dejittered source variant: the {@code dejitter} task of the
 * plugin creates the source delivery of the active flavour - the original
 * sources minus the flavour specific code, with the flavour processed
 * resources. Like the runtime itself, the test is not flavour specific: it
 * reads the variant of each flavour-specific source set and asserts its
 * shape for the active flavour.
 */
public class DejitteredModelTest {

    private static final Path DEJITTERED = Paths.get("build", "jitter-dejittered");
    private static final String PKG = "com/github/mictaege/eval/jitter";

    private final String flavour = System.getProperty("jitter.active.flavour", "");

    @Test
    void theModelSourcesAreTheActiveFlavoursVariant() throws IOException {
        // the @Fork gateway carries the active flavour's body, the template
        // method is gone, the annotations are cleaned
        final String component = source("model", "Component.java");
        assertThat(component, not(containsString("@Fork")));
        assertThat(component, not(containsString("@OnlyIf")));
        switch (flavour) {
            case "ESA" -> {
                assertThat(component, containsString("constructed"));
                assertThat(component, not(containsString("describeESA")));
            }
            case "NASA", "ROSKOSMOS" -> {
                assertThat(component, containsString("Unknown component"));
                assertThat(component, not(containsString("describeESA")));
            }
            // no flavour active: nothing is removed, only the annotations
            // are cleaned - the full 150% source variant
            default -> {
                assertThat(component, containsString("Unknown component"));
                assertThat(component, containsString("describeESA"));
            }
        }

        // only the active flavour's enum constants and members survive
        final String bearerType = source("model", "BearerType.java");
        assertThat(bearerType, not(containsString("@OnlyIf")));
        assertThat(bearerType.contains("ATLAS"), is(flavour.isEmpty() || "NASA".equals(flavour)));
        assertThat(bearerType.contains("WOSTOK"), is(flavour.isEmpty() || "ROSKOSMOS".equals(flavour)));
        assertThat(bearerType.contains("constructionYear"),
                is(flavour.isEmpty() || "ESA".equals(flavour)));
    }

    @Test
    void theBeansAreTheActiveFlavoursVariant() throws IOException {
        // the @Alter target class carries the active flavour's implementation
        // and its name; the template classes are consumed
        final String dao = source("beans", "Dao.java");
        switch (flavour) {
            case "ESA" -> assertThat(dao, containsString("esaBearer"));
            case "NASA" -> assertThat(dao, containsString("TITAN"));
            case "ROSKOSMOS" -> assertThat(dao, containsString("WOSTOK"));
            default -> {
                assertThat(dao, containsString("Not implemented"));
                assertThat(dao, containsString("class EsaDao"));
            }
        }
        assertThat(dao, not(containsString("@Alter")));
        assertThat(dao, not(containsString("@OnlyIf")));

        // the nasa package is flavour specific: only NASA has it, and only
        // without a flavour the dao template still exists inside it
        final Path beansJava = dejittered("beans").resolve("java").resolve(PKG);
        assertThat(Files.isRegularFile(beansJava.resolve("nasa/package-info.java")),
                is(flavour.isEmpty() || "NASA".equals(flavour)));
        assertThat(Files.isRegularFile(beansJava.resolve("nasa/NasaDao.java")),
                is(flavour.isEmpty()));
    }

    @Test
    void theUiResourcesAreTheActiveFlavoursVariant() throws IOException {
        // the flavour suffix convention renames the active flavour's
        // resources into place, the other flavours' resources are gone
        final Path uiResources = dejittered("ui").resolve("resources").resolve(PKG);
        final String form = Files.readString(uiResources.resolve("TowerControlForm.properties"),
                StandardCharsets.UTF_8);
        // with an active flavour the suffixed variant is renamed into place;
        // without a flavour the resources ship unprocessed
        assertThat(form.contains("constructionYear"), is("ESA".equals(flavour)));
        assertThat(Files.exists(uiResources.resolve("TowerControlForm_ESA.properties")),
                is(flavour.isEmpty()));

        final Path modelResources = dejittered("model").resolve("resources").resolve(PKG);
        assertThat(Files.exists(modelResources.resolve("Default.png")), is(true));
        switch (flavour) {
            case "ESA" -> {
                assertThat(Files.exists(modelResources.resolve("Ariane5.jpg")), is(true));
                assertThat(Files.exists(modelResources.resolve("Titan.jpg")), is(false));
                assertThat(Files.exists(modelResources.resolve("Sojus.jpg")), is(false));
            }
            case "NASA" -> {
                assertThat(Files.exists(modelResources.resolve("Titan.jpg")), is(true));
                assertThat(Files.exists(modelResources.resolve("Ariane5.jpg")), is(false));
                assertThat(Files.exists(modelResources.resolve("Sojus.jpg")), is(false));
            }
            case "ROSKOSMOS" -> {
                assertThat(Files.exists(modelResources.resolve("Sojus.jpg")), is(true));
                assertThat(Files.exists(modelResources.resolve("Ariane5.jpg")), is(false));
                assertThat(Files.exists(modelResources.resolve("Titan.jpg")), is(false));
            }
            default -> {
                assertThat(Files.isRegularFile(modelResources.resolve("Ariane5_ESA.jpg")), is(true));
                assertThat(Files.isRegularFile(modelResources.resolve("Titan_NASA.jpg")), is(true));
                assertThat(Files.isRegularFile(modelResources.resolve("Sojus_ROSKOSMOS.jpg")), is(true));
            }
        }
    }

    private Path dejittered(final String srcSet) {
        return DEJITTERED.resolve(srcSet);
    }

    private String source(final String srcSet, final String file) throws IOException {
        return Files.readString(dejittered(srcSet).resolve("java").resolve(PKG).resolve(file),
                StandardCharsets.UTF_8);
    }

}
