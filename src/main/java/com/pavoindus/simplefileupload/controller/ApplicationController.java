package com.pavoindus.simplefileupload.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.pavoindus.simplefileupload.exceptions.UnsupportedFileFormatException;
import com.pavoindus.simplefileupload.responsebody.SimpleResponeBody;
import com.pavoindus.simplefileupload.form.FileUploadForm;

/**
 * Created 12/11/2017 21:22
 *
 * @author Deepankar Sharma
 */
@Controller public class ApplicationController {

  @GetMapping("/") public String welcome() {
    return "welcome";
  }

  @PostMapping("/upload")
  public @ResponseBody SimpleResponeBody processUploadedFile(@ModelAttribute FileUploadForm fileUploadForm) {
    MultipartFile file = fileUploadForm.getFile();
    if (!file.getOriginalFilename().endsWith(".csv") && !file.getOriginalFilename()
        .endsWith(".txt")) {
      throw new UnsupportedFileFormatException();
    }
    List<String> stringLines;
    try {
      String contents = new String(file.getBytes());
      String[] contentLines = contents.split("\\r\\n");
      stringLines = Arrays.asList(contentLines);
    } catch (IOException e) {
      throw new UnsupportedFileFormatException();
    }
    return new SimpleResponeBody(stringLines);
  }

  @ExceptionHandler(UnsupportedFileFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody SimpleResponeBody handleException() {
    return new SimpleResponeBody("File format should be either .csv or .txt and should be readable");
  }

  /*@PostMapping("/upload") public String upload(
      @ModelAttribute FileUploadForm fileUploadForm,
      RedirectAttributes redirectAttributes) {
    StringJoiner sj = new StringJoiner(" , ");
    int count = 0;
    for (MultipartFile file : fileUploadForm.getFiles()) {
      if (file.isEmpty()) {
        continue; //next pls
      }
      count++;
      if (!file.getOriginalFilename().endsWith(".csv") && !file.getOriginalFilename()
          .endsWith(".txt")) {
        redirectAttributes.addFlashAttribute("fileContent" + count,
            "Invalid File Format... Expected .csv or .txt file");
        continue;
      }
      try {
        byte[] bytes = file.getBytes();
        String contents = new String(file.getBytes());
        String[] contentLines = contents.split("\\r\\n");
        List<String> stringLines = Arrays.asList(contentLines);
        StringJoiner sjFile = new StringJoiner("<br/>");
        stringLines.forEach(sjFile::add);
        redirectAttributes.addFlashAttribute("fileContent" + count, sjFile.toString());
        sj.add(file.getOriginalFilename());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String uploadedFileName = sj.toString();
    if (StringUtils.isEmpty(uploadedFileName)) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
    } else {
      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + uploadedFileName + "'");
    }
    return "redirect:/uploadStatus";
  }*/

  @GetMapping("/uploadStatus") public String uploadStatus() {
    return "uploadStatus";
  }
}
