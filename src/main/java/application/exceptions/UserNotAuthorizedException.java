package application.exceptions;

public class UserNotAuthorizedException  extends Exception{
    private String message;
    public UserNotAuthorizedException(String exception, String message){
        super(exception);
        this.message = message;
    }

    public UserNotAuthorizedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

