package onlineTest;
import java.util.ArrayList;
import java.io.*;

public class Student extends SystemManager implements Manager{

	public String name;
	public static ArrayList<Student> studentList = new ArrayList<Student>();
	public ArrayList<Question> studentAnswers;
	
	public Student(String name) {
		
		this.name = name;
		studentAnswers = new ArrayList<Question>();
	}
	
	public String getName() {
		
		return this.name;
	}
	
}
