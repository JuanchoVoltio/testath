package ath.hlgo.utils;

public class Constants {
    public static final String SELECT_SQL = "SELECT * FROM app_users u WHERE u.id = ?";
    public static final int NOT_FOUND_ID = -1;
    public static final int NOT_VALID_ID = -2;
    public static final String NOT_FOUND_MSG = "User not found";
    public static final String NOT_VALID_ID_MSG = "Not valid id, must be a number bigger than 0";

    public static String getErrorMessage(int id) {

        String result = "";

        switch(id){
            case NOT_FOUND_ID:
                result = NOT_FOUND_MSG;
                break;
            case NOT_VALID_ID:
                result = NOT_VALID_ID_MSG;
        }

        return result;
    }
}
