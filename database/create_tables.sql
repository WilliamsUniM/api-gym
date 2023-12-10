CREATE TABLE `cities` (
                          `name` varchar(100) NOT NULL,
                          `code` int NOT NULL,
                          PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sites` (
                         `name` varchar(100) NOT NULL,
                         `code` bigint NOT NULL AUTO_INCREMENT,
                         `city` int NOT NULL,
                         PRIMARY KEY (`code`),
                         KEY `sites_FK` (`city`),
                         CONSTRAINT `sites_FK` FOREIGN KEY (`city`) REFERENCES `cities` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `email` varchar(100) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         `admin` tinyint(1) NOT NULL DEFAULT '0',
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `site` bigint NOT NULL,
                         PRIMARY KEY (`id`),
                         KEY `users_FK` (`site`),
                         CONSTRAINT `users_FK` FOREIGN KEY (`site`) REFERENCES `sites` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clients` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `document` bigint NOT NULL unique,
                           `name` varchar(100) NOT NULL,
                           `email` varchar(100) NOT NULL,
                           `phone` numeric(10) NOT NULL,
                           `site` bigint NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `clients_FK` (`site`),
                           CONSTRAINT `clients_FK` FOREIGN KEY (`site`) REFERENCES `sites` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payments` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `amount` bigint NOT NULL,
                            `start_at` date NOT NULL,
                            `finish_at` date NOT NULL,
                            `type` varchar(10) NOT NULL,
                            `client` bigint NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `payments_FK` (`client`),
                            CONSTRAINT `payments_FK` FOREIGN KEY (`client`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;