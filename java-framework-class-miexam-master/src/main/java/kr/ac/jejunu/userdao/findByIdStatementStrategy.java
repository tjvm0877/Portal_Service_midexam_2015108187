package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class findByIdStatementStrategy implements StatementStrategy {
    private Integer id;
    public findByIdStatementStrategy(Integer id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from  userinfo where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
