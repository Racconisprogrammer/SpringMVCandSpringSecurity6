package com.codework.end2endapp.utility;


import jakarta.servlet.http.HttpServletRequest;

public class UrlUtil {

    public static String getApplicationUrl(HttpServletRequest httpServletRequest) {
        String appUrl = httpServletRequest.getRequestURL().toString();
        return appUrl.replace(httpServletRequest.getServletPath(), "");
    }
}
