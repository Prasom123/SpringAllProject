package com.heraizen.springftlexample.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Student {
  private String usn;
  private String name;
  private String sem;
  private List<Subject> subjects;
}
