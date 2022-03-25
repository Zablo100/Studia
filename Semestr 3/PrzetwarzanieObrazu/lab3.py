import cv2
import numpy as np
import matplotlib.pyplot as plt

GLAD = "Glad.png"
RGB = "rgb.png"
OBRAZEK = "obrazek.jpg"
N2 = "N2.jpg"

def zad1():
    img = cv2.imread(OBRAZEK)
    kontrast = 5
    jasnosc = 1
    img = cv2.resize(img, (0,0), fx=0.5, fy=0.5)
    #img2 = cv2.addWeighted(img, kontrast, np.zeros(img.shape, img.dtype), 0, jasnosc)
    img3  = cv2.convertScaleAbs(img, alpha=kontrast, beta=jasnosc)

    img4 = np.zeros(img.shape, img.dtype)
    for y in range(img.shape[0]):
        for x in range(img.shape[1]):
            for c in range(img.shape[2]):
                img4[y,x,c] = np.clip(kontrast*img[y,x,c] + jasnosc, 0, 255)


    cv2.imshow("obrazek", img)
    cv2.imshow("obrazek2", img4)
    cv2.imshow("obrazek3", img3)
    cv2.waitKey(0)


def zad2():
    img = cv2.imread(OBRAZEK)
    jasnosc = 50
    img2 = cv2.addWeighted(img, 1, np.zeros(img.shape, img.dtype), 0, jasnosc)

    cv2.imshow("obrazek", img)
    cv2.imshow("obrazek2", img2)
    cv2.waitKey(0)

def zad3():
    img = cv2.imread("Glad2.png")
    img2 = cv2.imread(RGB)

    dst = cv2.addWeighted(img, 0.7, img2, 0.3, 0.0)
    cv2.imshow("obrazek2", dst)
    cv2.waitKey(0)


def zad4():
    img = cv2.imread(N2, 0)
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    a = np.zeros(gray)
    dst = np.zeros(img.shape)

    b = cv2.normalize(a,dst,0,255,cv2.NORM_MINMAX)


    cv2.imshow("obrazek2", b)
    cv2.waitKey(0)

#moje zadanka

def zad5(): 
    img = cv2.imread("rgb3.png")
    red = img[:,:,2]
    green = img[:,:,1]
    blue = img[:,:,0]

    cv2.imshow("czerwony", red)
    cv2.imshow("niebieski", blue)
    cv2.imshow("zielony", green)


    cv2.waitKey(0)
    print(blue.shape)
    print(" ---------------------------- ")
    print(img.shape)

def zad6():
    img = cv2.imread(GLAD)
    img_hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)


    cv2.imshow("obrazek", img_hsv)
    cv2.waitKey(0)


def zad7():
    img = cv2.imread(OBRAZEK, 0)
    img = cv2.resize(img, (0,0), fx=0.5, fy=0.5)
    #img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)


    ret,tr1 = cv2.threshold(img, 60, 255, cv2.THRESH_BINARY)
    ret,tr2 = cv2.threshold(img, 60, 255, cv2.THRESH_TRUNC)
    ret,tr3 = cv2.threshold(img, 60, 255, cv2.THRESH_TOZERO)
    ret,tr4 = cv2.threshold(img, 150, 255, cv2.THRESH_BINARY)
    ret,tr5 = cv2.threshold(img, 200, 255, cv2.THRESH_TOZERO)


    cv2.imshow("1", tr1)
    cv2.imshow("2", tr2)
    cv2.imshow("3", tr3)
    cv2.imshow("4", tr4)
    cv2.imshow("5", tr5)
    cv2.waitKey(0)

def zad8():
    org = cv2.imread(GLAD)
    org2 = cv2.imread("GladJ.jpg")


    img1 = org + org2
    img2 = org - org2
    img3 = org2 - org
    img4 = org * org2
    img5 = org/org2
    img6 = org2/org

    cv2.imshow("dodawanie", img1)
    cv2.imshow("odejmowanie", img2)
    cv2.imshow("odejmowanie2", img3)
    cv2.imshow("mnozenie", img4)
    cv2.imshow("podzielic", img5)
    cv2.imshow("podzielic2", img6)


    cv2.waitKey(0)



def zad9():
    img = cv2.imread(N2, 0)

    plt.hist(img.ravel(),256,[0,256])
    plt.show()


    #cv2.imshow(" ", img)
    #cv2.waitKey(0)

zad9()