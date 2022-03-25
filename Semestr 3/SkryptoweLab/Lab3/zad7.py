def dekorator(func):
    tekst = func()
    def u():
        wynik = "<u> " + str(tekst) + " </u>"
        return wynik
    def i():
        wynik = "<i>" + str(u()) + "</i>"
        return wynik
    def b():
        wynik = "<b>" + str(i()) + "</b>"
        return wynik
    return b



@dekorator
def tekst():
    t = str(input("Wpisz tekst: "))
    return t

print(tekst())