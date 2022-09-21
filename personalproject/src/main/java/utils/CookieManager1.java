package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager1 {
	public static void makeCookie(HttpServletResponse response, String cName,
			String cValue, int cTime) {
		Cookie cookie=new Cookie(cName, cValue);
		cookie.setPath("/"); //최상위경로. 서버는 모든 경로로부터 오는 쿠키를 받음
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}
	
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieVal="";
		Cookie[] cookies=request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cookieName=c.getName();
				if(cookieName.equals(cName)) {
					cookieVal=c.getValue();
				}
			}
		}
		return cookieVal;
	}
	
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0);
	}
}
