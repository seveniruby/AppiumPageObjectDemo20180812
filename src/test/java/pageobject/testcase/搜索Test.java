package pageobject.testcase;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.pages.Page搜索;
import pageobject.pages.Page雪球;
import pageobject.utils.AppDriver;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class 搜索Test {

    @Parameterized.Parameters
    public static List<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"SOGO", "搜狗"},
                {"PDD", "拼多多"},
                {"xiaomi", "小米集团"}
        });
    }
    @Parameterized.Parameter
    public String keywords;
    @Parameterized.Parameter(1)
    public String result;

    @BeforeClass
    public void before() throws MalformedURLException {
        AppDriver.launchApp();
    }

    @Test
    public void search(){
        assertThat(
                new Page搜索().search(keywords).getStockFirst(),
                equalTo(result));
    }

    @AfterClass
    public void after() throws MalformedURLException {
        //
    }


}
