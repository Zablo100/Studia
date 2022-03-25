import calendar

while(True):
    x = input("Wpisz rok i miesiąc: ")
    rok,miesiac = x.split()
    if(int(miesiac) < 13):
        break
    
print(calendar.month(int(rok), int(miesiac)))

#https://www.geeksforgeeks.org/python-calendar-module/