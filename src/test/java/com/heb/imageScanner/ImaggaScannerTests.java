package com.heb.imageScanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.heb.imageScanner.contracts.ImageApiConnectorInterface;
import com.heb.imageScanner.utils.ImaggaApiConnector;
import com.heb.imageScanner.utils.ImaggaScanner;
import com.heb.imageScanner.utils.JsonValueExtractor;
import com.heb.imageScanner.utils.MockImageApiConnector;

@SpringBootTest
public class ImaggaScannerTests {
    @Value("${imagga.disable.api.call}")
    private boolean disableApiCall;

    @Autowired
    private ImaggaApiConnector imaggaApiConnector;

    @Test
    public void testConfig() throws Exception {
        String my_image_url = "https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png";

        ImageApiConnectorInterface imageApiConnector;

        if (disableApiCall) {
            // mock the ImaggoScanner, mock out the API call
            imageApiConnector = new MockImageApiConnector();
        } else {
            // run the ImaggoScanner as is
            imageApiConnector = imaggaApiConnector;
        }

        ImaggaScanner imaggaScanner = new ImaggaScanner(imageApiConnector);
        String imageDataResponse;

        try {
            imageDataResponse = imaggaScanner.scanImageFromUrl(my_image_url);
        } catch (Exception e) {
            throw e;
        }
        JSONObject jsonImageResponse = new JSONObject(imageDataResponse);
        List<Object> objectsDetected = JsonValueExtractor.getAllValuesForKey(jsonImageResponse, "en");
        List<String> expectedListSubset = Arrays.asList("confetti", "paper", "design", "holiday");

        assertTrue(objectsDetected.containsAll(expectedListSubset));

    }

    @Test
    public void testCatPicture() throws Exception {
        String my_image_url = "https://images.squarespace-cdn.com/content/v1/607f89e638219e13eee71b1e/1684821560422-SD5V37BAG28BURTLIXUQ/michael-sum-LEpfefQf4rU-unsplash.jpg";

        ImageApiConnectorInterface imageApiConnector;

        if (disableApiCall) {
            // mock the ImaggoScanner, mock out the API call
            imageApiConnector = new MockImageApiConnector();
        } else {
            // run the ImaggoScanner as is
            imageApiConnector = imaggaApiConnector;
        }

        ImaggaScanner imaggaScanner = new ImaggaScanner(imageApiConnector);
        String imageDataResponse;

        try {
            imageDataResponse = imaggaScanner.scanImageFromUrl(my_image_url);
        } catch (Exception e) {
            throw e;
        }
        JSONObject jsonImageResponse = new JSONObject(imageDataResponse);
        List<Object> objectsDetected = JsonValueExtractor.getAllValuesForKey(jsonImageResponse, "en");
        List<String> expectedListSubset = Arrays.asList("cat");

        assertTrue(objectsDetected.containsAll(expectedListSubset));

        
    }
    
}