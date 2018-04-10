package client;

public class Categories {
	
	private static InteractDatabase itd = new InteractDatabase();
	
	public static boolean categoryIsValid(String category){
		
		for(String str: itd.getCategories()){
			if(category.equals(str))
				return true;
		}
		
		return false;
	}

}
