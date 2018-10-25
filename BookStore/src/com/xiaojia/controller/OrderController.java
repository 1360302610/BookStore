package com.xiaojia.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaojia.domain.Book;
import com.xiaojia.domain.Order;
import com.xiaojia.domain.OrderItem;
import com.xiaojia.domain.User;
import com.xiaojia.service.OrderService;
import com.xiaojia.utils.PaymentUtils;
/**
 * 订单web层
 * @author wu
 *
 */
@Controller
public class OrderController {
		//依赖注入
		@Autowired
		private OrderService orderService;
		@Autowired
		private HttpServletRequest request;
		//提交订单
		@RequestMapping("/submitOrder")
		public String submitOrder(String receiverAddress,String receiverName,String receiverPhone,
				Double money,HttpServletRequest request){
			
			//封装订单对象
			Order order=new Order();
			order.setId(UUID.randomUUID().toString());
			order.setUser((User)request.getSession().getAttribute("user"));
			order.setReceiverAddress(receiverAddress);
			order.setReceiverName(receiverName);
			order.setReceiverPhone(receiverPhone);
			order.setMoney(money);
			order.setOrdertime(new Date());
			order.setPaystate(0);
			//封装订单项，从购物车里面获取,购物车存在session里面
			Map<Book,String> map=(Map<Book, String>) request.getSession().getAttribute("cart");
			List<OrderItem> orderItems=new ArrayList<OrderItem>();
			OrderItem orderItem=null;
			for(Book b:map.keySet()){
				orderItem=new OrderItem();
				orderItem.setBook(b);
				orderItem.setBuynum(Integer.valueOf(map.get(b)));
				orderItem.setOrder(order);
				
				//添加到集合
				orderItems.add(orderItem);
			}
			order.setOrderItems(orderItems);
			//订单id
			request.setAttribute("orderid", order.getId());
			//调用业务逻辑方法
			this.orderService.submitOrder(order);
			return "user/pay";
		}
		
		//查询我的订单
		@RequestMapping("/showMyOrders")
		public String showMyOrders(Integer id,HttpServletRequest request){
			if(id==null){
				request.setAttribute("loginMsg","请先登录");
				return "forward:/showLoginUI";
			}
			List<Order> orders=this.orderService.showMyOrders(id);
			request.setAttribute("orders",orders);
			return "user/orderlist";
		}
		//显示订单详情
		@RequestMapping("/showOrderInfo")
		public String showOrderInfo(@RequestParam(value="orderid") String orderid,HttpServletRequest request){
			Order order=this.orderService.findOrderByOrderId(orderid);
			request.setAttribute("order", order);
			return "user/orderInfo";
		}
		
		//显示订单支付页面
		@RequestMapping("/showPayUI")
		public String showPayUI( String orderid, String money){
			request.setAttribute("orderid", orderid);
			request.setAttribute("money", money);
			return "user/pay";
		}
		
		//支付     提交信息到第三方
		@RequestMapping("/payOnline")
		public String payOnline(String orderid,String money,String yh){
			//订单id  支付金额  银行类型
			String p0_Cmd ="Buy";
			String p1_MerId ="10001126856";
			String p2_Order =orderid;
			String p3_Amt =money;
			String p4_Cur = "CNY";
			String p5_Pid ="unknow";
			String p6_Pcat ="unknow";
			String p7_Pdesc ="unknow";
			String p8_Url ="http://localhost:8080"+request.getContextPath()+"/payResponse";
			String p9_SAF ="1";
			String pa_MP ="unknow";
			String pr_NeedResponse ="1";
			String  hmac = PaymentUtils.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, yh, pr_NeedResponse, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
		
			request.setAttribute("pd_FrpId", yh);
			request.setAttribute("p0_Cmd", p0_Cmd);
			request.setAttribute("p1_MerId", p1_MerId);
			request.setAttribute("p2_Order", p2_Order);
			request.setAttribute("p3_Amt", p3_Amt);
			request.setAttribute("p4_Cur", p4_Cur);
			request.setAttribute("p5_Pid", p5_Pid);
			request.setAttribute("p6_Pcat", p6_Pcat);
			request.setAttribute("p7_Pdesc", p7_Pdesc);
			request.setAttribute("p8_Url", p8_Url);
			request.setAttribute("p9_SAF", p9_SAF);
			request.setAttribute("pa_MP", pa_MP);
			request.setAttribute("pr_NeedResponse", pr_NeedResponse);
			request.setAttribute("hmac", hmac);
			
			//跳转到该银行支付页面
			return "user/confirm";
		}
		
		//支付成功或失败返回的信息
		@RequestMapping("/payResponse")
		public String payResponse(HttpServletResponse response) throws IOException{
			PrintWriter out = response.getWriter();
			String p1_MerId = request.getParameter("p1_MerId");
			String r0_Cmd = request.getParameter("r0_Cmd");
			String r1_Code = request.getParameter("r1_Code");//支付结果。1代表成功
			String r2_TrxId = request.getParameter("r2_TrxId");
			String r3_Amt = request.getParameter("r3_Amt");
			String r4_Cur= request.getParameter("r4_Cur");
			String r5_Pid= request.getParameter("r5_Pid");
			String r6_Order = request.getParameter("r6_Order");//订单编号
			String r7_Uid = request.getParameter("r7_Uid");
			String r8_MP = request.getParameter("r8_MP");
			String r9_BType = request.getParameter("r9_BType");//1浏览器访问的。2点对点
			String hmac = request.getParameter("hmac");
			
			//数据校验
			boolean ok = PaymentUtils.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
			if(!ok){
				out.write("数据有可能被篡改，请联系网站");
			}else{
				if("1".equals(r1_Code)){
					//支付成功：根据订单号更改订单状态。  点卡或充值时注意表单的重复提交问题。
					if("2".equals(r9_BType)){
						out.write("success");
					}
					out.write("支付成功");
					//修改订单状态
					this.orderService.changeOrderState(r6_Order);
					request.setAttribute("message","支付成功");
					return "user/payresult";
				}
			}
			request.setAttribute("message","支付失败");
			return "user/payresult";
		}
		
}
