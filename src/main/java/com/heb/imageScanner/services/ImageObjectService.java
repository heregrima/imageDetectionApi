package com.heb.imageScanner.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heb.imageScanner.models.ImageObject;
import com.heb.imageScanner.repositories.ImageObjectRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ImageObjectService {

    @Autowired
    private ImageObjectRepository ImageObjectRepository;

    public List<ImageObject> getAllUsers() {
        return ImageObjectRepository.findAll();
    }
}