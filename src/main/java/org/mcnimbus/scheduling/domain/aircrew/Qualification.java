package org.mcnimbus.scheduling.domain.aircrew;

public enum Qualification {
  U("U"),
  F("F"),
  M("M"),
  I("I"),
  E("E");

  private String qualification;

  Qualification(String qualification) {
    this.qualification = qualification;
  }
}
