package com.github.hashcodejbe.contests.hashcode2019.qualifications.models;

import java.util.HashSet;
import java.util.List;

public class Slide {
    private HashSet<String> tags;

    public Slide(List<Photo> photos) {
        tags = new HashSet<>();
        for (Photo photo: photos) {
            updateTags(photo.getTags());
        }
    }

    public Slide(Slide slide) {
        tags = new HashSet<>(slide.getTags());
    }

    public void updateTags(HashSet<String> otherTags) {
        for (String tag: otherTags) tags.add(tag);
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public HashSet<String> inclusion(Slide otherSlide) {
        HashSet<String> res = new HashSet<>();
        for (String tag: tags) {
            if (otherSlide.getTags().contains(tag)) res.add(tag);
        }
        return res;
    }

    public HashSet<String> exclusion(Slide otherSlide) {
        HashSet<String> res = new HashSet<>();
        for (String tag: tags) {
            if(!otherSlide.getTags().contains(tag)) res.add(tag);
        }
        return res;
    }
}
