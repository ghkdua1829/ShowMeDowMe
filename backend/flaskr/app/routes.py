from app import app
from flask import request, jsonify
from werkzeug.utils import secure_filename
from flask_cors import cross_origin


@app.route('/')
@app.route('/index')
def index():
	return "Hello, World!"


# @app.route('/pyapi/image', methods=['POST', 'GET'])
# def getImage():
# 	value = request.files["image"]
# 	print(value)
# 	return "확인완료"


# 파일 업로드 처리
@app.route('/fileUpload', methods = ['GET', 'POST'])
@cross_origin()
def upload_file():
	if request.method == 'POST':
		f = request.get_data()
		print(f)
		# 저장할 경로 + 파일명
		print("여기3")

		# f.save(secure_filename(f.filename))
		response = jsonify(message="테스트")
		response.headers.add("Access-Control-Allow-Origin", "*")
		return response


