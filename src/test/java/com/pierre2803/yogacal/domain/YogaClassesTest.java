package com.pierre2803.yogacal.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YogaClassesTest {

    @InjectMocks
    YogaClasses yogaClasses;

    YogaClass yogaClass1;
    YogaClass yogaClass2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
        LocalDateTime startTime = LocalDateTime.parse("2018-06-02T08:30:00.000+00:00", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2018-06-02T09:30:00.000+00:00", formatter);
        yogaClass1 = new YogaClass(startTime, endTime, "Yin", "Jenny K");
        yogaClass2 = new YogaClass(startTime, endTime, "Power Yoga", "Dave M");
    }

    @Test
    public void addClass_test(){
        // Given yogaClasses && yogaClass1
        // When
        yogaClasses.add(yogaClass1);
        Optional<YogaClass> yogaClass = yogaClasses.get(0);
        // Then
        assertEquals(yogaClasses.size(),1);
        assertTrue(yogaClass.isPresent());
        assertEquals(yogaClass.get().getInstructor(),"Jenny K");
    }

    @Test
    public void removeClass_test(){
        // Given yogaClasses && yogaClass1 && yogaClass2
        // When
        yogaClasses.add(yogaClass1);
        yogaClasses.add(yogaClass2);
        yogaClasses.remove(yogaClass1);
        Optional<YogaClass> yogaClass = yogaClasses.get(0);
        // Then
        assertEquals(yogaClasses.size(),1);
        assertTrue(yogaClass.isPresent());
        assertEquals(yogaClass.get().getInstructor(),"Dave M");
    }
}