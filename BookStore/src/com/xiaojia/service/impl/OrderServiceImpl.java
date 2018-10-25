package com.xiaojia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaojia.dao.BookDao;
import com.xiaojia.dao.OrderDao;
import com.xiaojia.dao.OrderItemDao;
import com.xiaojia.domain.Order;
import com.xiaojia.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	//依赖注入
	@Autowired
	private BookDao bookDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;

	//提交订单
	@Transactional
	@Override
	public void submitOrder(Order order) {
		//涉及3个表
		orderDao.addOrder(order);
		orderItemDao.addOrderItem(order);
		bookDao.updateBookNum(order);//修改库存
	}

	@Override
	public List<Order> showMyOrders(Integer id) {
		return this.orderDao.findOrdersByUserId(id);
	}

	@Override
	public Order findOrderByOrderId(String orderid) {
		return this.orderDao.findOrderByOrderId(orderid);
	}

	@Override
	public void changeOrderState(String orderid) {
		this.orderDao.changeOrderState(orderid);
	}

}
