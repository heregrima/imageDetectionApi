# Synopsis
This documentation is designed for me to set up a list of things To Do, and document my Definition of Done

# Summary
Normally, I'd set this up as a set of issues, including use cases, stakeholders, and acceptance criteria.  But since issues (and wiki) aren't set up for the GitHub repo and I didn't feel it was worth asking to enable, I'm setting this up as a means to track progress and inform the reviewers what I was working through

# Details
## Base To Do List
This list is stuff that I want to accomplish for the exercise
- set up Swagger documentation
    - includes learning Swagger from scratch
    - export to YAML and include with this checkout (see documenttion/PayloadInformation)
- set up ImageScanner
    - get Imagga and look into how it works
- implement JSON Value Extractor (to get all of the objects detected through the scanner)
    - learn how JSON is manipulated and try to pull all of the tags/en at once into a list
- write test for ImageScanner (after getting all the objects)
- set up database docker setup
- set up POSTMAN collection
- set up POST route for images
- test with Postman
- set up GET route for images/{imageid}
- test with Postman
- set up GET route for images
- test with Postman
- set up GET route with query param for images with a detected object
- test with Postman
- write at least one unit test and one functional test (which should be good enough)

## Stretch goals
This list is stuff that I would like to stretch for if I have time
- AWS deploy (I'm going to try to go for this one as my first priority past the core functionality)
- refactor to use service/repository pattern to separate business and data logic
- fill in the rest of the unit tests
- set up various items as config settings pulled from the standard config file for Spring Boot

## Expected Challenges
- Used Swagger to make small changes, but never set up an API doc from scratch
- Don't know Imagga
    - need an account to set things up
    - need to discover how the API is used
    - need to make sure to test everything for the library facade
- Routes
    - have to remember how filters are set up
    - have code that I created that should set up everything else
- Setting up the database
    - have code that I've created that should set up this for myself
- Probably will need to troubleshoot the Swagger API doc, and the Postman stuff
- Stay within SOLID practices
