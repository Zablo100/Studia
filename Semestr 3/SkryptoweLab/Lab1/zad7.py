from datetime import date
import math

data1 = input("Wpisz date(rrrr,mm,dd): ")
data2 = input("Wpisz drugą date(rrrr,mm,dd): ")

r,m,d= data1.split()
data1 = date(int(r),int(m),int(d))

r,m,d= data2.split()
data2 = date(int(r),int(m),int(d))

wynik = data2 - data1

print(str(math.fabs(wynik.days)) + " dni")


#https://stackoverflow.com/questions/151199/how-to-calculate-number-of-days-between-two-given-dates