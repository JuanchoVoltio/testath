package ath.hlgo.data;

import ath.hlgo.utils.Constants;
import ath.hlgo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UsersDao implements Dao<User> {

    @Autowired
    DataSource dataSource;

    @Override
    public User get(User u) {
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement selectStatement = conn.prepareStatement(Constants.SELECT_SQL)
        ){
            selectStatement.setInt(1, u.getId());
            ResultSet dbData = selectStatement.executeQuery();
            toDTO(dbData, u);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public void toDTO(ResultSet resultSet, User dto) throws SQLException {
        if(resultSet.next()) {
            dto.setFirstName(resultSet.getString("first_name"));
            dto.setLastName(resultSet.getString("last_name"));
        }else{
            dto.setId(Constants.NOT_FOUND_ID);
        }
    }
}
