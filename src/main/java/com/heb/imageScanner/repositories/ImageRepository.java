package com.heb.imageScanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heb.imageScanner.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findDistinctByObjectsNameIn(List<String> namesList);
}