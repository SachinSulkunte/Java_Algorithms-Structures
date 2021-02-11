package model;
import java.util.ArrayList;
public class WebPage implements Comparable<WebPage>{

	private String title;
	ArrayList<Element> list;
	
	public WebPage(String title) {
		
		this.title = title;
		list = new ArrayList<Element>();
	}
	
	public int addElement(Element element) {
		
		if(element != null) {	//cannot add null item
			
			list.add(element);
		}
		
		if(element instanceof TagElement) {
			
			return ((TagElement) element).getId();	//can only have id if instance of TagElement
		}
		else
			return -1;
	}
	
	public String getWebPageHTML(int indentation) {
		
		String output = "";
		
		String result = "<!doctype html>" + "\n";
		result  += "<html>" + "\n" + Utilities.spaces(indentation) + "<head>\n";	//additional tags
		result += Utilities.spaces(2 * indentation) + "<meta charset=\"utf-8\"/>\n";
		result += Utilities.spaces(2 * indentation) + "<title>" + title + "</title>\n";
		result += Utilities.spaces(indentation) + "</head>\n";
		
		result += Utilities.spaces(indentation) + "<body>";
		
		if(list != null) {
			
			output += "\n";
			
			for(int i = 0; i < list.size(); i++) {	//adds list items to webpage
									
				output += Utilities.spaces(indentation) + list.get(i).genHTML(indentation) + "\n";
			}
		}
		
		result += output + Utilities.spaces(indentation) + "</body>\n" + "</html>";
		return result;
	}
	
	public void writeToFile(String filename, int indentation) {
		
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}
	
	public Element findElem(int id) {
		
		for(int i = 0; i < list.size(); i++) {
			
			if(((TagElement) list.get(i)).getId() == id) {	//only instances of TagElement qualify
				
				return list.get(i);
			}
		}
		
		return null;
	}
	
	public String stats() {
		
		int para = 0;
		int lists = 0;
		int tables = 0;
		double utility = 0;
		
		for(int i = 0; i < list.size(); i++) {	
			
			if(list.get(i) instanceof ParagraphElement) {
				
				para++;
			}
			else if(list.get(i) instanceof ListElement) {
				
				lists++;
			}
			else if(list.get(i) instanceof TableElement) {
				
				tables++;
				utility += ((TableElement) list.get(i)).getTableUtilization();
			}
		}
		
		utility = utility/tables; //calculates average
		
		String output = "List Count: " + lists + "\nParagraph Count: " + para + "\nTable Count: " + tables + "\nTableElement Utilization: " + utility;
	
		return output;
	}
	
	@Override
	public int compareTo(WebPage o) {
		
		return this.title.compareTo(o.title);
	}

	public static void enableId(boolean choice) {
		
		TagElement.enableId(choice);
	}
	
}
