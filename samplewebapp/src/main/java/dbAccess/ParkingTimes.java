package dbAccess;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Scanner;

public class ParkingTimes {
	static int timeToPark = 0;
	
	public static String startTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(cal.getTime());
	}
	
	public static String endTime(int minutesToPark) {
		timeToPark = minutesToPark;
		
		LocalTime lt = LocalTime.parse(startTime());
		LocalTime ltLater = null;
		String endTime = null;
		ltLater = lt.plusMinutes(minutesToPark);
		endTime = ltLater.toString();
		return endTime;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);		
		System.out.println("How many minutes do you wish to park? ");
		int min = input.nextInt();
		System.out.println("Start time: "+startTime());
		System.out.println("End Time: "+endTime(min));

	}

}