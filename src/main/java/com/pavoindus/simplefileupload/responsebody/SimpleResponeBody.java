package com.pavoindus.simplefileupload.responsebody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 12/13/2017 01:23
 *
 * @author Deepankar Sharma
 */
public class SimpleResponeBody {

  private List<String> fileContent;
  private String message;

  public SimpleResponeBody(List<String> fileContent) {
    this.fileContent = fileContent;
  }

  public SimpleResponeBody(String message) {
    this.message = message;
  }

  public List<String> getFileContent() {
    return fileContent;
  }

  public void setFileContent(List<String> fileContent) {
    this.fileContent = fileContent;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
