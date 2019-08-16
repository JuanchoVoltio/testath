package ath.hlgo.data;

import ath.hlgo.model.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public interface Dao<T> {

    T get(T id);
    void toDTO(ResultSet resultSet, T dto) throws SQLException;

}
