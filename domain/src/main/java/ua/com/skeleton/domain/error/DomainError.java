package ua.com.skeleton.domain.error;


public class DomainError extends Throwable {

    public enum ErrorType  {
        CONNECTIVITY_LOST_ERROR,
        SOME_HTTP_ERROR
    }

    private ErrorType errorType;

    public DomainError(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
