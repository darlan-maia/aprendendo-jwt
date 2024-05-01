INSERT INTO
    tb_user (username, password)
VALUES
    ('joao', '$2a$10$4TJqg.jZLyjVaHM3AZqbx.XbebjYIHH8wGfU4f031FGNokUTSXq9m'),
    ('maria', '$2a$10$86irHv.eYWGo4zxtE61r6O1LDcUacsWwkazmrh.ZYQwi8cCyr0MXW');

INSERT INTO
    tb_role (name)
VALUES
    ('USER'), ('ADMIN');

INSERT INTO
    tb_user_role
VALUES
    (1, 1), (2, 2);