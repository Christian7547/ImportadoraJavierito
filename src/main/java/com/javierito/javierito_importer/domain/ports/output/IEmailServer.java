package com.javierito.javierito_importer.domain.ports.output;

public interface IEmailServer {

    void sendEmail(String to, String subject, String body);
}
