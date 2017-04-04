package com.epam.cdp.epambook.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bohdan_Suprun
 */
@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T, E> E process(ServerInstance serverInstance, T request, Class<E> response, Object... uriVars) {
        E responseObject = null;
        switch (serverInstance.getMethod()) {
            case POST:
                responseObject = restTemplate.postForObject(getUri(serverInstance), request, response, uriVars);
                break;
            case GET:
                responseObject = restTemplate.getForObject(getUri(serverInstance), response, uriVars);
                break;
            case DELETE:
                restTemplate.delete(getUri(serverInstance), uriVars);
                responseObject = null;
                break;
        }

        return responseObject;
    }

    private String getUri(ServerInstance serverInstance) {
        return serverInstance.getUrl().endsWith("/") || serverInstance.getUri().startsWith("/")
                ? serverInstance.getUrl() + serverInstance.getUri()
                : serverInstance.getUrl() + "/" + serverInstance.getUri();
    }
}
