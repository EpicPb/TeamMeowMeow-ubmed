package utility;

import javax.servlet.http.Cookie;

public class CookieHelper {
	
	public static Cookie getCookie(Cookie cookies[]){
		
		Cookie foundCookie = null;
		
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("userCookie")){
					foundCookie = cookie;
					break;
				}
			}
		}
		
		return foundCookie;
	}

}
