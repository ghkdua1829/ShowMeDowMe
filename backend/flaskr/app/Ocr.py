"""Detects text in the file."""
from google.cloud import vision_v1 as vision
import io
import re
from flask import Flask, request, jsonify
from PIL import Image

app = Flask(__name__)


def hello():
    value = request.files['image']
    client = vision.ImageAnnotatorClient()
    img = Image.open(value)
    content = image_to_byte_array(img)

    image = vision.types.Image(content=content)

    result = ["", ""]

    response = client.text_detection(image=image)
    texts = response.text_annotations
    # -*- Encoding:UTF-8 -*- #
    print(texts[0].description.split("\n"))
    textArr = texts[0].description.split("\n")
    # s = '123abc.-30=*/-'

    for text in textArr:
        # if text.find('.') != -1 or text.find(',') != -1:
        strNum = "".join(re.findall('\d+', text))
        print("[" + strNum + "]")
        if len(strNum) != 0:
            if 100 < int(strNum) < 500000:
                print("후보 가격 !! : " + str(strNum))
                result[1] = strNum

        #
        hangul = re.compile('[^ ㄱ-ㅣ가-힣]+')  # 한글과 띄어쓰기를 제외한 모든 글자
        hangulResult = hangul.sub('', text)  # 한글과 띄어쓰기를 제외한 모든 부분을 제거
        if len(hangulResult) > 3:
            if text.find('가능') == -1 and text.find('원산지') == -1 and text.find('표기') == -1 and text.find(
                    '용기') == -1 and text.find(
                    '개당') == -1 and text.find('g당') == -1 and text.find('기간') == -1 and text.find('추천') == -1 and text.find(',') == -1:
                print("후보 물건 이름!!! : " + text)
                # if result[0][0]=='|'
                result[0] = text

    # print('Texts:')
    #
    # for text in texts:
    #     content = text.description
    #     content = content.replace(',', '')
    #     print('\n"{}"'.format(content))

    if response.error.message:
        raise Exception(
            '{}\nFor more info on error messages, check: '
            'https://cloud.google.com/apis/design/errors'.format(
                response.error.message))

    jsonResponse = {'name': result[0], 'price': result[1]}

    return jsonify(jsonResponse)


def detect_text(path):
    """Detects text in the file."""
    import io
    client = vision.ImageAnnotatorClient()

    with io.open(path, 'rb') as image_file:
        content = image_file.read()

    image = vision.Image(content=content)

    response = client.text_detection(image=image)
    texts = response.text_annotations
    print('Texts:')

    for text in texts:
        print('\n"{}"'.format(text.description))

        vertices = (['({},{})'.format(vertex.x, vertex.y)
                     for vertex in text.bounding_poly.vertices])

        print('bounds: {}'.format(','.join(vertices)))

    if response.error.message:
        raise Exception(
            '{}\nFor more info on error messages, check: '
            'https://cloud.google.com/apis/design/errors'.format(
                response.error.message))


def image_to_byte_array(image: Image):
    imgByteArr = io.BytesIO()
    image.save(imgByteArr, format=image.format)
    imgByteArr = imgByteArr.getvalue()
    return imgByteArr


if __name__ == '__main__':
    app.run()
