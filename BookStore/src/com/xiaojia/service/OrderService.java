package com.xiaojia.service;

import java.util.List;

import com.xiaojia.domain.Order;

/**
 * 订单service层接口
 * @author wu
 *
 */
public interface OrderService {

	/**
	 * 提交订单
	 * @param order
	 */
	void submitOrder(Order order);

	/**
	 * 根据用户id查询我的订单
	 * @param id
	 * @return
	 */
	List<Order> showMyOrders(Integer id);

	/**
	 * 根据订单编号显示订单详情
	 * @param orderid
	 * @return
	 */
	Order findOrderByOrderId(String orderid);

	void changeOrderState(String orderid);
}
