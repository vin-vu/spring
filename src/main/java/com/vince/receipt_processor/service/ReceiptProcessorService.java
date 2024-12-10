package com.vince.receipt_processor.service;

import com.vince.receipt_processor.controller.ReceiptProcessorController;
import com.vince.receipt_processor.pojo.ReceiptRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class ReceiptProcessorService {

  private final HashMap<String, ReceiptRequest> receiptStorage = new HashMap<>();
  private static final Logger logger = LoggerFactory.getLogger(ReceiptProcessorController.class);

  public String saveReceipt(ReceiptRequest receipt) {
    String receiptId = UUID.randomUUID().toString();
    receiptStorage.put(receiptId, receipt);
    logger.info("receipt storage: {}", receiptStorage.toString());
    return receiptId;
  }

}
