insert into fly (origin_lat, origin_lng, destiny_lng, destiny_lat, origin_name, destiny_name, aero_line, price) VALUES
                                                                                                                    (99.9999, 88.8888, 11.1111, 22.2222, 'Mexico', 'Grecia', 'aero_gold', 43.00),
                                                                                                                    (11.1111, 22.2222, 99.9999, 88.8888, 'Grecia', 'Mexico','aero_gold', 33.33),
                                                                                                                    (99.9999, 88.8888, 88.8888, 77.7777, 'Mexico', 'Iceland', 'aero_gold', 48.70),
                                                                                                                    (99.9999, 88.8888, 88.8888, 77.7777, 'Iceland', 'Mexico', 'aero_gold', 12.99),
                                                                                                                    (88.8888, 77.7777,  11.1111, 22.2222, 'Iceland', 'Gracia', 'aero_gold', 85.98),
                                                                                                                    (11.1111, 22.2222, 88.8888, 77.7777, 'Gracia', 'Iceland', 'aero_gold', 29.99),
                                                                                                                    (99.9999, 88.8888, 11.1111, 22.2222, 'Mexico', 'Grecia', 'blue_sky', 25.65),
                                                                                                                    (11.1111, 22.2222, 99.9999, 88.8888, 'Grecia', 'Mexico', 'blue_sky', 12.99),
                                                                                                                    (44.4444, 55.555,  11.1111, 22.2222, 'Canada', 'Mexico', 'aero_gold', 19.99),
                                                                                                                    (11.1111, 22.2222, 44.4444, 55.5555, 'Mexico', 'Canada', 'aero_gold', 15.65),
                                                                                                                    (99.9999, 88.8888, 88.8888, 77.7777, 'Mexico', 'Iceland', 'blue_sky', 42.99),
                                                                                                                    (99.9999, 88.8888, 88.8888, 77.7777, 'Iceland', 'Mexico', 'blue_sky', 21.54),
                                                                                                                    (88.8888, 77.7777,  11.1111, 22.2222, 'Iceland', 'Gracia', 'blue_sky', 12.00),
                                                                                                                    (44.4444, 55.555,  11.1111, 22.2222, 'Canada', 'Mexico', 'blue_sky', 16.99),
                                                                                                                    (11.1111, 22.2222, 44.4444, 55.5555, 'Mexico', 'Canada', 'blue_sky', 14.65);

insert into ticket (id, price, fly_id, customer_id, departure_date, arrival_date, purchase_date) VALUES
                                                                                                     ('12345678-1234-5678-2236-567812345678', 330.05, 1, 'BBMB771012HMCRR022', '2024-08-01 14:00:00', '2024-08-01 16:00:00',now()),
                                                                                                     ('22345678-1234-5678-3235-567812345678', 220.33, 1, 'VIKI771012HMCRG093', '2024-08-01 14:00:00', '2024-08-01 16:00:00', now()),
                                                                                                     ('32345678-1234-5678-4234-567812345678', 320.00, 4, 'VIKI771012HMCRG093', '2024-08-01 12:00:00', '2024-08-01 15:00:00', now()),
                                                                                                     ('42345678-1234-5678-5233-567812345678', 560.77, 7, 'VIKI771012HMCRG093', '2024-08-12 20:00:00', '2020-01-14 22:00:00', now());
