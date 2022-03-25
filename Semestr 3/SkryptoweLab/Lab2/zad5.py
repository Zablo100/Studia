import random

def ocena():
    return random.randint(2,5)

id = 0

slownik = {}
lista = [
{"nr.indeksu": "223", "imie": "Jan", "nazwisko": "Kowalski", "ocena": ocena()},
{"nr.indeksu": "223", "imie": "Michał", "nazwisko": "Nowak", "ocena": ocena()},
{"nr.indeksu": "224", "imie": "Adam", "nazwisko": "Mucha", "ocena": ocena()},
]

def add(s):
    if s["ocena"] > 2:
        global id
        id += 1
        student = "student" + str(id)
        slownik.setdefault(student, s)

for x in range(len(lista)):
    add(lista[x])

print() 
for key, value in slownik.items():
    print(key, value) 
print()