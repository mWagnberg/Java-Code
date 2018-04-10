package mw222uu_assign1.ferry;

import java.util.ArrayList;
import java.util.Iterator;

public class FerryClass implements Ferry
{
	final int PASSENGER_MAX = 200;
	final int VEHICLE_MAX = 160;
	public int money;
	protected ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	public ArrayList<Integer> passengerList = new ArrayList<Integer>();
	
	/**
	 * This will return how many passenger thats on the ferry
	 * @return number of passenger on board
	 */
	@Override
	public int countPassengers() 
	{
		return passengerList.size();
	}

	/**
	 * This will return how many vehicles thats on the ferry
	 * @return Used vehicle space
	 */
	@Override
	public int countVehicleSpace() 
	{
		return vehicleList.size();
	}

	/**
	 * This will return how much money the ferry has bring
	 * @return earned money
	 */
	@Override
	public int countMoney() 
	{
		return money;
	}
	
	/**
	 * This will iterate over the vehicle-list
	 */
	@Override
	public Iterator<Vehicle> iterator() 
	{
		return null;
	}
	
	/**
	 * This will clear the ferry
	 */
	@Override
	public void disembark() 
	{
		vehicleList.clear();
		passengerList.clear();
	}

	/**
	 * This will check if the ferry has space for more vehicles
	 * @return true if the list has more elements, otherwise false
	 */
	@Override
	public boolean hasSpaceFor(Vehicle v) 
	{
		if(vehicleList.size() < VEHICLE_MAX)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This will check if the ferry has space for more passengers
	 * @return true if the list has more elements, otherwise false
	 */
	@Override
	public boolean hasRoomFor(Passenger p) 
	{
		if(passengerList.size() < PASSENGER_MAX)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void embark(Vehicle v)
	{	
		if(hasSpaceFor(v) == true)
		{
			money += v.prize;
			money += v.costPerPassenger;
			vehicleList.add(v);
			passengerList.add(v.passenger);
		}
		else
		{
			System.err.println("Note enough space!");
		}		
	}
	
	@Override
	public void embark(Passenger p) 
	{
		if(hasRoomFor(p))
		{
			passengerList.add(1);
		}
		else
		{
			System.err.println("Not enough room!");
		}
	}
}
