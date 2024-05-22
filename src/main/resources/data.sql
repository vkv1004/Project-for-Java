INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('STUDENT',CURDATE(),'DBA');

insert into `person` (`name`,`email`,`pwd`,`role_id`,`created_at`,`created_by`,`updated_at`,`updated_by`)
 values ('Admin', 'admin@gmail.com', '$2a$12$YRI8MSjVpyV6xRWpRJubSuj5U15Pv0T9UFo2VABtAzXRzkqW.7osa', 1, curdate(), 'Darya', curdate(), 'Darya')