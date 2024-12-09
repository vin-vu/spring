package com.vince.receipt_processor.pojo;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Receipt {
  String retailer;
  String purchaseDate;
  String purchaseTime;
  List<Item> items;
  String total;
}
