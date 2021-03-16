//request.getParameter("name");
//iske liye user method(@attribute("name") String cool) likhega vo
//{
//
//}
package com.hypertech.helloboot.pojo;
import javax.servlet.http.*;
import java.util.*;
public class RequestContainer implements java.io.Serializable
{
private HttpServletRequest httpServletRequest;

public RequestContainer(HttpServletRequest httpServletRequest)
{
this.httpServletRequest=httpServletRequest;
}

public HttpServletRequest getHttpServletRequest()
{
return httpServletRequest;
}

public String getRealPath(String value)
{
return httpServletRequest.getRealPath(value);
}

public String getQueryString()
{
return httpServletRequest.getQueryString();
}

void setHttpServletRequest(HttpServletRequest httpServletRequest) //can be accessed within same package
{
this.httpServletRequest=httpServletRequest;
}

public void setAttribute(String name, Object value)
{
this.httpServletRequest.setAttribute(name,value);
}

public Object getAttribute(String name)
{
return httpServletRequest.getAttribute(name);
}

public void removeAttribute(String name)
{
httpServletRequest.removeAttribute(name);
}

public void removeAttributes()
{
Enumeration<String> e=httpServletRequest.getAttributeNames();
while(e.hasMoreElements())
{
httpServletRequest.removeAttribute(e.nextElement());
}
}

}