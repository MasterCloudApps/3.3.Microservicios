-- create view
CREATE or replace VIEW loyalty.t_loyalty_card_number_view AS
SELECT
    id AS id,
    loyalty_card_number AS loyalty_card_number
FROM
    monolith.t_user;
