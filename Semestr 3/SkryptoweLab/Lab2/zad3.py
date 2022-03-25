lista1 = []
lista2 = []

def lis():
    dlugosc = int(input("Wpsiz ile liczbe bedzie w liście: "))
    for x in range(dlugosc):
        lista1.append(int(input("wpisz liczbe: ")))

    for x in lista1:
        if x not in lista2:
            lista2.append(x)
    
    print(lista2)


def lis1():
    user_input = input("wpisz liczby: ")
    lista1 = user_input.split()
    for x in lista1:
        if x not in lista2:
            lista2.append(x)

    print(f"Unikalna lista: {lista2}")
print()
lis1()
print()

#https://blog.finxter.com/how-to-convert-a-string-list-to-an-integer-list-in-python/
#https://www.geeksforgeeks.org/python-get-unique-values-list/