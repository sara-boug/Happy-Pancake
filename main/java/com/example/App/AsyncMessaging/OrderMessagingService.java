package com.example.App.AsyncMessaging;

import com.example.App.domain.Order;

public interface OrderMessagingService {
    String sendOrder(Order order); 
  }
