package com.heb.imageScanner.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.heb.imageScanner.contracts.ImageApiConnectorInterface;

@Component
public class ImaggaApiConnector implements ImageApiConnectorInterface{
    @Value("${imagga.creds}")
    private String creds;
    @Value("${imagga.secret}")
    private String secret;

    public String generateCredString() {
        return creds.trim() + ":" + secret.trim();
    }

    public String getImageDetails(URL urlObject) throws Exception {
        String credentialsToEncode = generateCredString();
        String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));
        // int responseCode;
        BufferedReader connectionInput = null;
        String jsonResponse;

        try {
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestProperty("Authorization", "Basic " + basicAuth);
            connection.setRequestMethod("GET");

            // Note: future implementations should read this and throw an exception for non-200 responsee
            // rwhite: will implement if I have time
            // responseCode = connection.getResponseCode();

            connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonResponse = connectionInput.readLine();
        } catch (Exception e) {
            throw e;
        } finally {
            if (connectionInput != null) {
                try {
                    connectionInput.close();
                } catch (IOException e) {
                    throw new Exception(e.getMessage());
                }
            }
        }

        return jsonResponse;
        
    }
    
}
