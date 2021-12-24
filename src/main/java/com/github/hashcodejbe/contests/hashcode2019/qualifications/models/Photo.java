package com.github.hashcodejbe.contests.hashcode2019.qualifications.models;

import java.util.HashSet;

public class Photo {
    private char orientation;
    private HashSet<String> tags;
    private int index;

    public Photo(char orientation, HashSet<String> tags, int index) {
        this.orientation = orientation;
        this.tags = tags;
        this.index = index;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
