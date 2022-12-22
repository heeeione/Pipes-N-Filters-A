package Utility;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Courses {
	Map<String, ArrayList<String>> preCourseList;
	public Courses() { preCourseList = new HashMap<>(); }
	public void addPrerequisite(String course, ArrayList<String> prerequisite) { preCourseList.put(course, prerequisite); }
	public boolean checkPreCourses(Student student) {
		ArrayList<String> allCourse = student.getCompletedCourses();
		for(int i = 0; i < allCourse.size(); i++) {
			String course = allCourse.get(i);
			ArrayList<String> prerequisite = preCourseList.get(course);
			if(prerequisite != null) {
				for(int j = 0; j < prerequisite.size(); j++) {
					String preCourse = prerequisite.get(j);
					if(!allCourse.contains(preCourse)) return false;
				}
			}
		}
		return true;
	}
}