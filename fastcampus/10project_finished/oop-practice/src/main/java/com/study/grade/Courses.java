package com.study.grade;

import java.util.List;

public class Courses {
    //일급 컬렉션
    List<Course> courses;  //필드를 Course의 리스트 하나만 가지는 컬렉션
    //list로 할 수 있는 기능들을 수행함.

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
//        double multipliedCreditAndCourseGrade=0;
//        for(Course course : courses){
//            multipliedCreditAndCourseGrade+=course.multiplyCreditAndCourseGrade();
//        }
//        return multipliedCreditAndCourseGrade;
        return courses.stream().mapToDouble(course -> course.multiplyCreditAndCourseGrade())
                .sum();
    }

    public int calculateTotalCompleteCredit() {
        return courses.stream().mapToInt(Course::getCredit)
                .sum();
    }
}
