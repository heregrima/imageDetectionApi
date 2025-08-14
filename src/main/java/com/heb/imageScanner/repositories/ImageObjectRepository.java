package com.heb.imageScanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heb.imageScanner.models.ImageObject;

@Repository
public interface ImageObjectRepository extends JpaRepository<ImageObject, Long> {
}