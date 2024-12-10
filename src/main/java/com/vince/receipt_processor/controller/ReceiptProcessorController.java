package com.vince.receipt_processor.controller;

import com.vince.receipt_processor.pojo.Receipt;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ReceiptProcessorController {

  private static final Logger logger = LoggerFactory.getLogger(ReceiptProcessorController.class);

  @PostMapping("/receipts/process")
  private void processReceipt(@RequestBody Receipt receipt) {
    System.out.println(receipt.getRetailer());
    logger.info("Receipt: {}", receipt);
  }

}
