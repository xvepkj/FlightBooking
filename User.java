import java.util.*;
import java.lang.*; 

class User{
	String username, password;
	String[] flightData;
	User (String u, String p, String[] flights) {
		username = u;
		password = p;
		flightData = flights;
	}
	public String getUserName(){
		return this.username;
	}
	public String getPass(){
		return this.password;
	}
	public String[] getFlightData() { return flightData; }
}