package ua.nure.distlearning.web.service;

import org.springframework.http.HttpMethod;

/**
 * @author Bohdan_Suprun
 */
public final class ServerInstance {

    private final String service;
    private final String uri;
    private final HttpMethod method;

    public ServerInstance(String service, String uri, HttpMethod method) {
        this.service = service;
        this.uri = uri;
        this.method = method;
    }

    public String getService() {
        return service;
    }

    public String getUri() {
        return uri;
    }

    public String getUrl() {
        return "http://" + service;
    }

    public HttpMethod getMethod() {
        return method;
    }
}
