package org.grabber.supremeGrabber;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;

public class Runner extends Application {
	private static String duration = "0";
	private static String user;
	private static boolean test = false;
	static boolean run = false;
	static String key = "";
	static String exception;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image("supreme.png"));
			primaryStage.getIcons().add(new Image("supreme16.png"));
			primaryStage.getIcons().add(new Image("supreme256.png"));
			primaryStage.getIcons().add(new Image("supreme32.png"));
			primaryStage.getIcons().add(new Image("supreme48.png"));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Supreme Grabber");
			primaryStage.show();
		} catch(Exception a) {
			a.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException, ParseException, IOException {
		launch(args);
		if(run) {
			Backend backend = new Backend(MainController.name, MainController.email, MainController.phone, MainController.address, MainController.apt, MainController.zip, MainController.card, MainController.month, MainController.year,MainController.ccv, MainController.desc, MainController.size, MainController.time);
			try {
				backend.RunGrabber();
				duration= Double.toString((System.currentTimeMillis()-backend.getStartTime()));
				sqlPurchase(user,backend.getFinished(),duration,"\""+key+"\"","\""+exception+"\"");
				JOptionPane.showMessageDialog(null, "Check that purchase has completed.  Program will close when OK is pressed", "Close", 1);
				backend.getDriver().close();
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		System.exit(0);
	}

	private static void sqlPurchase(String user,boolean success, String duration, String keyWord, String exception) throws SQLException {
		String jdbcUrl = "jdbc:mysql://supremegrabberdb.crjvk09dzo9d.us-east-2.rds.amazonaws.com/Connection";
		java.sql.Connection con = DriverManager.getConnection(jdbcUrl,"javaAccess","testpass");
		String queryString;
		if(success) {
			queryString = "INSERT INTO purchases(completed,user,duration,key_word, error) VALUES(1, "+user+","+duration+","+keyWord+","+exception+")";
		}
		else {
			queryString = "INSERT INTO purchases(completed,user,duration,key_word, error) VALUES(0, "+user+","+duration+","+keyWord+","+exception+")";
		}
		java.sql.Statement stmt = con.createStatement();
		stmt.executeUpdate(queryString);
		con.close();
		stmt.close();
	}
	public static boolean getTest() {
		return test;
	}
	public static void setUser(String setUser) {
		user = setUser;
	}
	public static void setTest(boolean setTest) {
		test = setTest;
	}
}