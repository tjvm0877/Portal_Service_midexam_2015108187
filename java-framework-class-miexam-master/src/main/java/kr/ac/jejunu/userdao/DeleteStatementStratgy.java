package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStratgy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        int id = (Integer) object;
        PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
