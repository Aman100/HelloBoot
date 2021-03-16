package com.hypertech.helloboot.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //ElementType.PARAMETER
public @interface FileConfiguration
{
public String maximumFileSize() default "1024*1024*2";
public String []extensions() default "*";
public String inputName() default "";
public String path() default "";
}