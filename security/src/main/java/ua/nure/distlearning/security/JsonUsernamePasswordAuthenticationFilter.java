package ua.nure.distlearning.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Bohdan_Suprun
 */
@Component
public final class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUsernamePasswordAuthenticationFilter.class);
    private static final String AUTH_JSON_REQUEST_PARAM_NAME = JsonUsernamePasswordAuthenticationFilter.class
            .getCanonicalName() + "_LOGIN_PARAMETER";

    private ObjectMapper jaksonMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // Parse request body once
        request.setAttribute(AUTH_JSON_REQUEST_PARAM_NAME, parseRequestBody(request));
        Authentication authentication = super.attemptAuthentication(request, response);
        // Reset parsed body
        request.setAttribute(AUTH_JSON_REQUEST_PARAM_NAME, null);
        LOG.debug("Authentication for user {}", authentication);
        return authentication;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return obtainUserPasswordMap(request).getOrDefault(getPasswordParameter(), "")
                .toString();
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return obtainUserPasswordMap(request).getOrDefault(getUsernameParameter(), "").toString();
    }

    private Map obtainUserPasswordMap(HttpServletRequest request) {
        return request.getAttribute(AUTH_JSON_REQUEST_PARAM_NAME) == null
                ? new TreeMap()
                : (Map) request.getAttribute(AUTH_JSON_REQUEST_PARAM_NAME);
    }

    private Map parseRequestBody(HttpServletRequest request) {
        try {
            return jaksonMapper.readValue(request.getInputStream(), Map.class);
        } catch (IOException ex) {
            LOG.debug("An error occurred while read login params", ex);
        }

        return new TreeMap<>();
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
