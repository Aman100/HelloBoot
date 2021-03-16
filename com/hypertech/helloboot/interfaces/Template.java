package com.hypertech.helloboot.interfaces;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Template
{
public String name();
public String id() default ""; //id is default because maybe the framework user doesn't want to specify id
}