package onlineTest;
import java.io.*;
public class Question extends SystemManager implements Manager {

	public int examId, questionNumber;
	public String text;
	public double points;
	public boolean boolAnswer;
	public String[] answer;

	public int type; // refers to the particular type of question (1 - T/F, 2 - multChoice, 3 - Blanks)

	public Question(int id, int questionNumber, String text, double points, Boolean boolAnswer, String[] answer,
			int type) {

		examId = id;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		if(boolAnswer != null) {
			this.boolAnswer = boolAnswer;
		}
		this.answer = answer;
		this.type = type;
	}
}
