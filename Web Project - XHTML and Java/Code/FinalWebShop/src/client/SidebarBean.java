package client;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * This class uses the database class and generates category links after the categories in the database
 * @author Thord Möller
 */

@Named
@SessionScoped
public class SidebarBean implements Serializable{
	
	private static final long serialVersionUID = 6656851676372708851L;
	
	
	public String generateLinks(){
		String string = "";
		
		InteractDatabase idb = new InteractDatabase();
		
		for(String str: idb.getCategories()){
			string = string + "<li><a href='products.xhtml?category=" + str + "'><span>" + str.toString() + "</span></a></li>\n";
		}
		
		return(string);
	}

}
