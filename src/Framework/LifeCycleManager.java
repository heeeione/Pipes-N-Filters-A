/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Course.*;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter studentSourceFilter = new SourceFilter("Students.txt", 0);
            CommonFilter courseSourceFilter = new SourceFilter("Courses.txt", 1);
            CommonFilter correctPreCourses = new SinkFilter("Output-1.txt", 0);
            CommonFilter unCorrectPreCourses = new SinkFilter("Output-2.txt", 1);
            CommonFilter checkPreCourseFilter = new CheckPreCourseFilter();
            
            studentSourceFilter.connectOutputTo(checkPreCourseFilter, 0);
            courseSourceFilter.connectOutputTo(checkPreCourseFilter, 1);
            checkPreCourseFilter.connectOutputTo(correctPreCourses, 0);
            checkPreCourseFilter.connectOutputTo(unCorrectPreCourses, 1);
            
            Thread studentSourceThread = new Thread(studentSourceFilter);
            Thread courseSourceThread = new Thread(courseSourceFilter);
            Thread correctPreCoursesTthread = new Thread(correctPreCourses);
            Thread unCorrectPreCoursesThread = new Thread(unCorrectPreCourses);
            Thread checkPreCourseFilterThread = new Thread(checkPreCourseFilter);
            
            studentSourceThread.start();
            courseSourceThread.start();
            correctPreCoursesTthread.start();
            unCorrectPreCoursesThread.start();
            checkPreCourseFilterThread.start();
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
