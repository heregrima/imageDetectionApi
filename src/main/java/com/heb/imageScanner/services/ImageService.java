package com.heb.imageScanner.services;

import java.util.List;

import com.heb.imageScanner.models.Image;
import com.heb.imageScanner.repositories.ImageRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ImageService {

    @Autowired
    private ImageRepository ImageRepository;

    public List<Image> getAllUsers() {
        return ImageRepository.findAll();
    }
}