package com.example.search.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;
@Order(1)
@WebFilter(filterName = "searchFilter", urlPatterns = "/weather/search")
public class SearchFilter extends OncePerRequestFilter {
    public static final String CORRELATION_ID = "correlation-id";
    final Logger logger = LoggerFactory.getLogger(SearchFilter.class);

    public static String uuid;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ThreadLocal<String> localName = new ThreadLocal();
        try{
            final String requestHeader = httpServletRequest.getHeader(CORRELATION_ID);
            uuid = requestHeader;
            localName.set(requestHeader);

            filterChain.doFilter(httpServletRequest,httpServletResponse);
            logger.info("SearchFilter's ThreadLocal fetch uuid = " + uuid);
            String uuid = localName.get();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            localName.remove();
            logger.info("SearchFilter's ThreadLocal of uuid removed");

        }
    }
}
