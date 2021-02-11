package model;

public class HeadingElement extends TagElement implements Element{
	
	public HeadingElement(Element content, int level, String attributes) {
		
		super("h" + level, true, content, attributes);
		
		if(attributes == null) {
			
			attributes = "";
		}
		
		setAttributes(attributes);	//update in super
	}
}
