package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try { connection = dataSource.getConnection();preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);

        resultSet = preparedStatement.executeQuery();
        resultSet.next();

        user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        } finally {
            try {
                resultSet.close();
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }

        }
        return user;
    }
    public void insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        connection = dataSource.getConnection();
        preparedStatement = connection.prepareStatement("insert into userinfo(name, password) VALUE (?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
        }
//        Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name, password) VALUE (?,?)", Statement.RETURN_GENERATED_KEYS);
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getPassword());
//        preparedStatement.executeUpdate();
//
//        ResultSet resultSet = preparedStatement.getGeneratedKeys();
//        resultSet.next();
//
//        user.setId(resultSet.getInt(1));


    }
}
