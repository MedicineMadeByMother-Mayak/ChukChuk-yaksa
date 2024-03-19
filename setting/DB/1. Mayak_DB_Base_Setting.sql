-- 모든 테이블 잠금 해제
UNLOCK TABLES;

-- 모든 트랜잭션 롤백
ROLLBACK;

-- 데이터베이스 삭제
DROP DATABASE IF EXISTS chukchuk_db;

CREATE DATABASE  IF NOT EXISTS `chukchuk_db`;
USE `chukchuk_db`;

-- ------------------------------
-- 모든건 소문자 + snake_case으로 작성 진행
-- 외래키 이름 설정 디폴트 : FK_[테이블명]_[컬럼명] 
-- 외래키 이름 설정 예시 : FK_ocr_pills_pill_id
-- 현재 spring 디폴트가 datetime(6)인데 백만분의 1까지 저장 가능하다고 함
-- default인 datetime만 쓰면 'YYYY-MM-DD HH:MM:SS'로 초까지 딱 저장됨 (datetime(6) -> datetime으로 수정 완료)
-- 어디는 ENUM이 대문자, 어디는 소문자? 
-- 		-> ENUM은 모두 대문자로 통일 (mysql은 기본적으로 대소문자 가리진 않지만, ENUM은 대소문자 구분하는 것을 확인)
-- 테이블간 외래키 참조 되어있으면 삭제 안되는 이슈 
-- 		-> CASCADE 추가로 테이블 삭제시 외래키 참조도 같이 삭제되도록 수정 완료 
-- 		-> CASCADE해도 다른 테이블에서 참조하면 삭제 불가 이슈
-- 		-> Database를 통째로 날리는 코드로 수정 (최상단 코드 3줄)

-- Q. 토큰 text 길이가 varchar(100)이면 충분? 
-- 		-> 짧을 것 같아서 일단 varchar(1000)로 수정해뒀음 (추후에 줄여도 됨)
-- Q. user_pill_effect_to_tag 테이블에 user_pill_effect_to_tag_id가 없음..!?!? (일단 ERD 기준으로 진행하였음)
-- ------------------------------

-- DROP TABLE IF EXISTS `user` CASCADE;
CREATE TABLE `user` (
  `user_id` 			bigint 														NOT NULL 			AUTO_INCREMENT,
  `user_name` 			varchar(20) 							DEFAULT NULL,
  `social_code` 		enum('NAVER', 'GOOGLE', 'KAKAO')		DEFAULT NULL,
  `social` 				varchar(100)							DEFAULT NULL,
  `birth` 				datetime     							DEFAULT NULL,
  `blood_type` 			enum('A', 'B', 'AB', 'O')				DEFAULT NULL,
  `height` 				int 														NOT NULL,
  `weight` 				int 														NOT NULL,
  `sex` 				enum('male', 'female')					DEFAULT NULL,
  `token` 				varchar(500) 							DEFAULT NULL,
  `create_date` 		datetime      							DEFAULT NOW(),
  `modify_date` 		datetime      							DEFAULT NULL,	
  `is_delete` 			boolean									DEFAULT false,
  
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `pill` CASCADE; 
CREATE TABLE `pill` (
  `pill_id` 				bigint 											NOT NULL 			AUTO_INCREMENT,
  `name` 					varchar(500) 			DEFAULT NULL,
  `code` 					bigint 					DEFAULT NULL,
  `company` 				varchar(50) 			DEFAULT NULL,
  `effect` 					TEXT 					DEFAULT NULL,
  `basis` 					TEXT 		 			DEFAULT NULL,
  `caution` 				TEXT 					DEFAULT NULL,
  `capacity` 				TEXT 					DEFAULT NULL,
  `image_url` 				varchar(1000) 			DEFAULT NULL,
  `type` 					varchar(100) 			DEFAULT NULL,
  `warning_pregnant` 		boolean					DEFAULT NULL,
  `warning_use_date` 		boolean					DEFAULT NULL,
  `warning_elders` 			boolean					DEFAULT NULL,
  `warning_together` 		boolean 				DEFAULT NULL,
  `create_date` 			datetime      			DEFAULT NOW(),
  `modify_date` 			datetime      			DEFAULT NULL,
  `is_delete` 				boolean 				DEFAULT false,
  
  PRIMARY KEY (`pill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `pill` WRITE;
/*!40000 ALTER TABLE `pill` DISABLE KEYS */;
/*!40000 ALTER TABLE `pill` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `category` CASCADE;
CREATE TABLE `category` (
  `category_id` 		bigint 											NOT NULL 			AUTO_INCREMENT,
  `category_name` 		varchar(20) 		DEFAULT NULL,
  `create_date` 		datetime      		DEFAULT NOW(),
  `modify_date` 		datetime      		DEFAULT NULL,
  `is_delete` 			boolean				DEFAULT false,
  
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 데이터 일관성을 유지하고, 병렬 작업에서의 충돌을 방지하는데 사용
LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `ocr_list` CASCADE;
CREATE TABLE `ocr_list` (
  `ocr_id` 				bigint 														NOT NULL 			AUTO_INCREMENT,
  `user_id` 			bigint 								DEFAULT NULL,
  `ocr_type` 			enum('DIAGNOSIS', 'PILLBAG') 								NOT NULL,
  `build_date` 			datetime      						DEFAULT NULL,
  `ill_code` 			varchar(50) 						DEFAULT NULL,
  `ill_name` 			varchar(50) 						DEFAULT NULL,
  `hospital_name` 		varchar(50) 						DEFAULT NULL,
  `diagnosis_date` 		datetime      						DEFAULT NULL,
  `opinion` 			varchar(1000) 						DEFAULT NULL,
  `pharmacy_name` 		varchar(50) 						DEFAULT NULL,
  `cost` 				int 								DEFAULT 0,
  `create_date` 		datetime      						DEFAULT NOW(),
  `modify_date` 		datetime      						DEFAULT NULL,
  `is_delete` 			boolean 							DEFAULT false,
  
  PRIMARY KEY (`ocr_id`),
  CONSTRAINT `FK_ocr_list_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `ocr_list` WRITE;
/*!40000 ALTER TABLE `ocr_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `ocr_list` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `ocr_pills` CASCADE;
CREATE TABLE `ocr_pills` (
  `ocr_pills_id` 			bigint 											NOT NULL 			AUTO_INCREMENT,
  `ocr_id` 		 			bigint 					DEFAULT NULL,
  `pill_id` 				bigint 					DEFAULT NULL,
  `guide` 					varchar(100) 			DEFAULT NULL,
  `create_date` 			datetime      			DEFAULT NOW(),
  `modify_date` 			datetime      			DEFAULT NULL,
  `is_delete` 				boolean 				DEFAULT false,
  
  PRIMARY KEY (`ocr_pills_id`),
  CONSTRAINT `FK_ocr_pills_pill_id` 	FOREIGN KEY (`pill_id`) 	REFERENCES `pill` (`pill_id`),
  CONSTRAINT `FK_ocr_pills_ocr_id` 		FOREIGN KEY (`ocr_id`) 		REFERENCES `ocr_list` (`ocr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `ocr_pills` WRITE;
/*!40000 ALTER TABLE `ocr_pills` DISABLE KEYS */;
/*!40000 ALTER TABLE `ocr_pills` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `tag` CASCADE;
CREATE TABLE `tag` (
  `tag_id` 				bigint 										NOT NULL 				AUTO_INCREMENT,
  `tag_name` 			varchar(20) 		DEFAULT NULL,
  `user_id` 			bigint 				DEFAULT NULL,
  `category_id` 		bigint 				DEFAULT NULL,
  `create_date` 		datetime      		DEFAULT NOW(),
  `modify_date` 		datetime      		DEFAULT NULL,
  `is_delete` 			boolean				DEFAULT false,
  
  PRIMARY KEY (`tag_id`),
  CONSTRAINT `FK_tag_category_id` 	FOREIGN KEY (`category_id`) 	REFERENCES `category` (`category_id`),
  CONSTRAINT `FK_tag_user_id` 		FOREIGN KEY (`user_id`) 		REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `take_list` CASCADE;
CREATE TABLE `take_list` (
  `take_list_id` 		bigint 										NOT NULL 			AUTO_INCREMENT,
  `take_list_name` 		varchar(20) 			DEFAULT NULL,
  `user_id` 			bigint 					DEFAULT NULL,
  `is_alarm` 			boolean 				DEFAULT NULL,
  `alarm_time` 			datetime      			DEFAULT NULL,
  `cycle` 				int 					DEFAULT 24			NOT NULL,
  `is_finish` 			boolean 				DEFAULT NULL,  
  `finish_date` 		datetime      			DEFAULT NULL,
  `create_date` 		datetime      			DEFAULT NOW(),
  `modify_date` 		datetime      			DEFAULT NULL,
  `is_delete` 			boolean					DEFAULT false,
  
  PRIMARY KEY (`take_list_id`),
  CONSTRAINT `FK_take_list_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `take_list` WRITE;
/*!40000 ALTER TABLE `take_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `take_list` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `take_pills` CASCADE;
CREATE TABLE `take_pills` (
  `take_pills_id` 		bigint 										NOT NULL 			AUTO_INCREMENT,
  `pill_id` 			bigint 					DEFAULT NULL,
  `take_list_id` 		bigint 					DEFAULT NULL,
  `create_date` 		datetime      			DEFAULT NOW(),
  `modify_date` 		datetime      			DEFAULT NULL,
  `is_delete` 			boolean					DEFAULT false,
  
  PRIMARY KEY (`take_pills_id`),
  CONSTRAINT `FK_take_pills_pill_id` 			FOREIGN KEY (`pill_id`) 			REFERENCES `pill` (`pill_id`),
  CONSTRAINT `FK_take_pills_take_list_id` 		FOREIGN KEY (`take_list_id`) 		REFERENCES `take_list` (`take_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `take_pills` WRITE;
/*!40000 ALTER TABLE `take_pills` DISABLE KEYS */;
/*!40000 ALTER TABLE `take_pills` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `user_pill_effect` CASCADE;
CREATE TABLE `user_pill_effect` (
  `user_pill_effect_id` 	bigint 											NOT NULL 		AUTO_INCREMENT,
  `category_id` 			bigint 					DEFAULT NULL,
  `user_id` 				bigint 					DEFAULT NULL,
  `pill_id` 				bigint 					DEFAULT NULL,
  `memo` 					varchar(100) 			DEFAULT NULL,
  `create_date` 			datetime      			DEFAULT NOW(),
  `modify_date` 			datetime      			DEFAULT NULL,
  `is_delete` 				boolean					DEFAULT false,
  
  PRIMARY KEY (`user_pill_effect_id`),
  CONSTRAINT `FK_user_pill_effect_pill_id` 			FOREIGN KEY (`pill_id`) 			REFERENCES `pill` (`pill_id`),
  CONSTRAINT `FK_user_pill_effect_user_id` 			FOREIGN KEY (`user_id`) 			REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_pill_effect_category_id` 		FOREIGN KEY (`category_id`) 		REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user_pill_effect` WRITE;
/*!40000 ALTER TABLE `user_pill_effect` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pill_effect` ENABLE KEYS */;
UNLOCK TABLES;




-- DROP TABLE IF EXISTS `user_pill_effect_to_tag` CASCADE;
CREATE TABLE `user_pill_effect_to_tag` (
  `user_pill_effect_to_tag_id` 		bigint 											NOT NULL 		AUTO_INCREMENT,
  `user_pill_effect_id` 			bigint 											NOT NULL,
  `tag_id` 							bigint 					DEFAULT NULL,
  `create_date` 					datetime      			DEFAULT NOW(),
  `modify_date` 					datetime      			DEFAULT NULL,
  `is_delete` 						boolean					DEFAULT false,
  
  PRIMARY KEY (`user_pill_effect_to_tag_id`),
  CONSTRAINT `FK_user_pill_effect_to_tag_user_pill_effect_id` 	FOREIGN KEY (`user_pill_effect_id`) 	REFERENCES `user_pill_effect` (`user_pill_effect_id`),
  CONSTRAINT `FK_user_pill_effect_to_tag_tag_id` 				FOREIGN KEY (`tag_id`) 					REFERENCES `tag` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user_pill_effect_to_tag` WRITE;
/*!40000 ALTER TABLE `user_pill_effect_to_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pill_effect_to_tag` ENABLE KEYS */;
UNLOCK TABLES;
