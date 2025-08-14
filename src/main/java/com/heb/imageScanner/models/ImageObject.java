package com.heb.imageScanner.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ImageObject {

  private @Id
  @GeneratedValue Long id;
  
  private String name;
  @ManyToOne
  @JoinColumn(name = "image_id") // FK column in ImageObject table
  @JsonBackReference
  private Image image;

  public ImageObject() {}

  public ImageObject(String name) {
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name; 
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof ImageObject))
      return false;
    ImageObject imageObject = (ImageObject) o;
    return Objects.equals(this.id, imageObject.id) && 
           Objects.equals(this.name, imageObject.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + this.id + ", " + 
           "firstName='" + this.name + "\'', " + 
           '}';
  }
}