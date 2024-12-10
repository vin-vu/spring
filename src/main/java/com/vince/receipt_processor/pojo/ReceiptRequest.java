package com.vince.receipt_processor.pojo;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceiptRequest {

  @NotBlank(message = "Retailer is mandatory")
  @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Invalid retailer name format")
  String retailer;

  @NotBlank(message = "Purchase date is mandatory")
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Purchase date must be in the format YYYY-MM-DD")
  String purchaseDate;

  @NotBlank(message = "Purchase time is mandatory")
  @Pattern(regexp = "\\d{2}:\\d{2}", message = "Purchase time must be in the format HH:mm")
  String purchaseTime;

  @NotNull(message = "Items list cannot be null")
  @Size(min = 1, message = "At least one item is required")
  @Valid
  List<Item> items;

  @NotBlank(message = "Total is mandatory")
  @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total must match the format 'XX.XX'")
  String total;
}
