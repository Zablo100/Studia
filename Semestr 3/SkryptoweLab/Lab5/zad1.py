import re

przyklad = "jan@onremove_thiset.pl"

m = re.search("remove_this", przyklad) #Szuka czy jest

if m: # Jak jest to
    x = re.sub( m.group(), "", przyklad) # Usuwa tany ciąg z tekstu.
    print(x)