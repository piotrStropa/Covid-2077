package com.stropa.clients;

public interface Request {
    String getRequestURL();
    Method getRequestMethod();
    byte[] getRequestBody();
}
