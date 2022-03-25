def user_input():
    x = input("Wpisz imie i nazwisko: ")
    imie,nazwisko = x.split()
    return imie,nazwisko

def wyswietl(imie, nazwisko):
    print(f"Witaj {imie} {nazwisko}")



imie,nazwisko = user_input()
wyswietl(imie,nazwisko)
