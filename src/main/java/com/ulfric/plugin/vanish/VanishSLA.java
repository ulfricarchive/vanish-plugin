package com.ulfric.plugin.vanish;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ulfric.dragoon.extension.intercept.sla.SLA;
import com.ulfric.dragoon.stereotype.Stereotype;

@Retention(RUNTIME)
@Target(METHOD)
@Stereotype
@SLA(1)
public @interface VanishSLA {

}
