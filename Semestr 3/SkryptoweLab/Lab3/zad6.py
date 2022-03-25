import time
from functools import wraps

def timer(func):
    def dekor(*args):
        t1 = time.time()
        wynik = func(*args)
        t2 = time.time()
        print("Czas: {:.5f} ms".format(t2-t1))
        return wynik
    return dekor

@timer
def silnia(x):
    if x > 1:
        return x*silnia(x-1)
    return 1

print("[Rekurencja]")
silnia(400)

@timer
def silnia2(n):
    s = 1;
    for i in range(2, n+1):
        s *= i
    return s

print("Zwykla:")
silnia2(2473)

@timer
def silnia3(n):
    f = lambda x: x*f(x-1) if x != 0 else 1
    return f(n)

print("Lambda:")
silnia3(889)

#http://www.algorytm.org/algorytmy-arytmetyczne/silnia/s-1-py.html
#https://stackoverflow.com/questions/5478351/python-time-measure-function