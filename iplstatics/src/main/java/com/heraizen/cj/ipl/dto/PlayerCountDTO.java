package com.heraizen.cj.ipl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerCountDTO {
private String label;
private long count;
}
