package ath.hlgo.test.business.impl;

import ath.hlgo.data.DataLayerException;
import ath.hlgo.utils.Constants;
import ath.hlgo.data.Dao;
import ath.hlgo.model.User;
import ath.hlgo.test.app.Application;
import ath.hlgo.business.ServiceBusinessImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ServiceBusinessImplTest {

    @Mock
    Dao<User> dao;

    @Spy
    @InjectMocks
    ServiceBusinessImpl testSubject;

    User testUser;

    @Before
    public void setUp() throws Exception {
        testUser = new User(0, "Richie", "Sambora");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_return_an_user_object_when_id_is_ok(){
        //GIVEN
        int testId = 1;
        User userFound;
        Mockito.when(dao.get(Mockito.any(User.class))).thenReturn(testUser);

        //WHEN
        userFound = testSubject.performUserSearch(testId);

        //THEN
        assertTrue(userFound.equals(testUser));
    }

    @Test
    public void should_return_error_message_when_id_is_not_ok(){
        //GIVEN
        int testId = 0;
        User userFound;

        //WHEN
        userFound = testSubject.performUserSearch(testId);

        //THEN
        assertTrue(Constants.getErrorMessage(userFound.getId()).equals(Constants.NOT_VALID_ID_MSG));
    }


    @Test
    public void should_return_error_when_exception_is_thrown() throws DataLayerException {
        //GIVEN
        String result;
        Mockito.when(dao.create(Mockito.any(User.class))).thenThrow(new DataLayerException("Error", new Exception()));

        //WHEN
        result = testSubject.performCreateUser(testUser);

        //THEN
        Assert.assertEquals(Constants.ERROR_MSG, result);
    }
}