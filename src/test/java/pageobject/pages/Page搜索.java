package pageobject.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.utils.AppDriver;

public class Page搜索 {

    By input=By.id("search_input_text");
    By stockName=By.id("stockName");

    public Page搜索 search(String keyword){
        AppDriver.driver.findElement(input).sendKeys(keyword);
        return this;
    }

    public String getStockFirst(){
        return AppDriver.driver.findElement(stockName).getText();
    }

}
