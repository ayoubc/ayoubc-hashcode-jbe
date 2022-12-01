package com.github.hashcodejbe.contests.hashcode2019.qualifications;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/{year}/qualifications")
    public HashMap<String, Integer> getScore(
            @PathVariable("year") int year, @RequestPart("file")List<MultipartFile> multipartFiles) throws IOException{
        return scoreService.getScore(multipartFiles);
    }
//    @PostMapping("/2020/qualifications")
//    public HashMap<String, Integer> getScore(@RequestPart("file")List<MultipartFile> multipartFiles) throws IOException{
//        return scoreService.getScore(multipartFiles);
//    }
    @GetMapping(path = "/dirs")
    public List<String> getFiles() {
        return scoreService.fileInputService.getFiles(2019, "qualifications");
    }
}
