INSERT INTO public.mt_est_part(
id, created_on, updated_on, is_active, name, seq, type)
VALUES (nextval('mt_est_part_id_seq'), now(), now(), true, '1', 0, 'ONE'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '2', 1, 'TWO'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '3', 2, 'THREE'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '4', 3, 'FOUR'),
 (nextval('mt_est_part_id_seq'), now(), now(), true, '>=5', 4, 'FIVE_OR_MORE');