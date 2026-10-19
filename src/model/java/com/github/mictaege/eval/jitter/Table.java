package com.github.mictaege.eval.jitter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Demo annotation whose value is a compile-time constant - javac inlines
 * it into the annotated class, so the flavour build re-bakes it to the
 * active flavour's value.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String name();

}
