from app import app
from app.Ocr import hello

@app.route('/')
@app.route('/index')
def index():
	return "Hello, World!"

@app.route('/postImage', methods=['POST'])
def imageOcr():
	hello()