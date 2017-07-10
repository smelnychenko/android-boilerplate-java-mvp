package ua.com.skeleton.data.repository;

import com.google.gson.Gson;
import ua.com.skeleton.data.net.error.ResponseErrorEntity;
import ua.com.skeleton.data.net.error.RestApiErrorException;
import ua.com.skeleton.data.net.wrapper.ResponseErrorWrapper;

import java.io.IOException;

import retrofit2.Response;
import ua.com.skeleton.domain.error.DomainError;

public abstract class RestApiRepository {

    protected void handleResponseError(Response response) {
        if (!response.isSuccessful()) {
            ResponseErrorWrapper errorWrapper;
            try {
                errorWrapper = new Gson().fromJson(response.errorBody().string(), ResponseErrorWrapper.class);
                ResponseErrorEntity error = errorWrapper.getError();
                throw new RestApiErrorException(error.getMessage(), error.getStatus());
            } catch (IOException | NullPointerException e) {
                throw new RestApiErrorException(response.message(), response.code());
            }
        }
    }

    //TODO should be domain error
    protected DomainError processErrorResponse(Response response) {
        return new DomainError(DomainError.ErrorType.SOME_HTTP_ERROR);
    }
}
