package uk.co.manifesto.wcs.dropwizard.representation;

public class Article {

		private final long id;
	    private final String headlineField;
	    private final String subheadlineField;
	    private final String abstractField;
	    private final String authorField;
	    private final String postDateField;
	    private final String bodyField;

	    public Article(long id, String headlineField,  String subheadlineField, String abstractField,String authorField,String postDateField, String bodyField) {
	        this.id = id;
	        this.headlineField = headlineField;
	        this.subheadlineField = subheadlineField;
	        this.abstractField = abstractField;
	        this.authorField = authorField;
	        this.postDateField = postDateField;
	        this.bodyField = bodyField;

	    }

	    public long getId() {
	        return id;
	    }

		public String getHeadlineField() {
			return headlineField;
		}

		public String getSubheadlineField() {
			return subheadlineField;
		}

		public String getAbstractField() {
			return abstractField;
		}

		public String getAuthorField() {
			return authorField;
		}

		public String getPostDateField() {
			return postDateField;
		}

		public String getBodyField() {
			return bodyField;
		}

}
