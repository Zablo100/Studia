user_input = input("Wpisz liczby: ")

lista = user_input.split(",")
krotka = tuple(user_input.split(","))

print(f"Lista: {lista}")
print()
print(f"Krotka: {krotka}")