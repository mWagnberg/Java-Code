package mw222uu_assign1.ferry;

public class Car extends Vehicle
{	
	public Car(int p)
	{
		super.prize = 100;
		super.among = 4;
		super.space = 5;
		super.str = "Car";
		super.costPerPassenger = p * 15;
		super.passenger += p;
		
		FerryClass ferry = new FerryClass();
		ferry.passengerList.add(p);
		
		if(p > among)
		{
			System.err.println("Too many passenger in the car!");
		}
	}
}
