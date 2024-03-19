-- -----------------------------------------------------------------------------------
-- 더미 데이터 insert (GPT한테 테이블 형태주고 더미 데이터 생성 해달라고 시켜도 됨)
-- 외래키로 참조되는 테이블은 외래키 등록되어있는 걸로 넣어줘야 함 (아래 샘플 코드 수정해서 쓸 것)
-- -----------------------------------------------------------------------------------
INSERT INTO `category` (`category_name`, `create_date`, `modify_date`, `is_delete`)
VALUES
    ('Electronics', NOW(), NOW(), false),
    ('Clothing', NOW(), NOW(), false),
    ('Books', NOW(), NOW(), false),
    ('Furniture', NOW(), NOW(), false),
    ('Sports', NOW(), NOW(), false),
    ('Toys', NOW(), NOW(), false),
    ('Beauty', NOW(), NOW(), false),
    ('Home & Kitchen', NOW(), NOW(), false),
    ('Automotive', NOW(), NOW(), false),
    ('Garden', NOW(), NOW(), false);

INSERT INTO `pill` (`name`, `code`, `company`, `effect`, `basis`, `caution`, `capacity`, `image_url`, `type`, `warning_pregnant`, `warning_use_date`, `warning_elders`, `warning_together`, `create_date`, `modify_date`, `is_delete`)
VALUES
    ('Pill A', 12345, 'Company A', 'Headache relief', 'For adults only', 'Do not exceed recommended dose', '10 tablets', 'url_a', 'Type A', true, false, false, false, NOW(), NOW(), false),
    ('Pill B', 67890, 'Company B', 'Stomachache relief', 'For adults and children over 12', 'Avoid if allergic to aspirin', '20 tablets', 'url_b', 'Type B', false, false, true, false, NOW(), NOW(), false);

INSERT INTO `user` (`user_name`, `social_code`, `social`, `birth`, `blood_type`, `height`, `weight`, `sex`, `token`, `create_date`, `modify_date`, `is_delete`)
VALUES
    ('John', 'GOOGLE', 'google_user123', '1990-05-15', 'A', 175, 70, 'male', 'token123', NOW(), NOW(), false),
    ('Emily', 'NAVER', 'naver_user456', '1985-08-20', 'B', 160, 55, 'female', 'token456', NOW(), NOW(), false),
    ('Michael', 'KAKAO', 'kakao_user789', '1995-03-25', 'AB', 180, 80, 'male', 'token789', NOW(), NOW(), false),
    ('Jessica', 'GOOGLE', 'google_user321', '1988-12-10', 'O', 165, 60, 'female', 'token321', NOW(), NOW(), false),
    ('Daniel', 'NAVER', 'naver_user654', '1992-07-05', 'A', 170, 75, 'male', 'token654', NOW(), NOW(), false);


-- 사용자 ID는 user 테이블의 사용자 ID에 맞게 수정해야 합니다.
INSERT INTO `take_list` (`take_list_name`, `user_id`, `is_alarm`, `alarm_time`, `cycle`, `is_finish`, `finish_date`, `create_date`, `modify_date`, `is_delete`)
VALUES
    ('Morning Pills', 1, true, '2024-03-15 08:00:00', 24, false, NULL, NOW(), NOW(), false),
    ('Afternoon Pills', 2, true, '2024-03-15 14:00:00', 24, false, NULL, NOW(), NOW(), false),
    ('Evening Pills', 3, true, '2024-03-15 20:00:00', 24, false, NULL, NOW(), NOW(), false),
    ('Before Bed Pills', 4, true, '2024-03-15 22:00:00', 24, false, NULL, NOW(), NOW(), false),
    ('Emergency Pills', 5, false, NULL, 0, false, NULL, NOW(), NOW(), false);

-- take_list_id 및 pill_id는 실제 테이블의 외래 키에 맞게 수정해야 합니다.
INSERT INTO `take_pills` (`take_list_id`, `pill_id`, `create_date`, `modify_date`, `is_delete`)
VALUES
    (1, 1, NOW(), NOW(), false),
    (2, 2, NOW(), NOW(), false),
    (3, 1, NOW(), NOW(), false),
    (4, 2, NOW(), NOW(), false),
    (5, 1, NOW(), NOW(), false);

-- 사용자 ID 및 기타 필요한 데이터에 맞게 수정
INSERT INTO `ocr_list` (`user_id`, `ocr_type`, `build_date`, `ill_code`, `ill_name`, `hospital_name`, `diagnosis_date`, `opinion`, `pharmacy_name`, `cost`, `create_date`, `modify_date`, `is_delete`)
VALUES
    (1, 'DIAGNOSIS', NOW(), '123456', 'Headache', 'Hospital A', NOW(), 'No specific opinion', 'Pharmacy A', 10000, NOW(), NOW(), false),
    (2, 'DIAGNOSIS', NOW(), '789012', 'Stomachache', 'Hospital B', NOW(), 'Needs further examination', 'Pharmacy B', 15000, NOW(), NOW(), false);
    
-- ocr_id 및 pill_id는 실제 테이블의 외래 키에 맞게 수정해야 합니다.
INSERT INTO `ocr_pills` (`ocr_id`, `pill_id`, `guide`, `create_date`, `modify_date`, `is_delete`)
VALUES
    (1, 1, 'Take 1 pill every 8 hours', NOW(), NOW(), false),
    (2, 2, 'Take 2 pills after meal', NOW(), NOW(), false);
    
-- user_id 및 category_id는 실제 테이블의 외래 키에 맞게 수정해야 합니다.
INSERT INTO `tag` (`tag_name`, `user_id`, `category_id`, `create_date`, `modify_date`, `is_delete`)
VALUES
    ('Tag A', 1, 1, NOW(), NOW(), false),
    ('Tag B', 2, 2, NOW(), NOW(), false);


INSERT INTO user_pill_effect (category_id, user_id, pill_id, memo, create_date, modify_date, is_delete)
VALUES
	(1, 1, 1, 'Sample memo 1', NOW(), NULL, 0),
	(2, 1, 2, 'Sample memo 2', NOW(), NULL, 0),
	(3, 2, 3, 'Sample memo 3', NOW(), NULL, 0),
	(1, 2, 4, 'Sample memo 4', NOW(), NULL, 0),
	(2, 3, 5, 'Sample memo 5', NOW(), NULL, 0),
	(3, 3, 6, 'Sample memo 6', NOW(), NULL, 0),
	(1, 4, 7, 'Sample memo 7', NOW(), NULL, 0),
	(2, 4, 8, 'Sample memo 8', NOW(), NULL, 0),
	(3, 5, 9, 'Sample memo 9', NOW(), NULL, 0),
	(1, 5, 10, 'Sample memo 10', NOW(), NULL, 0);


INSERT INTO user_pill_effect_to_tag (user_pill_effect_id, tag_id, create_date, modify_date, is_delete)
VALUES
	(1, 1, NOW(), NULL, 0),
	(2, 2, NOW(), NULL, 0),
	(3, 1, NOW(), NULL, 0),
	(4, 2, NOW(), NULL, 0),
	(5, 1, NOW(), NULL, 0),
	(6, 2, NOW(), NULL, 0),
	(7, 1, NOW(), NULL, 0),
	(8, 2, NOW(), NULL, 0),
	(9, 1, NOW(), NULL, 0),
	(10, 2, NOW(), NULL, 0);