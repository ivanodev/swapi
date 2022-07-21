package com.ldm.swapi.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticateFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATTRIB = "Authorization";
    public static final String PREFIX_ATTRIB = "Bearer ";

    public AuthenticateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String headerAttrib = request.getHeader(HEADER_ATTRIB);

        if(headerAttrib == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!headerAttrib.startsWith(PREFIX_ATTRIB)) {

            chain.doFilter(request, response);
            return;
        }

        String token = headerAttrib.replace(PREFIX_ATTRIB, "");
        UsernamePasswordAuthenticationToken authenticationToken = this.getUsernamePasswordAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {

        String user = JWT.require(Algorithm.HMAC512(AuthenticationFilter.SECRET))
                .build()
                .verify(token)
                .getSubject();

        if (StringUtils.isEmpty(user)) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());



    }
}
