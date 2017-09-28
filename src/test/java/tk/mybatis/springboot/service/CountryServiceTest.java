package tk.mybatis.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.model.Country;

import static org.junit.Assert.*;

/**
 * Created by Dong_Liu
 * date：2017/9/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceTest {
    @Autowired
    private CountryService countryService;
    @Test
    public void getAll() throws Exception {
        Country country=new Country();
        country.setPage(5);
        country.setRows(5);
        countryService.getAll(country);
    }

}