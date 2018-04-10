package mw222uu_assign1.ferry;

public class Bus extends Vehicle
{
	public Bus(int p)
	{
		super.prize = 200;
		super.among = 20;
		super.space = 20;
		super.str = "Bus";
		super.costPerPassenger = p * 10;
		super.passenger += p;

		FerryClass ferry = new FerryClass();
		ferry.passengerList.add(p);
		
		if(p > among)
		{
			System.err.println("Too many passenger in the bus!");
		}
	}

}
