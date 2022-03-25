class Triangle:
    number_of_sides = 3
    angle1 = 0
    angle2 = 0
    angle3 = 0

    def __init__(self,angle1, angle2, angle3):
        self.angle1 = angle1
        self.angle2 = angle2
        self.angle3 = angle3

    def check_angles(self):
        suma = self.angle1 + self.angle2 + self.angle3
        if suma == 180:
            return True
        else:
            return False


t1 = Triangle(60,60,60)
t2 = Triangle(90,90,90)
print(f"[Trójkąt 1] {t1.check_angles()}")
print(f"[Trójkąt 2] {t2.check_angles()}")

