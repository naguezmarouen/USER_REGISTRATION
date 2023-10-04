package application.exceptions;

public class NotValidDataException extends Exception {
    private String message;
    public NotValidDataException(String exception, String message){
        super(exception);
        this.message = message;
    }

    public NotValidDataException(String message) {
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
