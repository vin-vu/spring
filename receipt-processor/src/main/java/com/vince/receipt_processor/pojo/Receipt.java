package com.vince.receipt_processor.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Receipt {
  String retailer;
  String purchaseDate;
  String purchaseTime;
  List<Item> items;
  String total;
}
