package org.mcnimbus.scheduling.domain.schedule;

public enum SortieType {
  OPS("ops"),
  CHECK("check"),
  FORMAL_TRAINING("formal_training"),
  RECURRENCY("recurrency"),
  NORMAL_TRAINING("normal_training");

  private String type;

  SortieType(String type) {
    this.type = type;
  }
}
