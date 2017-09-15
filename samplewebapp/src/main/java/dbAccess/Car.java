package dbAccess;

import java.util.Scanner;

public class Car {
	
	private String carRegistration = null;
	
	public String registerCar(String reg) {
		carRegistration = reg;
		return carRegistration;
	}

//	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		Car obj = new Car();
//		
//		System.out.println("Enter car registration: ");
//		String rego = input.nextLine();
//		
//		System.out.println("You registered: "+obj.registerCar(rego));
//		
		
//	}

}