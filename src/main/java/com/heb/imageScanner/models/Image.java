package com.heb.imageScanner.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Image {

  private @Id
  @GeneratedValue Long id;

  private String uri;
  private String label;
  private boolean enableObjectDetection;

  @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<ImageObject> objects;

  public Image() {}

  public Image(String uri, String label) {
    this.uri = uri;
    this.label = label;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUri() {
    return this.uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public boolean getEnableObjectDetection() {
    return this.enableObjectDetection;
  }

  public void setEnableObjectDetection(boolean enableObjectDetection) {
    this.enableObjectDetection = enableObjectDetection;
  }

  public List<ImageObject> getObjects() {
    return this.objects;
  }

  public void setObjects(List<ImageObject> imageObjects) {
    this.objects = imageObjects;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Image))
      return false;
    Image role = (Image) o;
    return Objects.equals(this.id, role.id) && 
           Objects.equals(this.uri, role.uri) &&
           Objects.equals(this.label, role.label) &&
           Objects.equals(this.enableObjectDetection, role.enableObjectDetection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.uri, this.label, this.enableObjectDetection);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + this.id + ", " + 
           "uri='" + this.uri + '\'' + 
           "label='" + this.label + '\'' + 
           "enableObjectDetection='" + this.enableObjectDetection + '\'' + 
           '}';
  }
}