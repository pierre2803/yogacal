package com.pierre2803.yogacal.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YogaClassTest {

    YogaClass yogaClass1;
    YogaClass yogaClass2;

    @Before
    public void setUp() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
        LocalDateTime startTime = LocalDateTime.parse("2018-06-02T08:30:00.000+00:00", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2018-06-02T09:30:00.000+00:00", formatter);
        yogaClass1 = new YogaClass(startTime, endTime, "Power Yoga", "Jennie K");
        yogaClass2 = new YogaClass(startTime, endTime, "Power Yoga", "Jennie K");

    }

    @Test
    public void equals_test() {
        // Given yogaClass1 and yogaClass2
        // When
        boolean equality = yogaClass1.equals(yogaClass2);
        // Then
        assertTrue(equality);
    }

    @Test
    public void hashCode_test() {
        // Given yogaClass1 and yogaClass2
        // When
        int hash1 = yogaClass1.hashCode();
        int hash2 = yogaClass2.hashCode();
        // Then
        assertEquals(hash1, hash2);
    }
}