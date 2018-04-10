package client;

import java.util.List;

public class Products{
	
	//private List<Product> list;
	private static InteractDatabase id = new InteractDatabase();
	
/*
	public Products(String column, String value, String sortByColumn, boolean descending){
		searchProductsBy(column, value, sortByColumn, descending);
	}

	public Products(String column, String value){
		searchProductsBy(column, value, "", false);
	}
		

	public Products(String sortByColumn, boolean descending){
		searchProductsBy("", "", sortByColumn, descending);
	}

	public Products(){
		searchProductsBy("","","", false);
	}
	*/

	/*public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}*/
	
	public static List<Product> getAllProducts(){
		return(id.searchProducts(""));
	}
	
	/*public static List<Product> getAllProductsFromRow(int startRow, int endRow){
		return id.searchProducts(sqlRow(startRow, endRow));
	}*/
	
	public static List<Product> getAllProductsSorted(String sortBy, boolean descending){
		return id.searchProducts(sqlSort(sortBy, descending));
	}
	
	public static List<Product> getProductsByKeyword(String keyword, String sortBy, boolean descending){
		return id.searchProducts("WHERE name LIKE '" + keyword + "%' " + sqlSort(sortBy, descending));
	}
	public static List<Product> getProductsById(String keyword, String sortBy, boolean descending){
		return id.searchProducts("WHERE id regexp '[[:<:]]" + keyword + "[[:>:]]'" + sqlSort(sortBy, descending));
	}
	
	/*public static List<Product> getAllProductsSortedFromRow(String sortBy, boolean descending, int startRow, int endRow){
		return id.searchProducts(sqlSort(sortBy, descending) + sqlRow(startRow, endRow));
	}*/
	
	public static List<Product> getProductsByColumn(String searchByColumn, String valueToSearch){
		return id.searchProducts(sqlByColumn(searchByColumn, valueToSearch));
	}
	
	/*public static List<Product> getProductsByColumnFromRow(String searchByColumn, String valueToSearch, int startRow, int endRow){
		return id.searchProducts(sqlByColumn(searchByColumn, valueToSearch) + sqlRow(startRow, endRow));
	}*/
	
	public static List<Product> getSortedProductsByColumn(String searchByColumn, String valueToSearch, String sortBy, boolean descending){
		
		String addedSQL = sqlByColumn(searchByColumn, valueToSearch) + sqlSort(sortBy, descending);
			
		return(id.searchProducts(addedSQL));
	}
	
	/*public static List<Product> getSortedProductsByColumnFromRow(String searchByColumn, String valueToSearch, String sortBy, boolean descending, int startRow, int endRow){
		
		String addedSQL = sqlByColumn(searchByColumn, valueToSearch) + sqlSort(sortBy, descending) + sqlRow(startRow, endRow);
			
		return(id.searchProducts(addedSQL));
	}*/
	
	/*public static int getProductCount(List<Product> products){
		return products.size();
	}*/
	
	private static String sqlByColumn(String column, String valueToSearch){
		
		String string ="";
		
		if(column != ""){

			switch (column) {
			
				case "id": // In case we want to search by ID.
					
					int tmpInt = -1; // A variable to save the ID as integer into (to be sure that it is an integer)
					
					try {
						tmpInt = Integer.parseInt(valueToSearch);	// In case the 'valueToSearch' field is not an integer an error will be raised where
					} catch (NumberFormatException e) {					// we'll assign -1 (which later will be translated as 'get all records in table')
						tmpInt = -1;
					}
					
					// In case 'valueToSearch' is integer then return the record with that ID
					if(tmpInt > -1)
						string = string + " WHERE id=" + tmpInt;
					break;
					
				case "name":
					
					if(valueToSearch != null)
						string = string + " WHERE name LIKE '%" + valueToSearch + "%'";
					break;
					
					
				case "category":
					string = string + " WHERE category='" + valueToSearch + "'"; // This will not return on false category
					break;
			}
		}
		
		return string;
		
	}
	
	private static String sqlSort(String sortBy, boolean descending){
		
		String string = "";
		
		if(sortBy != ""){
			string = string + " ORDER BY " + sortBy;
		}
		
		if(descending == true)
			string = string + " DESC";
		
		return string;
	}
	
	
	
	/*private static String sqlRow(int startRow, int endRow){
		
		return " LIMIT " + startRow + ", " + endRow;
	}*/


}
