package mw222uu_assign1.ferry;

public class Lorry extends Vehicle
{	
	public Lorry(int p)
	{
		super.prize = 300;
		super.among = 2;
		super.space = 40;
		super.str = "Lorry";
		super.costPerPassenger = p * 15;
		super.passenger += p;
		
		FerryClass ferry = new FerryClass();
		ferry.passengerList.add(p);
		
		if(p > among)
		{
			System.err.println("Too many passenger in the lorry!");
		}
	}

}
