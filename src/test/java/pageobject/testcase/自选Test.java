package pageobject.testcase;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pageobject.pages.Page雪球;
import pageobject.utils.AppDriver;

import java.net.MalformedURLException;

public class 自选Test {

    @Before
    public void before() throws MalformedURLException {
        AppDriver.launchApp();
    }

    @Test
    public void 美股(){
        assertThat(
                new Page雪球().enter自选().enter沪深股票().getStockList().size(),
                greaterThan(0)
        );
    }
}
