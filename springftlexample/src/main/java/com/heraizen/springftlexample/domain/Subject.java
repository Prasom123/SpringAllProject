package com.heraizen.springftlexample.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
   private String name;
   private float score;
   private float passScore;
   private String result;
}
