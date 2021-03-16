//Service se client ko jo maal jaega vo is wrapper main rakha hoga
package com.hypertech.helloboot.pojo;
import com.hypertech.helloboot.annotations.*;
import java.io.*;
public class ResponseFile implements java.io.Serializable
{
private File file;
private String mimeType;
private String name;
private boolean shouldSave;
public void setFile(File file)
{
this.file=file;
}
public void setMimeType(String mimeType)
{
this.mimeType=mimeType;
}
public void setName(String name)
{
this.name=name;
}
public void setShouldSave(boolean shouldSave)
{
this.shouldSave=shouldSave;
}
public File getFile()
{
return this.file;
}
public String getMimeType()
{
return this.mimeType;
}
public String getName()
{
return this.name;
}
public boolean getShouldSave()
{
return this.shouldSave;
}
}