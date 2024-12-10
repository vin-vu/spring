package com.vince.receipt_processor.controller;

import com.vince.receipt_processor.pojo.ReceiptRequest;
import com.vince.receipt_processor.pojo.ReceiptResponse;
import com.vince.receipt_processor.service.ReceiptProcessorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/receipts")
public class ReceiptProcessorController {

  @Autowired private ReceiptProcessorService receiptProcessorService;

  private static final Logger logger = LoggerFactory.getLogger(ReceiptProcessorController.class);

  @PostMapping(value = "/process")
  private ReceiptResponse processReceipt(@Valid @RequestBody ReceiptRequest receipt) {
    logger.info("Receipt: {}", receipt.getRetailer());

    String receiptId = receiptProcessorService.saveReceipt(receipt);
    logger.info("id: {}", receiptId);
    return new ReceiptResponse(receiptId);
  }

  @GetMapping(value = "/{id}/points")
  private void getPoints(@PathVariable String id) {
    logger.info("id: {}", id);
    receiptProcessorService.calculatePoints(id);
  }
}
