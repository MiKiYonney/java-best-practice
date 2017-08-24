package com.yonney.stram.validator.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by yonney on 17/8/24.
 */
@Data
public class TechnicianDTO {
    @NotBlank
    private String techName;
    @Min(0)
    @Max(100)
    private int age;
    private String techPic;
}
