import time

def odliczanie(n):
    if n < 0:
        return 0
    time.sleep(1)
    print(n)
    return odliczanie(n-1)


odliczanie(5)