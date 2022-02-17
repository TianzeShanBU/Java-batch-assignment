package com.example.details.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(2)
@WebFilter(filterName = "detailFilter", urlPatterns = "/details")
public class DetailFilter extends OncePerRequestFilter {
    public static final String CORRELATION_ID = "correlation-id";
    final Logger logger = LoggerFactory.getLogger(DetailFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ThreadLocal<String> localName = new ThreadLocal();
        try{
            final String requestHeader = httpServletRequest.getHeader(CORRELATION_ID);
            localName.set(requestHeader);
            String uuid = localName.get();
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            logger.info("DetailFilter's ThreadLocal fetch uuid = " + uuid);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            localName.remove();
            logger.info("DetailFilter's ThreadLocal of uuid removed");

        }
    }
}
