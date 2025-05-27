package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IClientService;
import com.javierito.javierito_importer.domain.models.ClientModels.NewClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @PostMapping("/insertClient")
    public ResponseEntity<?> insertClient(@RequestBody NewClient newClient) {

        var result = clientService.insertClient(newClient);

        if(result != true)
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/getClientByci")
    public ResponseEntity<?> getClientByci(@RequestBody String ci) {

        var result = clientService.getClient(ci);

        if (result != null)
                return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Could not get client", HttpStatus.NOT_FOUND);
    }
}
