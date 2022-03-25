
dodaj = lambda x,y: x+y
minus = lambda x,y: x-y
mnozenie = lambda x,y: x*y
dzielenie = lambda x,y: x/y

def komunikacja():
    user_input = input("Wybierze dzialanie(+,-,*,/) oraz wpisze 2 liczby: ")
    dzialanie,x,y = user_input.split()
    if dzialanie == "+":
        wynik = dodaj(int(x),int(y))
    elif dzialanie == "-":
        wynik = minus(int(x),int(y))
    elif dzialanie == "*":
        wynik = mnozenie(int(x),int(y))
    elif dzialanie == "/":
        wynik = dzielenie(int(x),int(y))
    print(wynik)

komunikacja()