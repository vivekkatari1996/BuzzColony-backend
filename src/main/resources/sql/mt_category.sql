INSERT INTO public.mt_category(
	id, created_on, updated_on, is_active, name, seq, type)
	VALUES (nextval('mt_category_id_seq'), now(), now(), true, 'Dealership', 0, 'DEALERSHIP'),
           (nextval('mt_category_id_seq'), now(), now(), true, 'Syndicate', 1, 'SYNDICATE'),
         (nextval('mt_category_id_seq'), now(), now(), true, 'Patent', 2, 'PATENT'),
(nextval('mt_category_id_seq'), now(), now(), true, 'Patent Inprocess', 3, 'PATENT_INPROCESS');