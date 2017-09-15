package dbAccess;

import java.util.Scanner;

public class ComputeParkingFee {

	double baseRate;
	static double totalAmount;
	static ParkingTimes obj = new ParkingTimes();
	static ComputeParkingFee obj1 = new ComputeParkingFee();
	
	public ComputeParkingFee() {
		baseRate = 1.5;
		totalAmount = 0;	
	}

	public double calculateFee() {
		int time = 120;
//		obj.timeToPark
		totalAmount = (time)/60 * baseRate;
		return totalAmount;
	}

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);		
		System.out.println("How many minutes do you wish to park? ");
		int min = input.nextInt();
		
		
		System.out.println("Start time: "+ obj.startTime());
		System.out.println("End time: "+ obj.endTime(min));
		System.out.println(obj.timeToPark);
		System.out.println("Total Amount:$ "+obj1.calculateFee());
		
	}

}
