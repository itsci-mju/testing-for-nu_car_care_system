package TC12;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

public class Utility {
	private WebDriver webDriver = null;
	
	public void browser_open(String fURL) {
		if (webDriver == null) {
			System.setProperty("webdriver.chrome.driver", "c:/chomedriver/chromedriver.exe");
			webDriver = new ChromeDriver();
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else
			webDriver = new ChromeDriver();

		webDriver.get(fURL);
	}

	public  void radio_select(String xPath, String fText) {
		List<WebElement> radio = webDriver.findElements(By.xpath(xPath));
	     for (int i = 0; i < radio.size(); i++) {
	    	 if (radio.get(i).getAttribute("value").equals(fText)) {
	    		 radio.get(i).click();
	    	     return ;
	        }		    	 
	     }
	     throw new NoSuchElementException("Invalid Radio button Selected");
	}
	
	public String get_text(String xPath) {
		return webDriver.findElement(By.xpath(xPath)).getText();
	}
	
	public void edit_input(String xPath, String fText) {
			webDriver.findElement(By.xpath(xPath)).clear();;
			webDriver.findElement(By.xpath(xPath)).sendKeys(fText);
	}
	

	public void list_select(String xPath, String fText) { 
		 
 		Select select = new Select(webDriver.findElement(By.xpath(xPath)));
		List <WebElement> options = select.getOptions(); 

		for (int i = 0; i < options.size(); i++) { 
		if (options.get(i).getText().equals(fText)){ 
			if (!options.get(i).isSelected())
				options.get(i).click(); 
		      return; 
		    } 
		  }
		throw new NoSuchElementException("Invalid list Selected");
	} 
	
	public String get_alert() {
		Alert alert = webDriver.switchTo().alert();
		String alert_text = alert.getText();
		alert.accept();
		return alert_text;
	}
	
	public void checkbox_set(String xPath, String fText) {
		String [] extract = fText.split(",");
            if (extract.length == 4) {
		    List <WebElement> checkBox = webDriver.findElements(By.xpath(xPath));
		    for (int i = 0; i < extract.length; i++){
			    if (extract[i].equalsIgnoreCase("ON")){
				    checkBox.get(i).click();
			    }
		       }   
            }
            else {
            	throw new NoSuchElementException("Invalid CheckBox Selected");
            }	
	}

	public void button_click(String xPath) {
		 webDriver.findElement(By.xpath(xPath)).click();
	}

	public void click_link(String fText) {
		 webDriver.findElement(By.linkText(fText)).click();
	}

	public boolean dialog_click() {
		String text ="";
        WebDriverWait wait = new WebDriverWait(webDriver,10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    Alert alert = webDriver.switchTo().alert();
	    text = alert.getText();
	    alert.accept();
	    String[] temp = text.split(":");
     
	    if (temp[0].trim().equals("ข้อมูลผิดพลาด"))		
	    		return false;
	    	else
	    	     return true;
	   } 
	  public void browser_close(){
		  //webDriver.navigate().refresh();
		  webDriver.close();
	}
	  public void saveScreen(String index) {
		  
		 File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile(scrFile, new File("G://KDF_CarcareProject/IMG/TC10/x"+ index +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}

	public void edit_inputById(String ip, String ip2) {
		// TODO Auto-generated method stub
		
	}
}	
