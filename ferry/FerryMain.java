package mw222uu_assign1.ferry;

public class FerryMain 
{
	public static void main(String[] args)
	{	
		FerryClass ferry = new FerryClass();
		
		ferry.embark(new Car(3));
		ferry.embark(new Bus(15));
		ferry.embark(new Lorry(2));
		ferry.embark(new Bicycle(1));
		
		for(int i = 0; i < 133; i++)
		{
			ferry.embark(new Passenger());
		}

		System.out.println("Money earned: " + ferry.countMoney());
		System.out.println("Used vehicle space: " + ferry.countVehicleSpace());
		System.out.println("Passenger on board: " + ferry.countPassengers());
		System.out.println(ferry.hasSpaceFor(new Car(4)));

		ferry.disembark();
		
		System.out.println(ferry.countMoney());
		System.out.println(ferry.countVehicleSpace());
		System.out.println(ferry.countPassengers());
	}
}
