package com.test.devops.oca;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class examTest {

    class Student {
        int marks = 10;
    }

    @Test
    void tested() {
        Student student = new Student();
        assertThat(student.marks).isEqualTo(10);
    }

}
