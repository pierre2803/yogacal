package com.pierre2803.yogacal.domain;

import lombok.*;
import org.jsoup.helper.Validate;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class YogaClass {

    @Getter @Setter @NonNull
    private LocalDateTime startTime;
    @Getter @Setter @NonNull
    private LocalDateTime endTime;
    @Getter @Setter @NonNull
    private String classType;
    @Getter @Setter @NonNull
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

    public static class YogaClassBuilder
    {
        private LocalDateTime nestedStartTime;
        private LocalDateTime nestedEndTime;
        private String nestedClassType;
        private String nestedInstructor;

        public YogaClassBuilder(final LocalDateTime nestedStartTime,
                                final LocalDateTime nestedEndTime,
                                final String nestedClassType,
                                final String nestedInstructor){
            this.nestedStartTime = nestedStartTime;
            this.nestedEndTime = nestedEndTime;
            this.nestedClassType = nestedClassType;
            this.nestedInstructor = nestedInstructor;
        }

        public YogaClassBuilder withStartTime(LocalDateTime startTime){
            this.nestedStartTime = startTime;
            return this;
        }

        public YogaClassBuilder withEndTime(LocalDateTime endTime){
            this.nestedEndTime = endTime;
            return this;
        }

        public YogaClassBuilder withClassType(String classType){
            this.nestedClassType = classType;
            return this;
        }

        public YogaClassBuilder withInstructor(String instructor){
            this.nestedInstructor = instructor;
            return this;
        }

        public YogaClass createYogaClass(){
            return new YogaClass(nestedStartTime, nestedEndTime, nestedClassType, nestedInstructor);
        }
    }

}
