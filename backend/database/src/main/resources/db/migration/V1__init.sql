CREATE TABLE course
(
    id                VARCHAR(255) NOT NULL,
    title             VARCHAR(100) NOT NULL,
    short_description VARCHAR(500),
    miniature_id      VARCHAR(255),
    user_id           VARCHAR(255),
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE course_step
(
    id           VARCHAR(255) NOT NULL,
    order_number BIGINT       NOT NULL,
    title        VARCHAR(100) NOT NULL,
    description  VARCHAR(1000),
    course_id    VARCHAR(255),
    CONSTRAINT pk_course_step PRIMARY KEY (id)
);

CREATE TABLE course_step_modeling_product
(
    course_step_id      VARCHAR(255) NOT NULL,
    modeling_product_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_course_step_modeling_product PRIMARY KEY (course_step_id, modeling_product_id)
);

CREATE TABLE faction
(
    id      VARCHAR(255) NOT NULL,
    name    VARCHAR(20)  NOT NULL,
    game_id VARCHAR(255),
    CONSTRAINT pk_faction PRIMARY KEY (id)
);

CREATE TABLE game
(
    id          VARCHAR(255) NOT NULL,
    title       VARCHAR(20)  NOT NULL,
    type        VARCHAR(255) NOT NULL,
    producer_id VARCHAR(255),
    CONSTRAINT pk_game PRIMARY KEY (id)
);

CREATE TABLE miniature
(
    id         VARCHAR(255) NOT NULL,
    name       VARCHAR(20)  NOT NULL,
    type       VARCHAR(20),
    faction_id VARCHAR(255),
    CONSTRAINT pk_miniature PRIMARY KEY (id)
);

CREATE TABLE modeling_product
(
    id          VARCHAR(255) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    category    VARCHAR(50),
    producer_id VARCHAR(255),
    CONSTRAINT pk_modeling_product PRIMARY KEY (id)
);

CREATE TABLE paint
(
    id          VARCHAR(255) NOT NULL,
    name        VARCHAR(50)  NOT NULL,
    type        VARCHAR(255) NOT NULL,
    producer_id VARCHAR(255),
    CONSTRAINT pk_paint PRIMARY KEY (id)
);

CREATE TABLE paint_technique_mapping
(
    course_step_id VARCHAR(255) NOT NULL,
    paint_id       VARCHAR(255) NOT NULL,
    technique_id   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_paint_technique_mapping PRIMARY KEY (course_step_id, paint_id)
);

CREATE TABLE painting_technique
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(20)  NOT NULL,
    CONSTRAINT pk_painting_technique PRIMARY KEY (id)
);

CREATE TABLE producer
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(40)  NOT NULL,
    CONSTRAINT pk_producer PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       VARCHAR(255) NOT NULL,
    username VARCHAR(20)  NOT NULL,
    password VARCHAR(20)  NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_MINIATURE FOREIGN KEY (miniature_id) REFERENCES miniature (id);

ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE course_step
    ADD CONSTRAINT FK_COURSE_STEP_ON_COURSE FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE faction
    ADD CONSTRAINT FK_FACTION_ON_GAME FOREIGN KEY (game_id) REFERENCES game (id);

ALTER TABLE game
    ADD CONSTRAINT FK_GAME_ON_PRODUCER FOREIGN KEY (producer_id) REFERENCES producer (id);

ALTER TABLE miniature
    ADD CONSTRAINT FK_MINIATURE_ON_FACTION FOREIGN KEY (faction_id) REFERENCES faction (id);

ALTER TABLE modeling_product
    ADD CONSTRAINT FK_MODELING_PRODUCT_ON_PRODUCER FOREIGN KEY (producer_id) REFERENCES producer (id);

ALTER TABLE paint
    ADD CONSTRAINT FK_PAINT_ON_PRODUCER FOREIGN KEY (producer_id) REFERENCES producer (id);

ALTER TABLE course_step_modeling_product
    ADD CONSTRAINT fk_coustemodpro_on_course_step_entity FOREIGN KEY (course_step_id) REFERENCES course_step (id);

ALTER TABLE course_step_modeling_product
    ADD CONSTRAINT fk_coustemodpro_on_modeling_product_entity FOREIGN KEY (modeling_product_id) REFERENCES modeling_product (id);

ALTER TABLE paint_technique_mapping
    ADD CONSTRAINT fk_paitecmap_on_course_step_entity FOREIGN KEY (course_step_id) REFERENCES course_step (id);

ALTER TABLE paint_technique_mapping
    ADD CONSTRAINT fk_paitecmap_on_painting_technique_entity FOREIGN KEY (technique_id) REFERENCES painting_technique (id);