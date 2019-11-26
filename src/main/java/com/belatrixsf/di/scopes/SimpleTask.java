package com.belatrixsf.di.scopes;

import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("thread")
public class SimpleTask {

  private final String uniqueId;

  public SimpleTask() {
    this.uniqueId = UUID.randomUUID().toString();
  }

  public String getUniqueId() {
    return uniqueId;
  }

}
