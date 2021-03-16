package com.hypertech.helloboot.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Path
{
public String value(); 
public boolean isPublic() default true;
}

