package model;

public class TableElement extends TagElement implements Element {

	private Element[][] contents;
	private int row, col;

	public TableElement(int rows, int cols, String attributes) {

		super("table", true, null, attributes);
		contents = new Element[rows][cols];	//initialize array in constructor
		
		row = rows;
		col = cols;
		
		if (attributes == null || attributes == "") {

			setAttributes("");
		} 
		else
			setAttributes(" " + attributes);	//adds in space for attributes if present
	}

	public void addItem(int rowIndex, int colIndex, Element item) {

		contents[rowIndex][colIndex] = item;
	}

	public double getTableUtilization() {

		double counter = 0;

		for (int r = 0; r < row; r++) {

			for (int c = 0; c < col; c++) {

				if (contents[r][c] != null) {	//array has something at indices

					counter++;
				}
			}
		}

		return (counter / (this.row * this.col)) * 100; // # filled spots divided by total number of spots
	}

	@Override
	public String genHTML(int indentation) {

		String result = "";
		
		String output = "";
		
		for(int row = 0; row < contents.length; row++) {
			
			output += Utilities.spaces(2 * indentation) + "<tr>";	//outside tags
			
			for(int col = 0; col < contents[row].length; col++) {
				
				if(contents[row][col] != null) {
					
					output += "<td>" + contents[row][col].genHTML(3) + "</td>";	//each element tags
				}
				else
					output += "<td>" + "</td>";	//for empty elements
			}
			
			output += "</tr>\n";
		}

		result += Utilities.encompass(super.getStartTag()) + "\n" + output
				+ Utilities.spaces(indentation) + super.getEndTag();
		
		return result;
	}
}
