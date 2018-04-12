package org.grabber.supremeGrabber;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Backend {
	public String name;
	public String email;
	public String tel;
	public String address;
	public String zip;
	public String cardnum;
	public String month;
	public String year;
	public String ccv;
	public String desc;
	public String size;
	public String time;
	boolean finished = false;

	public Backend(String name, String email, String tel, String address, String zip, String cardnum, String month,String year, String ccv, String desc, String size, String time) {
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.zip = zip;
		this.cardnum = cardnum;
		this.month = month;
		this.year = year;
		this.ccv = ccv;
		this.desc = desc;
		this.size = size;
		this.time = time;
	}

	public void RunGrabber() throws InterruptedException, ParseException, IOException {
		if ((name != null) && (email != null) && (tel != null) && (address != null) && (zip != null) && (cardnum != null) && (month != null) && (year != null) && (ccv != null)) {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("general.useragent.override", "iPhone");
			WebDriver driver = new FirefoxDriver(options);
			String keyWord = "//*[text()[contains(.,'" + desc + "')]]";
			String keySize = "//*[text()[contains(.,'" + size + "')]]";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			String dateString = dateFormat.format(date);
			String dateSub = dateString.substring(11, 16);
			System.out.println("Supreme Launcher will execute at " + time);
			while (!dateString.substring(11, 16).equalsIgnoreCase(time)) {
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				date = new Date(System.currentTimeMillis());
				dateString = dateFormat.format(date);
				dateSub = dateString.substring(11, 16);
				System.out.println(dateSub);
			}

			driver.get("http://www.supremenewyork.com/mobile/?c=1#categories/new");
			try {
				System.out.println("Attempting to find Item...");
				Thread.sleep(1000L);
				driver.findElement(By.xpath(keyWord)).click();
				Thread.sleep(1000L);
				driver.findElement(By.id("size-options")).click();
				driver.findElement(By.xpath(keySize)).click();
				Thread.sleep(1500L);
				driver.findElement(By.className("cart-button")).click();
				Thread.sleep(1000L);
				driver.get("http://supremenewyork.com/checkout");
				driver.findElement(By.id("order_billing_name")).sendKeys(new CharSequence[] { name });
				driver.findElement(By.id("order_email")).sendKeys(new CharSequence[] { email });
				driver.findElement(By.id("order_tel")).sendKeys(new CharSequence[] { tel });
				driver.findElement(By.id("bo")).sendKeys(new CharSequence[] { address });
				driver.findElement(By.id("order_billing_zip")).sendKeys(new CharSequence[] { zip });
				driver.findElement(By.id("nnaerb")).sendKeys(new CharSequence[] { cardnum });
				driver.findElement(By.id("credit_card_month")).sendKeys(new CharSequence[] { month });
				driver.findElement(By.id("credit_card_year")).sendKeys(new CharSequence[] { year });
				driver.findElement(By.id("orcer")).sendKeys(new CharSequence[] { ccv });
				ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.className("iCheck-helper"));
				list.get(1).click();
				driver.findElement(By.name("commit")).click();

				Thread.sleep(5000L);
				driver.close();
				finished = true;
			} catch (NoSuchElementException | InterruptedException ab) {
				JOptionPane.showMessageDialog(null, "Key word did not point to a valid item or size.");
			}
		}
	}

	public boolean getFinished() {
		return finished;
	}
}