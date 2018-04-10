package client;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import admin.Admin;
import client.Order.OrderStatus;

/**
 * A class used for interacting with the database
 * @author Janty Azmat
 */
public class InteractDatabase {
	// Fields
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/web_shop_db?useSSL=false";	//
	private static final String DATABASE_USERNAME = "java";													// Database credentials
	private static final String DATABASE_PASSWORD = "qazwsx";												//
	private static final String ORDERS_TABLE = "orders"; // Orders table
	private static final String PRODUCTS_TABLE = "products"; // Products table
	private static final String ADMINS_TABLE = "admins"; // Admins table
	private static final String ORDER_PRODUCTS_TABLE = "order_products"; // A table that links Orders with their productsORDERS_TABLE
	private static final String CATEGORIES_TABLE = "categories"; // Categories table

	/**
	 * Returns a Connection object to database (set it to private for now; don't forget to call '.close' method after use).
	 * @return	a Connection object to the database
	 */
	private Connection getConnection() {
		Connection outConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			outConn = DriverManager.getConnection(CONNECTION_STRING, DATABASE_USERNAME, DATABASE_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Error: Cannot connect to MySQL server.");
			//e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("Error: Cannot create MySQL connection.");
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Error: Cannot access MySQL connection.");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: Cannot find MySQL connection drivers.");
			//e.printStackTrace();
		}

		return outConn;
	}

	/**
	 * Returns a list of Product categories for the web-shop.
	 * @return	a list of Product categories for the web-shop
	 */
	public List<String> getCategories() {
		ArrayList<String> outList = new ArrayList<String>();
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + CATEGORIES_TABLE + ";");
			tmpResult = tmpStatement.executeQuery();
			while (tmpResult.next()) // A loop to fill the fields from the results of the query
				outList.add(tmpResult.getString(1));
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outList;
	}

	/**
	 * Adds a new category to the list of Product categories for the web-shop.
	 * @param theCategory	the new category to add
	 */
	public void addCategory(String theCategory) {
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("INSERT INTO " + CATEGORIES_TABLE + "(name) VALUES ('" + theCategory + "');");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Deletes a certain category from the list of Product categories for the web-shop.
	 * @param theCategory	the category to be deleted
	 */
	public void deleteCategory(String theCategory) {
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("DELETE FROM " + CATEGORIES_TABLE + " WHERE name='" + theCategory + "';");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) // To check if the exception is due to Foreign-Key constrint
				System.out.println("Safe Exception: This is an intentionally raised exception, to be sure that the Foreign-Key constrint works."); // TODO
			else
				System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the actual updating of an Order in database.
	 * @param updatedOrder	the Product to be updated
	 */
	public void updateOrder(Order updatedOrder) {
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("UPDATE " + ORDERS_TABLE + " SET customer_first_name='" 
					+ updatedOrder.getCustomerFirstName() + "',customer_last_name='" + updatedOrder.getCustomerLastName()
					+ "',customer_phone_number='" + updatedOrder.getCustomerPhoneNumber() + "',customer_email='" + updatedOrder.getCustomerEmail()
					+ "',customer_address='" + updatedOrder.getCustomerAddress() + "',order_date=order_date,order_status='"
					+ updatedOrder.getOrderStatus().toString() + "' WHERE order_number=" + updatedOrder.getOrderNumber() + ";");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the actual updating of a Product in database.
	 * @param updatedProduct	the Product to be updated
	 */
	public void updateProduct(Product updatedProduct) {
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("UPDATE " + PRODUCTS_TABLE + " SET name='" 
					+ updatedProduct.getProductName() + "',image='" + updatedProduct.getProductImageAsBase64()
					+ "',price='" + updatedProduct.getProductPrice() + "',category='" + updatedProduct.getProductCategory()
					+ "',description='" + updatedProduct.getProductDescription() + "',long_description='" + updatedProduct.getProductLongDescription().replaceAll("'", "''")
					+ "',amount='" + updatedProduct.getProductAmount() + "' WHERE id=" + updatedProduct.getProductID() + ";");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the actual updating of an Admin in database.
	 * @param updatedAdmin	the Admin to be updated
	 */
	public void updateAdmin(Admin updatedAdmin) {
		if (updatedAdmin.getPassword().equals("XXXXXXXXXXXXXXXXXXXX"))
			return; // Change nothing (this condition happens when hitting save on unchanged password)
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("UPDATE " + ADMINS_TABLE
					+ " SET password='" + this.getMD5String(updatedAdmin.getPassword()) + "' WHERE user_name='" + updatedAdmin.getUserName() + "';");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		updatedAdmin.setPassword("XXXXXXXXXXXXXXXXXXXX"); // Return everything to where it was
	}

	/**
	 * Performs the actual deleting of an Order from database.
	 * @param theOrder	the Order to be deleted
	 */
	public void deleteOrder(Order theOrder) {
		PreparedStatement tmpStatement = null;
		PreparedStatement tmpProdStatement = null;
		try {
			tmpProdStatement = this.getConnection().prepareStatement("DELETE FROM " + ORDER_PRODUCTS_TABLE + " WHERE order_number=" + theOrder.getOrderNumber() + ";");
			tmpProdStatement.executeUpdate(); // Execute deleting only the Order's link to its Products in ORDER_PRODUCTS_TABLE
			tmpStatement = this.getConnection().prepareStatement("DELETE FROM " + ORDERS_TABLE + " WHERE order_number=" + theOrder.getOrderNumber() + ";");
			tmpStatement.executeUpdate(); // Execute deleting only the Order
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the actual deleting of a Product from database.
	 * @param theProduct	the Product to be deleted
	 */
	public void deleteProduct(Product theProduct) {
		Statement tmpStatement = null; // I used a 'Statement' class here instead of 'PreparedStatement' coz it works better here.
		ResultSet tmpResult = null;
		ArrayList<Integer> tmpOrderIDs = new ArrayList<Integer>();
		try {
			tmpStatement = this.getConnection().createStatement();
			tmpResult = tmpStatement.executeQuery("SELECT order_number FROM " + ORDERS_TABLE + " WHERE order_status='Shipped' AND order_number IN (SELECT order_number FROM "
							+ ORDER_PRODUCTS_TABLE + " WHERE product_id=" + theProduct.getProductID() + ");"); // Get the order numbers for the orders that link to the product and that are marked as shipped
			while(tmpResult.next()) // Extract all matching order-numbers
				tmpOrderIDs.add(tmpResult.getInt(1));
			for (int tmpID : tmpOrderIDs) { // Go through every order numbers to delete anything that had to do with them so we can delete the product
				tmpStatement.executeUpdate("DELETE FROM " + ORDER_PRODUCTS_TABLE + " WHERE order_number=" + tmpID + ";");
				tmpStatement.executeUpdate("DELETE FROM " + ORDERS_TABLE + " WHERE order_number=" + tmpID + ";");
			}
			tmpStatement.executeUpdate("DELETE FROM " + PRODUCTS_TABLE + " WHERE id=" + theProduct.getProductID() + ";");
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) // To check if the exception is due to Foreign-Key constraint
				System.out.println("Safe Exception: This is an intentionally raised exception, to be sure that the Foreign-Key constrint works.");
			else
				System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpResult != null && !tmpResult.isClosed())					//
					tmpResult.close();											//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the actual deleting of an Admin from database.
	 * @param theAdmin	the Admin to be deleted
	 */
	public void deleteAdmin(Admin theAdmin) {
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("DELETE FROM " + ADMINS_TABLE + " WHERE user_name='" + theAdmin.getUserName() + "';");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the actual adding of a Product to database.
	 * @param theProduct	the Product to be added
	 */
	public void addProduct(Product theProduct) {
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement(
					"INSERT INTO " + PRODUCTS_TABLE + "(name,image,price,category,description,long_description,amount) VALUES ('" + theProduct.getProductName() + "','"
					+ theProduct.getProductImageAsBase64() + "','" + theProduct.getProductPrice() + "','" + theProduct.getProductCategory().toString()
					+ "','" + theProduct.getProductDescription() + "','" + theProduct.getProductLongDescription() + "','" + theProduct.getProductAmount() + "');");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the actual adding of an Admin to database.
	 * @param theAdmin	the Admin to be added
	 */
	public void addAdmin(Admin theAdmin) {
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("INSERT INTO " + ADMINS_TABLE + "(user_name,password) VALUES ('"
					+ theAdmin.getUserName() + "','" + this.getMD5String(theAdmin.getPassword()) + "');");
			tmpStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * Checks if the input credentials are valid login credentials.
	 * @param adminName		the user-name to check
	 * @param adminPassword	the password to check
	 * @return				true if credentials are OK.
	 */
	public boolean checkLogin(String adminName, String adminPassword) {
		boolean outValue = false;
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		try {
			tmpStatement = this.getConnection().prepareStatement( // Read only one record (we only need one coz it is unique anyway)
					"SELECT password FROM " + ADMINS_TABLE + " WHERE user_name = '" + adminName + "' LIMIT 1;");
			tmpResult = tmpStatement.executeQuery();
			if (tmpResult.next()) {															// If there exists a record with 'adminName' in it??
				outValue = this.getMD5String(adminPassword).equals(tmpResult.getString(1));	// then check if the MD5 passwords match
				//outValue = adminPassword.equals(tmpResult.getString(1));					// then check if the passwords match
			} else {				// If not...
				outValue = false;	// then fail the login
			}
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outValue;
	} // End method 'checkLogin'

	/**
	 * @return	a list of all the Orders in database
	 */
	public List<Order> getAllOrders() {
		ArrayList<Order> outList = new ArrayList<Order>();
		PreparedStatement tmpStatement = null;
		PreparedStatement tmpProdStatement = null; // To get the order's Products
		ResultSet tmpResult = null;
		ResultSet tmpProdResult = null; // To get the order's Products
		try {
			tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + ";");
			tmpResult = tmpStatement.executeQuery();
			while (tmpResult.next()) { // A loop to fill the fields from the results of the query
				Order tmpOrder = new Order();
				tmpOrder.setOrderNumber(tmpResult.getInt(1));
				tmpOrder.setCustomerFirstName(tmpResult.getString(2));
				tmpOrder.setCustomerLastName(tmpResult.getString(3));
				tmpOrder.setCustomerPhoneNumber(tmpResult.getString(4));
				tmpOrder.setCustomerEmail(tmpResult.getString(5));
				tmpOrder.setCustomerAddress(tmpResult.getString(6));
				tmpOrder.setOrderDate(tmpResult.getTimestamp(7));
				tmpOrder.setOrderStatus(OrderStatus.valueOf(tmpResult.getString(8)));
				ArrayList<Product> tmpList = new ArrayList<Product>(); // To fill it with Order's products from 'order_products' table
				tmpProdStatement = this.getConnection().prepareStatement("SELECT " + PRODUCTS_TABLE + ".*," + ORDER_PRODUCTS_TABLE + ".product_amount FROM "
						+ PRODUCTS_TABLE + "," + ORDER_PRODUCTS_TABLE + "," + ORDERS_TABLE + " WHERE " + PRODUCTS_TABLE + ".id=" + ORDER_PRODUCTS_TABLE
						+ ".product_id AND " + ORDERS_TABLE + ".order_number=" + ORDER_PRODUCTS_TABLE + ".order_number AND " + ORDER_PRODUCTS_TABLE
						+ ".order_number=" + tmpOrder.getOrderNumber() + ";"); // Used only WHERE here because my JOIN statement was a bit longer
				tmpProdResult = tmpProdStatement.executeQuery();
				while (tmpProdResult.next()) { // A loop to fill the fields of Order's Products from the results of the second query
					Product tmpProd = new Product();
					tmpProd.setProductID(tmpProdResult.getInt(1));
					tmpProd.setProductName(tmpProdResult.getString(2));
					tmpProd.setProductImageAsBase64(tmpProdResult.getString(3));
					tmpProd.setProductPrice(tmpProdResult.getBigDecimal(4));
					//tmpProd.setProductCategory(Category.valueOf(tmpProdResult.getString(5)));
					tmpProd.setProductCategory(tmpProdResult.getString(5));
					tmpProd.setProductDescription(tmpProdResult.getString(6));
					tmpProd.setProductLongDescription(tmpProdResult.getString(7));
					//tmpProd.setProductAmount(tmpProdResult.getInt(8)); // This is the amount in all the web-shop (and we want just the amount in the Order so we choose the next)
					tmpProd.setProductAmount(tmpProdResult.getInt(9)); // All the last loong SQL statement was to get this attached to product (so we'll have amount only for order)
					tmpList.add(tmpProd);
				}
				tmpOrder.setOrderProducts(tmpList); // Link Products with Order

				outList.add(tmpOrder); // Add Order to output list
			}
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())			// Closing connection
					tmpStatement.getConnection().close();																				//
				if (tmpProdStatement != null && tmpProdStatement.getConnection() != null && !tmpProdStatement.getConnection().isClosed())	// Closing connection
					tmpProdStatement.getConnection().close();																				//
				if (tmpProdResult != null && !tmpProdResult.isClosed())			//
					tmpProdResult.close();										//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpProdStatement != null && !tmpProdStatement.isClosed())	//
					tmpProdStatement.close();									//
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outList;
	} // End method 'getAllOrders'

	/**
	 * @return	a list of all the Products in database
	 */
	public List<Product> getAllProducts() {
		ArrayList<Product> outList = new ArrayList<Product>();
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + PRODUCTS_TABLE + ";");
			tmpResult = tmpStatement.executeQuery();
			while (tmpResult.next()) { // A loop to fill the fields from the results of the query
				Product tmpProd = new Product();
				tmpProd.setProductID(tmpResult.getInt(1));
				tmpProd.setProductName(tmpResult.getString(2));
				tmpProd.setProductImageAsBase64(tmpResult.getString(3));
				tmpProd.setProductPrice(tmpResult.getBigDecimal(4));
				tmpProd.setProductCategory(tmpResult.getString(5));
				tmpProd.setProductDescription(tmpResult.getString(6));
				tmpProd.setProductLongDescription(tmpResult.getString(7));
				tmpProd.setProductAmount(tmpResult.getInt(8));
				outList.add(tmpProd);
			}
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outList;
	} // End method 'getAllProducts'

	/**
	 * @return	a list of all the Admins in database
	 */
	public List<Admin> getAllAdmins() {
		ArrayList<Admin> outList = new ArrayList<Admin>();
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		try {
			tmpStatement = this.getConnection().prepareStatement("SELECT user_name FROM " + ADMINS_TABLE + ";"); // We don't want to read the MD5 password
			tmpResult = tmpStatement.executeQuery();
			while (tmpResult.next()) { // A loop to fill the fields from the results of the query
				Admin tmpAdmin = new Admin();
				tmpAdmin.setUserName(tmpResult.getString(1));
				//tmpAdmin.setPassword(tmpResult.getString(2)); // We don't want to read the MD5 password
				outList.add(tmpAdmin);
			}
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outList;
	} // End method 'getAllAdmins'

	/**
	 * Searches for a list of Product based on the column to base the search on and the value to search for.
	 * @param searchByColumn	the column to base the search on
	 * @param valueToSearch		the value to search for
	 * @return					a list of Products as a search result based on parameters
	 */
	public List<Order> searchOrders(String searchByColumn, String valueToSearch) {
		ArrayList<Order> outList = new ArrayList<Order>();
		PreparedStatement tmpStatement = null;
		PreparedStatement tmpProdStatement = null; // To get the order's Products
		ResultSet tmpResult = null;
		ResultSet tmpProdResult = null; // To get the order's Products
		try {
			switch (searchByColumn) {
				case "order_number": // In case we want to search by Order-Number.
					int tmpInt = -1; // A variable to save the Order-Number as integer into (to be sure that it is an integer)
					try {
						tmpInt = Integer.parseInt(valueToSearch);	// In case the 'valueToSearch' field is not an integer an error will be raised where
					} catch (NumberFormatException e) {				// we'll assign -1 (which later will be translated as 'get all records in table')
						tmpInt = -1;
					}
					if (tmpInt < 1) // Which translates as "valueToSearch is not integer", so we'll return all records
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + ";");
					else // In case 'valueToSearch' is integer then return the record with that ID
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + " WHERE order_number=" + tmpInt + ";");
					break;
				case "customer_first_name":
					if (valueToSearch == null || valueToSearch.isEmpty() || valueToSearch.equals(" ")) // If 'valueToSearch' is not accepted as a search term then return all records
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + ";");
					else // Otherwise, return all records that have 'valueToSearch' in their 'name field 
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + " WHERE customer_first_name LIKE '%" + valueToSearch + "%';");
					break;
				case "customer_last_name":
					if (valueToSearch == null || valueToSearch.isEmpty() || valueToSearch.equals(" ")) // If 'valueToSearch' is not accepted as a search term then return all records
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + ";");
					else // Otherwise, return all records that have 'valueToSearch' in their 'name field 
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + " WHERE customer_last_name LIKE '%" + valueToSearch + "%';");
					break;
				case "customer_phone_number":
					if (valueToSearch == null || valueToSearch.isEmpty() || valueToSearch.equals(" ")) // If 'valueToSearch' is not accepted as a search term then return all records
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + ";");
					else // Otherwise, return all records that have 'valueToSearch' in their 'name field 
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + " WHERE customer_phone_number LIKE '%" + valueToSearch + "%';");
					break;
				case "customer_email":
					if (valueToSearch == null || valueToSearch.isEmpty() || valueToSearch.equals(" ") || valueToSearch.equals("@")) // If 'valueToSearch' is not accepted as a search term then return all records
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + ";");
					else // Otherwise, return all records that have 'valueToSearch' in their 'name field 
						tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + " WHERE customer_email LIKE '%" + valueToSearch + "%';");
					break;
				case "order_status":
					tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + " WHERE order_status='" + valueToSearch + "';"); // This will not return on false category
					break;
				default: // In case some miss-choosing (or some error) happened then return all records
					tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ORDERS_TABLE + ";");
			}
			tmpResult = tmpStatement.executeQuery();
			while (tmpResult.next()) { // A loop to fill the fields from the results of the query
				Order tmpOrder = new Order();
				tmpOrder.setOrderNumber(tmpResult.getInt(1));
				tmpOrder.setCustomerFirstName(tmpResult.getString(2));
				tmpOrder.setCustomerLastName(tmpResult.getString(3));
				tmpOrder.setCustomerPhoneNumber(tmpResult.getString(4));
				tmpOrder.setCustomerEmail(tmpResult.getString(5));
				tmpOrder.setCustomerAddress(tmpResult.getString(6));
				tmpOrder.setOrderDate(tmpResult.getTimestamp(7));
				tmpOrder.setOrderStatus(OrderStatus.valueOf(tmpResult.getString(8)));
				ArrayList<Product> tmpList = new ArrayList<Product>(); // To fill it with Order's products from 'order_products' table
				tmpProdStatement = this.getConnection().prepareStatement("SELECT " + PRODUCTS_TABLE + ".*," + ORDER_PRODUCTS_TABLE + ".product_amount FROM "
						+ PRODUCTS_TABLE + "," + ORDER_PRODUCTS_TABLE + "," + ORDERS_TABLE + " WHERE " + PRODUCTS_TABLE + ".id=" + ORDER_PRODUCTS_TABLE
						+ ".product_id AND " + ORDERS_TABLE + ".order_number=" + ORDER_PRODUCTS_TABLE + ".order_number AND " + ORDER_PRODUCTS_TABLE
						+ ".order_number=" + tmpOrder.getOrderNumber() + ";"); // Used only WHERE here because my JOIN statement was a bit longer
				tmpProdResult = tmpProdStatement.executeQuery();
				while (tmpProdResult.next()) { // A loop to fill the fields of Order's Products from the results of the second query
					Product tmpProd = new Product();
					tmpProd.setProductID(tmpProdResult.getInt(1));
					tmpProd.setProductName(tmpProdResult.getString(2));
					tmpProd.setProductImageAsBase64(tmpProdResult.getString(3));
					tmpProd.setProductPrice(tmpProdResult.getBigDecimal(4));
					//tmpProd.setProductCategory(Category.valueOf(tmpProdResult.getString(5)));
					tmpProd.setProductCategory(tmpProdResult.getString(5));
					tmpProd.setProductDescription(tmpProdResult.getString(6));
					tmpProd.setProductLongDescription(tmpProdResult.getString(7));
					//tmpProd.setProductAmount(tmpProdResult.getInt(8)); // This is the amount in all the web-shop (and we want just the amount in the Order so we choose the next)
					tmpProd.setProductAmount(tmpProdResult.getInt(9)); // All the last loong SQL statement was to get this attached to product (so we'll have amount only for order)
					tmpList.add(tmpProd);
				}
				tmpOrder.setOrderProducts(tmpList); // Link Products with Order

				outList.add(tmpOrder); // Add Order to output list
			}
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpProdStatement != null && tmpProdStatement.getConnection() != null && !tmpProdStatement.getConnection().isClosed())	// Closing connection
					tmpProdStatement.getConnection().close();																				//
				if (tmpProdResult != null && !tmpProdResult.isClosed())			//
					tmpProdResult.close();										//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpProdStatement != null && !tmpProdStatement.isClosed())	//
					tmpProdStatement.close();									//
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outList;
	} // End method 'searchOrders'

	/**
	 * (Remark: method parameter list was changed from original as new approach by Thord Moller)
	 * Searches for a list of Product based on the column to base the search on and the value to search for.
	 * @param addSQL	an addition to SQL statement to search based on a column
	 * @return			a list of Products as a search result based on parameters
	 */
	public List<Product> searchProducts(String addSQL){
		ArrayList<Product> outList = new ArrayList<Product>();
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		
		String string = "SELECT * FROM " + PRODUCTS_TABLE;
					
			string = string + " " + addSQL + ";";
			
			System.out.println(string);
			
			try {
				tmpStatement = getConnection().prepareStatement(string);
				
				tmpResult = tmpStatement.executeQuery();
				while (tmpResult.next()) { // A loop to fill the fields from the results of the query
					Product tmpProd = new Product();
					tmpProd.setProductID(tmpResult.getInt(1));
					tmpProd.setProductName(tmpResult.getString(2));
					tmpProd.setProductImageAsBase64(tmpResult.getString(3));
					tmpProd.setProductPrice(tmpResult.getBigDecimal(4));
					tmpProd.setProductCategory(tmpResult.getString(5));
					tmpProd.setProductDescription(tmpResult.getString(6));
					tmpProd.setProductLongDescription(tmpResult.getString(7));
					tmpProd.setProductAmount(tmpResult.getInt(8));
					outList.add(tmpProd);
				}
			} catch (SQLException e) {
				System.out.println("Error: While creating or excuting the SQL statement.");
				//e.printStackTrace();
			} finally {
				try {
					if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
						tmpStatement.getConnection().close();																		//
					if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
						tmpResult.close();											// So, will try closing everything and see if it helps.
					if (tmpStatement != null && !tmpStatement.isClosed())			//
						tmpStatement.close();										//
				} catch (SQLException e) {
					System.out.println("Error: Could not close the connection properly.");
					//e.printStackTrace();
				}
			}
			return(outList);
	}

	/**
	 * Searches for a list of Admins based on part of the Admin's name.
	 * @param partOfAdminName	part of the Admin's name to fearch for
	 * @return					a list of Admins as a search result based on parameters
	 */
	public List<Admin> searchAdmins(String partOfAdminName) {
		ArrayList<Admin> outList = new ArrayList<Admin>();
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		try {
			if (partOfAdminName == null || partOfAdminName.isEmpty() || partOfAdminName.equals(" "))
				tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ADMINS_TABLE + ";");
			else
				tmpStatement = this.getConnection().prepareStatement("SELECT * FROM " + ADMINS_TABLE + " WHERE user_name LIKE '%" + partOfAdminName + "%';");
			tmpResult = tmpStatement.executeQuery();
			while (tmpResult.next()) { // A loop to fill the fields from the results of the query
				Admin tmpAdmin = new Admin();
				tmpAdmin.setUserName(tmpResult.getString(1));
				tmpAdmin.setPassword(tmpResult.getString(2));
				outList.add(tmpAdmin);
			}
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outList;
	}


	public void removeAllProducts() {
	}

	/**
	 * Adds a new order to the database (Chamged from original).
	 * @param theOrder	the order to be registered in the database
	 * @param products	the list of products that this order contains
	 * @return			the 'order_number' field value for this order in the database
	 */
	public int addOrder(Order theOrder, List<Product> products) {

		int outOrderNumber = this.getNextOrderNumber();
		String str = "INSERT INTO orders" + 
				"(order_number,customer_first_name,customer_last_name,customer_phone_number,customer_email,customer_address,order_status) VALUES" + "('" + 
				outOrderNumber + "','" + theOrder.getCustomerFirstName() + "','" + theOrder.getCustomerLastName()+ "','" + theOrder.getCustomerPhoneNumber() + "','" + 
				theOrder.getCustomerEmail() + "','" + theOrder.getCustomerAddress() + "','" + theOrder.getOrderStatus().toString() + "');";

		executeSQL(str);

		str = "";

		for(Product p: products){
			str = "INSERT INTO `web_shop_db`.`order_products` (`" +
			"order_number`, `product_id`, `product_amount`) VALUES " + "('" +
			outOrderNumber + "','" + p.getProductID() + "','" + p.getAmountBought() + "');\n";
			
			executeSQL(str);
			
			System.out.println(str);
		}

		System.out.println(str);
		return outOrderNumber;
	}
	
	private void executeSQL(String sql){
		
		PreparedStatement tmpStatement = null;
		try {
			tmpStatement = this.getConnection().prepareStatement(sql);
			tmpStatement.executeUpdate();
			System.out.println("Done.");
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
	}
	/**
	 * @return the 'order_number' of the next entry in the orders table
	 */
	private int getNextOrderNumber()
	{
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		int outNumber = 0;
		try {
			tmpStatement = this.getConnection().prepareStatement("SELECT set_random_order_number();");
			tmpResult = tmpStatement.executeQuery();
			if (tmpResult.next())
				outNumber=tmpResult.getInt(1);
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpStatement != null && !tmpStatement.isClosed())			// Got some heaviness while interacting with database.
					tmpStatement.close();										// So, will try closing everything and see if it helps.
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outNumber;
	}
	
	public List<Product> getNewestProducts() {
		ArrayList<Product> outList = new ArrayList<>();
		PreparedStatement tmpStatement = null;
		ResultSet tmpResult = null;
		
		
		try {
			tmpStatement = this.getConnection().prepareStatement("SELECT * FROM products ORDER BY id DESC LIMIT 5;");
			tmpResult = tmpStatement.executeQuery();
			while (tmpResult.next()) { // A loop to fill the fields from the results of the query
				Product tmpProd = new Product();
				tmpProd.setProductID(tmpResult.getInt(1));
				tmpProd.setProductName(tmpResult.getString(2));
				tmpProd.setProductImageAsBase64(tmpResult.getString(3));
				tmpProd.setProductPrice(tmpResult.getBigDecimal(4));
				//tmpProd.setProductCategory(Category.valueOf(tmpResult.getString(5)));
				tmpProd.setProductCategory(tmpResult.getString(5));
				tmpProd.setProductDescription(tmpResult.getString(6));
				tmpProd.setProductLongDescription(tmpResult.getString(7));
				tmpProd.setProductAmount(tmpResult.getInt(8));
				outList.add(tmpProd);
			}
		} catch (SQLException e) {
			System.out.println("Error: While creating or excuting the SQL statement.");
			//e.printStackTrace();
		} finally {
			try {
				if (tmpStatement != null && tmpStatement.getConnection() != null && !tmpStatement.getConnection().isClosed())	// Closing connection
					tmpStatement.getConnection().close();																		//
				if (tmpResult != null && !tmpResult.isClosed())					// Got some heaviness while interacting with database.
					tmpResult.close();											// So, will try closing everything and see if it helps.
				if (tmpStatement != null && !tmpStatement.isClosed())			//
					tmpStatement.close();										//
			} catch (SQLException e) {
				System.out.println("Error: Could not close the connection properly.");
				//e.printStackTrace();
			}
		}
		return outList;
	}

	/**
	 * Returns an MD5 representation of a String
	 * @param theOrig	the original String to be converted to MD5
	 * @return			an MD5 representation of a String
	 */
	private String getMD5String(String theOrig) {
		String outMD5 = null;
		try {
			outMD5 = (new BigInteger(1, MessageDigest.getInstance("MD5").digest(theOrig.getBytes()))).toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: Could not convert to MD5.");
			//e.printStackTrace();
		}
		return outMD5;
	}

}
