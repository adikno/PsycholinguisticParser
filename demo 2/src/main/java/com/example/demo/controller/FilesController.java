package com.example.demo.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Random; 


import com.example.demo.server.ReadExcelFileDemo;
import com.example.demo.server.ServerMain;
import com.example.demo.server.WritingAns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.modal.FileInfo;
import com.example.demo.message.ResponseMessage;
import com.example.demo.service.FilesStorageService;

@Controller
@CrossOrigin("http://localhost:8081")
//@CrossOrigin("http://132.70.30.35:8080")
public class FilesController {

    @Autowired
    FilesStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        Random rand = new Random(); 
        int rand_int = rand.nextInt(1000000); 
        String id = Integer.toString(rand_int);
        String name = id + "_" + file.getOriginalFilename(); 
        try {
            if (!(getFileExtension(file.getOriginalFilename()).equals("xlsx"))) {
                throw new Exception("The file is not in the correct format! should be xlsx.");
            }
            storageService.save(file, name);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            ReadExcelFileDemo demo = new ReadExcelFileDemo("uploads/" +  name);
            try {
                demo.parser();
                List<String> sentences = demo.getSentences();
                ServerMain serverMain = new ServerMain();
                Map<String, List<Integer>> ans = serverMain.processData(sentences);
                WritingAns writer = new WritingAns();
                writer.editFile(ans, "uploads/" +  name , serverMain.getKeys());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message ,name));
        } catch (Exception e) {
            System.out.println(e.toString());
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, name));
        }
    }
    @GetMapping("/files/fileInfo/{filename:.+}")
    @ResponseBody
    public ResponseEntity<FileInfo> getFileToShow(@PathVariable String filename) throws IOException {
        Resource file = storageService.load(filename);
        String url = MvcUriComponentsBuilder
        .fromMethodName(FilesController.class, "getFile",file.getFilename()).build().toString();
        String nameOfFile = file.getFilename().split("_")[1];
        FileInfo fileInfo =  new FileInfo(nameOfFile , url);
        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
