DELIMITER //

DROP PROCEDURE IF EXISTS dumy_data_for_API;
CREATE PROCEDURE dumy_data_for_API (
    IN input_user_id BIGINT
)
BEGIN
    -- 트랜잭션 시작
    START TRANSACTION;	
    
    -- DB safe 모드 해제
	SET SQL_SAFE_UPDATES = 0;
    
	-- 카테고리 이름 업데이트
	UPDATE category
		SET category_name = "부작용!"
		WHERE category_id = 1;

	UPDATE category
		SET category_name = "중단!"
		WHERE category_id = 2;

	UPDATE category
		SET category_name = "효과!"
		WHERE category_id = 3;

	-- 사용자 ID에 맞게 takeList(복용리스트) 추가
	-- DELETE FROM take_list;
	INSERT INTO `take_list` (`take_list_name`, `user_id`, `is_alarm`, `alarm_time`, `cycle`, `is_finish`, `finish_date`, `create_date`, `modify_date`, `is_delete`)
		VALUES
			('감기약 종합세트', 	input_user_id, true, 	'2024-03-15 08:00:00', 	24, false, NULL, NOW(), NULL, false),
			('비타민 루틴', 		input_user_id, false, 	'2024-03-15 14:00:00', 	24, false, NULL, NOW(), NULL, false),
			('면역력 증진세트', 	input_user_id, true, 	'2024-03-15 20:00:00', 	24, false, NULL, NOW(), NULL, false),
			('일단 대충 먹자 세트', 	input_user_id, true, 	'2024-03-15 22:00:00', 	24, false, NULL, NOW(), NULL, false),
			('고라니 세트', 		input_user_id, false, 	NULL, 					0, 	false, NULL, NOW(), NULL, false);
        
	-- 각 takeList(복용리스트)에 약 추가
    INSERT INTO `take_pills` (`take_list_id`, `pill_id`, `create_date`, `modify_date`, `is_delete`)
		VALUES
			(1, 1, NOW(), null, false),
			(1, 10, NOW(), null, false),
			(2, 2, NOW(), null, false),
			(2, 22, NOW(), null, false),
			(2, 27, NOW(), null, false),
			(3, 11, NOW(), null, false),
			(3, 117, NOW(), null, false),
			(4, 29, NOW(), null, false),
			(4, 24, NOW(), null, false),
			(4, 27, NOW(), null, false),
			(4, 28, NOW(), null, false),
			(5, 14, NOW(), null, false),
			(5, 12, NOW(), null, false),
			(5, 16, NOW(), null, false);
    
    -- 태그 미리 추가
    -- DELETE FROM tag;
	insert into tag (tag_name, user_id, category_id, create_date, modify_date, is_delete)
		values 
			("부작용-졸림1!", input_user_id, 1, now(), null, null),
			("부작용-졸림2!", input_user_id, 1, now(), null, null),
			("부작용-졸림3!", input_user_id, 1, now(), null, null),
			("부작용-졸림4!", input_user_id, 1, now(), null, null),
			("부작용-졸림5!", input_user_id, 1, now(), null, null),
			("중단-피곤함1!", input_user_id, 2, now(), null, null),
			("중단-피곤함2!", input_user_id, 2, now(), null, null),
			("중단-피곤함3!", input_user_id, 2, now(), null, null),
			("중단-피곤함4!", input_user_id, 2, now(), null, null),
			("중단-피곤함5!", input_user_id, 2, now(), null, null),
			("효과-개운함1!", input_user_id, 3, now(), null, null),
			("효과-개운함2!", input_user_id, 3, now(), null, null),
			("효과-개운함3!", input_user_id, 3, now(), null, null),
			("효과-개운함4!", input_user_id, 3, now(), null, null);
    
    -- 약효 기록 데이터
    -- DELETE FROM user_pill_effect;
    INSERT INTO user_pill_effect (category_id, user_id, pill_id, memo, create_date, modify_date, is_delete)
		VALUES
			(1, input_user_id, 1, '아프다', NOW(), NULL, 0),
            (1, input_user_id, 4, '졸리다', NOW(), NULL, 0),
            (1, input_user_id, 7, '피곤하다', NOW(), NULL, 0),
			(1, input_user_id, 10, '집에 가고 싶다', NOW(), NULL, 0),
            
			(2, input_user_id, 2, '가렵다', NOW(), NULL, 0),
			(2, input_user_id, 8, '심장이 도키도키', NOW(), NULL, 0),
			(2, input_user_id, 5, '무기력하다', NOW(), NULL, 0),
            
			(3, input_user_id, 6, '체력증진!', NOW(), NULL, 0),			
			(3, input_user_id, 9, '기력증진!', NOW(), NULL, 0),
            (3, input_user_id, 3, '기분 SO HIGH!', NOW(), NULL, 0);
			
    
    -- 약효 기록에 태그 추가
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
    
    -- 트랜잭션 커밋
    COMMIT;

END //

DELIMITER ;
