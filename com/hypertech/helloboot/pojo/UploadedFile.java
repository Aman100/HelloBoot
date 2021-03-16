//Client se server ko jo maal jaegawo is wrapper main hoga
package com.hypertech.helloboot.pojo;
import java.io.*;
public class UploadedFile implements java.io.Serializable
{
private File file;
private boolean removeLater;
public UploadedFile(File file, boolean removeLater)
{
this.file=file;
this.removeLater=removeLater;
}
public void setFile(File file)
{
this.file=file;
}
public File getFile()
{
return this.file;
}
public void setRemoveLater(boolean removeLater)
{
this.removeLater=removeLater;
}
public boolean getRemoveLater()
{
return this.removeLater;
}
}