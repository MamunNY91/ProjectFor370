import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class HomeFrame extends JFrame
{
	public JLabel backgroundImage;
    public JMenu userMenu;
    private JMenuBar menuBar;
    public JMenu savedMovie;
    private JMenu searchMovie;
    String x = new File(System.getProperty("user.dir")).toString();
      public HomeFrame()
      {
    	  Toolkit kit = Toolkit.getDefaultToolkit();
    	  setLayout(new BorderLayout());
	       Dimension screen = (Dimension) kit.getScreenSize();
	       int width = screen.width;
	       int height = screen.height;
	       setSize(width/2,height/2);
    	  backgroundImage = new JLabel(new ImageIcon(x+"/"+"rotentom.jpg"));
    	  add(backgroundImage,BorderLayout.CENTER);
    	  menuBar = new JMenuBar();
    	  userMenu = new JMenu();
    	  savedMovie = new JMenu();
    	  searchMovie = new JMenu();
    	  searchMovie.setBackground(new java.awt.Color(249, 105, 14));
    	  searchMovie.setForeground(new java.awt.Color(255, 255, 255));
    	  searchMovie.setText(" Search Movie |");
    	  searchMovie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    	  searchMovie.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    	  searchMovie.setOpaque(true);
    	  searchMovie.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  try {
					searchMovieMouseClicked(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              }
            });
    	  userMenu.setBackground(new java.awt.Color(249, 105, 14));
    	  userMenu.setForeground(new java.awt.Color(255, 255, 255));
    	  userMenu.setText(" | User List ");
    	  userMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    	  userMenu.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    	  userMenu.setOpaque(true);
    	  userMenu.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) 
              {
                	  userMenuMouseClicked(evt);
              }
            });
    	  savedMovie.setBackground(new java.awt.Color(249, 105, 14));
    	  savedMovie.setForeground(new java.awt.Color(255, 255, 255));
    	  savedMovie.setText(" Saved Movie ");
    	  savedMovie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    	  savedMovie.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    	  savedMovie.setOpaque(true);
    	  savedMovie.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) 
              {
            	  savedMovieMouseClicked(evt);
              }
            });
    	  menuBar.add(searchMovie);
    	  menuBar.add(savedMovie);
    	  menuBar.add(userMenu);
    	  setJMenuBar(menuBar);
    	  
    	  
      }
      private void searchMovieMouseClicked(MouseEvent evt) throws IOException 
		{
      	  ManageMovieSearch searchMovie = new ManageMovieSearch();
			searchMovie.pack();
			searchMovie.setVisible(true);
			searchMovie.setLocationRelativeTo(null);
			searchMovie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
      private void userMenuMouseClicked(MouseEvent evt)
      {
    	  
      }
      private void savedMovieMouseClicked(MouseEvent evt)
      {
    	  
      }
}
