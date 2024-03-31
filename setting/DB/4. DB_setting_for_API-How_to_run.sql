-- 4. DB_setting_for_API.sql를 실행

-- 아래 쿼리문 실행해서 내가 로그인한, 토큰값을 가지고 있는 user_id를 확인
select *
	from user;
	
-- 아래 쿼리문 파라미터로 내 user_id를 적어두고 실행하기
CALL dumy_data_for_API(내_user_id);