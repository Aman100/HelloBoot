package com.hypertech.helloboot.pojo;
import javax.servlet.*;
import javax.servlet.http.*;
public class CookieManager
{
private HttpServletRequest request;
private HttpServletResponse response;
public CookieManager(HttpServletRequest request, HttpServletResponse response)
{
this.request=request;
this.response=response;
}
public void setCookie(String key, String value)
{
Cookie cookie=new Cookie(key,value);
response.addCookie(cookie);
}
public String getCookie(String key)
{
String value=null;
Cookie[] cookies=request.getCookies();
if(cookies!=null)
{
for(Cookie cookie: cookies)
{
if(cookie.getName().equals(key))
{
value=cookie.getValue();
break;
}
}
}
return value;
}
}