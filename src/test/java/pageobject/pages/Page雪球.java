package pageobject.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.utils.AppDriver;

public class Page雪球 {

    By tvSearch=By.id("tv_search");
    By 自选=By.xpath("//android.widget.TextView[@text='自选']");

    public Page搜索 enterSearch(){
        AppDriver.driver.findElement(tvSearch).click();
        return new Page搜索();

    }
    public Page自选 enter自选(){
        AppDriver.driver.findElement(自选).click();
        return new Page自选();

    }
}
