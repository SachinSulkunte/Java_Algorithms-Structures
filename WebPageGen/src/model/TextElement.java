package model;

public class TextElement implements Element {

	private String textContent;
	
	public TextElement(String text) {
		
		textContent = text;
	}
	
	@Override
	public String genHTML(int indentation) {

		return textContent;	//returns text with proper spacing at beginning
	}

	
}
