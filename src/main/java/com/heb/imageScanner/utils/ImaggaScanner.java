package com.heb.imageScanner.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heb.imageScanner.contracts.ImageApiConnectorInterface;
import com.heb.imageScanner.utils.pojos.ImageData;

public class ImaggaScanner {
    // NOTE: would store these in Hashicorp Vault - haven't worked with a lot
    String endpoint_url = "https://api.imagga.com/v2/tags";

    ImageApiConnectorInterface imageApiConnector;

    public ImaggaScanner(ImageApiConnectorInterface imageApiConnector) {
        this.imageApiConnector = imageApiConnector;
    }

    public void parseJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"name\":\"John Doe\",\"age\":30}";
        try {
            ImageData data = objectMapper.readValue(jsonString, ImageData.class);
            System.out.println("Data: " + data.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String encodeUrlForImagga(String url) {
        // handle special characters in the url (taking the nuclear option here)
        return url.replace(" ", "%20")
                .replace("\"", "%22")
                .replace("<", "%3C")
                .replace(">", "%3E")
                .replace("#", "%23")
                .replace("%", "%25")
                .replace("{", "%7B")
                .replace("}", "%7D")
                .replace("|", "%7C")
                .replace("\\", "%5C")
                .replace("^", "%5E")
                .replace("[", "%5B")
                .replace("]", "%5D")
                .replace("`", "%60");
    }

    public String scanImageFromUrl(String image_url) throws Exception {
        String encodedImageUrl = URLEncoder.encode(image_url, StandardCharsets.UTF_8);
        // String encodedImageUrl = encodeUrlForImagga(image_url);
        String url = endpoint_url + "?image_url=" + encodedImageUrl;
        String jsonResponse = "";
        try {
            URI uri = new URI(url);
            URL urlObject = uri.toURL();
            jsonResponse = imageApiConnector.getImageDetails(urlObject);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return jsonResponse;
    }
}
