package com.sihai.dd;

import com.sihai.dd.annotation.DataSource;
import com.sihai.dd.model.User;
import com.sihai.dd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class DynamicDatasourceApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void test01() {
		userService.test();
	}

	@Test
	void contextLoads() {
		List<User> list = userService.getAllUser();
		for (User user : list) {
			System.out.println(user);
		}
	}

}
