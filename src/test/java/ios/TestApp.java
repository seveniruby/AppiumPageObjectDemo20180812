package ios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestApp {

    private IOSDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "ios");
        desiredCapabilities.setCapability("deviceName", "iPhone X");
        desiredCapabilities.setCapability("platformVersion", "11.4");
        desiredCapabilities.setCapability("app", "/Users/seveniruby/Downloads/TestApp.app");

        URL remoteUrl = new URL("http://10.130.33.98:4723/wd/hub");

        driver = new IOSDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        System.out.println(driver.getPageSource());
        driver.findElementByAccessibilityId("IntegerA").sendKeys("1");
        driver.findElementByAccessibilityId("IntegerB").sendKeys("2");
        driver.findElementByAccessibilityId("ComputeSumButton").click();
        assertThat(driver.findElementByAccessibilityId("Answer").getText(), equalTo("3"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
