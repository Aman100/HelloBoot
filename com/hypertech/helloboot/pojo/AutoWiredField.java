//wrapper hi bna dete hain abhi toh pr "Dynamic class" k concept se kaam or aasaan ho sakta hain
package com.hypertech.helloboot.pojo;
import java.lang.reflect.*;
public class AutoWiredField implements java.io.Serializable
{
private Field field;
private String name;
public void setField(Field field)
{
this.field=field;
}
public Field getField()
{
return this.field;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
}
