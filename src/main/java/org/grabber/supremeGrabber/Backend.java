package org.grabber.supremeGrabber;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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
	public String cardNum;
	public String month;
	public String year;
	public String ccv;
	public String desc;
	public String size;
	public String time;
	public boolean finished = false;
	int close;

	public Backend(String name, String email, String tel, String address, String zip, String cardNum, String month,String year, String ccv, String desc, String size, String time) {
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.zip = zip;
		this.cardNum = cardNum;
		this.month = month;
		this.year = year;
		this.ccv = ccv;
		this.desc = desc;
		this.size = size;
		this.time = time;
	}

	public void RunGrabber() throws InterruptedException, ParseException, IOException, WebDriverException, InvocationTargetException{
		if ((name != null) && (email != null) && (tel != null) && (address != null) && (zip != null) && (cardNum != null) && (month != null) && (year != null) && (ccv != null)) {
			WebDriver driver = setDriver();
			String keyWord = "//*[text()[contains(.,'" + desc + "')]]";
			String keySize = "//*[text()[contains(.,'" + size + "')]]";
			waitForTime();
			driver.get("http://www.supremenewyork.com/mobile/?c=1#categories/new");
			try {
				driver.findElement(By.xpath(keyWord)).click();
				driver.findElement(By.id("size-options")).click();
				driver.findElement(By.xpath(keySize)).click();
				driver.findElement(By.className("cart-button")).click();
				driver.get("http://supremenewyork.com/checkout");
				fillInfo(driver,  name,  email,  tel,  address,  zip,  cardNum,  month,  year,  ccv );
				ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.className("iCheck-helper"));
				list.get(1).click();
				driver.findElement(By.name("commit")).click();
				JOptionPane.showMessageDialog(null, "Purchase completed", "Completed",2);
				finished = true;
			} catch (NoSuchElementException ab) {
				JOptionPane.showMessageDialog(null, "Key word did not point to a valid item or size.");
			}
		}
	}
	private void waitForTime() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String dateString = dateFormat.format(date);
		while (!dateString.substring(11, 16).equalsIgnoreCase(time)) {
			Thread.sleep(100L);
			date = new Date(System.currentTimeMillis());
			dateString = dateFormat.format(date);
		}
	}
	
	private WebDriver setDriver() {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("general.useragent.override", "iPhone");
		WebDriver driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}
	
	private void fillInfo(WebDriver driver, String name, String email, String tel, String address, String zip, String cardNum, String month, String year, String ccv) {
		driver.findElement(By.id("order_billing_name")).sendKeys(new CharSequence[] { name });
		driver.findElement(By.id("order_email")).sendKeys(new CharSequence[] { email });
		driver.findElement(By.id("order_tel")).sendKeys(new CharSequence[] { tel });
		driver.findElement(By.id("bo")).sendKeys(new CharSequence[] { address });
		driver.findElement(By.id("order_billing_zip")).sendKeys(new CharSequence[] { zip });
		driver.findElement(By.id("nnaerb")).sendKeys(new CharSequence[] { cardNum });
		driver.findElement(By.id("credit_card_month")).sendKeys(new CharSequence[] { month });
		driver.findElement(By.id("credit_card_year")).sendKeys(new CharSequence[] { year });
		driver.findElement(By.id("orcer")).sendKeys(new CharSequence[] { ccv });
	}
	public boolean getFinished() {
		return this.finished;
	}
}