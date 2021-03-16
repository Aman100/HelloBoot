package com.hypertech.helloboot.utility;
import java.util.*;
public class Utility
{
public static Object parseParamValue(String paramValue, Class type) throws Exception//for non array 
{
if(type.equals(Object.class)) return paramValue;
if(type.equals(String.class)) return paramValue;
if(type.equals(Long.class) || type.equals(long.class)) return Long.parseLong(paramValue);
if(type.equals(Integer.class) || type.equals(int.class)) return Integer.parseInt(paramValue);
if(type.equals(Short.class) || type.equals(short.class)) return Short.parseShort(paramValue);
if(type.equals(Byte.class) || type.equals(byte.class)) return Byte.parseByte(paramValue);
if(type.equals(Double.class) || type.equals(double.class)) return Double.parseDouble(paramValue);
if(type.equals(Float.class) || type.equals(float.class)) return Float.parseFloat(paramValue);
if(type.equals(Character.class) || type.equals(char.class)) return paramValue.charAt(0);
if(type.equals(Boolean.class) || type.equals(boolean.class)) return Boolean.parseBoolean(paramValue);
return null;
}
}