package Components.Course;

import java.io.IOException;
import java.util.ArrayList;

import Framework.CommonFilterImpl;
import Utility.Courses;
import Utility.Student;

public class CheckPreCourseFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int checkBlank0 = 4;
    	int checkBlank1 = 1;
    	int checkBlank2 = 3;
        int numOfBlank0 = 0;
        int numOfBlank1 = 0;
        int idx0 = 0;
        int idx1 = 0;
        byte[] buffer0 = new byte[64];
        byte[] buffer1 = new byte[64];
        boolean correctData = false;
        int byte_read0 = 0;
        int byte_read1 = 0;

        Student student = new Student();
        Courses courses = new Courses();
        
        while(true) {
        	String courseId = "";
        	ArrayList<String> preCourse = new ArrayList<>();
        	while(byte_read1 != -1) {
        		byte_read1 = in.get(1).read();
                if(byte_read1 == ' ') numOfBlank1++;
                if(byte_read1 != -1) buffer1[idx1++] = (byte)byte_read1;
            	if(numOfBlank1 == checkBlank1 && byte_read1 == ' ')          	
                	for(int i = 0; i < 5; i++) courseId += (char)buffer1[idx1 - 6 + i];
            	if(numOfBlank1 == checkBlank2 && (byte_read1 == ' ' || byte_read1 == '\r')) {
	            	while(byte_read1 != '\r' && byte_read1 != -1) {
	            		byte_read1 = in.get(1).read();
	            		if(byte_read1 != -1) buffer1[idx1++] = (byte)byte_read1;
	            		if(byte_read1 == -1) idx1++;
	            		if(byte_read1 == ' ' || byte_read1 == '\r' || byte_read1 == -1) {
	            			String str = "";
		                	for(int i = 0; i < 5; i++) str += (char)buffer1[idx1 - 6 + i];
		                	preCourse.add(str);
	            		}
	                }
	            	if(!preCourse.isEmpty()) courses.addPrerequisite(courseId, preCourse);
            	}
            	if(byte_read1 == '\n') {
            		idx1 = 0;
            		numOfBlank1 = 0;
            		courseId = "";
            		preCourse = new ArrayList<>();
            	}
            }
        	while(byte_read0 != '\n' && byte_read0 != -1) {
        		byte_read0 = in.get(0).read();
                if(byte_read0 == ' ') numOfBlank0++;
                if(byte_read0 != -1) buffer0[idx0++] = (byte)byte_read0;
            	if(numOfBlank0 > checkBlank0 && (byte_read0 == ' ' || byte_read0 == '\r' || byte_read0 == -1)) {
                	String str = "";
                	for(int i = 0; i < 5; i++) str = str + (char)buffer0[idx0 - 6 + i];
                	student.addCompletedCourse(str);
                }
            }
        	if((byte_read0 == '\n' || byte_read0 == -1) && idx0 >= 10) {
	        	correctData = courses.checkPreCourses(student);
	            if(correctData) for(int i = 0; i < idx0; i++) out.get(0).write((char)buffer0[i]);
	            else  for(int i = 0; i < idx0; i++) out.get(1).write((char)buffer0[i]);
        	}
            if (byte_read0 == -1 && byte_read1 == -1) return true;
            idx0 = 0;
            idx1 = 0;
            numOfBlank0 = 0;
            numOfBlank1 = 0;
            byte_read0 = '\0';
            byte_read1 = '\0';
            student = new Student();
        }
    }
}