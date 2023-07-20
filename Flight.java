import java.util.*;
import java.io.*;
import java.lang.*;

class Flight {
	int id;
	String src, des, date,airline;
	Flight (String data) {
		String s[] = data.split("\\s+");
		id = Integer.parseInt(s[0]);
		src = s[1];
		des = s[2];
		date = s[3];
		airline = s[4];
	}

	public String getSource () { return src; }
	public String getDestination () { return des; }
	public String getDate() { return date; }
	public int getId() { return id; }
	public String info() {
		return "Flight " + id + ": " + src + " to " + des + " on " + date + " with "+airline;
	}
}