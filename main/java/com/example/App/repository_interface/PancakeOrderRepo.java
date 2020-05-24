package com.example.App.repository_interface;

import com.example.App.domain.Order;

public interface PancakeOrderRepo {

	 Order save( Order  order) throws Exception; 
}
