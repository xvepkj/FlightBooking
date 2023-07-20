import java.util.*;
import java.io.*;
import java.lang.*; 
class UserDatabase{
	boolean userExists(String username){
		File x=new File("Users");
		return new File(x,username+".txt").exists();
	}
	User getUser(String username) {
		try{
		if(!userExists(username))return null;
		File dir=new File("Users");
		File f=new File(dir,username+".txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		String password=br.readLine();
		String[] flights = new String[0];
		ArrayList<String> fl = new ArrayList<>();
		while (br.ready()) {
			String str = br.readLine();
			if (str.length() != 0)
				fl.add(str);
		}
		return new User(username,password, fl.toArray(flights));
		}
		catch (Exception e) {}
		return null;
	}
	void addUser (User user) {
		try {
		File dir=new File("Users");
		File f=new File(dir,user.getUserName()+".txt");
		f.createNewFile();
		FileWriter fw1=new FileWriter(f);
		BufferedWriter bf1=new BufferedWriter(fw1);
		bf1.write(user.getPass(),0,user.getPass().length());
		bf1.newLine();
		bf1.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	void addFlight (User user, Flight fl) {
		try {
		File dir = new File ("users");
		File f = new File (dir, user.getUserName() + ".txt");
		BufferedWriter bf = new BufferedWriter(new FileWriter(f, true));
		bf.write(fl.info());
		bf.newLine();
		bf.flush();
		}
		catch (Exception e) {}
	}
	void deleteFlight (User user,Flight fl){
		try{
		File dir=new File("users");
		File f = new File (dir, user.getUserName() + ".txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		File f1=new File(dir,"temp.txt");
		f1.createNewFile();
		BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
		bf.write(br.readLine());
		bf.write("\n");
		int canflight=fl.getId();
		while (br.ready()) {
			String s=br.readLine();
			String w[] = s.split("\\s+");
			w[1] = w[1].replace(w[1].substring(w[1].length()-1), "");
			int currentid = Integer.parseInt(w[1]);
			if(currentid!=canflight){
				bf.write(s);
				bf.write("\n");
				}
		}
		bf.flush();
		PrintWriter pw = new PrintWriter(new FileWriter(f), false);
		pw.flush();
		BufferedReader br1=new BufferedReader(new FileReader(f1));
		BufferedWriter bf1= new BufferedWriter(new FileWriter(f,true));
		while(br1.ready()){
			bf1.write(br1.readLine());
			bf1.write("\n");
			}
		bf1.flush();
		bf.close();
		br1.close();
		f1.delete();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	boolean authenticate (User user, String password){
		return (user.getPass().equals(password));
	}
}