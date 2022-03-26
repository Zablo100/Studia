
void zad1()
{
    Random random = new Random();
    int userInput, randomNumber;

    do
    {
        randomNumber = random.Next(0, 10);
        Console.Write("Wpisz liczbe: ");
        userInput = Convert.ToInt32(Console.ReadLine());
        if (userInput < randomNumber)
        {
            Console.WriteLine("Podana liczba jest zbyt mała!");
        }
        else if (userInput > randomNumber)
        {
            Console.WriteLine("Podana liczba jest zbyt duża!");
        }
    } while (userInput != randomNumber);

    Console.WriteLine("Gratulacje zgadłeś!");
}


void zad2()
{
    int x;
    HashSet<int> set = new HashSet<int>();
    Console.Write("Wpisz ile liczb: ");
    x = Convert.ToInt32(Console.ReadLine());
    for(int i = 0; i < x; i++)
    {
        set.Add(Convert.ToInt32(Console.ReadLine()));
    }

    Console.WriteLine($"Liczba unikalnych wartości {set.Count()}");
}

void zad3()
{
    String binarna = "010";
    bool jedynka = false;
    bool zero = false;
    int liczbaDziur = 0;

    foreach (Char x in binarna)
    {
        if (x.Equals('1') && jedynka == false)
        {
            jedynka = true;
        }
        else if (x.Equals('0') && jedynka == true)
        {
            zero = true;
        }else if (jedynka == true && zero == true)
        {
            liczbaDziur++;
        }

    }

    Console.WriteLine($"Liczba dziura: {liczbaDziur}");
}

void zad4()
{
    List<int> zbior1 = new List<int>{ 1, 2, 3, 4 };
    List<int> zbior2 = new List<int>{ 3, 4, 5, 6 };
    List<int> suma = new List<int>();

    suma.AddRange(zbior1);
    suma.AddRange(zbior2);

    suma.ForEach(e => Console.Write("{0} ", e));

    Console.WriteLine("");
    Console.WriteLine("Różnica zbiorów A/B");
    foreach (int x in zbior1)
    {
        if (!zbior2.Contains(x))
        {
            Console.Write($"{x} ");
        }
    }
    Console.WriteLine("");
    Console.WriteLine("Cześć wspólna");
    foreach (int x in zbior1)
    {
        if (zbior2.Contains(x))
        {
            Console.WriteLine($"{x} ");
        }
    }

    Console.WriteLine("");
    Console.WriteLine("Różnica symetryczna zbiorów");
    foreach(int x in suma)
    {
        if (!zbior1.Contains(x) || !zbior2.Contains(x))
        {
            Console.Write($"{x} ");
        }
    }


}

void zad5()
{
    Console.Write("Wpisz liczbe: ");
    int x = Convert.ToInt32(Console.ReadLine());
    int a = 0;

    for (int i=1; i <=x; i++)
    {
        if (x % i == 0)
        {
            a++;
        }
    }

    if (a == 2)
    {
        Console.WriteLine($"{x} to liczba pierwsza");
    }
    else
    {
        Console.WriteLine($"{x} to nie jest liczba pierwsza");
    }
}
