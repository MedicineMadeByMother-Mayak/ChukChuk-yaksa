import os
import re
import pymysql
from yolov5 import detect_custom
from flask import Flask, request, jsonify

app = Flask(__name__)

db = pymysql.connect(host='j10b304.p.ssafy.io', user='mayak', password='chukchuk', db='chukchuk_db', charset='utf8')
cursor = db.cursor()

@app.route('/api/v1/pill/photo-search', methods=['POST'])
def pillSearch():
    if 'image' not in request.files:
        return 'No file part'

    file = request.files['image']

    # 이미지를 저장할 디렉토리 경로
    input_folder = os.path.join('yolov5', 'runs', 'input')

    # 디렉토리가 존재하지 않으면 생성
    if not os.path.exists(input_folder):
        os.makedirs(input_folder)

    # 파일을 'image' 폴더에 저장
    file_path = os.path.join(input_folder, file.filename)
    file.save(file_path)

    results_ai = detect_custom.run(source=file_path)

    result_base_path = re.sub(r'^yolov5/runs/detect', '/image', str(results_ai[0].save_dir))
    full_image_url = f"j10p304.p.ssafy.io{result_base_path}/{file.filename}"

    # 데이터베이스 연결
    db = pymysql.connect(host='j10b304.p.ssafy.io', user='mayak', password='chukchuk', db='chukchuk_db', charset='utf8')
    # 커서 생성
    cursor = db.cursor()
    # 결과를 담을 리스트 초기화
    results_db = []
    try:
        # 이름 목록 순회
        for result_ai in results_ai:
            # 이름을 사용하여 쿼리 생성
            query = "SELECT * FROM pill WHERE code = %s"
            # 쿼리 실행
            cursor.execute(query, (result_ai.cls,))
            # 결과 가져오기
            result_db = cursor.fetchone()

            # 결과가 있다면 결과 리스트에 추가
            if result_db:
                results_db.append(result_db)
    except Exception as e:
        print("db 작업 수행중 에러가 발생했습니다 : ", e)
        # 롤백 수행
        db.rollback()

    finally:
        # 커서와 연결 닫기
        cursor.close()
        db.close()

    pills = []
    for result_db, result_ai in zip(results_db, results_ai):
        crop_image_url = f"j10p304.p.ssafy.io{result_base_path}/crops/{result_ai.crop_img}"
        pill = {
            "imageUrl": crop_image_url,
            "pillId": result_db[0],
            "pillName": result_db[1],
            "acc": f"{result_ai.acc.item():.2f}",
            "type": result_db[9],
            "warningPregnant": bool(result_db[10]),
            "warningUseDate": bool(result_db[11]),
            "warningElders": bool(result_db[12]),
            "warningTogether": bool(result_db[13])
        }
        pills.append(pill)

    response = {
        "pic_url": full_image_url,
        "pills": pills
    }

    return response


if __name__ == '__main__':
    app.run()