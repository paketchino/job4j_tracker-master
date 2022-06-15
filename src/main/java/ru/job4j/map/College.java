package ru.job4j.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class College {
    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        Optional<Student> rsl = Optional.empty();
        for (Student s : students.keySet()) {
            if (account.equals(s.getAccount())) {
                rsl = Optional.of(s);
                break;
            }
        }
        return rsl;
        }

    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Student> a = findByAccount(account);
        Optional<Subject> rsl = Optional.empty();
        if (a.isPresent()) {
            Set<Subject> subjects = students.get(a.get());
            for (Subject subj : subjects) {
                if (name.equals(subj.getName())) {
                    rsl = Optional.of(subj);
                    break;
                }
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        Map<Student, Set<Subject>> studentSetMap = Map.of(
                new Student("Student", "00001", "201-18-15"),
                Set.of(
                        new Subject("Math", 70),
                        new Subject("English", 80))
        );

        College college = new College(studentSetMap);
        Optional student = college.findByAccount("00001");
        if (student.isPresent()) {
            System.out.println("Found student: " + student);
        }
        Optional subject = college.findBySubjectName("00001", "Student");
        if (subject.isPresent()) {
            System.out.println("Score found of subject: " + subject);
        }

    }
}