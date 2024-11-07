package chevron81.barebone.api;

public class UrlConstants {

    // HealthChecks
    public static final String HEALTH_BASE_PATH = "/health";
    public static final String HEALTH_PING_PATH = "/ping";
    public static final String HEALTH_STATUS_PATH = "/status";
    public static final String HEALTH_BASE_PATH_PING = UrlConstants.HEALTH_BASE_PATH + UrlConstants.HEALTH_PING_PATH;
    public static final String HEALTH_BASE_PATH_STATUS = UrlConstants.HEALTH_BASE_PATH + UrlConstants.HEALTH_STATUS_PATH;
}
