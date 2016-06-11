package com.lbb.Test;

import com.lbb.model.User;
import com.lbb.service.UserService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

/**
 * Created by lbb on 2016/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestUserService {

    Logger logger = Logger.getLogger("TestUserService");

    @Autowired
    private UserService userService;

    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123");
        boolean b2 = userService.hasMatchUser("admin", "111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void findUserByUserName() {
        User user = userService.findUserByuserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

}
