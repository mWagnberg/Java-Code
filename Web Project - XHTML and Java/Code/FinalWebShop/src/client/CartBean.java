package client;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import client.Order.OrderStatus;

/**
 * @author Harrison
 */
@Named
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 6920116737366907642L;
	private List<Product> cartProducts=new ArrayList<Product>();
	private BigDecimal totalPrice;
	private int cartQuantity;
	private Order order;
	private String customerFirstName;
	private String customerLastName;
	private String customerPhoneNumber;
	private String customerEmail;
	private String customerAddress;

	
//	@PostConstruct
//	public ArrayList<Product> getProducts() {
//		return cart;
//	}
	public void addProductToCart(Product product) {
		/*Product tmpProd = new Product(); // This an the next set lines are needed to create a copy of the input to prevent quantity duplication (May make a method for that)
		tmpProd.setProductID(in.getProductID());
		tmpProd.setProductName(in.getProductName());
		tmpProd.setProductPrice(in.getProductPrice());
		tmpProd.setProductCategory(in.getProductCategory());
		tmpProd.setProductDescription(in.getProductDescription());
		tmpProd.setProductLongDescription(in.getProductLongDescription());
		tmpProd.setProductAmount(in.getProductAmount());
		tmpProd.setProductImage(in.getProductImage());
		tmpProd.setProductImageAsBase64(in.getProductImageAsBase64());
		tmpProd.setAmountBought(in.getAmountBought());

		boolean inCart = false;
		for (Product p : this.cartProducts) {
			if (p.getProductID() == tmpProd.getProductID()) {
				inCart = true;
				p.setAmountBought(p.getAmountBought() + tmpProd.getAmountBought());
			}
		}
		if (!inCart)
			this.cartProducts.add(tmpProd);
		this.cartQuantity += tmpProd.getAmountBought();
		System.out.println(tmpProd.getAmountBought() + " Product with id=\"" + tmpProd.getProductID() + "\" added to cart");
		for(Product p: cartProducts)
			System.out.println(p.getProductName());
		System.out.println(cartQuantity);
		*/
		
		boolean add = true;
		
		for(Product p: cartProducts){
			if(p.getProductID() == product.getProductID()){
				
				add = false;
				
				if(p.getAmountBought() < product.getProductAmount()){
					p.setAmountBought(p.getAmountBought() + 1);
					System.out.println(p.getAmountBought());
				}
			}
		}
		
		if(add){
			cartProducts.add(product);
			System.out.println(product.getAmountBought() + " Product with id=\"" + product.getProductID() + "\" added to cart");
		}
		
		getTotalPrice();
		System.out.println(getTotalPrice());
		
		
	}
	
	public void controlMaxAmount(Product product){
		for(Product p: cartProducts){
			if(p.getProductID() == product.getProductID())
				if(p.getAmountBought() > product.getProductAmount())
					p.setAmountBought(p.getProductAmount());
		}
	}
	
	public boolean fullAmountInCart(Product product){
		for(Product p: cartProducts){
			if(p.getProductID() == product.getProductID()){
				if(p.getAmountBought() >= product.getProductAmount())
					return true;
			}
		}
		
		return false;
	}
	
	public void updateCart(){
		getCartQuantity();
		getTotalPrice();
		System.out.println("hej");
	}
	
	public int getCartQuantity(){
		cartQuantity = 0;
		
		for(Product p: cartProducts){
			cartQuantity = cartQuantity + p.getAmountBought();
		}
		
		return cartQuantity;
	}
	
	public void newOrder()
	{
		order=new Order();
		order.setCustomerFirstName(customerFirstName);
		order.setCustomerLastName(customerLastName);
		order.setCustomerPhoneNumber(customerPhoneNumber);
		order.setCustomerEmail(customerEmail);
		order.setCustomerAddress(customerAddress);
		order.setOrderProducts(cartProducts);
		Date now=new Date();
		order.setOrderDate(now);
		order.setOrderStatus(OrderStatus.New);
	}

	
//	public void setCartQuantity(int cartQuantity) {
//		this.cartQuantity = cartQuantity;
//	}
	public void removeFromCart(int productId){
		for(Product x:cartProducts){
			if(x.getProductID()==productId) {
				//this.cartQuantity -= x.getAmountBought();
				cartProducts.remove(x);
				return;
			}
		}
	}
	
//	public boolean isInCart(int id){
//		
//		for(Product p: cartProducts){
//			if(id == p.getProductID())
//				return true;
//		}
//		
//		return false;
//	}

	public void showAddedMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage("The product was added to cart.."));
	}
	
	public List<Product> getCartProducts() {
		//return cartProducts;
		return cartProducts;
	}

	public BigDecimal getTotalPrice() {
		
		totalPrice = BigDecimal.ZERO;
		BigDecimal itemCost = new BigDecimal(0);
		
		for(Product p: cartProducts){
			itemCost  = p.getProductPrice().multiply(new BigDecimal(p.getAmountBought()));
        	totalPrice = totalPrice.add(itemCost);
		}
		
		return totalPrice;
	}
	
	public void clearCart(){
		cartProducts.clear();
		cartQuantity = 0;
	}
	
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
