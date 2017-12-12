package com.pavoindus.simplefileupload.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created 12/11/2017 21:27
 *
 * @author Deepankar Sharma
 */
public class FileUploadForm {

  private MultipartFile file;

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile files) {
    this.file = files;
  }

}
