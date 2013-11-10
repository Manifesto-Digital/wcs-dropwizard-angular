package uk.co.manifesto.wcs.dropwizard.client;

import javax.ws.rs.core.MediaType;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.wem.sso.SSO;
import com.fatwire.wem.sso.SSOException;
import com.fatwire.wem.sso.SSOSession;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class WcsAssetClient {

	private Client client;
	private String baseUri;
	private String multiticket;
	private WebResource baseResource;
	private boolean connected = false;
	private final String username;
	private final String password;

	public WcsAssetClient(String baseUri, String username, String password) {
		this.client = new Client();
		this.baseUri = baseUri;
		this.baseResource = client.resource(baseUri + "REST");
		this.username = username;
		this.password = password;
	}

	public AssetsBean getAssets(String site, String assetType) {
		makeSureConnected();
		WebResource getResource = this.baseResource.path(String.format("/sites/%s/types/%s/search", site, assetType));
		Builder builder = getResource.accept(MediaType.APPLICATION_XML);
		return builder.get(AssetsBean.class);
	}
	
	public AssetBean getAsset(String site, String assetIdParam) {
		makeSureConnected();
		String[] params = assetIdParam.split(":");
		String assetType = params[0];
		String assetId = params[1];
		
		WebResource getResource = this.baseResource.path(String.format("/sites/%s/types/%s/assets/%s", site, assetType, assetId));
		Builder builder = getResource.accept(MediaType.APPLICATION_XML);
		return builder.get(AssetBean.class);
	}

	public boolean canConnect() {
		makeSureConnected();
		return connected;
	}

	private void connect() throws SSOException {
		SSOSession ssoSession = SSO.getSSOSession(baseUri);
		multiticket = ssoSession.getMultiTicket(username, password);
		baseResource = baseResource.queryParam("multiticket", multiticket);
		connected = true;
	}
	
	
	private void makeSureConnected() {
		if (!connected) {
			try {
				connect();
			} catch (SSOException e) {
				e.printStackTrace();
			}
		}
	}


}
