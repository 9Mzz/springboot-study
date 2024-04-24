package hello.practice.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.engine.jdbc.Size;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

class OrderQueryRepositoryTest {

    @Test
    void Test() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("male", "남자A", "백수"));
        students.add(new Student("male", "남자B", "직장인"));
        students.add(new Student("male", "남자C", "학생"));
        students.add(new Student("male", "남자D", "학생"));
        students.add(new Student("male", "남자E", "직장인"));
        students.add(new Student("female", "여자A", "백수"));
        students.add(new Student("female", "여자B", "백수"));
        students.add(new Student("female", "여자C", "백수"));
        students.add(new Student("female", "여자D", "직장인"));
        students.add(new Student("female", "여자E", "학생"));


        Map<String, List<Student>> collect = students.stream()
                .collect(groupingBy(student -> "[" + student.getSex() + "] 입니다. "));
        System.out.println("collect.keySet() = " + collect.keySet());
        System.out.println("collect.values() = " + collect.values());

    }

    @Data
    @AllArgsConstructor
    static class Student {
        private String sex;
        private String name;
        private String job;

        public Student(String sex) {
            this.sex = sex;
        }


    }

}