package com.scaler.myspringproject.models;

import java.util.List;

public class CsvList {
  public List<Csv> csvList;

  public CsvList () {}
  
  public List<Csv> getCsvList() {
    return csvList;
  }

  public void setCsvList(List<Csv> csvList) {
    this.csvList = csvList;
  }
}
