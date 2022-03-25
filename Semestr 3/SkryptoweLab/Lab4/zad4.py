class drink:
    name : str
    price: int
    alc: int
    capacity: int

    def show_drink(self):
        print(f"Nazwa: {self.name} Cena: {self.price}zł Stężenie alkoholu: {self.alc}% objętość: {self.capacity}ml")
    
    def __init__(self, name, price, alc, capacity):
        self.name = name
        self.price = price
        self.alc = alc
        self.capacity = capacity
    def __add__(self, x):
        self.name = self.name + " z " + x.name
        self.alc = ((self.capacity * self.alc) + (x.capacity * x.alc)) / (self.capacity + x.capacity)
        self.price += x.price
        self.capacity += x.capacity
    def __lt__(self, other) -> bool:
        try:
            if self.alc/self.price < other.alc/other.price:
                return True
            else:
                return False
        except ZeroDivisionError:
            try:
                if self.alc/self.price < 0:
                    return True
                else:
                    return False
            except ZeroDivisionError:
                if 0 < other.alc/other.price:
                    return True
                else:
                    return False
    def __repr__(self) -> str:
        return self.name


wodka = drink("Wódka", 8, 40, 50)
lod = drink("Lód", 0, 0, 30)
rum = drink("Rum",9,60,50)
cola = drink("Cola", 2, 0, 100)
lista = [rum, lod, wodka, cola]
print(lista)
lista.sort()
print(lista)