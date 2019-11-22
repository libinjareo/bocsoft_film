package com.binsoft.film;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.binsoft.film.dao.entity.NextUser;
import com.binsoft.film.dao.mapper.NextUserMapper;
import com.binsoft.film.example.dao.UserMapper;
import com.binsoft.film.example.dao.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BinFilmApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private NextUserMapper nextUserMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void mybatisHelloWorld(){

		AbstractWrapper wrapper = new QueryWrapper();
		wrapper.eq("uuid",4);
		//List<User> users = userMapper.selectList(null);

		List<User> users = userMapper.selectList(wrapper);

		users.forEach(System.out::println);

	}

	@Test
	public void mybatisGeneratorTest(){
		List<NextUser> nextUsers = nextUserMapper.selectList(null);
		nextUsers.forEach(System.out::println);
	}

	@Test
	public void testAdd(){
		NextUser nextUser = new NextUser();
		nextUser.setUserName("bin");
		nextUser.setUserPwd("666");

		int success = nextUserMapper.insert(nextUser);
		System.out.println("新增成功！" + success);

	}

	@Test
	public void testUpdate(){

		NextUser nextUser = new NextUser();
		nextUser.setUserPwd("999999");

		AbstractWrapper wrapper = new UpdateWrapper();
		wrapper.eq("user_name","bin");

		int update = nextUserMapper.update(nextUser, wrapper);

		System.out.println("修改成功! " + update);

	}

	@Test
	public void testDelete(){

		Integer id = 6;

		final int delete = nextUserMapper.deleteById(id);
		System.out.println("删除成功! " + delete);

	}

	@Test
	public void testQueryById(){

		Integer id = 5;

		NextUser nextUser = nextUserMapper.selectById(id);
		System.out.println("查询结果:" + nextUser);

	}

}
