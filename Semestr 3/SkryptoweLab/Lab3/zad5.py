def dzielnik(x):
    for i in range(1, int(x/2) + 1):
        if x % i == 0:
            print(i)
    print(x)

liczba = int(input("Wpisz liczbe: "))
for i in filter(lambda x: liczba % x == 0, range(1, liczba)):
    print(i)
