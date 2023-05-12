package com.scaler.myspringproject.models;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.web.multipart.MultipartFile;

public class Attachment {

  private String fileName;
  private DataSource dataSource;

  public Attachment() {
  }

  public Attachment(MultipartFile file) {
    try {
      byte[] bytes = file.getBytes();
      this.fileName = file.getOriginalFilename();
      this.dataSource = new ByteArrayDataSource(bytes, "application/octet-stream");
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public Attachment(Csv csv) {
    byte[] bytes = csv.csvString.getBytes();
    this.fileName = csv.fileName;
    this.dataSource = new ByteArrayDataSource(bytes, "application/octet-stream");
  }

  public String getFileName() {
    return fileName;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
