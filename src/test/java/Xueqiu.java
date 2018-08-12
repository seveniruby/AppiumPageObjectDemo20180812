import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.swing.plaf.TableHeaderUI;

public class Xueqiu {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //desiredCapabilities.setCapability("app", "/Users/seveniruby/Downloads/com.xueqiu.android_11.2_174.apk");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");

        //desiredCapabilities.setCapability("appPackage", "com.example.android.apis");
        //desiredCapabilities.setCapability("appActivity", ".ApiDemos");


        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("avd", "Three");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("chromedriverExecutableDir", "/Users/seveniruby/projects/chromedriver/chromedrivers/");



        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementById("cancel").click();
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("tv_search").click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.sendKeys("pdd");
        MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView");
        el4.click();
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
    @Test
    public void testDevice() throws Exception {
        driver.findElementById("tv_search").click();
        driver.findElementById("search_input_text").sendKeys("PDD");
        driver.findElementByXPath("//*[@text='拼多多']").click();
        System.out.println(driver.getPerformanceData("com.xueqiu.android", "cpuinfo", 5).toString());
        driver.manage().logs().getAvailableLogTypes().forEach(x-> System.out.println(x));
        //System.out.println(driver.manage().logs().get("logcat").toJson().toString());
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

    @Test
    public void webview(){
        driver.findElementByXPath("//android.widget.TextView[@text='沪深' and @resource-id='com.xueqiu.android:id/button_text']").click();
        driver.findElementByAccessibilityId("立即开户").click();
        driver.findElementByAccessibilityId("开始").click();
    }

    @Test
    public void context() throws InterruptedException {
        for(Object c : driver.getContextHandles()){
            System.out.println(c.toString());
        }
        System.out.println(driver.getPageSource());
        driver.findElementByXPath("//android.widget.TextView[@text='沪深' and @resource-id='com.xueqiu.android:id/button_text']").click();
        for(Object c : driver.getContextHandles()){
            System.out.println(c.toString());
        }
        Thread.sleep(3000);
        for(Object c : driver.getContextHandles()){
            System.out.println(c.toString());
        }

        driver.context("WEBVIEW_com.xueqiu.android");
        System.out.println(driver.getPageSource());
        driver.findElementByCssSelector(".inner").click();

    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}
