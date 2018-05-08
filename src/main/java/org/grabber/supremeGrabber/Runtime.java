package org.grabber.supremeGrabber;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriverException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;

public class Runtime extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("GuiNew.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception a) {
			a.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException, ParseException, IOException {

		String userEntry = "\""+JOptionPane.showInputDialog(null,"UserName:","Login: Username",3)+"\"";
		String passEntry = "\""+JOptionPane.showInputDialog(null,"Password:","Login: Password",3)+"\"";
		try {
			//attempt login using credentials
			ResultSet rset = verifyLogIn(userEntry, passEntry);
			if(rset.next()) {
				//log-in successful
				sqlLogSucceed(userEntry);
				rset.close();
				launch(args);
				Backend backend = new Backend(Controller.name, Controller.email, Controller.phone,
						Controller.address, Controller.zip, Controller.card, Controller.month, Controller.year,
						Controller.ccv, Controller.desc, Controller.size, Controller.time);
				try {
					backend.RunGrabber();
				}
				catch(InterruptedException | WebDriverException | InvocationTargetException c) {	
				}
				if(backend.getFinished()) {
					//purchase completed
					sqlPurchaseSucceed(userEntry);
				}
				else {
					//purchase failed
					sqlPurchaseFail(userEntry);	
				}
			}
			else {
				//log-in failed
				JOptionPane.showMessageDialog(null, "Username/Password does not match any user. please try again, or contact rybeck.abraham@gmail.com");
				sqlLogFail(userEntry);
			}


		}
		catch (SQLException b) {
			JOptionPane.showMessageDialog(null, "Unable to connect to verification server.  Make sure you are connected to the internet.  If problem persists, contact rybeck.abraham@gmail.com");
			b.printStackTrace();
		}
		//System.getProperty("webdriver.gecko.driver")
		System.exit(0);
	}



	private static void sqlLogFail(String user) throws SQLException {
		String jdbcUrl = "jdbc:mysql://alr203.cn4zhmagrghc.ap-northeast-1.rds.amazonaws.com/Connection";
		java.sql.Connection con = DriverManager.getConnection(jdbcUrl,"javaAccess","testpass");
		String queryString = "INSERT INTO log_in(user,granted) VALUES("+user+", 0)";
		java.sql.Statement stmt = con.createStatement();
		stmt.executeUpdate(queryString);
		con.close();
		stmt.close();
	}
	private static void sqlLogSucceed(String user) throws SQLException {
		String jdbcUrl = "jdbc:mysql://alr203.cn4zhmagrghc.ap-northeast-1.rds.amazonaws.com/Connection";
		java.sql.Connection con = DriverManager.getConnection(jdbcUrl,"javaAccess","testpass");
		String queryString = "INSERT INTO log_in(user,granted) VALUES("+user+", 1)";
		java.sql.Statement stmt = con.createStatement();
		stmt.executeUpdate(queryString);
		con.close();
		stmt.close();
	}
	private static void sqlPurchaseSucceed(String user) throws SQLException {
		String jdbcUrl = "jdbc:mysql://alr203.cn4zhmagrghc.ap-northeast-1.rds.amazonaws.com/Connection";
		java.sql.Connection con = DriverManager.getConnection(jdbcUrl,"javaAccess","testpass");
		String queryString = "INSERT INTO purchases(completed,user) VALUES(1, "+user+")";
		java.sql.Statement stmt = con.createStatement();
		stmt.executeUpdate(queryString);
		con.close();
		stmt.close();
	}
	private static void sqlPurchaseFail(String user) throws SQLException {
		String jdbcUrl = "jdbc:mysql://alr203.cn4zhmagrghc.ap-northeast-1.rds.amazonaws.com/Connection";
		java.sql.Connection con = DriverManager.getConnection(jdbcUrl,"javaAccess","testpass");
		String queryString = "INSERT INTO purchases(completed,user) VALUES(0, "+user+")";
		java.sql.Statement stmt = con.createStatement();
		stmt.executeUpdate(queryString);
		con.close();
		stmt.close();
	}
	private static ResultSet verifyLogIn(String user, String pass) throws SQLException {
		String jdbcUrl = "jdbc:mysql://alr203.cn4zhmagrghc.ap-northeast-1.rds.amazonaws.com/Connection";
		java.sql.Connection con;
		con = DriverManager.getConnection(jdbcUrl,"javaAccess","testpass");
		String queryString = "SELECT * FROM Authentication WHERE user = "+user+" AND pass_hash ="+pass;
		java.sql.Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(queryString);
		return rset;
	}
}
