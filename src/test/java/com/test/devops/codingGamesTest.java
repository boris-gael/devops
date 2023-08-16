package com.test.devops;

import com.test.devops.codingGame.Exercices;
import com.test.devops.service.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class codingGamesTest {

    @Test
    void encode() {
        assertThat(Exercices.encode("aaaabcccaaa")).isEqualTo("4a1b3c3a");
        assertThat(Exercices.encode("aabaa")).isEqualTo("2a1b2a");
    }

    @Test
    void translate() {
        assertThat(Exercices.translate("hello, secret meeting tonight.")).isEqualTo("havellavo, savecravet maveetaving tavonavight.");
    }

    @Test
    void arrayListTest() {
        ArrayList l = new ArrayList(2);
        l.add(1);
        l.add(2);
        l.add(3);
        assertThat(l.size()).isEqualTo(3);
    }

    @Test
    void refAndCloneTest() throws CloneNotSupportedException {
        ProductDTO p1 = ProductDTO.builder().name("test1").price(2D).build();
        ProductDTO p2 = p1;
        p2.setName("test2");
        ProductDTO p3 = (ProductDTO) p1.clone();
        p3.setName("test3");
        assertEquals("test2", p2.getName());
        assertEquals("test3", p3.getName());
        assertEquals(p1.getName(), p2.getName());
    }

    @ParameterizedTest
    @CsvSource({"'4,2','6'", "'1.1,2.2,3.8','7.1'"})
    void stringCalculator(String chaine, String result) {
        assertEquals(result, Exercices.stringCalculator(chaine));
    }

    @Test
    void sumRange() {
        int[] ints = {0,1,6,10,15,100,123,1555};
        assertEquals(125, Exercices.sumRange(ints));
    }

    @Test
    void training() throws ParseException {
        Map<Integer, Integer> treeMap = new HashMap<>();
        treeMap.put(3,3);
        treeMap.put(2,1);
        treeMap.put(1,2);
        System.out.println(treeMap.values());
        assertEquals("", "");
    }

}
