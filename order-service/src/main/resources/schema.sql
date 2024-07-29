CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      user_id BIGINT NOT NULL,
                                      product_id BIGINT NOT NULL,
                                      quantity INT NOT NULL
);
