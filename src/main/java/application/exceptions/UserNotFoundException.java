package application.exceptions;

public class UserNotFoundException extends Exception{
    private String message;
    public UserNotFoundException(String exception, String message){
        super(exception);
        this.message = message;
    }

    public UserNotFoundException(String message) {
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
