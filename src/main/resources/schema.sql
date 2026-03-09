CREATE TABLE user_detail
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `client_id`   varchar(255) DEFAULT NULL,
    `customer_id` varchar(255) DEFAULT NULL,
    `email`       varchar(255) DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    `roles`       varchar(255) DEFAULT NULL,
    `status`      bit(1) NOT NULL,
    PRIMARY KEY (`id`) /*T![clustered_index] CLUSTERED */,
    KEY           `idx_client_email` (`client_id`,`email`),
    KEY           `idx_customer_id` (`customer_id`),
    UNIQUE KEY `uk_client_email` (`client_id`,`email`)
) AUTO_ID_CACHE=1;


CREATE TABLE complaint
(
    id             BIGINT NOT NULL AUTO_INCREMENT,
    assigned_to    varchar(255),
    category       varchar(255),
    client_id      varchar(255),
    created_at     datetime(6),
    created_by     varchar(255),
    customer_id    varchar(255),
    description    varchar(255),
    escalate_to    varchar(255),
    priority       varchar(255),
    reported_by    varchar(255),
    resolution     varchar(255),
    room_number    varchar(255),
    status_id      int,
    status_updates blob,
    tags           blob,
    title          varchar(255),
    updated_at     datetime(6),
    updated_by     varchar(255),
    watchers       blob,
    PRIMARY KEY (id)
) AUTO_ID_CACHE=1;


CREATE TABLE complaint_status
(
    id                BIGINT NOT NULL AUTO_INCREMENT,
    client_id         varchar(255),
    created_at        datetime(6),
    created_by        varchar(255),
    current_status_id int    NOT NULL,
    description       varchar(255),
    name              varchar(255),
    title             varchar(255),
    updated_at        datetime(6),
    updated_by        varchar(255),
    PRIMARY KEY (id)
) AUTO_ID_CACHE=1;


