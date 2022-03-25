import random

def set_ocena():
    return random.randint(2,5)

id = 0

slownik = {}
slownik2 = {}
keys = ["nr.indeksu", "imie", "nazwisko", "ocena"]

def sprint(x):
    for i in range(id):
        for key in x:
            if (key == "ocena"):
                if (int(x[key][i]) > 2):
                    pozytywna(i)
 

def add():
    global id
    id += 1
    for x in range(3):
        key = keys[x]
        user_input = input(f"[{id}]Wpisz dane: ")
        slownik.setdefault(key, []).append(user_input)
    slownik.setdefault("ocena", []).append(set_ocena())
def pozytywna(indeks):
    for x in range(4):
        key = keys[x]
        dane = slownik.get(key)[indeks]
        slownik2.setdefault(key, []).append(dane)


add()
add()
add()
add()
sprint(slownik)

print(slownik2)
print(" ")
print(slownik)