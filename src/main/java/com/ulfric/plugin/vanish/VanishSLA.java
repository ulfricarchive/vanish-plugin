package com.ulfric.plugin.vanish;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import com.ulfric.dragoon.extension.intercept.sla.SLA;
import com.ulfric.dragoon.stereotype.Stereotype;

@Retention(RUNTIME)
@Target(METHOD)
@Stereotype
@SLA(value = 200000, unit = TimeUnit.NANOSECONDS)
public @interface VanishSLA {

}
