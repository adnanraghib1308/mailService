package com.scaler.myspringproject.models;

public class Csv {
  public String fileName;
  public String csvString;

  public Csv(){

  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getCsvString() {
    return csvString;
  }

  public void setCsvString(String csvString) {
    this.csvString = csvString;
  };
}