package com.pierre2803.yogacal.domain;

import org.jsoup.helper.Validate;

import java.time.LocalDateTime;
import java.util.Objects;

public class YogaClass {

    LocalDateTime startTime;
    LocalDateTime endTime;
    String classType;
    String instructor;

    public YogaClass(final LocalDateTime startTime, final LocalDateTime endTime, final String classType, final String instructor){
        Validate.notNull(startTime);
        Validate.notNull(endTime);
        Validate.notNull(classType);
        Validate.notNull(instructor);
        this.startTime = startTime;
        this.endTime = endTime;
        this.classType = classType;
        this.instructor = instructor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
            return false;

        if (obj instanceof YogaClass) {
            YogaClass other = (YogaClass) obj;
            return startTime.equals(other.startTime) && endTime.equals(other.endTime)
                    && classType.equals(other.classType) && instructor.equals(other.instructor);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(startTime, endTime, classType, instructor);
    }

}
