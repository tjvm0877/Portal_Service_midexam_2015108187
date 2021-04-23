package kr.ac.jejunu.userdao;

import java.sql.*;

abstract public class UserDao {
    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴
        return user;
    }
    public void insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name, password) VALUE (?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        user.setId(resultSet.getInt(1));
    }

    abstract public Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/portal_service?" +
//                "characterEncoding=utf-8&serverTimezone=UTC", "jeju", "1234");
//    }
}
