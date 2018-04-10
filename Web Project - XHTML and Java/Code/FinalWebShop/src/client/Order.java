package client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * A class that represents an order in the web-shop
 * @author Janty Azmat
 */
public class Order {

	public static enum OrderStatus {
		New, Delayed, Shipped
	}

	// Fields
	private int orderNumber;
	private String customerFirstName;
	private String customerLastName;
	private String customerPhoneNumber;
	private String customerEmail;
	private String customerAddress;
	private Date orderDate;
	private List<Product> orderProducts;
	//private BigDecimal totalPrice;
	private OrderStatus orderStatus;
	private boolean inEdit = false; // An inside field to check if the Order is in the edit state in the order-pages tables

	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the customerFirstName
	 */
	public String getCustomerFirstName() {
		return customerFirstName;
	}

	/**
	 * @param customerFirstName the customerFirstName to set
	 */
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	/**
	 * @return the customerLastName
	 */
	public String getCustomerLastName() {
		return customerLastName;
	}

	/**
	 * @param customerLastName the customerLastName to set
	 */
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	/**
	 * @return the customerPhoneNumber
	 */
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	/**
	 * @param customerPhoneNumber the customerPhoneNumber to set
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderProducts
	 */
	public List<Product> getOrderProducts() {
		return orderProducts;
	}

	/**
	 * @param orderProducts the orderProducts to set
	 */
	public void setOrderProducts(List<Product> orderProducts) {
		this.orderProducts = orderProducts;
	}

	/**
	 * @return the totalPrice
	 */
	public BigDecimal getTotalPrice() {
		if (this.orderProducts == null || this.orderProducts.isEmpty()) // No products?? then return zero
			return BigDecimal.ZERO;

		BigDecimal outVal = BigDecimal.ZERO;
		for (Product p : this.orderProducts) // A loop to count the price of all contained products (taking their count in consideration)
			outVal = outVal.add(p.getProductPrice().multiply(new BigDecimal(p.getProductAmount())));
		outVal = outVal.setScale(2, RoundingMode.HALF_UP);
		return outVal;
	}

	/**
	 * @return the orderStatus
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the inEdit
	 */
	public boolean getInEdit() {
		return inEdit;
	}

	/**
	 * @param inEdit the inEdit to set
	 */
	public void setInEdit(boolean inEdit) {
		this.inEdit = inEdit;
	}

}
