package model;

public class ImageElement extends TagElement implements Element {

	private int width, height;
	private String imageURL, alt;

	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {

		super("img", false, null, attributes);

		this.imageURL = imageURL;
		this.width = width;
		this.height = height;
		this.alt = alt;

		if (attributes == null || attributes == "") {

			attributes = "";
		} else
			attributes = " " + attributes; // adds spacing

		setAttributes(" src=" + '"' + imageURL + '"' + " " + "width=" + '"' + width + '"' + " " + "height=" + '"'
				+ height + '"' + " " + "alt=" + '"' + alt + '"' + attributes); // adds in necessary info
	}

	public String getImageURL() {

		return imageURL;
	}
}
