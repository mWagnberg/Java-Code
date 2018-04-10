package client;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import client.Order.OrderStatus;

/**
 * 
 * @author lamblj
 *
 */

@Named
@RequestScoped
public class FinalizeBean implements Serializable {
	
    @Inject
    private CartBean cartBean;

	//FIELDS
	
	private static final long serialVersionUID = -1122054787870589472L;
	private InteractDatabase idb=new InteractDatabase();
	Order order=new Order(); //Order to be processed
	
	private String customerFirstName; //fields for customer info
	private String customerLastName;
	private String customerPhoneNumber;
	private String customerEmail;
	private String customerAddress;
	//METHODS
	
	/** 
	 *  Sets the fields' values of the order from user input, then adds order to the database, and gives feedback to the user
	 */
	public void newOrder()
	{
        Boolean proceed = true;
        for(Product p: cartBean.getCartProducts()){
        	if(p.getAmountBought() > Products.getProductsById(Integer.toString(p.getProductID()) , "id", true).get(0).getProductAmount()){
        		proceed = false;
        		break;
        	}
        	else
        		proceed = true;
        		p.setProductAmount(p.getProductAmount() - p.getAmountBought());
        }
        
        if(proceed){
        	for(Product p: cartBean.getCartProducts()){
        		idb.updateProduct(p);
        	}
		
			order.setCustomerFirstName(customerFirstName);
			order.setCustomerLastName(customerLastName);
			order.setCustomerPhoneNumber(customerPhoneNumber);
			order.setCustomerEmail(customerEmail);
			order.setCustomerAddress(customerAddress);
	//		order.setOrderProducts(cartProducts);
			order.setOrderStatus(OrderStatus.New);
//			idb.addOrder(order, cartBean.getCartProducts());
//			int ordernr=idb.getLatestOrderNumber();

			int ordernr = idb.addOrder(order, cartBean.getCartProducts());

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Order placed, thank you. Your order number is: " + ordernr);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else{
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Sorry, it seems as something went wrong. Please go back and try again.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        cartBean.clearCart();
	}
	
	//MUTATORS
	
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	
	
}
