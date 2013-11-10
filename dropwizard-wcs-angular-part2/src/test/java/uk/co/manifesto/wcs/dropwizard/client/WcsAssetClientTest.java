package uk.co.manifesto.wcs.dropwizard.client;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.Attribute;

@Ignore
public class WcsAssetClientTest {

	private WcsAssetClient assetClient;
	
	@Before
	public void setup() {
		assetClient = new WcsAssetClient("http://localhost:9080/cs/", "fwadmin", "xceladmin");
	}
	
	@Test
	public void getAssetTypeTest() {
		AssetsBean assets = assetClient.getAssets("avisports", "AVIArticle");
		
		for (AssetInfo assetInfo : assets.getAssetinfos()) {
			System.out.println(assetInfo.getId());
		}
	}
	
	@Test
	public void getAssetTest() {
		AssetBean asset = assetClient.getAsset("avisports","AVIArticle:1328196047241");
		for (Attribute attribute : asset.getAttributes()) {
			System.out.println(attribute.getName());
		}
	}
}
