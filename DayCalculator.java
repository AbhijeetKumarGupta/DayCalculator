import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DayCalculator {

	// Checks weather the year is leap or not
	static boolean isLeap(int year) {

		if (year % 400 == 0) {
			return true;
		}

		if (year % 100 == 0) {
			return false;
		}

		if (year % 4 == 0) {
			return true;
		}

		return false;
	}

	// Return Day
	public static String getDayName(int dayNum) {
		String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		return days[dayNum];
	}

	// Returns Month
	public static String getMonthName(int month) {
		String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		return months[month];
	}

	public static String convertDate(String date) {

		// To store date in an array of date month year as separate element
		String[] date1 = date.split("/");

		// Converting string values to integer values for calculation
		int monthInt = Integer.valueOf(date1[0]);
		int dateInt = Integer.valueOf(date1[1]);
		int yearInt = Integer.valueOf(date1[2]);

		// Variables holding current values
		int currentDay = 0;
		int currentMonth = monthInt;
		int currentYear = yearInt;

		// Array storing leading number of days values
		int lNoOfDay[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

		// Array storing valid number of days for the months
		int[] monthDaysNotLeap = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] monthDaysLeap = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		// This loop checks whether given date is valid or not
		if (monthInt > 12 || monthInt < 1) {
			return "Invalid Month!";
		} else if (dateInt > monthDaysNotLeap[monthInt - 1] || dateInt < 1) {
			if (isLeap(yearInt) == true && monthInt == 2 && dateInt == monthDaysLeap[1]) {
				// do nothing
			} else {
				return "Invalid Date!";
			}
		} else if (yearInt > 2020 || yearInt < 1800) {
			return "Invalid Year!";
		}

		yearInt -= (monthInt < 3) ? 1 : 0;

		currentDay = (yearInt + yearInt / 4 - yearInt / 100 + yearInt / 400 + lNoOfDay[monthInt - 1] + dateInt) % 7;

		if (currentDay == 0) {
			currentDay = 1;
		}

		return getDayName(currentDay - 1) + ", " + getMonthName(currentMonth - 1) + " " + currentYear;
	}

	// Driver Program to test above function 
	public static void main(String arg[]) throws IOException {
		String fName;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the name of the file with extension txt: ");
		fName = scan.nextLine();

		File file = new File(fName);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		while ((st = br.readLine()) != null) {
			System.out.println(convertDate(st));
		}
		
		br.close();
		scan.close();
	}
}
