CREATE TABLE course_step_image
(
    id             VARCHAR(255) NOT NULL,
    course_step_id VARCHAR(255),
    image_data     BLOB,
    CONSTRAINT pk_course_step_image PRIMARY KEY (id)
);

ALTER TABLE course_step_image
    ADD CONSTRAINT FK_COURSE_STEP_IMAGE_ON_COURSE_STEP FOREIGN KEY (course_step_id) REFERENCES course_step (id);