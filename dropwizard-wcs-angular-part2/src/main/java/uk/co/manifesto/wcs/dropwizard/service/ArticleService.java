package uk.co.manifesto.wcs.dropwizard.service;

import uk.co.manifesto.wcs.dropwizard.client.WcsAssetClient;
import uk.co.manifesto.wcs.dropwizard.configuration.ArticleConfiguration;
import uk.co.manifesto.wcs.dropwizard.health.WcsHealthCheck;
import uk.co.manifesto.wcs.dropwizard.resource.ArticleResource;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class ArticleService extends Service<ArticleConfiguration> {
    
	public static void main(String[] args) throws Exception {
        new ArticleService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ArticleConfiguration> bootstrap) {
        bootstrap.setName("article");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(ArticleConfiguration configuration, Environment environment) {

    	final WcsAssetClient client = new WcsAssetClient(configuration.getBaseUri(), configuration.getUsername(), configuration.getPassword());
        environment.addResource(new ArticleResource(client, configuration.getSiteName(), configuration.getAssetType()));
        environment.addHealthCheck(new WcsHealthCheck(client));
        	
    }

}
