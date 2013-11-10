package uk.co.manifesto.wcs.dropwizard.configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class ArticleConfiguration extends Configuration{
    @NotEmpty
    @JsonProperty
    private String baseUri;

    @NotEmpty
    @JsonProperty
    private String username;
    
    @NotEmpty
    @JsonProperty
    private String password;
    
    @NotEmpty
    @JsonProperty
    private String siteName;
    
    @NotEmpty
    @JsonProperty
    private String assetType;

	public String getBaseUri() {
		return baseUri;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSiteName() {
		return siteName;
	}

	public String getAssetType() {
		return assetType;
	}
}
