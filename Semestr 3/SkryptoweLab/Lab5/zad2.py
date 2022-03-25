import re


regex = r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b'
tekst = "Kamil kamil@google.com, Tomek jan.nowak@o2.pl"


m = re.search(regex, tekst)

if m:
    x = re.findall(regex, tekst)
    print(x)

