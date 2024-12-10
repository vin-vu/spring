package com.vince.receipt_processor.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Item {
  @NotBlank(message = "Short description is mandatory")
  @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid short description format")
  String shortDescription;

  @NotBlank(message = "Price is mandatory")
  @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must match format 'XX.XX'")
  String price;
}
