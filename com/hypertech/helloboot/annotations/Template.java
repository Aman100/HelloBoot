package com.hypertech.helloboot.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Template
{
public String name();
public String id() default "";
}