package com.xiaojia.dao;

import com.xiaojia.domain.Order;

public interface OrderItemDao {

	/**
	 * 添加订单项
	 * @param order
	 */
	void addOrderItem(Order order);

	/**
	 * 根据图书id删除
	 * @param id
	 */
	void deleteOrderItem(String id);

}
