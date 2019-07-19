import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class MainFrame {
	private static  String x = new File(System.getProperty("user.dir")).toString();
	public static String inputFile = "";
	public static String outputFile ="";
	public static String OSName = System.getProperty("os.name");
	public static void main(String[] args) 
	{
		openMainFrame();	
	}
	public static void openMainFrame()
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() 
            {		
				try {
					new  LoginFrame().setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
	}
}
