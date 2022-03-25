import cv2
import numpy as np

def zad1():
    img = cv2.imread("Glad.png")

    height,width = img.shape[:2]
    height2,width2 = height/6, width/6
    T = np.float32([[1,0,height2], [0,1,width2]])

    transl = cv2.warpAffine(img,T,(width,height))

    cv2.imshow("Obrazek z napisem", img)
    cv2.imshow(" ", transl)
    cv2.waitKey(0)
    

def zad2():
    img = cv2.imread("Glad.png")
    nogami = cv2.flip(img, -1)
    lustro = cv2.flip(img, 1)
    cv2.imshow("Do gory nogami", nogami)
    cv2.imshow("Lustro", lustro )
    cv2.imshow("Obrazek", img)
    cv2.waitKey(0)


def zad3():
    kat = int(input("Wpisz kąt: "))
    img = cv2.imread("Glad.png")
 
    height,width = img.shape[:2]
    h,w = height/2, width/2
    T = cv2.getRotationMatrix2D((w,h), kat, 1)
    obrocik = cv2.warpAffine(img, T, (width,height))

    cv2.imshow("Obrazek", obrocik)
    cv2.waitKey(0)

def zad4():
    user_input = input("Wpisz wysokosc i szerokość: ")
    szerokosc,wysokosc = user_input.split()
    org = cv2.imread("Glad.png")
    img = org[0:int(szerokosc), 0:int(wysokosc)]
    print(img.shape)

    cv2.imshow("Obrazek", img)
    cv2.waitKey(0)


def zad5():
    img = cv2.imread("Glad.png")
    img2 = cv2.resize(img, (0,0), fx=0.5,fy=0.5)
    img4 = cv2.resize(img, (0,0), fx=0.25, fy=0.25)

    cv2.imshow("Obrazek", img)
    cv2.imshow("Obrazek2", img2)
    cv2.imshow("Obrazek4", img4)
    cv2.waitKey(0)

def zad5v2():
    img = cv2.imread("Glad.png")
    x,y = img.shape[:2]

    img2 = cv2.pyrDown(img, dst=(x/2,y/2))
    img4 = cv2.pyrDown(img2, dst=(x/2,y/2))
    
    cv2.imshow("Obrazek", img)
    cv2.imshow("Obrazek2", img2)
    cv2.imshow("Obrazek4", img4)
    cv2.waitKey(0)

def zad6():
    img = cv2.imread("Glad.png")
    img2 = cv2.resize(img, (0,0), fx=2,fy=2)
    img4 = cv2.resize(img, (0,0), fx=4, fy=4)

    cv2.imshow("Obrazek", img)
    cv2.imshow("Obrazek2", img2)
    cv2.imshow("Obrazek4", img4)
    cv2.waitKey(0)

def zad6v2():
    img = cv2.imread("Glad.png")
    x,y = map(int, img.shape[:2])

    img2 = cv2.pyrUp(img, dst=(x*2,y*2))
    img4 = cv2.pyrUp(img2, dst=(x*2,y*2))
    
    cv2.imshow("Obrazek", img)
    cv2.imshow("Obrazek2", img2)
    cv2.imshow("Obrazek4", img4)
    cv2.waitKey(0)

def zad7():
    img = cv2.imread("Glad.png")
    img = cv2.resize(img, (0,0), fx=1.5, fy=1.5)
    cv2.imshow("Obrazek", img)
    cv2.waitKey(0)

zad5v2()