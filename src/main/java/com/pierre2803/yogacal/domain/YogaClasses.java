package com.pierre2803.yogacal.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class YogaClasses {

    List<YogaClass> classes;

    public YogaClasses() {
        this.classes = new ArrayList<YogaClass>();
    }

    public void add(YogaClass yogaClass) {
        this.classes.add(yogaClass);
    }

    public Optional<YogaClass> get(int index) {
        if(index > this.classes.size())
            return Optional.empty();
        return Optional.of(this.classes.get(index));
    }

    public void remove(int index) {
        if(index > this.classes.size())
            this.classes.remove(index);
    }

    public void remove(YogaClass yogaClass) {
        this.classes.remove(this.classes.indexOf(yogaClass));
    }

    public int size() {
        return this.classes.size();
    }

}
