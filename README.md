# Overview
Interview exercise for an API that I created from scratch to use an image API (called Imaggo).  Uses 4 API routes (see below in API Specification) that exercises this library.

# Problem: Image Object Detection
## Overview
Build an HTTP REST API in Java for a service that ingests user images, analyzes them for object detection, and returns the enhanced content. A user of the full API should be able to:
- Upload an optionally labeled image and run image object detection on it.
- Retrieve all images and any metadata obtained from their analyses.
- Search for images based on detected objects.

## Object Detection
Image object detection can be performed using any 3rd-party API of your choosing (such as [Imagga](https://imagga.com/), or with a process managed by your backend. The only requirement is that it must return a list of object names detected within that image.

## Database
- A persistent datastore is required. Any SQL or NoSQL variation is valid.

## API Specification
- All endpoints should implement error handling and return HTTP codes reflecting the level of failure (client vs. system).
- Unit and/or integration tests for your code are preferred.

### POST /images
- Send a JSON request body including an internet URL referencing an image, an optional label for the image, and an optional field to enable object detection.
- Returns an HTTP 200 OK with a JSON response body including the image data, its label (generate one if the user did not provide it), its identifier provided by the persistent data store, and any objects detected (if object detection was enabled).
### GET /images
- Return HTTP 200 OK with JSON response containing all image metadata.
### GET /images?objects=
- Returns an HTTP 200 OK with a JSON response body containing only images that have the detected objects specified in the query parameter.
### GET /images/{imageId}
- Returns HTTP 200 OK with a JSON response containing image metadata for the specified image.

### Setup Mysql container

* For port forwarding:
`docker run -d --name test-mysql -e MYSQL_ROOT_PASSWORD=abc123 -p 3307:3306 mysql`
* For no port forwarding:
`docker run --name test-mysql -e MYSQL_ROOT_PASSWORD=[pick something, but remember it] -d mysql`

#### Accessing Mysql container

```
docker exec -it container_name bash
mysql -uroot -p
```

#### Accessing Mysql container

```
docker exec -it container_name bash
mysql -uroot -p
```

#### Accessing Mysql instance directly from local

```
mysql --host=127.0.0.1 --port=3307 -u root -p
```

### Starting this instance
Run this command:
`./gradlew bootRun`