package com.xiaojia.dao;

import java.util.List;

import com.xiaojia.domain.Order;

/**
 * 订单dao层接口
 * @author wu
 *
 */
public interface OrderDao {
	//添加订单
	void addOrder(Order order);
	//根据用户id查询所有订单
	List<Order> findOrdersByUserId(Integer id);
	//根据订单id查询该订单详情
	Order findOrderByOrderId(String orderid);
	//修改订单状态
	void changeOrderState(String orderid);
	//根据订单id批量删除
	void deleteOrderById(List<String> orderIds);

}
