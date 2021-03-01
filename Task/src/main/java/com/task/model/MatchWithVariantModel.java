package com.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchWithVariantModel {

    @NotNull(message = "Match cannot be null.")
    @NotEmpty(message = "Match cannot be empty.")
    @NotBlank(message = "Match cannot be blank.")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Accept only letters and whitespaces.")
    private String match;

    @NotNull(message = "Variant cannot be null.")
    @NotEmpty(message = "Variant cannot be empty.")
    @NotBlank(message = "Variant cannot be blank.")
    @Pattern(regexp = "[1-2]", message = "Accept only number 1 or 2.")
    private String variant;
}
