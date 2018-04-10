package client;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.Part;



/**
 * A class that represents a product in the web-shop
 * @author Janty Azmat
 */
public class Product {

//	/**
//	 * An inside enum that represents a category that a Product belongs to. (made it inside to make it easier to link both)
//	 * @author Janty Azmat
//	 */
//	public static enum Category {
//		HighEnd, Multimedia, WorkStation, Budget, Hybrid
//	}


	// Fields
	private int productID;												//
	private String productName;											//
	private BigDecimal productPrice;									//
	private String productCategory = Product.getCategories().get(0);	// The Product's main properties
	private String productDescription;									//
	private String productLongDescription;								//
	private int productAmount;											//
	private Part productImage;											//
	private String productImageBase64; // A base64 representation of the Product's image (to save in database and present directly on page)
	private boolean inEdit = false; // An inside field to check if the Product is in the edit state in the admin-pages tables
	private int amountBought = 1;


	/**
	 * A static method the returns all the Product's categories
	 * @return a list of Product's categories
	 */
	public static List<String> getCategories() {
		return (new InteractDatabase()).getCategories();
	}

//	/**
//	 * A method to validate if the uploaded image for this Product object is valid in type and size
//	 */
//	public boolean validateUploadedImage() {
//		System.out.println("Entered");
//		if (productImage.getContentType() == "image/jpeg" && productImage.getSize() > 10485760) { // If JPEG and size less than 10MB
//			System.out.println("True");
//			return true;
//		} else {
//			System.out.println("False");
//			return false;
//		}
//	}

	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productPrice
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the productCategory
	 */
	public String getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the productLongDescription
	 */
	public String getProductLongDescription() {
		return productLongDescription;
	}

	/**
	 * @param productLongDescription the productLongDescription to set
	 */
	public void setProductLongDescription(String productLongDescription) {
		this.productLongDescription = productLongDescription;
	}

	/**
	 * @return the productAmount
	 */
	public int getProductAmount() {
		return productAmount;
	}

	/**
	 * @param productAmount the productAmount to set
	 */
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * @return	a base64 representation of the productImage
	 */
	public String getProductImageAsBase64() {
//		if (this.productImage == null) // Check if null
//			return "";

//		byte[] tmpByteImage = null; // To be filled with image bytes
//		try {
//			InputStream tmpStream = this.productImage.getInputStream(); // Extract the input stream
//			tmpByteImage = new byte[tmpStream.available()]; // Set a new byte array with size as the available bytes to read
//			tmpStream.read(tmpByteImage); // Fill the byte array
//		} catch (IOException e) {
//			System.out.println("Error: Exception was thrown trying to extract image bytes.");
//			//e.printStackTrace();
//		}
//		String outStr = Base64.getEncoder().encodeToString(tmpByteImage); // Convert to base64
//		return outStr;

		return this.productImageBase64;
	}

	public void setProductImageAsBase64(String theImageBase64) {
		this.productImageBase64 = theImageBase64;
	}

	/**
	 * @return	the productImage
	 */
	public Part getProductImage() {
		return this.productImage;
	}

	/**
	 * @param productImage	the productImage to set
	 */
	public void setProductImage(Part productImage) {
		if (productImage != null && productImage.getContentType().startsWith("image/") && productImage.getSize() < 10485760) {

			byte[] tmpByteImage = null; // To be filled with image bytes
			try {
				InputStream tmpStream = productImage.getInputStream(); // Extract the input stream
				tmpByteImage = new byte[tmpStream.available()]; // Set a new byte array with size as the available bytes to read
				tmpStream.read(tmpByteImage); // Fill the byte array
			} catch (IOException e) {
				System.out.println("Error: Exception was thrown trying to extract image bytes.");
				//e.printStackTrace();
			}
			this.productImageBase64 = Base64.getEncoder().encodeToString(tmpByteImage); // Convert to base64

			this.productImage = productImage;
		}
	}

	/**
	 * Check if the Product object is in the edit state in the admin-pages tables
	 * @return the inEdit
	 */
	public boolean getInEdit() {
		return inEdit;
	}

	/**
	 * Sets if the Product object is in the edit state in the admin-pages tables
	 * @param inEdit the inEdit to set
	 */
	public void setInEdit(boolean inEdit) {
		this.inEdit = inEdit;
	}

	/**
	 * @return the amountBought
	 */
	public int getAmountBought() {
		return amountBought;
	}

	/**
	 * @param amountBought the amountBought to set
	 */
	public void setAmountBought(int amountBought) {
		this.amountBought = amountBought;
	}

}
