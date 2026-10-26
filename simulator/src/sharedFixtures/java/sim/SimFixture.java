package sim;

import com.github.mictaege.jitter.api.OnlyIf;

/**
 * The fixtures of the simulator, shared with the consumers of this
 * library through the custom sharedFixtures source set: they compile
 * against the full 150% model and run against the stripped model of
 * the active flavour.
 */
public class SimFixture {

    @OnlyIf("ESA")
    public String esaBooster() {
        return "ariane";
    }

    @OnlyIf("NASA")
    public String nasaBooster() {
        return "falcon";
    }

    @OnlyIf("ROSKOSMOS")
    public String roskosmosBooster() {
        return "sojus";
    }

    public String plain() {
        return "any mission";
    }
}
