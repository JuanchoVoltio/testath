package ath.hlgo.test.data;

import ath.hlgo.data.DataLayerException;
import ath.hlgo.data.UsersDao;
import ath.hlgo.model.User;
import ath.hlgo.test.app.Application;
import ath.hlgo.utils.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class UsersDaoTest {

    @Mock
    DataSource dataSource;

    @Mock
    ResultSet resultSet;

    @Mock
    Connection conn;

    @Mock
    PreparedStatement statement;

    @Autowired
    @InjectMocks
    UsersDao testSubject;

    @Test
    public void should_return_a_empty_object_with_error_id_when_result_set_is_empty() throws SQLException {
        //GIVEN
        Mockito.when(resultSet.next()).thenReturn(false);
        User testUser = new User(100);

        //WHEN
        testSubject.toDTO(resultSet, testUser);

        //THEN
        Assert.assertEquals(Constants.NOT_FOUND_MSG, Constants.getErrorMessage(testUser.getId()));
    }

    /*
     * Prueba para la verificación de comportamientos relacionados con excepciones.
     */
    @Test(expected = DataLayerException.class)
    public void should_throw_exception_when_error_occurs() throws SQLException, DataLayerException {
        //GIVEN
        User testUser = new User(100);
        Mockito.when(dataSource.getConnection()).thenReturn(conn);
        Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);
        Mockito.when(statement.execute()).thenThrow(new SQLException("SQL ERROR!"));

        //WHEN, THEN
        testSubject.create(testUser);
    }
}