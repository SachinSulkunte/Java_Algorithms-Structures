package onlineTest;
import java.io.*;
import java.io.Serializable;
public class SystemManager extends Serialize implements Manager{
	
	@Override
	public boolean addExam(int examId, String title) {

		for (Exam e : Exam.examList) {

			if (examId == e.examId && title == e.examTitle) {	//checks whether exam already exists

				return false;
			}
		}
		Exam e = new Exam(examId, title);
		Exam.examList.add(e);
		return true;
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		
		Question newQuestion = new Question(examId, questionNumber, text, points, answer, null, 1);

		for (Exam e : Exam.examList) {

			if (examId == e.examId) {
				
				int check = e.checkExisting(questionNumber);	//call to helper method checking existing questions
				
				if(check == -1) {
					
					e.questionList.add(newQuestion); // adds new question
				}
				else {
					
					e.questionList.set(check, newQuestion);	//overwrites existing question
				}
			}
		}
		
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		
		Question newQ = new Question(examId, questionNumber, text, points, null, answer, 2);

		for (Exam e : Exam.examList) {

			if (examId == e.examId) {
				
				int check = e.checkExisting(questionNumber);	//call to helper method checking existing questions
				
				if(check == -1) {
					
					e.questionList.add(newQ); // adds new question
				}
				else {
					
					e.questionList.set(check, newQ);	//overwrites existing question
				}
			}
		}
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		
		Question newQs = new Question(examId, questionNumber, text, points, null, answer, 3);

		for (Exam e : Exam.examList) {

			if (examId == e.examId) {
				
				int check = e.checkExisting(questionNumber);	//call to helper method checking existing questions
				
				if(check == -1) {
					
					e.questionList.add(newQs); // adds new question
				}
				else {
					
					e.questionList.set(check, newQs);	//overwrites existing question
				}
			}
		}
	}

	@Override
	public String getKey(int examId) {
		
		String returned = "";
		Boolean existing = false;
		
		for(Exam e: Exam.examList) {
			
			if(e.examId == examId) {	//for particular exam
				
				existing = true;
				for(int i = 0; i < e.questionList.size(); i++) {
					
					returned += "Question Text: " + e.questionList.get(i).text;
					returned += "\nPoints: " + e.questionList.get(i).points;
					returned += "\nCorrect Answer: " + e.keyHelper(e.questionList.get(i)) + "\n";
				}
					
			}
		}
		
		if(existing == false) {	//if exam does not exist
			
			returned = "Exam not found";
		}
		
		return returned;
	}

	@Override
	public boolean addStudent(String name) {
		
		for(Student i: Student.studentList) {
			
			if(name == i.getName()) {
				
				return false;
			}
		}
		
		Student.studentList.add(new Student(name));
		return true;
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {

		for(Student s: Student.studentList) {
			
			if(s.name == studentName) {
				
				Question newAnswer = new Question(examId, questionNumber, "", 0.0, answer, null, 1);
				s.studentAnswers.add(newAnswer);	//adds to arraylist belonging to each student
			}
		}
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		
		for(Student s: Student.studentList) {
			
			if(s.name == studentName) {
				
				Question newAnswer = new Question(examId, questionNumber, "", 0.0, null, answer, 2);
				s.studentAnswers.add(newAnswer);  //adds to arraylist belonging to each student
			}
		}
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		
		for(Student s: Student.studentList) {
			
			if(s.name == studentName) {
				
				Question newAnswer = new Question(examId, questionNumber, "", 0.0, null, answer, 3);
				s.studentAnswers.add(newAnswer);   //adds to arraylist belonging to each student
			}
		}
	}

	@Override
	public double getExamScore(String studentName, int examId) {

		double score = 0.0;
		for (Student s : Student.studentList) {

			if (s.name == studentName) {

				for (Exam e : Exam.examList) {

					if (e.examId == examId) {

						score = calculateScore(s, e);
						return score;
					}
				}
			}
		}

		return score;
	}
	
	@Override
	public String getGradingReport(String studentName, int examId) {
		
		double total = 0;
		String report = "";
		for (Student s : Student.studentList) {

			if (s.name == studentName) {

				for (Exam e : Exam.examList) {

					if (e.examId == examId) {

						for(Question q: e.questionList) {
							
							if(q.type == 1 || q.type == 2) {
								
								report += "Question #" + q.questionNumber;
								report += " " + getReport(s, e, q) + " points out of " + q.points;
								report += "\n";
							}
							else {
								
								report += "Question #" + q.questionNumber;
								report += " " + getFillReport(s, e, q) + " points out of " + q.points;
								report += "\n";
							}
						}
						
						for (Question totals : e.questionList) {
							
							total += totals.points;
						}
						
						report += "Final Score: " + calculateScore(s, e) + " out of " + total;
					}
				}
			}
		}
		
		return report;
	}
	
	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		
		Score.setCutoffs(letterGrades, cutoffs);
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		
		double total = 0;
		double grade = 0;
		int count = 0;
		for(Exam e: Exam.examList) {
						
			double newGrade = 0;
			total = 0;
			newGrade += getExamScore(studentName, e.examId);
			
			for (Question totals : e.questionList) {
				
				total += totals.points;
			}	
			
			grade += newGrade/total;
			count++;
		}
		
		grade = grade/count;
		grade = Math.round(grade*100);
		return grade;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		
		String letter = "";
		double grade = getCourseNumericGrade(studentName);
		
		for(int i = 0; i < Score.numCutoffs.length; i++) {
			
			if(grade >= Score.numCutoffs[i]) {	//checks for largest cutoff that it meets
				
				letter = Score.letters[i];
				return letter;
			}
		}
		return letter;
	}

	@Override
	public String getCourseGrades() {
		
		String report = "";
		
		for(int i = 0; i < Student.studentList.size(); i++) {	//sort student list
			
			for (int j = i + 1; j < Student.studentList.size(); j++) { 
	        
				if (Student.studentList.get(i).name.compareTo(Student.studentList.get(j).name) > 0) {
	                   
						Student holder = Student.studentList.get(i);
	                    Student.studentList.set(i, Student.studentList.get(j));
	                    Student.studentList.set(j, holder);
	            }
	        }
		}
		
		for(Student s: Student.studentList) {
			
			report += s.name + " " + getCourseNumericGrade(s.name);
			report += " " + getCourseLetterGrade(s.name) + "\n";
		}
		
		return report;
	}

	@Override
	public double getMaxScore(int examId) {
		
		double max = 0.0;
		for(Student s: Student.studentList) {
				
			if(getExamScore(s.name, examId) > max) {
					
				max = getExamScore(s.name, examId);
			}
		}
		
		return max;
	}

	@Override
	public double getMinScore(int examId) {
		
		double min = 200; //impossible score
		for(Student s: Student.studentList) {
				
			if(getExamScore(s.name, examId) < min) {
					
				min = getExamScore(s.name, examId);
			}
		}
		
		return min;
	}

	@Override
	public double getAverageScore(int examId) {
		
		double average = 0;
		double total = 0;
		
		for (Exam e : Exam.examList) {

			if (e.examId == examId) {
				
				for (Question totals : e.questionList) {
					
					total += totals.points;
				}
				
				for(Student s: Student.studentList) {
					
					average += getExamScore(s.name, examId)/total;
				}
				
				average = average/Student.studentList.size()*total;
				average = Math.round(average*100)/100;
				return average;
			}
		}
		return total;
	}


	@Override
	public void saveManager(Manager manager, String fileName){
		
		Serialize obj = new Serialize();
		obj.saveObject(manager, fileName);
	}

	@Override
	public Manager restoreManager(String fileName){
		
		Serialize obj = new Serialize();
		return obj.restoreObject(fileName);
	}
	
	//calculator methods for scoring
	private double calculateScore(Student s, Exam exam) {

		double count = 0;

		for (Question checkCorrect : exam.questionList) {

			if(checkCorrect.type == 1 || checkCorrect.type == 2) {
					
				count += getReport(s, exam, checkCorrect);// increases score by point value
			}
							
			else if(checkCorrect.type == 3) {
						
					count += getFillReport(s, exam, checkCorrect);
			}
		}
		
		return count;	//total score
	}
	
	private double getReport(Student s, Exam exam, Question question) {	//get individual points for report for t/f
		
		for (Question check : s.studentAnswers) {

			if(question.questionNumber == check.questionNumber && question.examId == check.examId) {
				
				if(question.type == 1) {
					
					if(question.boolAnswer == check.boolAnswer) {
						
						return question.points;
					}
					else {
						return 0;
					}
				}
				else if(question.type == 2) {
					
					int count = 0;
					for(int i = 0; i < check.answer.length; i++) {
						
						for(int j = 0; j < check.answer.length; j++) {
							
							if(check.answer.length != question.answer.length) {
								
								return 0;
							}
							else if(check.answer[i] == question.answer[j]) {
								
								count++;
							}
						}
					}
					
					if(count == question.answer.length) {
						
						return question.points;
					}
					else
						return 0;
				}
			}
		}
		
		return 0;
	}
	
	//calculates partial credit in points
	private double getFillReport(Student s, Exam exam, Question question) {
		
		double count = 0;
		for (Question check : s.studentAnswers) {

			if(question.questionNumber == check.questionNumber && question.examId == check.examId) {
					
					for(int i = 0; i < question.answer.length; i++) {
						
						for(int j = 0; j < check.answer.length; j++) {
							
							if(question.answer[i] == check.answer[j]) {
								
								//partial points
								count = count + question.points/question.answer.length;
							}
						}
					}
			}
			
		}
		
		return count;
	}
}

