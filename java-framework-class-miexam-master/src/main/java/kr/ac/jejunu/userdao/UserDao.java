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
        try {
            connection = dataSource.getConnection();preparedStatement =
            connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            }
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
    }
    public void update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "update userinfo set name = ?, password = ? where id = ?"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "delete from userinfo where id = ?"
            );
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
