CREATE TABLE payments (
      transaction_id BIGSERIAL,
      order_id BIGINT NOT NULL,
      receiver_id BIGINT NOT NULL,
      status INT NOT NULL DEFAULT 1
);
