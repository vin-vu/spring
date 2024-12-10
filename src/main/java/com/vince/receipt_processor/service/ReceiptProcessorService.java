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

  public int calculatePoints(String id) {
    ReceiptRequest receipt = receiptStorage.get(id);
    String retailer = receipt.getRetailer();

    long retailerPoints = retailer.chars().filter(Character::isLetterOrDigit).count();
    logger.info("Retailer points: {} ", retailerPoints);

    String total = receipt.getTotal();
    int pointsForRoundDollar = Double.parseDouble(total) % 1 == 0 ? 50 : 0;
    int pointsForMultiplePoint25 = Double.parseDouble(total) % 0.25 == 0 ? 25 : 0;

    double pointsForEvery2Items = Math.ceil((double) receipt.getItems().size() / 2);
    return 0;

  }

}
