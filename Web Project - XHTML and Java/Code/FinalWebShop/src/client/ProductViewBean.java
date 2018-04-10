package client;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ProductViewBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2747941321618929002L;
	private String id;
	private Product product;
	
	
	public void onLoad(){
		
		List<Product> products = Products.getProductsById(id, "name", false);
		
		if(!products.isEmpty()){
			setProduct(products.get(0));
		}
		
	}
	
	public String stockImgUrl(){
		if(inStock(product))
			return("instock.png");
		else return "outofstock.png";
	}
	public String stockStatus(){
		if(inStock(product))
			return "<span style=\"color:#92DD5A;font-size: 11px;\">Available</span>";
		return "<span style=\"color:#D90022; font-size: 11px;\">Unavailable</span>";
	}
	
	public boolean inStock(Product product){
		if(product.getProductAmount() > 0)
			return true;
		return false;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}

}
