CREATE TABLE IF NOT EXISTS payment (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       user_id BIGINT NOT NULL,
                                       order_id BIGINT NOT NULL,
                                       amount DOUBLE NOT NULL,
                                       status VARCHAR(255) NOT NULL
    );
