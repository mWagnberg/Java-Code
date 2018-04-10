package admin;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import client.InteractDatabase;
import client.Order;
import client.Order.OrderStatus;
import client.Product;
import client.Products;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A class the handles communications with admin and login online pages.
 * @author Janty Azmat
 */
@Named
@SessionScoped
public class AdminBean implements Serializable {
	// Fields
	private static final long serialVersionUID = -9036775120918746200L; // Seems to be needed by 'Serializable' interface
	private static final OrderStatus[] ORDER_STATUSES = OrderStatus.values(); // Just to pass a list of the order-statuses to the pages
	//private static final Category[] CATEGORIES = Product.Category.values(); // Just to pass a list of the categories to the pages
	private static List<String> categories = Product.getCategories(); // Just to pass a list of the categories to the pages
	private InteractDatabase interactDatabase = new InteractDatabase(); // The object to interact with database
	private String searchTextBox = ""; // A string to communicate with search text-boxes in admin-pages
	private String searchComboMenu = ""; // A string to communicate with Search menu-combo-boxes in admin-pages
	private String searchByValue = ""; // A string to communicate with Search-By menu-combo-boxes in admin-pages
	private String selectedCategory = ""; // A string to communicate with selected category text-box in admin-pages
	private Product[] productToSave = {new Product()}; // For temporarily store new Product to save
	private Admin[] adminToSave = {new Admin()}; // For temporarily store new Admin to save
	private String section = "Orders"; // Will hold the current section in admin-pages to display based on selection (starting with 'Orders')
	private List<Order> tableOrders = Arrays.asList(new Order[5]); // A list of Orders to be displayed on the orders-table (can be filled with all the Orders or with a search result)
	private List<Product> tableProducts; // A list of Products to be displayed on the data-table (can be filled with all the Products or with a search result)
	private List<Admin> tableAdmins; // A list of Admins to be displayed on the admins-table (can be filled with all the Admins or with a search result)
	private List<Product> tableNewProducts; // A list of te latest Products to be displayed on the data-table (can be filled with all the Products or with a search result)
	private boolean tableOrdersNeedUpdate = true;		//
	private boolean tableProductsNeedUpdate = true; 	// To check if the table of Orders, Products, and Admins needs updating (we need these because JSF calls the
	private boolean tableAdminsNeedUpdate = true;		// getters several times in a single view and we don't want to call the database on every getter call)
	private int stat;

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	/**
	 * Adds or deletes a category depending on 'selectedCategory' value
	 */
	public void addDeleteCategory() {
		if (this.selectedCategory == null || this.selectedCategory.isEmpty())
			return;
		if (AdminBean.categories.contains(this.selectedCategory))
			this.interactDatabase.deleteCategory(this.selectedCategory);
		else
			this.interactDatabase.addCategory(this.selectedCategory);
		this.selectedCategory = "";
	}

	/**
	 * Searches database for Orders based on this bean's and page's choices
	 */
	public void searchOrders() {
		switch (this.searchByValue) {
			case "By Order Number":
				this.tableOrders = this.interactDatabase.searchOrders("order_number", this.searchTextBox); // Forward to 'interactDatabase' object
				this.tableOrdersNeedUpdate = false; // Just in case to be sure to not update the list
				break;
			case "By Customer First Name":
				this.tableOrders = this.interactDatabase.searchOrders("customer_first_name", this.searchTextBox); // Forward
				this.tableOrdersNeedUpdate = false; // Just in case.. to be sure not to update the list where all Products will display
				break;
			case "By Customer Last Name":
				this.tableOrders = this.interactDatabase.searchOrders("customer_last_name", this.searchTextBox); // Forward
				this.tableOrdersNeedUpdate = false; // Just in case.. to be sure not to update the list where all Products will display
				break;
			case "By Customer Phone Number":
				this.tableOrders = this.interactDatabase.searchOrders("customer_phone_number", this.searchTextBox); // Forward
				this.tableOrdersNeedUpdate = false; // Just in case.. to be sure not to update the list where all Products will display
				break;
			case "By Customer Email":
				this.tableOrders = this.interactDatabase.searchOrders("customer_email", this.searchTextBox); // Forward
				this.tableOrdersNeedUpdate = false; // Just in case.. to be sure not to update the list where all Products will display
				break;
			case "By Order Status":
				this.tableOrders = this.interactDatabase.searchOrders("order_status", this.searchComboMenu); // Forward
				this.tableOrdersNeedUpdate = false; // Just in case.. to be sure not to update the list where all Products will display
				break;
			case "Display All":
				this.tableOrdersNeedUpdate = true;
				this.getTableOrders(); // Treat it as a normal get all products
		}
	}

	/**
	 * (Remark: original method was changed as new approach by Thord Moller)
	 * Searches database for Products based on this bean's and page's choices
	 */
	public void searchProducts() {
		switch (this.searchByValue) {
			case "By ID":
				this.tableProducts = Products.getProductsByColumn("id", this.searchTextBox);// Forward to 'interactDatabase' object
				this.tableProductsNeedUpdate = false; // Just in case to be sure to not update the list
				break;
			case "By Name":
				this.tableProducts = Products.getProductsByColumn("name", this.searchTextBox); // Forward
				this.tableProductsNeedUpdate = false; // Just in case.. to be sure not to update the list where all Products will display
				break;
			case "By Category":
				this.tableProducts = Products.getProductsByColumn("category", this.searchComboMenu); // Forward
				this.tableProductsNeedUpdate = false; // Just in case.. to be sure not to update the list where all Products will display
				break;
			case "Display All":
				this.tableProductsNeedUpdate = true;
				this.getTableProducts(); // Treat it as a normal get all products
		}
	}

	/**
	 * Searches database for Adminss based on this bean's and page's choices
	 */
	public void searchAdmins() {
		this.tableAdmins = this.interactDatabase.searchAdmins(this.searchTextBox);
		this.tableAdminsNeedUpdate = false; // Just in case.. to be sure not to update the list where all Admins will display
	}

	/**
	 * Initiates updating an Order in database.
	 * @param updatedOrder	the changed Order to be updated
	 */
	public void updateOrderInDatabase(Order updatedOrder) {
		this.interactDatabase.updateOrder(updatedOrder);
	}

	/**
	 * Initiates updating a Product in database.
	 * @param updatedProduct	the changed Product to be updated
	 */
	public void updateProductInDatabase(Product updatedProduct) {
		this.interactDatabase.updateProduct(updatedProduct);
	}

	/**
	 * Initiates updating an Admin in database.
	 * @param updatedAdmin	the changed Admin to be updated
	 */
	public void updateAdminInDatabase(Admin updatedAdmin) {
		this.interactDatabase.updateAdmin(updatedAdmin);
	}

	/**
	 * Initiates deleting a Product from database.
	 * @param theProduct	the Product to be deleted
	 */
	public void deleteOrderFromDatabase(Order theOrder) {
		this.interactDatabase.deleteOrder(theOrder);
		this.tableOrdersNeedUpdate = true;
	}

	/**
	 * Initiates deleting a Product from database.
	 * @param theProduct	the Product to be deleted
	 */
	public void deleteProductFromDatabase(Product theProduct) {
		this.interactDatabase.deleteProduct(theProduct);
		this.tableProductsNeedUpdate = true;
	}

	public void deleteAdminFromDatabase(Admin theAdmin) {
		this.interactDatabase.deleteAdmin(theAdmin);
		this.tableAdminsNeedUpdate = true;
	}

	/**
	 * Initiates adding a Product to database.
	 * @param theProduct	the Product to be added
	 */
	public void addProductToDatabase(Product theProduct) {
		interactDatabase.addProduct(theProduct);
		this.productToSave[0] = new Product();
	}

	/**
	 * Initiates adding an Admin to database.
	 * @param theProduct	the Product to be added
	 */
	public void addAdminToDatabase(Admin theAdmin) {
		interactDatabase.addAdmin(theAdmin);
		this.adminToSave[0] = new Admin();
	}

	/**
	 * A method that is called on admin pages' load event and when changing of sections to reset all the fields to their default values
	 */
	public void resetAdminPage() {
		this.tableOrdersNeedUpdate = true;
		this.tableProductsNeedUpdate = true;
		this.tableAdminsNeedUpdate = true;
		this.tableProducts = null;
		this.tableAdmins = null;
		this.searchTextBox = "";
		this.searchComboMenu = "";
		this.searchByValue = this.section.equals("Orders") ? "By Order Number" : "By ID";
		AdminBean.categories = this.interactDatabase.getCategories();
		this.selectedCategory = "";
	}

	/**
	 * @return the categories
	 */
	public static List<String> getCategories() {
		return categories;
	}

	/**
	 * @return the orderStatuses
	 */
	public OrderStatus[] getOrderStatuses() {
		return ORDER_STATUSES;
	}

	/**
	 * Return the section the should be displayed up to this stage.
	 * @return	the section to be displayed
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the section that should be displayed at this moment.
	 * @param section	the section to be displayed
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * Returns a list of Orders that is supposed to be displayed in a table of Orders.
	 * @return	the tableOrders that holds a list of Orders
	 */
	public List<Order> getTableOrders() {
		if (this.tableOrdersNeedUpdate) {
			this.tableOrders = this.interactDatabase.getAllOrders();
			this.tableOrdersNeedUpdate = false;
		}
		return tableOrders;
	}

//	/**
//	 * @param tableOrders the tableOrders to set
//	 */
//	public void setTableOrders(List<Order> tableOrders) {
//		this.tableOrders = tableOrders;
//	}

	/**
	 * Returns a list of Products that is supposed to be displayed in a table of Products.
	 * @return	the tableProducts that holds a list of Products
	 */
	public List<Product> getTableProducts() {
		if (this.tableProductsNeedUpdate) {
			this.tableProducts = this.interactDatabase.getAllProducts();
			this.tableProductsNeedUpdate = false;
		}
		return tableProducts;
	}

//	/**
//	 * @param tableProducts the tableProducts to set
//	 */
//	public void setTableProducts(List<Product> tableProducts) {
//		this.tableProducts = tableProducts;
//	}

	/**
	 * Returns a list of Admins that is supposed to be displayed in a table of Admins.
	 * @return	the tableAdmins that holds a list of Admin
	 */
	public List<Admin> getTableAdmins() {
		if (this.tableAdminsNeedUpdate) {
			this.tableAdmins = this.interactDatabase.getAllAdmins();
			this.tableAdminsNeedUpdate = false;
		}
		return tableAdmins;
	}

//	/**
//	 * @param tableAdmins the tableAdmins to set
//	 */
//	public void setTableAdmins(List<Admin> tableAdmins) {
//		this.tableAdmins = tableAdmins;
//	}

	/**
	 * @return the productToSave
	 */
	public Product[] getProductToSave() {
		return productToSave;
	}

//	/**
//	 * @param productToSave the productToSave to set
//	 */
//	public void setProductToSave(Product[] productToSave) {
//		this.productToSave = productToSave;
//	}

	/**
	 * @return the adminToSave
	 */
	public Admin[] getAdminToSave() {
		if (this.adminToSave[0].getPassword().equals("XXXXXXXXXXXXXXXXXXXX"))
			this.adminToSave[0].setPassword("");
		return this.adminToSave;
	}

//	/**
//	 * @param adminToSave the adminToSave to set
//	 */
//	public void setAdminToSave(Admin[] adminToSave) {
//		this.adminToSave = adminToSave;
//	}

	/**
	 * @return	the searchTextBox
	 */
	public String getSearchTextBox() {
		return searchTextBox;
	}

	/**
	 * @param searchTextBox the displayText to set
	 */
	public void setSearchTextBox(String searchTextBox) {
		this.searchTextBox = searchTextBox;
	}

	/**
	 * @return the searchComboMenu
	 */
	public String getSearchComboMenu() {
		return searchComboMenu;
	}

	/**
	 * @param searchComboMenu the displayCategory to set
	 */
	public void setSearchComboMenu(String searchComboMenu) {
		this.searchComboMenu = searchComboMenu;
	}

	/**
	 * @return the searchByValue
	 */
	public String getSearchByValue() {
		return searchByValue;
	}

	/**
	 * @param searchByValue	the displayValue to set
	 */
	public void setSearchByValue(String searchByValue) {
		this.searchByValue = searchByValue;
	}

	/**
	 * @return the selectedCategory
	 */
	public String getSelectedCategory() {
		return selectedCategory;
	}

	/**
	 * @param selectedCategory the selectedCategory to set
	 */
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	

	public List<Product> getTableNewestProducts() {

		this.tableNewProducts = this.interactDatabase.getNewestProducts();
		return tableNewProducts;
	}

}
