
def komunikacja():
    user_input = input("Wybierze dzialanie(+,-,*,/) oraz wpisze 2 liczby: ")
    dzialanie,x,y = user_input.split()
    if dzialanie == "+":
        dodaj(x,y)
    elif dzialanie == "-":
        minus(x,y)
    elif dzialanie == "*":
        razy(x,y)
    elif dzialanie == "/":
        dzielenie(x,y)


def dodaj(x,y):
     wynik = int(x) + int(y)
     print(wynik)

def minus(x,y):
    wynik = int(x) - int(y)
    print(wynik)

def razy(x,y):
    wynik = int(x) * int(y)
    print(wynik)

def dzielenie(x,y):
    wynik = int(x) * int(y)
    print(wynik)

komunikacja()