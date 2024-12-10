package com.vince.receipt_processor.service;

import com.vince.receipt_processor.pojo.ReceiptRequest;
import java.util.HashMap;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReceiptProcessorService {

  private final HashMap<String, ReceiptRequest> receiptStorage = new HashMap<>();
  private static final Logger logger = LoggerFactory.getLogger(ReceiptProcessorService.class);

  public String saveReceipt(ReceiptRequest receipt) {
    String receiptId = UUID.randomUUID().toString();
    receiptStorage.put(receiptId, receipt);
    logger.info("receipt storage: {}", receiptStorage);
    return receiptId;
  }

}
