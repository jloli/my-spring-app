package com.belatrixsf.di.config;

import java.util.UUID;

public class RequestInfo {

  private final String uniqueId;

  public RequestInfo() {
    uniqueId = UUID.randomUUID().toString();
  }

  public String getRequestId() {
    return uniqueId;
  }

}
