package client;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import admin.AdminBean;

@Named
@RequestScoped
public class CheckOrderBean implements Serializable{
	
	private static final long serialVersionUID = -3759509929367633312L;
	
    private AdminBean adminBean = new AdminBean();
	
	private String orderNumber = "0";
	
	private Order order;
	
	private boolean orderFound = false;
	
	public boolean isOrderFound() {
		return orderFound;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String checkOrder(){
		
		int number = 0;
		
		try{
			number = Integer.parseInt(orderNumber);
		}catch(NumberFormatException e){
			System.out.println(e);
		}
		
		for(Order o : adminBean.getTableOrders()){
			if(number == o.getOrderNumber()){
				
				setOrder(o);
				orderFound = true;
				
				return String.valueOf(o.getOrderStatus());
			}
		}
		orderFound = false;
		return "No order found";
	}
	
	public String redirect(){
		return "checkorder.xhtml" + "?faces-redirect=true&ordernumber=" + orderNumber;
	}

}
