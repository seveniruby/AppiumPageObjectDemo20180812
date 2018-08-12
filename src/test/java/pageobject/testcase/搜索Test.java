package pageobject.testcase;

import org.junit.Before;
import org.junit.Test;
import pageobject.pages.Page搜索;
import pageobject.pages.Page雪球;
import pageobject.utils.AppDriver;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class 搜索Test {

    @Before
    public void before() throws MalformedURLException {
        AppDriver.launchApp();
    }
    @Test
    public void search(){
        Page雪球 page雪球=new Page雪球();
        Page搜索 page搜索=page雪球.enterSearch();
        page搜索.search("PDD");
        assertThat(page搜索.getStockFirst(), equalTo("拼多多"));
    }

    @Test
    public void search2(){
        Page雪球 page雪球=new Page雪球();
        Page搜索 page搜索=page雪球.enterSearch();
        page搜索.search("SOGO");
        assertThat(page搜索.getStockFirst(), equalTo("搜狐"));
    }

    @Test
    public void search3(){
        assertThat(
                new Page雪球().enterSearch().search("alibaba").getStockFirst(),
                equalTo("阿里巴巴"));
    }
}
