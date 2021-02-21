INSERT INTO public.mt_est_amount(
id, created_on, updated_on, is_active, name, seq, type)
VALUES (nextval('mt_est_amount_id_seq'), now(), now(), true, 'less than 10,000', 0, 'LESS_THAN_10000'),
 (nextval('mt_est_amount_id_seq'), now(), now(), true, '10,000-50,000', 1, 'BETWEEN_10000_TO_50000'),
 (nextval('mt_est_amount_id_seq'), now(), now(), true, '50,000-1,00,000', 2, 'BETWEEN_50000_TO_100000'),
 (nextval('mt_est_amount_id_seq'), now(), now(), true, '1,00,000-10,00,000', 3, 'BETWEEN_100000_TO_1000000'),
 (nextval('mt_est_amount_id_seq'), now(), now(), true, '10,00,000-50,00,000', 4, 'BETWEEN_1000000_TO_5000000'),
 (nextval('mt_est_amount_id_seq'), now(), now(), true, '50,00,000-1,00,00,000', 5, 'BETWEEN_5000000_TO_10000000'),
 (nextval('mt_est_amount_id_seq'), now(), now(), true, 'Above 1,00,00,000', 6, 'ABOVE_10000000');