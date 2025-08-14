# Synopsis
This article is designed to tell the reader with high technical expertise about the table structures and such to be used in the database for this project

# Details
# Data information
The data will be structured in 2 tables: images, and objects

The Images table will include:
- id (primary key, autoincrement)
- uri (varchar(32))
- enableObjectDetection (boolean)

The ImageObjects table will include:
- id (primary key, autoincrement)
- name (varchar(32))
- imageId (int) (foreign key to images table)

This setup will have a one-to-many relationship between images (one) and objects (many)