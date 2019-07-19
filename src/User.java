
public class User {
	private int id;
    private String username;
    private String password;
    private String user_type; 
    public User(String UNAME, String PASW,String UTYP)
    {
        this.username = UNAME;
        this.password = PASW;
        this.user_type = UTYP;
    }
    public User() 
    {
		username = "";
		user_type = "";
	}
	public void setId(int id)
    {
    	this.id = id;
    }
    public int getId()
    {
    	return id;
    }
    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
       
}
