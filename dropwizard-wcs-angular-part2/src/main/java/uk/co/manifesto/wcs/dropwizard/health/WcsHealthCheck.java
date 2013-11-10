package uk.co.manifesto.wcs.dropwizard.health;

import uk.co.manifesto.wcs.dropwizard.client.WcsAssetClient;

import com.yammer.metrics.core.HealthCheck;

public class WcsHealthCheck extends HealthCheck {
    private final WcsAssetClient client;

    public WcsHealthCheck(WcsAssetClient client) {
        super("client");
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
    	if (client.canConnect()) {
    		return Result.healthy();
    	}
    	return Result.unhealthy("Can't connect to WCS please check the logs");
    }
}