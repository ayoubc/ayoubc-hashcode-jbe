package com.github.hashcodejbe.contests.hashcode2019.qualifications;

import com.github.hashcodejbe.contests.hashcode2019.qualifications.models.*;
import com.github.hashcodejbe.fileinputs.FileInputService;
import com.github.hashcodejbe.fileinputs.InputReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class ScoreService {

    @Autowired
    public FileInputService fileInputService;

    public HashMap<String, Integer> getScore(List<MultipartFile> multipartFiles) throws IOException {
        HashMap<String, Integer> result = new HashMap<>();
        ScoreThread[] scores = new ScoreThread[multipartFiles.size()];
        for (int i = 0; i < multipartFiles.size(); i++) {
            scores[i] = new ScoreThread(multipartFiles.get(i));
            scores[i].start();
        }
        for (int i=0;i<scores.length;i++) {
            try {
                scores[i].join();
            } catch(InterruptedException e) {
                System.out.println("Thread interrupted: " + e);
                e.printStackTrace();
            }
        }

        int total = 0;
        for (int i=0;i<scores.length;i++) {
            int score = scores[i].getScore();
            total += score;

            String fileName = String.valueOf(multipartFiles.get(i).getOriginalFilename().charAt(0));
            result.put(fileName, score);
        }
        result.put("total", total);

        return result;
    }

    private int getOneScore(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        List<Photo> photos = getPhotosFromInput(2019, "qualifications", fileName);
        List<Slide> slides = getSlidesFromOutput(multipartFile.getInputStream(), photos);

        int n = slides.size();
        int score = 0;

        for (int i=0;i<n-1;i++) {
            Slide s1 = slides.get(i);
            Slide s2 = slides.get(i+1);
            int a = s1.inclusion(s2).size();
            int b = s1.exclusion(s2).size();
            int c = s2.exclusion(s1).size();
            score += Math.min(a, Math.min(b,c));
        }
        return score;
    }

    private class ScoreThread extends Thread {
        private MultipartFile multipartFile;
        private int score;
        ScoreThread(MultipartFile m) {
            multipartFile = m;
        }

        @Override
        public void run() {
            try {
                this.score = getOneScore(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public int getScore() {
            return score;
        }
    }

    private List<Photo> getPhotosFromInput(int year, String stage, String fileName) throws IOException {
        InputReader in = fileInputService.getFileReader(year, stage, fileName);

        List<Photo> photos = new ArrayList<>();
        int n = in.nextInt();
        for (int i=0;i<n;i++) {
            char orientation = in.next().toCharArray()[0];
            int numTags = in.nextInt();
            HashSet<String> tags = new HashSet<>();
            for(int j=0;j<numTags;j++) tags.add(in.next());

            photos.add(new Photo(orientation, tags, i));
        }
        return photos;
    }

    private List<Slide> getSlidesFromOutput(InputStream is, List<Photo> photos) {
        InputReader in = new InputReader(is);

        List<Slide> slides = new ArrayList<>();
        int n = in.nextInt();

        for(int i=0;i<n;i++) {
            int[] photoIndex = in.readIntArray();
            List<Photo> phs = new ArrayList<>();
            for (int j=0;j<photoIndex.length;j ++) phs.add(photos.get(photoIndex[j]));
            slides.add(new Slide(phs));
        }
        return slides;
    }
}
