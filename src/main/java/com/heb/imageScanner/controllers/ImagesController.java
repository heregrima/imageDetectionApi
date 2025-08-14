package com.heb.imageScanner.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.heb.imageScanner.contracts.ImageApiConnectorInterface;
import com.heb.imageScanner.models.Image;
import com.heb.imageScanner.models.ImageObject;
import com.heb.imageScanner.repositories.ImageRepository;
import com.heb.imageScanner.utils.ImaggaApiConnector;
import com.heb.imageScanner.utils.ImaggaScanner;
import com.heb.imageScanner.utils.JsonValueExtractor;
import com.heb.imageScanner.utils.MockImageApiConnector;

@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    protected ImageRepository imageRepository;

    @Value("${imagga.disable.api.call}")
    private boolean disableApiCall;

    @Value("${imagga.limit}")
    private int imageDetectionLimit;

    @Autowired
    private ImaggaApiConnector imaggaApiConnector;

    @GetMapping("/health")
    public String healthCheck() {
        // healthcheck
        return "image is getting hit correctly";
    }

    @GetMapping("")
    public @ResponseBody Iterable<Image> getAllImages(@RequestParam(required = false) String objects) {
        if (objects != null && !objects.isEmpty()) {
            List<String> namesList = Arrays.asList(objects.split(","));
            return imageRepository.findDistinctByObjectsNameIn(namesList);
        }
        return imageRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        // get image object by Id
        Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("image not found"));
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image imageRequest) {
        Image newImage = new Image();
        if (imageRequest.getLabel() == null || imageRequest.getLabel().isBlank()) {
            newImage.setLabel("image_" + System.currentTimeMillis()); // using timestamp to create a unique label
        } else {
            newImage.setLabel(imageRequest.getLabel());
        }

        newImage.setUri(imageRequest.getUri());
        newImage.setEnableObjectDetection(imageRequest.getEnableObjectDetection());

        if (Boolean.TRUE.equals(newImage.getEnableObjectDetection())) {
            // if enableObjectDetection is set, use the ImageScanner library to analyze the image and then add imageObjects associated to this image
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
                imageDataResponse = imaggaScanner.scanImageFromUrl(newImage.getUri());
            } catch (Exception e) {
                throw new RuntimeException("Image scanning failed", e);
            }
            JSONObject jsonImageResponse = new JSONObject(imageDataResponse);
            // based on the response, parse it to get all values under tags/en to collect all of the objects detected
            List<Object> objectsDetected = JsonValueExtractor.getAllValuesForKey(jsonImageResponse, "en");

            
            // run through each of the objects detected and set them to a list of ImageObjects
            Stream<ImageObject> objectStream = objectsDetected.stream()
                .map(label -> {
                    ImageObject imageObject = new ImageObject();
                    imageObject.setName(label.toString());
                    imageObject.setImage(newImage);
                    return imageObject;
                });

            // Apply limit only if imageDetectionLimit > 0
            if (imageDetectionLimit > 0) {
                objectStream = objectStream.limit(imageDetectionLimit);
            }

            List<ImageObject> imageObjects = objectStream.toList();

            newImage.setObjects(imageObjects);
        }

        // save the final constructed image
        Image savedImage = imageRepository.save(newImage);

        // set up the response to link the image with the ID
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedImage.getId())
            .toUri();
        // return the 201 response with the URI and the user object created

        return ResponseEntity.created(location).body(savedImage);
    }
    
}
