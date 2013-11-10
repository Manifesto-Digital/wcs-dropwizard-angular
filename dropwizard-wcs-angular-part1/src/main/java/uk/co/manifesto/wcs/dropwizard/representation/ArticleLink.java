package uk.co.manifesto.wcs.dropwizard.representation;

public class ArticleLink {

	private final long id;
	private final String name;
	private final String href;
	
	public ArticleLink(long id, String name, String href) {
		this.id = id;
		this.name = name;
		this.href = href;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getHref() {
		return href;
	}
}
