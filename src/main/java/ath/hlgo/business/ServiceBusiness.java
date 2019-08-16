package ath.hlgo.business;

import ath.hlgo.model.User;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public interface ServiceBusiness {
    User performUserSearch(int userId);
}
