package mw222uu_assign1.ferry;

public class Bicycle extends Vehicle
{	
	public Bicycle(int p)
	{
		super.prize = 40;
		super.among = 1;
		super.space = 1;
		super.str = "Bicycle";
		super.passenger += p;
		
		FerryClass ferry = new FerryClass();
		ferry.passengerList.add(p);
		
		if(p > among)
		{
			System.err.println("Too many passenger on the bike!");
		}
	}
	
}
