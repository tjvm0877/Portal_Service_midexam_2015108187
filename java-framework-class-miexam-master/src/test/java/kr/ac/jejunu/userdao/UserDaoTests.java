package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void JejuGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "재현";
        String password = "1234";
        UserDao userDao = new JejuUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    public void JejuInsert() throws SQLException, ClassNotFoundException {
        String password = "1234";
        String name = "재현";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new JejuUserDao();
        userDao.insert(user);

        User insertedUser = userDao.findById(1);

        assertThat(user.getId(), greaterThan(0));
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void HallaGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "재현";
        String password = "1234";
        UserDao userDao = new HallaUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    public void HallaInsert() throws SQLException, ClassNotFoundException {
        String password = "1234";
        String name = "재현";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new HallaUserDao();
        userDao.insert(user);

        User insertedUser = userDao.findById(1);

        assertThat(user.getId(), greaterThan(0));
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }
}
