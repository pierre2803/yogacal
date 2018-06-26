package com.pierre2803.yogacal.domain;

import lombok.*;
import org.jsoup.helper.Validate;

import java.time.LocalDateTime;

@Data
public class YogaClass {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String classType;
    private String instructor;

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

    /*public boolean equals(Object obj) {
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
    }*/

    public static class Builder
    {
        private LocalDateTime nestedStartTime;
        private LocalDateTime nestedEndTime;
        private String nestedClassType;
        private String nestedInstructor;

        public Builder(){}

        public Builder withStartTime(LocalDateTime startTime){
            this.nestedStartTime = startTime;
            return this;
        }

        public Builder withEndTime(LocalDateTime endTime){
            this.nestedEndTime = endTime;
            return this;
        }

        public Builder withClassType(String classType){
            this.nestedClassType = classType;
            return this;
        }

        public Builder withInstructor(String instructor){
            this.nestedInstructor = instructor;
            return this;
        }

        public YogaClass build(){
            return new YogaClass(nestedStartTime, nestedEndTime, nestedClassType, nestedInstructor);
        }
    }

}
