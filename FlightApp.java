import java.util.*;
import java.io.*;
import java.lang.*;
interface FlightClasses{
	int ECONOMY=1000;
	int BUSINESS=10000;
	int FIRSTCLASS=100000;
}
class FlightApp implements FlightClasses{
	FlightDatabase fdb;
	UserDatabase udb;
	User currentUser;
	Scanner scanner;

	FlightApp() {
		fdb = new FlightDatabase();
		udb = new UserDatabase();
		scanner = new Scanner(System.in);
		loginProcess();
	}

	void clearScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	void waitForResponse() {
		System.out.println("--------------------------");
		System.out.println("Press enter to continue...");
		System.out.flush();
		scanner.nextLine();
		scanner.nextLine();
	}

	void refreshCurrentUser() {
		currentUser = udb.getUser(currentUser.getUserName());
	}

	void mainMenu() {
		clearScreen();
		System.out.println("----------------------");
		System.out.println("Welcome, " + currentUser.getUserName());
		System.out.println("Options");
		System.out.println("1. Book a flight");
		System.out.println("2. Show flight history");
		System.out.println("3. Cancel flight");
		System.out.println("Any other option to exit");

		int option = scanner.nextInt();
		clearScreen();
		refreshCurrentUser();
		if (option == 1) {
			System.out.println("Enter source, destination and date in format dd/mm/yyyy");
			String src = scanner.next();
			String des = scanner.next();
			String date = scanner.next();
			ArrayList<Flight> flights = fdb.query(src, des, date);
			System.out.println("Available flights:");
			for (int i = 0; i < flights.size(); i++)
				System.out.println(flights.get(i).info());

			System.out.println("Select a flight by id (-1 to cancel):");
			int id = scanner.nextInt();
			if (id == -1) mainMenu();
			System.out.println("Enter class you want to go in");
			System.out.println("E for ECONOMY");
			System.out.println("F for FIRSTCLASS");
			System.out.println("B for BUSINESS");
			String x=scanner.next();
			if(x.charAt(0)=='E')System.out.printf("Your Price: %d",ECONOMY);
			if(x.charAt(0)=='F')System.out.printf("Your Price: %d",FIRSTCLASS);
			if(x.charAt(0)=='B')System.out.printf("Your Price: %d",BUSINESS);
			System.out.println();
			System.out.println("Enter 1 to confirm");
			int y = scanner.nextInt();
			if (y == 1) {
				udb.addFlight(currentUser, fdb.getById(id));
				System.out.println("Booked!");
			}
			System.out.println();
			waitForResponse();
			
		}
		else if (option == 2) {
			String[] fd = currentUser.getFlightData();
			for (int i = 0; i < fd.length; i++)
				System.out.println(fd[i]);
			waitForResponse();
		} 
		else if(option==3){
			String[] fd = currentUser.getFlightData();
			for (int i = 0; i < fd.length; i++)
				System.out.println(fd[i]);
			System.out.println("Enter Id of the Flight to be cancelled");
			int deleteid=scanner.nextInt();
			udb.deleteFlight(currentUser, fdb.getById(deleteid));
		}
		else {
			loginProcess();
		}
		mainMenu();
	}

	void login () {
		clearScreen();
		Console cnsl =  System.console();
		System.out.print("Enter username: ");
		String username = scanner.next();
		if (!udb.userExists(username)) {
			System.out.println("No such user exists");
			waitForResponse();
			loginProcess();
		}
		currentUser = udb.getUser(username);
		System.out.print("Enter password: ");
		String password = String.valueOf(cnsl.readPassword());

		if (!udb.authenticate(currentUser, password)) {
			currentUser = null;
			System.out.println("Incorrect password");
			waitForResponse();
			loginProcess();
		}
		System.out.println("Log in successful");
		waitForResponse();
		mainMenu();
	}

	void register() {
		clearScreen();
		System.out.print("Enter username: ");
		String username = scanner.next();
		if (udb.userExists(username)) {
			System.out.println("User already exists");
			waitForResponse();
			loginProcess();
		}
		System.out.print("Set password: ");
		String password = scanner.next();
		String[] fd = new String[0];
		udb.addUser(new User(username, password, fd));
		System.out.println("Registered!");
		waitForResponse();
		loginProcess();
	}

	void loginProcess() {
		currentUser = null;
		clearScreen();
		System.out.println("----------------------");
		System.out.println("Welcome to flight app.");
		System.out.println("Please select from the following options:");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println("3: Exit");

		int option;
		option = scanner.nextInt();

		if (option == 1) login();
		else if (option == 2) register();
		else System.exit(0);
	}
	

	public static void main(String[] args) {
		new FlightApp();
	}

}