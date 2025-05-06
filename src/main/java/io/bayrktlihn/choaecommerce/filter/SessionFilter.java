package io.bayrktlihn.choaecommerce.filter;

import io.bayrktlihn.choaecommerce.model.Customer;
import io.bayrktlihn.choaecommerce.service.application.SessionApplicationService;
import io.bayrktlihn.choaecommerce.util.HttpServletRequestUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionFilter extends OncePerRequestFilter {

    private final SessionApplicationService sessionApplicationService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequestUtil httpServletRequestUtil = HttpServletRequestUtil.getInstance();

        Customer customer = null;

        try {
            if (!CorsUtils.isCorsRequest(request)) {
                String sessionId = httpServletRequestUtil.getHeader(request, "sessionId");

                if (sessionId == null) {
                    sessionId = UUID.randomUUID().toString();
                }


                sessionApplicationService.addSessionWithoutCustomerIfNotExists(sessionId);

                customer = sessionApplicationService.findCustomer(sessionId);

                if (customer != null) {
                    HashSet<GrantedAuthority> authorities = new HashSet<>();
                    authorities.add(new SimpleGrantedAuthority("USER"));

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer.getPassword(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                request.setAttribute("sessionId", sessionId);
                response.addHeader("sessionId", sessionId);

                filterChain.doFilter(request, response);
                return;
            }

            filterChain.doFilter(request, response);
        } finally {
            if (customer != null) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
            request.removeAttribute("sessionId");
        }
    }


}
