import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MovieDetailsFrame extends JFrame

{
	    static String x = new File(System.getProperty("user.dir")).toString();
	    private javax.swing.JScrollPane jScrollPane2;
	    private javax.swing.JButton saveButton;
	    private javax.swing.JTable table;
	    private Movie movie;
	public MovieDetailsFrame(String url) throws IOException
	{
		initComponents();
		populateTable(url);
		 table.setSelectionBackground(Color.gray);
	        JTableHeader th = table.getTableHeader();
	        th.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        
	}
	private void populateTable(String url) throws IOException
	{
		ArrayList<Movie> list = getMovieInfoFromUrl(url);
		String[] colNames = {"ID","Name","Image","Score","Total_Reviews_By_Critics","Audience's_LikedPercentage"
				,"UserRatings"};
		Object[][]rows = new Object[list.size()][7];
		for(int i = 0; i < list.size(); i++){
            rows[i][0] = list.get(i).getId();
            rows[i][1] = list.get(i).getName();
            ManageMovieSearch.downloadImage(list.get(i).getImageUrl(),list.get(i).getName());
            ImageIcon pic = new ImageIcon(
                    (ManageMovieSearch.x+"/"+list.get(i).getName()+".jpg"));      
            rows[i][2] = pic;
            rows[i][3] = list.get(i).getScore();
            rows[i][4] = list.get(i).getNumberOfReviews();
            rows[i][5] = list.get(i).getLikedPercentage();
            rows[i][6] = list.get(i).getUserRatings();

     }
        MyTableModel mmd = new MyTableModel(rows, colNames);
        table.setModel(mmd);
        table.setRowHeight(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
        table.getColumnModel().getColumn(4).setPreferredWidth(300);
        table.getColumnModel().getColumn(5).setPreferredWidth(400);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
	}
	
	private void initComponents() {
		   Toolkit kit = Toolkit.getDefaultToolkit();
	       Dimension screen = (Dimension) kit.getScreenSize();
	       int width = screen.width;
	       int height = screen.height;
	       setSize(width/2,height/2);
           
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();
        
        if(LoginFrame.db.getCurrentUserType().equalsIgnoreCase("guest"))
        {
        	saveButton.setVisible(false);
        }
        
        saveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					saveButtonAction();
				} catch (ParserConfigurationException | TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        	
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        table.setModel(new javax.swing.table.DefaultTableModel(new Object [][]
        		{},new String[]{}));
        jScrollPane2.setViewportView(table);
        saveButton.setText("Save Movie");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(saveButton)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

    }// </editor-fold>                 
	private  ArrayList<Movie> getMovieInfoFromUrl(String urlName) throws IOException
    {
    	//<div class="mop-ratings-wrap score_panel">
    	ArrayList<Movie> movieInfoList = new ArrayList<>();
    	String htmlContentINeed =ManageMovieSearch.getHtmlContent(urlName);
    	String name = extractValueFromHtml("<h1\\b[^>]*>([\\w\\s-]*)</h1>",htmlContentINeed);
    	String score = extractValueFromHtml("<span class=\"mop-ratings-wrap__percentage\">([\\d%\\s]*)</span>"
    			,htmlContentINeed);
    	String numberofReviewsByCritics = extractValueFromHtml("<small class=\"mop-ratings-wrap__text--small\">"
    			+ "([\\d\\s]+)</small>",htmlContentINeed);
    	String likedPercentage = extractValueFromHtml("<span class=\"mop-ratings-wrap__percentage mop-ratings-wrap__"
    			+"percentage--audience\">([\\d\\s%]+)<br */>",htmlContentINeed);
    	String userRatings = extractValueFromHtml("<small class=\"mop-ratings-wrap__text--small\">([\\d\\s,]+)</small>"
    			,htmlContentINeed);
    	String imgUrl ="";
    	Pattern imagePattern = Pattern.compile("data-src([\\s]*?)=([\\s\\S]+)/>");
    	Matcher m6 = imagePattern.matcher(htmlContentINeed);
    	while(m6.find())
    	{
    		imgUrl = m6.group().split("\"")[1];
    	}
    	
    	int id = generateRandomNumber();
    	while(LoginFrame.db.containMovieID(id))
    	{
    		id = generateRandomNumber();
    	}
    	
    	if(LoginFrame.db.containMovieName(name))
    	{
    		JOptionPane.showMessageDialog(null,
        		    "Movie Name already exist in the Database");
    	}
    	if(score.isEmpty())
    	{
    		score = "N/A";
    	}
    	if(numberofReviewsByCritics.isEmpty())
    	{
    		numberofReviewsByCritics = "N/A";
    	}
    	movie = new Movie (id,name,score,numberofReviewsByCritics,likedPercentage,userRatings,imgUrl,
    			LoginFrame.db.getCurrentUser());
    	movieInfoList.add(movie);
    	return movieInfoList;
    }
	  private static String extractValueFromHtml(String pattern,String html)
	    {
	    	 String result = "";
	    	 Pattern p = Pattern.compile(pattern);
	     	 Matcher m = p.matcher(html);
	     	while(m.find())
	     	{
	     		result = m.group(1).trim();
	     		
	     	}
	     	return result;	 
	    }
	  private  int generateRandomNumber()
	  {
		  Random random = new Random();
		  int randomInt = random.nextInt(1000);
		  return randomInt;
	  }
	  private void saveButtonAction() throws ParserConfigurationException, TransformerException
	  {
		//If table is not empty check whether user selected a row
	      if(table.getSelectionModel().isSelectionEmpty())
	       {
		     JOptionPane.showMessageDialog(null, "Please select a movie from the table");
	       }
	      else
	      {
	    	//save movie to Database
	    	  if( !LoginFrame.db.containMovieName(movie.getName()))
	    	  {
	    	 LoginFrame.db.addMovie(movie);
	    	 JOptionPane.showMessageDialog(null, "Movie has been saved to database. An output file has been created "
	    	 		+ "at user's current directory");
	    	          if(MainFrame.OSName.contains("Windows"))
	    	          {
	    	        	  generateXMLFile(x+"\\"+MainFrame.outputFile);
	    	          }
	    	          else
	    	          {
	    	        	  generateXMLFile(x+"/"+MainFrame.outputFile);
	    	          }
	    	 
	    	  }
	    	  else
	    	  {
	    		  JOptionPane.showMessageDialog(null, "Movie already exist in database.");
	    	  }
	    	  
	      }
	  }
	  private void generateXMLFile(String filePath) throws ParserConfigurationException, TransformerException
	  {
	            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
	 
	            DocumentBuilder documentBuilder = df.newDocumentBuilder();
	 
	            Document document = documentBuilder.newDocument();
	 
	            // root element
	            Element root = document.createElement("UserStore");
	            document.appendChild(root);
	 
	            // movie element
	            Element movieElement = document.createElement("movie");
	 
	            root.appendChild(movieElement);
	 
	            // set an attribute to movie element
	            Attr attribute = document.createAttribute("id");
	            attribute.setValue(Integer.toString(movie.getId()));
	            
	            movieElement.setAttributeNode(attribute);
	 
	            // name element
	            Element movieName = document.createElement("name");
	            movieName.appendChild(document.createTextNode(movie.getName()));
	            movieElement.appendChild(movieName);
	 
	            // numberofReviewsByCritics element
	            Element numberofReviewsByCritics = document.createElement("numberofReviewsByCritics");
	            numberofReviewsByCritics.appendChild(document.createTextNode(movie.getNumberOfReviews()));
	            movieElement.appendChild(numberofReviewsByCritics);
	            // score element
	            Element score = document.createElement("score");
	            score.appendChild(document.createTextNode(movie.getScore()));
	            movieElement.appendChild(score);
	            // likedPercentage element
	            Element likedPercentage = document.createElement("likedPercentage");
	            likedPercentage.appendChild(document.createTextNode(movie.getLikedPercentage()));
	            movieElement.appendChild(likedPercentage);
	            // userRatings element
	            Element userRatings = document.createElement("userRatings");
	            userRatings.appendChild(document.createTextNode(movie.getUserRatings()));
	            movieElement.appendChild(userRatings);
	         // imageUrl element
	            Element imageUrl = document.createElement("imageUrl");
	            imageUrl.appendChild(document.createTextNode(movie.getImageUrl()));
	            movieElement.appendChild(imageUrl);
	            // movieSearchedByUser element
	            Element movieSearchedByUser = document.createElement("movieSearchedByUser");
	            movieSearchedByUser.appendChild(document.createTextNode(movie.getItemOwner()));
	            movieElement.appendChild(movieSearchedByUser);
	 
	            // create the xml file
	            //transform the DOM Object to an XML File
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = null;
				try {
					transformer = transformerFactory.newTransformer();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            DOMSource domSource = new DOMSource(document);
	            StreamResult streamResult = new StreamResult(new File(filePath));
	            transformer.transform(domSource, streamResult);
	            //System.out.println("Done creating XML File");   
	  }
}
