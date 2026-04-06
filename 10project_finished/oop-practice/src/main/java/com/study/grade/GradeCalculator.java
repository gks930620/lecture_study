package com.study.grade;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;


    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    /**
     * 평균학점 계산 방법 = (학점수×교과목 평점)의 합계/수강신청 총학점 수
     * • MVC패턴(Model-View-Controller) 기반으로 구현한다.
     * • 일급 컬렉션 사용
     */
    public double calculateGrade() {
        double totalMultiplyCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
        int totalCompleteCredit = courses.calculateTotalCompleteCredit();
        return  totalMultiplyCreditAndCourseGrade/totalCompleteCredit;
        //return 0;   //메소드 만들기 전에 테스트 실패하는지 확인
        //return 4.5;  // 테스트 성공하는지 확인.   아 그럼 테스트는 잘 만들었구나 확인.
        //그다음에 실제 메소드 작성   이게 바로 TDD
    }
}
