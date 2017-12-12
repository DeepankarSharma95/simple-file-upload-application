package com.pavoindus.simplefileupload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

  @PostMapping("/upload") public String upload(
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
  }

  @GetMapping("/uploadStatus") public String uploadStatus() {
    return "uploadStatus";
  }
}
