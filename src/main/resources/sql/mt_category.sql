INSERT INTO public.mt_category(
	id, created_on, updated_on, is_active, name, seq, type)
	VALUES (nextval('mt_category_id_seq'), now(), now(), true, 'DealerShip', 0, 'DEALERSHIP'),
           (nextval('mt_category_id_seq'), now(), now(), true, 'Franchise', 1, 'Franchise'),
           (nextval('mt_category_id_seq'), now(), now(), true, 'JOB Search', 2, 'JOB_SEARCH'),
           (nextval('mt_category_id_seq'), now(), now(), true, 'Patent', 3, 'PATENT'),
           (nextval('mt_category_id_seq'), now(), now(), true, 'Patent Inprocess', 4, 'PATENT_INPROCESS'),
           (nextval('mt_category_id_seq'), now(), now(), true, 'Sales', 5, 'SALES'),
           (nextval('mt_category_id_seq'), now(), now(), true, 'Syndicate', 6, 'SYNDICATE');