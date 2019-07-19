import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoginFrame extends JFrame
{
	 public static final int TEXTAREA_ROWS = 8;
	   public static final int TEXTAREA_COLUMNS = 20;
	   public static Database db = new Database();
	   private JTextField userName;
	   private JPasswordField passwordField;
	   private JComboBox<String> cb;
	   private JTextArea ta;
	   public static Date date = new Date();
	   public static SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	public LoginFrame() throws IOException
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
	       Dimension screen = (Dimension) kit.getScreenSize();
	       int width = screen.width;
	       int height = screen.height;
	       setSize(width/2,height/2);
	       //populateDatabaseWithDataFromInputFile(inputFile);
	     userName = new JTextField();
	   	 passwordField = new JPasswordField();
	   	 cb = new JComboBox<String>();
	   	cb.addItem("User");
	   	cb.addItem("Admin");
	   	cb.addItem("Guest");
	   	JPanel northPanel = new JPanel();
	   	northPanel.setLayout(new GridLayout(3,3));
	   	northPanel.add(new JLabel("User Name ",SwingConstants.RIGHT));
	   	northPanel.add(userName);
	   	northPanel.add(new JLabel("Password ",SwingConstants.RIGHT));
	   	northPanel.add(passwordField);
	   	northPanel.add(new JLabel("Select User Type ",SwingConstants.RIGHT));
	   	northPanel.add(cb);
	   	add(northPanel,BorderLayout.NORTH);
	   	ta = new JTextArea(TEXTAREA_ROWS,TEXTAREA_COLUMNS);
	   	JScrollPane scrollPane = new JScrollPane(ta);
	   	add(scrollPane,BorderLayout.CENTER);
	   	//add button to append text into the text area
	   	JPanel southPanel = new JPanel();
	   	JButton createUserButton = new JButton("Create User");
	   	JButton loginButton = new JButton("Login");
	   	loginButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginActionPerformed();
			}
	   		
	   	});
	   	createUserButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				createAccount();
			}
	   		
	   	});
	   	southPanel.add(createUserButton);
	   	southPanel.add(loginButton);
	   	add(southPanel,BorderLayout.SOUTH);
	   	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
	}
	private void loginActionPerformed()
	{
		if(!userName.getText().isEmpty() & !(passwordField.getPassword().length==0))
		{
			String selectedUser = (String) cb.getSelectedItem();
			  String pass = new String(passwordField.getPassword());
			ArrayList<User> list = db.getUserList();
			for(User u :list)
			{
				if(userName.getText().equalsIgnoreCase(u.getUsername())&pass.equals(u.getPassword())&
						selectedUser.equalsIgnoreCase(u.getUser_type()))
				{
					db.setCurrentUser(userName.getText());
					db.setCurrentUserType(selectedUser);
					HomeFrame cf = new HomeFrame();
					if(selectedUser.equalsIgnoreCase("user"))
					{
						cf.userMenu.setVisible(false);
					}
					else if(selectedUser.equalsIgnoreCase("guest"))
					{
						cf.userMenu.setVisible(false);
						cf.savedMovie.setVisible(false);
					}
						
					cf.setVisible(true);
					cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					this.dispose();
				}
				else
				{
					ta.setText("username or password or user type is incorrect");
					continue;
				}
			}
			
		
			
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,
        		    "Please Enter username, password and select user type");
		}
		/*
		
		*/
	}
	private void createAccount()
	{
		if(!userName.getText().isEmpty() & !(passwordField.getPassword().length==0))
		{
			String selectedUser = (String) cb.getSelectedItem();
			int id = generateRandomNumber();
	    	while(db.containUserId(id))
	    	{
	    		id = generateRandomNumber();
	    	}
	    	//db.user.setId(id);
	    	//System.out.println(db.user.getId());
			if(!db.containUserName(userName.getText()) )
			{
				db.addUser(userName.getText(),new String(passwordField.getPassword()),selectedUser);
				ta.setText("User Account Has been created. You can login now.");
			}
			else
			{
				ta.setText("username already exist!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,
        		    "Please Enter username, password and select user type");
		}
	}
	public static void populateDatabaseWithDataFromInputFile(String inputFile)
	{
		try
		{
			File xmlFile = new File(inputFile);
			DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document doc = documentBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			//System.out.println("Root Element :"+doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("User");
			//System.out.println("--------------");
			for(int i = 0; i<nodeList.getLength();i++)
			{
				Node node = nodeList.item(i);
				//System.out.println("\nCurrent Element :"+node.getNodeName());
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String password = element.getElementsByTagName("password").item(0).getTextContent();
					String type = element.getElementsByTagName("type").item(0).getTextContent();
					db.addUser(name, password, type);
					System.out.println("Database has been populated");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	private  int generateRandomNumber()
	  {
		  Random random = new Random();
		  int randomInt = random.nextInt(1000);
		  return randomInt;
	  }
}
