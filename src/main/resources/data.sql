insert into customer (id, name, address) select -1, 'Kalles Grustransporter AB', 'Cementvagen 8, 111 11 Sodertalje' where not exists (select id from customer where id = -1);
insert into customer (id, name, address) select -2, 'Johans Bulk AB', 'Balkvagen 12, 222 22 Stockholm' where not exists (select id from customer where id = -2);
insert into customer (id, name, address) select -3, 'Haralds Vardetransporter AB', 'Budgetvagen 1, 333 33 Uppsala' where not exists (select id from customer where id = -3);

insert into vehicle (id, vin) select -1, 'YS2R4X20005399401' where not exists (select id from vehicle where id = -1);
insert into vehicle (id, vin) select -2, 'VLUR4X20009093588' where not exists (select id from vehicle where id = -2);
insert into vehicle (id, vin) select -3, 'VLUR4X20009048066' where not exists (select id from vehicle where id = -3);
insert into vehicle (id, vin) select -4, 'YS2R4X20005388011' where not exists (select id from vehicle where id = -4);
insert into vehicle (id, vin) select -5, 'YS2R4X20005387949' where not exists (select id from vehicle where id = -5);
insert into vehicle (id, vin) select -6, 'YS2R4X20005387055' where not exists (select id from vehicle where id = -6);

insert into customers_vehicles (id, customer_id, vehicle_id, registration_no) select -1, -1, -1, 'ABC123' where not exists (select id from customers_vehicles where id = -1);
insert into customers_vehicles (id, customer_id, vehicle_id, registration_no) select -2, -1, -2, 'DEF456' where not exists (select id from customers_vehicles where id = -2);
insert into customers_vehicles (id, customer_id, vehicle_id, registration_no) select -3, -1, -3, 'GHI789' where not exists (select id from customers_vehicles where id = -3);

insert into customers_vehicles (id, customer_id, vehicle_id, registration_no) select -4, -2, -4, 'JKL012' where not exists (select id from customers_vehicles where id = -4);
insert into customers_vehicles (id, customer_id, vehicle_id, registration_no) select -5, -2, -5, 'MNO345' where not exists (select id from customers_vehicles where id = -5);

insert into customers_vehicles (id, customer_id, vehicle_id, registration_no) select -6, -3, -3, 'PQR678' where not exists (select id from customers_vehicles where id = -6);
insert into customers_vehicles (id, customer_id, vehicle_id, registration_no) select -7, -3, -6, 'STU901' where not exists (select id from customers_vehicles where id = -7);
