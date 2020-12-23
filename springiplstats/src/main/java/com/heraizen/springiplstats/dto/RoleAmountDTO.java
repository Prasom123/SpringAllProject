package com.heraizen.springiplstats.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleAmountDTO {
private String role;
private double amount;
}
