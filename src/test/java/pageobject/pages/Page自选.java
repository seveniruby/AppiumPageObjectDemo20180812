package pageobject.pages;

import org.openqa.selenium.By;
import pageobject.utils.AppDriver;

import java.util.List;
import java.util.stream.Collectors;

public class Page自选 {
    By tips= By.id("tip_close_left");
    By stock=By.id("page_type_stock");
    By 美股=By.xpath("//android.widget.TextView[@resource-id='com.xueqiu.android:id/text' and @text='美股']");
    By stockList=By.id("portfolio_stockName");

    public Page自选(){
        if(AppDriver.driver.findElements(tips).size()>0) {
            AppDriver.driver.findElement(tips).click();
        }
    }
    public Page自选 enter沪深股票(){
        AppDriver.driver.findElement(stock).click();
        AppDriver.driver.findElement(美股).click();
        return this;
    }

    public List<String> getStockList(){
        return AppDriver.driver.findElements(stockList)
                .stream()
                .map(e->e.getText())
                .collect(Collectors.toList());
    }
}
