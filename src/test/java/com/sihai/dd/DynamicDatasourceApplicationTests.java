package com.sihai.dd;

import com.sihai.dd.annotation.DataSource;
import com.sihai.dd.model.User;
import com.sihai.dd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DynamicDatasourceApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		List<User> list = userService.getAllUser();
		for (User user : list) {
			System.out.println(user);
		}
	}

}
