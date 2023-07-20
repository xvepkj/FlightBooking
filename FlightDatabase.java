import java.util.*;
import java.io.*;
import java.lang.*;
class FlightDatabase {
	Flight[] flights;
	
	FlightDatabase() {
		try {
			File f = new File("flights.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(f));
		
			ArrayList<String> fData = new ArrayList<String>();
			while (bfr.ready()) {
				fData.add(bfr.readLine());
			}
			flights = new Flight[fData.size()];
			for (int i = 0; i < fData.size(); i++)
				flights[i] = new Flight(fData.get(i));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Flight> query (String src, String des, String date) {
		ArrayList<Flight> fList = new ArrayList<>();
		for (int i = 0; i < flights.length; i++) {
			if (flights[i].getSource().equals(src) && 
				flights[i].getDestination().equals(des) &&
				flights[i].getDate().equals(date))
				fList.add(flights[i]);
		}
		return fList;
	}

	public Flight getById (int id) {
		int ix = -1;
		for (int i = 0; i < flights.length; i++) if (flights[i].getId() == id) {
			ix = i; 
			break;
		}
		if (ix == -1) return null;
		return flights[ix];
	}

}