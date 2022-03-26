
void zad1()
{
    Random random = new Random();
    int userInput, randomNumber;
    randomNumber = random.Next(0, 10);

    do
    {
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
    HashSet<int> set = new HashSet<int>();
    Console.Write("Wpisz wartości: ");
    String[] tab = Console.ReadLine().Split(" ");

    foreach (String s in tab)
    {
        if(int.TryParse(s, out int number))
        {
            set.Add(number);
        }
    }
    Console.WriteLine($"Liczba unikalnych wartości: {set.Count()}");
    
}

void zad3(String binarna)
{
    Console.WriteLine(binarna);
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

    Console.Write("Zbiór A: ");
    zbior1.ForEach(e => Console.Write($"{e} "));
    Console.WriteLine("");
    Console.Write("Zbiór B: ");
    zbior2.ForEach(e => Console.Write($"{e} "));
    Console.WriteLine("");
    Console.Write("Suma: ");
    suma.Distinct().ToList().ForEach(e => Console.Write($"{e} "));

    Console.WriteLine("");
    Console.Write("Różnica zbiorów A-B: ");
    foreach (int x in zbior1)
    {
        if (!zbior2.Contains(x))
        {
            Console.Write($"{x} ");
        }
    }
    Console.WriteLine("");
    Console.Write("Cześć wspólna: ");
    foreach (int x in zbior1)
    {
        if (zbior2.Contains(x))
        {
            Console.Write($"{x} ");
        }
    }

    Console.WriteLine("");
    Console.Write("Różnica symetryczna zbiorów: ");
    foreach(int x in suma)
    {
        if (!zbior1.Contains(x) || !zbior2.Contains(x))
        {
            Console.Write($"{x} ");
        }
    }

    Console.WriteLine("");
}

void zad5()
{
    Console.Write("Wpisz liczbe: ");
    int x = Convert.ToInt32(Console.ReadLine());
    int dzielniki = 0;

    for (int i=1; i <=x; i++)
    {
        if (x % i == 0)
        {
            dzielniki++;
        }
    }

    if (dzielniki == 2)
    {
        Console.WriteLine($"{x} to liczba pierwsza");
    }
    else
    {
        Console.WriteLine($"{x} to nie jest liczba pierwsza");
    }
}

zad5();