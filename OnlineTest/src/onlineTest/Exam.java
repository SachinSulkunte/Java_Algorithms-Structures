package onlineTest;

import java.util.ArrayList;
import java.io.*;
public class Exam extends SystemManager implements Manager {

	public static ArrayList<Exam> examList = new ArrayList<Exam>();
	public ArrayList<Question> questionList;

	public int examId;
	public String examTitle;
	
	public Exam(int id, String title) {

		examId = id;
		examTitle = title;

		questionList = new ArrayList<Question>();
	}

	public String getTitle() {

		return examTitle;
	}

	public int getId() {

		return examId;
	}
	
	
	
	protected int checkExisting(int questionNumber) {	//helper method determines if question exists
		
		if(questionList != null) {
			
			for (int i = 0; i < questionList.size(); i++) {

				if (questionList.get(i).questionNumber == questionNumber) { 
					// if same exam and same question number
					
					return i;
				}
			}
		}
		return -1;
	}
	
	protected String keyHelper(Question q) {	//helper method for proper formatting of answer items
		
		String answer = "";
		
		if(q.type == 1) {	//if true/false
			
			if(q.boolAnswer == true) {
				
				answer = "True";
				return answer;
			}
			else if (q.boolAnswer == false){
				
				answer = "False";
				return answer;
			}
		}
		else if(q.type == 2 || q.type == 3) {	//if mult choice or fill in blanks question
			
			answer = "[";
			String [] sorted = q.answer;

			for(int i = 0; i < q.answer.length; i++) {
				
				for (int j = i + 1; j < q.answer.length; j++) { 
		        
					if (q.answer[i].compareTo(q.answer[j]) > 0) {
		                    String holder = q.answer[i];
		                    sorted[i] = sorted[j];
		                    sorted[j] = holder;
		            }
		        }
				
				if(i == q.answer.length - 1) {
					
					answer += sorted[i] + "]";	//proper ending formatting
				}
				else {
					
					answer += sorted[i] + ", ";
				}
			}
		}

		return answer;
	}
}
