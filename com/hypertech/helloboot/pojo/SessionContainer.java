package com.hypertech.helloboot.pojo;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
public class SessionContainer extends HttpServlet implements java.io.Serializable
{
private HttpSession httpSession;

public SessionContainer(HttpSession httpSession)
{
this.httpSession=httpSession;
}

void setSession(HttpSession httpSession) //can be accessed within same package
{
this.httpSession=httpSession;
}

public void setAttribute(String name, Object value)
{
httpSession.setAttribute(name,value);
}

public Object getAttribute(String name)
{
return httpSession.getAttribute(name);
}

public void removeAttributes()
{
Enumeration<String> e=httpSession.getAttributeNames();
while(e.hasMoreElements())
{
httpSession.removeAttribute(e.nextElement());
}
}

public void endSession()
{
httpSession.invalidate();
}

public String getId()
{
return httpSession.getId();
}

public void setMaxInactiveInterval(int timeout, SessionTimeout sessionTimeout)
{
if(sessionTimeout==SessionTimeout.SECONDS)
{
httpSession.setMaxInactiveInterval(timeout);
}
if(sessionTimeout==SessionTimeout.MINUTES)
{
httpSession.setMaxInactiveInterval(timeout*60);
}
if(sessionTimeout==SessionTimeout.HOURS)
{
httpSession.setMaxInactiveInterval(timeout*60*60);
}
}

public long getLastAccessedTime()
{
return httpSession.getLastAccessedTime();
}

}