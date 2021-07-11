INSERT INTO public.mt_est_part(
id, created_on, updated_on, is_active, name, seq, type)
VALUES (nextval('mt_est_part_id_seq'), now(), now(), true, '0', 0, 'ZERO'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '1', 1, 'ONE'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '2', 2, 'TWO'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '3', 3, 'THREE'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '4', 4, 'FOUR'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '>=5', 5, 'FIVE_OR_MORE');