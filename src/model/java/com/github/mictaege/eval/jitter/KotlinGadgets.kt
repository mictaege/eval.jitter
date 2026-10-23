package com.github.mictaege.eval.jitter

import com.github.mictaege.jitter.api.OnlyIf

/**
 * The Kotlin flavour of the model sources: like the Java sources, the Kotlin
 * sources carry the flavour specific code of every flavour, and the
 * dejittering surgery creates the source delivery of the active flavour -
 * the flavour gated members of the inactive flavours leave the variant, the
 * jitter annotations are cleaned.
 */
class KotlinGadgets {

    fun plain(): String = "plain"

    @OnlyIf(Flavour.ESA)
    fun esaOnly(): String = "esa"

    @OnlyIf(Flavour.NASA, Flavour.ROSKOSMOS)
    fun nasaRoskosmosOnly(): String = "nasa/roskosmos"

    @OnlyIf(Flavour.ESA)
    class EsaGadget

    @OnlyIf(Flavour.NASA, Flavour.ROSKOSMOS)
    class NasaRoskosmosGadget
}
