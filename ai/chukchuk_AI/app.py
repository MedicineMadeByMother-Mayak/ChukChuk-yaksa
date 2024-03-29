import os
from yolov5 import detect_custom
from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route('/upload', methods=['POST'])
def upload_image():
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

    result = detect_custom.run(source=file_path)

    response = jsonify({
        'result': result
    })

    return response

if __name__ == '__main__':
    app.run()