insert into customer (dni, full_name, credit_card, total_flights, total_lodgings, total_tours, phone_number) VALUES
                                                                                                                 ('VIKI771012HMCRG093', 'Ragnar Lothbrok', '6473-9486-9372-0921', 0, 0, 0, '33-74-58-43'),
                                                                                                                 ('BBMB771012HMCRR022', 'Walter White', '4463-3326-9980-5454', 0, 0, 0, '55-83-32-22'),
                                                                                                                 ('WALA771012HCRGR054', 'Wednesday Addams', '6677-5244-94572-0165', 0, 0, 0, '33-24-41-54'),
                                                                                                                 ('GOTW771012HMRGR087', 'Khal Drogo', '6766-9484-9442-0222', 0, 0, 0, '55-78-33-11');

insert into roles(role_name) VALUES
                                ('ROLE_ADMIN'),
                                ('ROLE_USER'),
                                ('ROLE_MANAGER'),
                                ('ROLE_SUPERVISOR');


 INSERT INTO authorities (authority_name) VALUES
                                                ('SCOPE_read'),
                                                ('SCOPE_write'),
                                                ('SCOPE_delete'),
                                                ('SCOPE_export'),
                                                ('SCOPE_import');

INSERT INTO role_authorities (role_id, authority_id)
VALUES
    (1, 1), -- read
    (1, 2), -- write
    (1, 3), -- delete
    (1, 4), -- export
    (1, 5); -- import

INSERT INTO role_authorities (role_id, authority_id)
VALUES
    (2, 1);