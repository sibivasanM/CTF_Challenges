package com.example.securewebapp.controller;

import com.example.securewebapp.model.EncryptRequest;
import com.example.securewebapp.model.EncryptResponse;
import com.example.securewebapp.crypto.Secure;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/encrypt")
public class EncryptionController {

    @PostMapping
    public EncryptResponse encrypt(@RequestBody EncryptRequest request) {
        byte[] encryptedBytes = Secure.encryptStr(request.getPlaintext());
        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
        return new EncryptResponse(encryptedBase64);
    }
}

