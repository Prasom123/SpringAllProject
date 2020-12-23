package com.heraizen.springiplstats.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {
  private String message;
}
