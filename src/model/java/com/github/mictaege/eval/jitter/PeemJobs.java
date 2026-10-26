package com.github.mictaege.eval.jitter;

/**
 * Demo consumer of the {@link TableNames} constants: the annotation value
 * is inlined by javac and re-baked by the flavour build, the wrapper
 * constant is referenced and follows the flavour at runtime.
 */
public class PeemJobs {

    @Table(name = TableNames.OLOGIS_2_PEEM_JOB)
    public static final Object JOB = new Object();

    public static int nodeIndex() {
        return TableNames.NODE_IDX;
    }

}
