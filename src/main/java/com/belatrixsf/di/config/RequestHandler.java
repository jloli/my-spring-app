package com.belatrixsf.di.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RequestHandler {

  private final RequestInfo requestInfo;

  private HashSet<String> uniqueRequestsIds = new HashSet();


  public RequestHandler(RequestInfo requestInfo) {
    this.requestInfo = requestInfo;
  }

  public void addRequestIdToList() {
    String requestId = requestInfo.getRequestId();
    uniqueRequestsIds.add(requestId);
    System.out.println("Receiving request with id: " + requestId);
  }


  public List<String> getRequestsIds() {
    return new ArrayList<>(uniqueRequestsIds);
  }


}
