package com.pavoindus.simplefileupload.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created 12/11/2017 21:27
 *
 * @author Deepankar Sharma
 */
public class FileUploadForm {

  private MultipartFile[] files;

  public MultipartFile[] getFiles() {
    return files;
  }

  public void setFiles(MultipartFile[] files) {
    this.files = files;
  }

}
