-- SQL_SAFE_UPDATES 설정 변경
SET SQL_SAFE_UPDATES = 0;

ALTER TABLE `chukchuk_db`.`user` 
CHANGE COLUMN `sex` `sex` ENUM('MALE', 'FEMALE') NULL DEFAULT NULL ;


-- 유저 키, 몸무게 디폴트 NULL 처리
SET SQL_SAFE_UPDATES = 0;
ALTER TABLE `chukchuk_db`.`user` 
CHANGE COLUMN `height` `height` int NULL DEFAULT NULL,
CHANGE COLUMN `weight` `weight` int NULL DEFAULT NULL;
