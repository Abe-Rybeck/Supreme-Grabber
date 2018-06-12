package org.grabber.supremeGrabber;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Backend {

	private String name;
	private String email;
	private String tel;
	private String address;
	private String apt;
	private String zip;
	private String cardNum;
	private String month;
	private String year;
	private String ccv;
	private String desc;
	private String size;
	private String time;
	private boolean finished = false;
	int close;
	double startTime;
	WebDriver driver;



	public Backend(String name, String email, String tel, String address,String apt, String zip, String cardNum, String month,String year, String ccv, String desc, String size, String time) {
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.apt = apt;
		this.zip = zip;
		this.cardNum = cardNum;
		this.month = month;
		this.year = year;
		this.ccv = ccv;
		this.desc = desc;
		this.size = size;
		this.time = time;
		Runner.key = desc;

	}

	public void RunGrabber() throws InterruptedException {
		if ((name != null) && (email != null) && (tel != null) && (address != null) && (zip != null) && (cardNum != null) && (month != null) && (year != null) && (ccv != null)) {
			driver = setDriver(); //set-up Firefox driver
			String keyWord = "//*[text()[contains(.,'" + desc + "')]]";
			String keySize = "//*[text()='" + size + "']";
			googleLogin();
			if(!Runner.getTest()) { //if test, skip wait-until
				waitForTime();
			}
			else { 
				Thread.sleep(2000);
			}

			startTime = System.currentTimeMillis();
			driver.get("http://www.supremenewyork.com/mobile"); //go-to supreme
			while(driver.findElements(By.id("new-category")).isEmpty()) {
				driver.navigate().refresh();
			}
			driver.get("http://www.supremenewyork.com/mobile/?c=1#categories/new");//go-to new page
			while(!driver.findElement(By.xpath(keyWord)).isDisplayed()) {
			}
			try {driver.findElement(By.xpath(keyWord)).click();} //go-to item page
			catch (Exception wordException) { //go-to item page failed, ABORT
				JOptionPane.showMessageDialog(null, "Item matching Keyword not found");
				wordException.printStackTrace();
				Runner.exception = "wordException";
				return;
			}
			if(checkSold()) { // iterate until non-sold-out-color found
				checkSize(keySize); //attempt to find chosen size, otherwise choose default
				driver.findElement(By.className("cart-button")).click(); //add to cart
				fillInfo(driver,  name,  email,  tel,  address,  zip,  cardNum,  month,  year,  ccv ); //checkout
				finished = true; //announce finished

			}

			else {JOptionPane.showMessageDialog(null, "all item colors sold out");} //all colors sold out, ABORT
		}

	}

	private void googleLogin() {
		try {
			driver.get("https://accounts.google.com/signin");
			driver.findElement(By.id("Email")).sendKeys("supremegrabbertest1");
			driver.findElement(By.id("next")).click();
			driver.findElement(By.id("Passwd")).sendKeys("abe12345");
			driver.findElement(By.id("signIn")).click(); 
			driver.get("https://patrickhlauke.github.io/recaptcha");
		}
		catch(Exception googleException) {
			googleException.printStackTrace();
			Runner.exception = "googleException";
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

	private void waitForTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String dateString = dateFormat.format(date);
		while (!dateString.substring(11, 16).equalsIgnoreCase(time)) {
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			date = new Date(System.currentTimeMillis());
			dateString = dateFormat.format(date);
		}
	}

	public boolean checkSold() {
		int counter = 0;
		List<WebElement> soldList = driver.findElements(By.cssSelector("span[class='cart-button sold-out']"));
		while(true) {
			if(soldList.size() !=0) {//if sold out)
				counter++;
				List<WebElement> colorList = driver.findElements(By.cssSelector("[id*=style-2]"));
				if(counter <= colorList.size()) {
					driver.findElement(By.id(colorList.get(counter).getAttribute("id"))).click();
					soldList = driver.findElements(By.cssSelector("span[class='cart-button sold-out']"));
				}
				else { // all sold out
					return false;
				}
			}
			else { //not sold out , Continue
				return true;
			}
		}
	}

	public void checkSize(String keySize) {
		if(driver.findElements(By.xpath(keySize)).size() !=0){ //if size is available
			driver.findElement(By.id("size-options")).click();//open option bar
			try {
				driver.findElement(By.xpath(keySize)).click();//get option
			}
			catch(ElementNotInteractableException sizeException1) {
				sizeException1.printStackTrace();
				Runner.exception = "sizeException1";
			}
		}
	}

	private void fillInfo(WebDriver driver, String name, String email, String tel, String address, String zip, String cardNum, String month, String year, String ccv) {
		driver.get("http://supremenewyork.com/checkout");
		driver.findElement(By.id("order_billing_name")).sendKeys(new CharSequence[] { name });
		driver.findElement(By.id("order_email")).sendKeys(new CharSequence[] { email });
		driver.findElement(By.id("order_tel")).sendKeys(new CharSequence[] { tel });
		driver.findElement(By.id("bo")).sendKeys(new CharSequence[] { address });
		driver.findElement(By.id("oba3")).sendKeys(new CharSequence[] { apt });
		driver.findElement(By.id("order_billing_zip")).sendKeys(new CharSequence[] { zip });
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nnaerb")));
		driver.findElement(By.id("nnaerb")).sendKeys(new CharSequence[] { cardNum });
		driver.findElement(By.id("credit_card_month")).sendKeys(new CharSequence[] { month });
		driver.findElement(By.id("credit_card_year")).sendKeys(new CharSequence[] { year });
		driver.findElement(By.id("orcer")).sendKeys(new CharSequence[] { ccv });
		ArrayList<WebElement> checkList = (ArrayList<WebElement>) driver.findElements(By.className("iCheck-helper"));
		checkList.get(1).click();
		driver.findElement(By.name("commit")).click();
	}

	public boolean getFinished() {
		return this.finished;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public double getStartTime() {
		return startTime;
	}
}