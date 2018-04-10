package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * This class handles the product and category display.
 * @author Thord Möller
 */

@Named
@SessionScoped

public class ProductsBean implements Serializable{

	private static final long serialVersionUID = -6861875777997608017L;
	
	private String category;
	private String categoryInUse;
	private String sort ="";
	//private int display;
	//private int page;
	private String descendingOrder = "";
	private List<Product> products;
	private String heading = "";
	private String paragraph = "";
	private boolean needUpdate = true;
	
	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	
	public String getDescendingOrder() {
		return descendingOrder;
	}

	public void setDescendingOrder(String descendingOrder) {
		this.descendingOrder = descendingOrder;
	}

	/**
	 * @return the needUpdate
	 */
	public boolean getNeedUpdate() {
		return needUpdate;
	}

	/**
	 * @param needUpdate the needUpdate to set
	 */
	public void setNeedUpdate(boolean needUpdate) {
		this.needUpdate = needUpdate;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}
	
	public String getCategoryInUse() {
		return categoryInUse;
	}

	public void setCategoryInUse(String categoryInUse) {
		this.categoryInUse = categoryInUse;
	}

	public void onLoad(){
		
		setHeading(category);
		setCategoryInUse(category);
		
		if(getCategory() == (null) || getCategory().equals("")){
			setProducts(new ArrayList<Product>(0));
			setHeading("\"\"");
		}
		
		else if(category.equals("All")){
			if(!sort.equals("")){
				if(descendingOrder.equals("1"))
					setProducts(Products.getAllProductsSorted(sort, true));
				else
					setProducts(Products.getAllProductsSorted(sort, false));
			}
			else
				setProducts(Products.getAllProducts());
			
			setCategory(null);
		}
		
		else if(Categories.categoryIsValid(category)){
			if(!sort.equals("")){
				if(descendingOrder.equals("1"))
					setProducts(Products.getSortedProductsByColumn("category", category, sort, true));
				else
					setProducts(Products.getSortedProductsByColumn("category", category, sort, false));
			}
			else
				setProducts(Products.getProductsByColumn("category", category));
			
			setCategory(null);
		}
		
		else if(!category.equals("")){
			if(descendingOrder.equals("1"))
				setProducts(Products.getProductsByKeyword(category, sort, true));
			else{
				setProducts(Products.getProductsByKeyword(category, sort, false));
			}
			setHeading("\"" + category + "\"");
		}
		
		setParagraph("<p>" + products.size() + " results.</p>");
	}
	
	public String getAscendingLink(){
		
		if(descendingOrder.equals("1")){
			return "0";
		}
		else{
			return "1";
		}
	}
	
	public boolean highlight(boolean highlight){
		if(highlight == true)
			return false;
		else return true;
	}
	
	public String redirect(){
		return "products.xhtml" + "?faces-redirect=true&category=" + category;
	}

	/*public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	*/

}
