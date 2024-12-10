package com.vince.receipt_processor.service;

import com.vince.receipt_processor.pojo.Item;
import com.vince.receipt_processor.pojo.ReceiptRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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
    logger.info("pointsForRoundDollar: {}", pointsForRoundDollar);
    logger.info("pointsForMultiplePoint25: {}", pointsForMultiplePoint25);

    List<Item> items = receipt.getItems();
    double pointsForEvery2Items = Math.floor((double) items.size() / 2) * 5;
    logger.info("pointsForEvery2Items: {}", pointsForEvery2Items);

    double pointsForDescription = 0;
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getShortDescription().trim().length() % 3 == 0) {
        pointsForDescription += Math.ceil(Double.parseDouble(items.get(i).getPrice()) * 0.2);
      }
    }
    logger.info("pointsForDescription: {}", pointsForDescription);

    int purchaseDate = LocalDate.parse(receipt.getPurchaseDate(), DateTimeFormatter.ISO_DATE).getDayOfMonth();
    int pointsForPurchaseDate = purchaseDate % 2 != 0 ? 6 : 0;
    logger.info("pointsForPurchaseDate: {}", pointsForPurchaseDate);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime(), formatter);
    LocalTime startTime = LocalTime.parse("14:00", formatter);
    LocalTime endTime = LocalTime.parse("16:00", formatter);
    logger.info("time: {}, {}, {}", purchaseTime, startTime, endTime);

    int pointsForPurchaseTime = purchaseTime.isAfter(startTime) && purchaseTime.isBefore(endTime) ? 10 : 0;
    logger.info("pointsForPurchaseTime: {}", pointsForPurchaseTime);
    return 0;
  }
}
