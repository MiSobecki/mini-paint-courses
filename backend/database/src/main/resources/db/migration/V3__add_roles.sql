CREATE TABLE role
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(30)  NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_entity_id VARCHAR(255) NOT NULL,
    roles_id       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_entity_id, roles_id)
);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role_entity FOREIGN KEY (roles_id) REFERENCES role (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user_entity FOREIGN KEY (user_entity_id) REFERENCES users (id);