package com.epam.cdp.epambook.web.service;

/**
 * @author Bohdan_Suprun
 */
public interface WebService {

    /**
     * @param request
     * @param response
     * @param <T>
     * @param <E>
     * @return
     * @throws ServiceProcessException
     */
    <T, E> E process(ServerInstance serverInstance, T request, Class<E> response, Object ... uriVars);
}
