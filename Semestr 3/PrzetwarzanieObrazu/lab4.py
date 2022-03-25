import cv2
import numpy as np
import matplotlib.pyplot as plt


GLAD = "Glad.png"
RGB = "rgb.png"
OBRAZEK = "obrazek.jpg"
N2 = "N2.jpg"
JD = "jd.png"
O1 = "o1.png"
L4 = "l4.png"
XD = "xd1.png"

def zad1():
    img = cv2.imread("test.png")
    ret,img2 = cv2.threshold(img, 0, 255, cv2.THRESH_BINARY)
    
    for x in range(0,5):
        eroded = cv2.erode(img2, None, iterations=x)

        cv2.imshow("", eroded)
        cv2.waitKey(0)

def zad2():
    img = cv2.imread("n1.png")
    ret,img2 = cv2.threshold(img, 0, 255, cv2.THRESH_BINARY)
    
    for x in range(0,5):
        dilated = cv2.dilate(img2, None, iterations=x)


        cv2.imshow("", dilated)
        cv2.waitKey(0)

zad1()



def zad3():
    img = cv2.imread(L4)
    size = [(3,3), (5,5), (7,7), (9,9), (12,12)]    
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    for x in size:
        ker = cv2.getStructuringElement(cv2.MORPH_RECT, x)
        otwarcie = cv2.morphologyEx(gray, cv2.MORPH_OPEN, ker)
        domkniecie = cv2.morphologyEx(gray, cv2.MORPH_CLOSE, ker)

        cv2.imshow("Otwarcie", otwarcie)
        cv2.imshow("Zamkniecie", domkniecie)
        cv2.waitKey(0)

def zad4():
    img = cv2.imread(N2)
    dilated = cv2.dilate(img, None, iterations=1)
    eroded = cv2.erode(img, None, iterations=1)
    kontur1 = (img + dilated) - img
    kontur2 = img - (img+eroded)
    kontur3 = (img+dilated) - (img + eroded)

    cv2.imshow("Kontur1", kontur1)
    cv2.imshow("Kontur2", kontur2)
    cv2.imshow("Kontur3", kontur3)
    cv2.waitKey(0)

def zad5():
    img = cv2.imread("n1.png", 0)
    org = cv2.imread("n1.png", 0)

    size = np.size(img)
    skel = np.zeros(img.shape,np.uint8)
    
    ret,img = cv2.threshold(img,0,255,0)
    element = cv2.getStructuringElement(cv2.MORPH_CROSS,(3,3))
    done = False
    
    while( not done):
        eroded = cv2.erode(img,element)
        temp = cv2.dilate(eroded,element)
        temp = cv2.subtract(img,temp)
        skel = cv2.bitwise_or(skel,temp)
        img = eroded.copy()
    
        zeros = size - cv2.countNonZero(img)
        if zeros==size:
            done = True
    
    cv2.imshow("skel",skel)
    cv2.imshow("org", org)
    cv2.waitKey(0)





#https://www.pyimagesearch.com/2021/04/28/opencv-morphological-operations/