package com.hypertech.helloboot.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ExceptionHandler
{
public ResponseType[] forMethodResponseType() default {};
}