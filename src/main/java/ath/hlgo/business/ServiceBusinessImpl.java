package ath.hlgo.business;

import ath.hlgo.data.DataLayerException;
import ath.hlgo.utils.Constants;
import ath.hlgo.data.Dao;
import ath.hlgo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBusinessImpl implements ServiceBusiness {

    @Autowired
    Dao<User> dao;

    @Override
    public User performUserSearch(int userId) {
        User result;

        if(userId > 0){
            result = dao.get(new User(userId));
        }else{
            result = new User(Constants.NOT_VALID_ID);
        }
        return result;
    }

    @Override
    public String performCreateUser(User u) {
        String result = Constants.ERROR_MSG;

        if (u != null){
            try {
                result = dao.create(u);
            } catch (DataLayerException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
