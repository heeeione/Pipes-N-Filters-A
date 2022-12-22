package Utility;

import java.util.ArrayList;

public class Student {
	ArrayList<String> completedCourses = new ArrayList<>();
	public void addCompletedCourse(String str) { completedCourses.add(str); }
	public boolean match(String str) {
		for(String completedCourse : completedCourses) {
			if(completedCourse.equals(str)) return true;
		}
		return false;
	}
	public ArrayList<String> getCompletedCourses( ) { return completedCourses; }
}
