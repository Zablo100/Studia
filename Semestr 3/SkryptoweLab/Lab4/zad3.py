from enum import Enum

class BatteryType(Enum):
    lithium_ion = 0
    nickel_metal_hydride = 1
    lead_acid = 2
    ultracapacitor = 3


class Car:
    model = ""
    color = ""
    condition = ""
    hp = 0

    def display_car(self):
        return (f"Marka: {self.model} Stan: {self.condition} Kolor: {self.color} Moc silnika: {self.hp}")

    def drive_car(self):
        self.condition = "Używany"

    def __init__(self, model, color, condition, hp):
        self.model = model
        self.condition = condition
        self.color = color
        self.hp = hp


class ElectricCar(Car):
    battery_type = BatteryType

    def __init__(self, model, color, condition, hp, battery_type: BatteryType): 
        super().__init__(model, color, condition, hp)
        self.battery_type = battery_type
    
    def display_car(self):
        print(f"{super().display_car()} Typ bateri: {BatteryType(self.battery_type).name}")



c1 = ElectricCar("Tesla", "Czerwony", "Nowy", 283, BatteryType(3) )
c1.display_car()
c1.drive_car()
print()
c1.display_car()


