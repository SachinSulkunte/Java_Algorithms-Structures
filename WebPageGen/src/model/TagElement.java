package model;

public class TagElement implements Element {

	private String tag, attribute; // aspects of a tag
	private Element contains;
	private boolean end;
	private static int counter = 0; // unique id for each tag element
	private int id = 0;
	private static boolean printed; // used for enableId

	public TagElement(String tagName, boolean endTag, Element content, String attributes) {

		tag = tagName;
		end = endTag;
		contains = content;
		attribute = attributes;
		
		counter++;	//increments static var
		id = counter; //updates with correct # based on previous objects
	}

	@Override
	public String genHTML(int indentation) {

		String output = Utilities.spaces(indentation) + Utilities.encompass(getStartTag());
		
		if(contains != null) {
			
			output += contains.genHTML(indentation); //null elements are left out
		}
		output += getEndTag();
		
		return output;
	}

	public static void enableId(boolean choice) {

		if (choice == true) {

			printed = true;
		} 
		else
			printed = false;
	}

	public int getId() {

		return id;
	}

	public String getStringId() {

		return " id=" + '"' + tag + id + '"'; // returns id label
	}

	public String getStartTag() {

		if (printed == true) {

			return tag + getStringId() + attribute;
		} 
		else {

			return tag + attribute;
		}
	}

	public String getEndTag() {

		if (this.end == false) {

			return "";
		} else
			return Utilities.encompass("/" + tag); // call to utility method for adding <> symbols
	}

	public void setAttributes(String attributes) {
		
		this.attribute = attributes;
	}

	public static void resetIds() {

		counter = 0;	//resets static variable to 0
	}
}
