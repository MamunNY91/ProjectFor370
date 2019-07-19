import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class Database 
{
   private Hashtable<Integer,Movie> movieStore;
   private Hashtable<String,User> userStore;
   private User user;
   private String currentUser;
   private String currentUserType; // prevent guest from saving movies.
   public Database()
   {
	   movieStore = new Hashtable<Integer,Movie>();
	   userStore = new Hashtable<String,User>();
	  
   }
   public void addMovie(Movie movie)
   {
	   movieStore.put(movie.getId(), movie);
   }
   
   public void addUser(String UNAME, String PASW,String UTYP)
   {
	   user = new User();
	   user.setPassword(PASW);
	   user.setUser_type(UTYP);
	   user.setUsername(UNAME);
	   userStore.put(user.getUsername(), user);
	   
   }
   public boolean containUserName(String userName)
   {
	  
	   return userStore.containsKey(userName);
   }
   public boolean containMovieID(int id)
   {
	   return movieStore.containsKey(id);
   }
   public boolean containMovieName(String name)
   {
	   Enumeration<Integer> keys;
	   Integer keyNames;
	   keys = movieStore.keys();
	   while(keys.hasMoreElements())
	   {
		   keyNames = keys.nextElement();
		   Movie movie = movieStore.get(keyNames);
		   if (name.equalsIgnoreCase(movie.getName()))
		   {
			   return true;
		   }
	    }
	    return false;
   }
   public boolean containUserId(int id)
   {
	   Enumeration<String> keys;
	   String keyNames;
	   keys = userStore.keys();
	   while(keys.hasMoreElements())
	   {
		   keyNames = keys.nextElement();
		   User u = userStore.get(keyNames);
		   if (id == u.getId())
		   {
			   return true;
		   }
	    }
	    return false;
   }
   public boolean containUserPassword(String password)
   {
	   
	   Enumeration<String> keys;
	   String keyNames;
	   keys = userStore.keys();
	   while(keys.hasMoreElements())
	   {
		   keyNames = keys.nextElement();
		   User u = userStore.get(keyNames);
		   if (password.equals(u.getPassword()))
		   {
			   return true;
		   }
	    }
	    return false;   
   }
    
   public ArrayList<String> returnUserList()
   {
	 
	   ArrayList<String> dataList = new ArrayList<String>();
	   String data = "";
	   Enumeration<String> keys;
	   String keyNames;
	   keys = userStore.keys();
	   //System.out.println(keys);
	   
	   while(keys.hasMoreElements())
	   {
		   data = "";
		   keyNames = keys.nextElement();
		   User u = userStore.get(keyNames);
		   data = keyNames +" | "+u.getUsername()+" | "+u.getUser_type()+" | "+u.getPassword()+" | "+"\n";
		   dataList.add(data);
		   
	   }
	   return dataList;
   }
   public ArrayList<User> getUserList()
   {
	 
	   ArrayList<User> userList = new ArrayList<User>();
	   Enumeration<String> keys;
	   String keyNames;
	   keys = userStore.keys();
	   //System.out.println(keys);
	   
	   while(keys.hasMoreElements())
	   {
		   keyNames = keys.nextElement();
		   User u = userStore.get(keyNames);
		   User u1 = new User(u.getUsername(),u.getPassword(),u.getUser_type());
		   userList.add(u1);
		   
	   }
	   return userList;
   }
   public void setCurrentUser(String userName)
   {
	   currentUser = userName;
   }
   public String getCurrentUser()
   {
	   return currentUser;
   }
   public void setCurrentUserType(String userType)
   {
	   currentUserType = userType;
   }
   public String getCurrentUserType()
   {
	   return currentUserType;
   }
}
