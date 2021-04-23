package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    static UserDao userDao;

    @BeforeAll
    public static void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "재현";
        String password = "1234";
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String password = "1234";
        String name = "재현";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);

        User insertedUser = userDao.findById(user.getId());

        assertThat(user.getId(), greaterThan(0));
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String password = "1234";
        String name = "재현";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        user.setName("김재현");
        user.setPassword("1234");

        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());

        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(user.getName()));
        assertThat(updatedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        String password = "1234";
        String name = "재현";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());

        assertThat(deletedUser, nullValue());
    }


//    @Test
//    public void HallaGet() throws SQLException, ClassNotFoundException {
//        Integer id = 1;
//        String name = "재현";
//        String password = "1234";
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        User user = userDao.findById(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    public void HallaInsert() throws SQLException, ClassNotFoundException {
//        String password = "1234";
//        String name = "재현";
//
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        userDao.insert(user);
//
//        User insertedUser = userDao.findById(1);
//
//        assertThat(user.getId(), greaterThan(0));
//        assertThat(insertedUser.getId(), is(user.getId()));
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//    }
}
