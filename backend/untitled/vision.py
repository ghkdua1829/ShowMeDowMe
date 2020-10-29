def run_quickstart(file_name):
    import io
    import os

    # 구글 라이브러리 import
    from google.cloud import vision
    from google.cloud.vision import types

    # 사용할 클라이언트 설정
    client = vision.ImageAnnotatorClient()

    # 이미지 읽기
    with io.open(file_name, 'rb') as image_file:
        content = image_file.read()

    image = types.Image(content=content)

    # 택스트 뽑아냄
    response = client.text_detection(image=image)
    texts = response.text_annotations

    print('Labels:')
    for text in texts:
        print('\n"{}"'.format(text.description))

        vertices = (['({},{})'.format(vertex.x, vertex.y)
                     for vertex in text.bounding_poly.vertices])

        print('bounds: {}'.format(','.join(vertices)))


if __name__ == '__main__':
    run_quickstart("number2.jpg")