package com.vince.receipt_processor.service;

import com.vince.receipt_processor.controller.ReceiptProcessorController;
import com.vince.receipt_processor.pojo.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class ReceiptProcessorService {

  private final HashMap<String, Receipt> receiptStorage = new HashMap<>();
  private static final Logger logger = LoggerFactory.getLogger(ReceiptProcessorController.class);

  private void saveReceipt(Receipt receipt) {
    String uuid = UUID.randomUUID().toString();
    receiptStorage.put(uuid, receipt);
    logger.info("receipt storage: {}", receiptStorage.toString());
  }

}
