CREATE TABLE `order_detail` (
  `orderDetailId` bigint NOT NULL AUTO_INCREMENT,
  `productId` bigint DEFAULT NULL,
  `orderId` bigint DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  PRIMARY KEY (`orderDetailId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `orders` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `orderedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `status` smallint DEFAULT NULL,
  `total` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `products` (
  `productId` bigint NOT NULL AUTO_INCREMENT,
  `productName` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  `iventoryNumber` int DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
