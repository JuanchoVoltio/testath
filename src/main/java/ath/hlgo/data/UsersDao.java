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
    public String create(User u) throws DataLayerException {
        String result;
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement selectStatement = conn.prepareStatement(Constants.INSERT_SQL)
        ){
            selectStatement.setString(1, u.getFirstName());
            selectStatement.setString(2, u.getLastName());
            result = (selectStatement.executeUpdate() != 0) ? Constants.OK_MSG : Constants.ERROR_MSG;
            conn.commit();
        } catch (SQLException ex) {
            throw new DataLayerException(Constants.ERROR_MSG + " " + ex.getMessage(), ex);
        }

        return result;
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
