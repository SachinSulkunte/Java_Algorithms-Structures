package model;

import java.util.ArrayList;

public class ListElement extends TagElement implements Element {

	private ArrayList<Element> list = new ArrayList<Element>();

	public ListElement(boolean ordered, String attributes) {

		super(Utilities.isOrdered(ordered), true, null, attributes);	//isOrdered passes in boolean determing tag

		if (attributes == null || attributes == "") {

			setAttributes("");
		} 
		else
			setAttributes(" " + attributes);	//adds proper spacing
	}

	public void addItem(Element item) {

		list.add(item);
	}

	@Override
	public String genHTML(int indentation) {

		String result = Utilities.spaces(indentation) + Utilities.encompass(super.getStartTag()) + "\n"; // first line

		for (int i = 0; i < list.size(); i++) { // adds properly indented list elements to output

			result += Utilities.spaces(2 * indentation) + Utilities.encompass("li") + "\n";
			result += Utilities.spaces(2 * indentation) + list.get(i).genHTML(indentation) + "\n"
					+ Utilities.spaces(2 * indentation);
			result += Utilities.encompass("/li") + "\n";
		}

		result += Utilities.spaces(indentation) + super.getEndTag(); // adds end tag after

		return result;
	}
}
