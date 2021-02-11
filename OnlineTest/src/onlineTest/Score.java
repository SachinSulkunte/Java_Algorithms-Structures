package onlineTest;
import java.io.*;
public class Score extends SystemManager implements Manager {

	public String letterGrade;
	public int numericGrade;
	public static String [] letters;
	public static double [] numCutoffs;
	
	protected static void setCutoffs(String [] let, double [] num) {
		
		letters = let;
		numCutoffs = num;
	}
}