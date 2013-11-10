package uk.co.manifesto.wcs.dropwizard.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uk.co.manifesto.wcs.dropwizard.client.WcsAssetClient;
import uk.co.manifesto.wcs.dropwizard.mapper.ArticleMapper;
import uk.co.manifesto.wcs.dropwizard.representation.Article;
import uk.co.manifesto.wcs.dropwizard.representation.ArticleLink;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.google.common.collect.Lists;
import com.yammer.metrics.annotation.Timed;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

	private WcsAssetClient client;
	private String siteName;
	private String assetType;
	
	public ArticleResource(WcsAssetClient client, String siteName, String assetType) {
		this.client = client;
		this.siteName = siteName;
		this.assetType = assetType;
	}
	
	@GET
	@Timed
	public List<ArticleLink> getAllArticles() {
		List<ArticleLink> articleLinks = Lists.newArrayList();
		for (AssetInfo assetInfo : client.getAssets(siteName, assetType).getAssetinfos()) {
			articleLinks.add(ArticleMapper.mapArticleLink(assetInfo));
		}
		return articleLinks;
	}
	
	@GET
	@Path("{articleId}")
	@Timed
	public Article getArticle(@PathParam("articleId") String articleId) {
		AssetBean asset = client.getAsset(siteName, String.format("%s:%s",assetType, articleId));
		return ArticleMapper.mapArticle((asset));

	}
}
