package com.vnpay.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpay.security.entity.SessionManagerEntity;
import com.vnpay.security.repository.SessionManagerRepository;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component("jwtAuthorizationFilter")
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final SessionManagerRepository sessionManagerRepository;

    public JwtAuthorizationFilter(JwtUtils utils, UserDetailsService service, SessionManagerRepository sessionManagerRepository) {
        jwtUtils = utils;
        userDetailsService = service;
        this.sessionManagerRepository = sessionManagerRepository;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // authorize the request before
        try {
            String token = jwtUtils.getJwtTokenFromRequest(request);
            Optional<SessionManagerEntity> optSessionManager = sessionManagerRepository.findByToken(token);
            if (token != null && jwtUtils.validateJwtToken(token) && optSessionManager.isPresent()) {
                String username = jwtUtils.getUsernameFromToken(token);

                // authorize
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);


            }
        } catch (Exception e) {
            logger.debug("An unathorized request has been sent from {}.", request.getRemoteAddr());
        }

        filterChain.doFilter(request, response);
        // do after
    }

    private void handleException(HttpServletResponse resp) {
        try {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setHeader("Content-Type", "application/json");


            Map<String,Object> responseMap = new HashMap<>();

            responseMap.put("status",HttpServletResponse.SC_UNAUTHORIZED);
            responseMap.put("message","Missing token");
            resp.getWriter().write(new ObjectMapper().writeValueAsString(responseMap));

            resp.getWriter().flush();
            resp.getWriter().close();
        }
        catch (IOException e) {
//            LOG.error("Error in handleException : ", e);
        }
    }

}
