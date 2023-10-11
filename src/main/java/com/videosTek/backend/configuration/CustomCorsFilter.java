//package com.videosTek.backend.configuration;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsUtils;
//
//import java.io.IOException;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CustomCorsFilter extends CorsFilter {
//
//    public void doFilter(HttpServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        if(!CorsUtils.isPreFlightRequest(servletRequest)){
//        String clientIpAddress = servletRequest.getRemoteAddr();
//            System.out.println("Adresse IP client : "+clientIpAddress);
//        }
//        super.doFilter(servletRequest,servletResponse,filterChain);
//    }
//}