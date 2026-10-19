package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.AlterConstValue;
import com.github.mictaege.jitter.api.InlinedConstantApproved;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;

/**
 * Demo of the flavour variance of a constant's VALUE, declared with
 * {@code @AlterConstValue} on the constant itself - the class is NOT
 * swapped with {@code @Alter}, which must not hold static constants
 * anymore: javac has inlined the target's values into the usage sites,
 * so they would not follow the swap.
 *
 * <p>The String constant's full model value is the placeholder anchor -
 * the fully qualified name of the constant itself - which the flavour
 * build re-bakes wherever it was inlined (annotation values of every
 * module of the build) and which therefore needs the
 * {@code @InlinedConstantApproved} marker: its usages must sit inside
 * annotations. The wrapper constant is no constant variable: nothing is
 * inlined, the usages are references that follow the flavour at runtime,
 * and the flavour build patches the single assignment of the declaring
 * class.
 */
public final class TableNames {

    @AlterConstValue(ifActive = ESA, with = "ESA_JOB")
    @AlterConstValue(ifActive = NASA, with = "NASA_JOB")
    @AlterConstValue(ifActive = ROSKOSMOS, with = "ROSKOSMOS_JOB")
    @InlinedConstantApproved
    public static final String OLOGIS_2_PEEM_JOB =
            "com.github.mictaege.eval.jitter.TableNames.OLOGIS_2_PEEM_JOB";

    @AlterConstValue(ifActive = ESA, with = "1")
    @AlterConstValue(ifActive = {NASA, ROSKOSMOS}, with = "2")
    public static final Integer NODE_IDX = 1;

    private TableNames() {
    }

}
