package com.hypertech.helloboot.annotations;
import java.lang.annotation.*;
import com.hypertech.helloboot.pojo.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Secured
{
public Class guard() default Secured.class;
}
