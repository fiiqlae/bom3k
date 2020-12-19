package data.exceptions;

public class UserIsNotLoggedInException extends Exception{
    public UserIsNotLoggedInException(String s) {
        super(s);
    }
}
