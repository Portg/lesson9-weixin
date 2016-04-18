package com.jeecg.p3.spinwin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

	public String exportName();

	public int exportFieldWidth();

	public int exportConvertSign();

	public int importConvertSign();
} 