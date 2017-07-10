package ua.com.skeleton.data.net.error;


import java.io.IOException;

public class NoConnectionError extends IOException {

    @Override
    public String getMessage() {
        return "Connection is not available";
    }
}