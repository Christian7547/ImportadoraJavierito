package com.javierito.javierito_importer.infrastructure.adapters.interfaces.output;

public interface IEmailServer {

    void sendEmail(String to, String subject, String body);
}
