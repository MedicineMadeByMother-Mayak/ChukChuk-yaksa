-- 24.03.20 업데이트 내역
-- SQL_SAFE_UPDATES 설정 변경
SET SQL_SAFE_UPDATES = 0;

ALTER TABLE `chukchuk_db`.`user` 
CHANGE COLUMN `sex` `sex` ENUM('MALE', 'FEMALE') NULL DEFAULT NULL ;


-- 유저 키, 몸무게 디폴트 NULL 처리
SET SQL_SAFE_UPDATES = 0;
ALTER TABLE `chukchuk_db`.`user` 
CHANGE COLUMN `height` `height` int NULL DEFAULT NULL,
CHANGE COLUMN `weight` `weight` int NULL DEFAULT NULL;



-- 24.03.25 업데이트 내역
-- 약 테이블 default 변경
ALTER TABLE `pill`
MODIFY COLUMN `warning_pregnant` BOOLEAN DEFAULT false,
MODIFY COLUMN `warning_use_date` BOOLEAN DEFAULT false,
MODIFY COLUMN `warning_elders` BOOLEAN DEFAULT false,
MODIFY COLUMN `warning_together` BOOLEAN DEFAULT false;



-- 약 테이블 default false, 전부 true로 변경 (테스트 편하게 하기 위해서)
update pill
	set warning_pregnant = true,
		warning_use_date = true,
		warning_elders = true, 
		warning_together = true,
        image_url = "../setting/DB/MySQL Safe Update 해제.png",
        type = "진통제 (painkiller)";
