package com.javierito.javierito_importer.domain.ports.output;

public interface IEmailServer {

    void sendEmail(String to, String body);
    void sendCredentials(String to, String name, String lastName, String userName, String password);
}
