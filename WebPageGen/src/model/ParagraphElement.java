package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement implements Element {

	private ArrayList<Element> paragraph = new ArrayList<Element>();

	public ParagraphElement(String attributes) {

		super("p", true, null, attributes);

		if (attributes == null || attributes == "") {

			setAttributes("");	//updates in super
		} 
		else
			setAttributes(" " + attributes);	//proper spacing
	}

	public void addItem(Element item) {

		paragraph.add(item);
	}

	@Override
	public String genHTML(int indentation) {

		String results = Utilities.spaces(indentation) + Utilities.encompass(super.getStartTag()) + "\n"
				+ Utilities.spaces(indentation);

		for (int i = 0; i < paragraph.size(); i++) {

			results += Utilities.spaces(3) + paragraph.get(i).genHTML(indentation) + "\n";	//adds in items
		}

		results += Utilities.spaces(indentation) + super.getEndTag();
		return results;
	}
}
