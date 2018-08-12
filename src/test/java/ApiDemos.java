import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApiDemos {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appPackage", "com.example.android.apis");
        desiredCapabilities.setCapability("appActivity", ".ApiDemos");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("avd", "Three");
        desiredCapabilities.setCapability("automationName", "uiautomator2");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void testToast() throws InterruptedException {
        driver.findElementByXPath("//*[@text='Views']").click();
        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" +
                        "new UiSelector().text(\"Popup Menu\").instance(0));").click();
        driver.findElementByXPath("//*[contains(@text, 'Make')]").click();
        driver.findElementByXPath("//*[@text='Search']").click();
        //System.out.println(driver.findElementByClassName("android.widget.Toast").getText());
        System.out.println(driver.findElementByXPath("//*[@class='android.widget.Toast']").getText());

    }

    @Test
    public void testSwipe() throws InterruptedException, IOException {
        Thread.sleep(3000);
        for(int i=0;i<10;i++) {
            swipe(0.8, 0.8, 0.4, 0.4);
            FileUtils.copyFile(
                    driver.getScreenshotAs(OutputType.FILE).getCanonicalFile(),
                    new File(i+".png"));
        }
    }

    @Test
    public void testRorate() throws InterruptedException {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        Thread.sleep(2000);
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.findElementByXPath("//*[@text='App']").click();
        driver.findElementByXPath("//*[@text='Alarm']").click();
        driver.navigate().back();
        driver.navigate().back();

    }

    @Test
    public void testCall(){
        driver.sendSMS("15600534760", "hello from seveniruby");
        driver.makeGsmCall("15600534760", GsmCallActions.CALL);
    }

    public void swipe(Double startX, Double startY, Double endX, Double endY) throws InterruptedException {
        Dimension size=driver.manage().window().getSize();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point((int)(size.width*startX), (int)(size.height*startY)));
        touchAction.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        touchAction.moveTo(PointOption.point((int)(size.width*endX), (int)(size.height*endY)));
        touchAction.release();
        touchAction.perform();
        Thread.sleep(1000);
    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}
