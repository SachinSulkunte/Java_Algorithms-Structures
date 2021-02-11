package model;

public class AnchorElement extends TagElement implements Element{

	private String url, linkText, attributes;
	
	public AnchorElement(String url, String linkText, String attributes) {
		
		super("a", true, null, attributes);
		
		this.url = url;
		this.linkText = linkText;
		this.attributes = attributes;	
		
		if(attributes == null) {	//sets it to empty string
			
			attributes = "";
		}
		else
			attributes = " " + attributes;	//adds appropriate spacing
		
		setAttributes(" href=" + '"' + url + '"' + attributes);
	}
	
	public String getLinkText() {
		
		return linkText;
	}
	
	public String getUrlText() {
		
		return url;
	}
	
	@Override
	public String genHTML(int indentation) {
		
		String result = Utilities.spaces(indentation); 
		result += Utilities.encompass(super.getStartTag());	//utilities method for adding <> symbols
		result += linkText + super.getEndTag();
		
		return result;
	}
}
