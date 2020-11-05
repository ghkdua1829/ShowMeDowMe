"""Detects text in the file."""
from google.cloud import vision_v1 as vision
import io
from flask import Flask, request
from PIL import Image

app = Flask(__name__)


@app.route('/postImage', methods=['POST'])
def hello():
    value = request.files['image']
    client = vision.ImageAnnotatorClient()
    img = Image.open(value)
    content = image_to_byte_array(img)

    image = vision.types.Image(content=content)

    price_candidate = []
    card_numbera_candidate = []
    date_candidate = []

    response = client.text_detection(image=image)
    texts = response.text_annotations
    print('Texts:')

    for text in texts:
        content = text.description
        content = content.replace(',', '')
        print('\n"{}"'.format(content))

    if response.error.message:
        raise Exception(
            '{}\nFor more info on error messages, check: '
            'https://cloud.google.com/apis/design/errors'.format(
                response.error.message))

    return str(texts)


def image_to_byte_array(image: Image):
    imgByteArr = io.BytesIO()
    image.save(imgByteArr, format=image.format)
    imgByteArr = imgByteArr.getvalue()
    return imgByteArr


if __name__ == '__main__':
    app.run()
