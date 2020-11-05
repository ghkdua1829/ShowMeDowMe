import cv2
import matplotlib.pyplot as plt
import cvlib as cv

image_path = 'kcy.jpg'
im = cv2.imread(image_path) # 이미지 읽기
plt.imshow(im)
plt.show()

# detect faces (얼굴 검출)
faces, confidences = cv.detect_face(im)

# loop through detected faces and add bounding box (검출된 얼굴을 박스로 감싸기)
for face in faces:
    (startX,startY) = face[0],face[1]
    (endX,endY) = face[2],face[3]
    # draw rectangle over face
    cv2.rectangle(im, (startX,startY), (endX,endY), (0,255,0), 2)

# display output
plt.imshow(im)
plt.show()
cv2.imwrite('result.jpg', im) # 이미지 쓰기