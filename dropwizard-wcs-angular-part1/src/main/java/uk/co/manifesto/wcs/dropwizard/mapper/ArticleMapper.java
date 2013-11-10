package uk.co.manifesto.wcs.dropwizard.mapper;

import java.util.Map;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.FieldInfo;
import com.google.common.collect.Maps;

import uk.co.manifesto.wcs.dropwizard.representation.Article;
import uk.co.manifesto.wcs.dropwizard.representation.ArticleLink;

public class ArticleMapper {

	public static Article mapArticle(AssetBean asset) {
		Map<String,String> am = Maps.newHashMap();
		for (Attribute attr : asset.getAttributes()) {
			if (attr.getName().equals("postDate")) {
				am.put(attr.getName(), attr.getData().getDateValue().toString());
			} else {
				am.put(attr.getName(), attr.getData().getStringValue());
			}
		}
		
		String assetId [] = asset.getId().split(":");
		
		Article article = new Article(Long.parseLong(assetId[1]),
									am.get("headline"),
									am.get("subheadline"),
									am.get("abstract"),
									am.get("author"),
									am.get("postDate"),
									am.get("body")
						);
		return article;		
	}
	
	public static ArticleLink mapArticleLink(AssetInfo assetInfo) {
		String [] assetTypeParams = assetInfo.getId().split(":");
		String assetId = assetTypeParams[1];
		String name = "";
		String href = String.format("/articles/%s",assetId);
		for (FieldInfo fieldInfo : assetInfo.getFieldinfos()) {
			if (fieldInfo.getFieldname().equals("name")) {
				name = fieldInfo.getData().toString();
			}
		}
		
		return new ArticleLink(Long.parseLong(assetId),name,href);	
	}
	
}
