using System.Collections.Generic;

void zad1()
{
    Console.Write("Wpisz wartości: ");
    String[] tab = Console.ReadLine().Split(" ");
    int[] tablica = new int[tab.Length];

    for (int i = 0; i < tab.Length; i++)
    {
        tablica[i] = int.Parse(tab[i]);
    }

    int n = tablica.Length;
    do
    {
        for (int i = 0; i < n - 1; i++)
        {
            if (tablica[i] > tablica[i + 1])
            {
                int tmp = tablica[i];
                tablica[i] = tablica[i + 1];
                tablica[i + 1] = tmp;
            }
        }
        n--;
    }
    while (n > 1);

    Console.Write("Tablica po sortowaniu: ");
    foreach (int i in tablica)
    {
        Console.Write($"{i} ");
    }

}

void zad2()
{
    List<double> list = new List<double>() { 2,3,3.5,4,4.5,5};

    Console.Write("Wpisz rozmiar tablicy: ");

    int size =  Convert.ToInt32(Console.ReadLine());
    double[] tab = new double[size];
    int temp;

    Random random = new Random();

    for(int i = 0; i < size; i++)
    {
        temp = random.Next(list.Count());
        tab[i] = list[temp];
    }

    double min = tab.Min();
    double max = tab.Max();
    double average = tab.Average();
    Console.WriteLine($"Min: {min} Max: {max} Średnia: {average}");
    Console.WriteLine("Wartości mniejsze niż średnia");
    foreach(double value in tab)
    {
        if (value < average)
        {
            Console.Write($"{value} ");
        }
    }
    Console.WriteLine(" ");
    Console.WriteLine("Wartości większe niż średnia");
    foreach (double value in tab)
    {
        if (value > average)
        {
            Console.Write($"{value} ");
        }
    }
    Console.WriteLine(" ");

    Dictionary<double, int> wys = new Dictionary<double, int>();
    foreach (double i in tab)
    {
        if (wys.ContainsKey(i))
        {
            wys[i]++;
        }
        else
        {
            wys.Add(i, 1);
        }
    }

    Console.WriteLine(" ");
    foreach (KeyValuePair<double, int> i in wys)
    {
        Console.WriteLine($"{i.Key} występuje {i.Value}");
    }





    double sum = tab.Select(x => (x - average) * (x - average)).Sum();
    double sd = Math.Sqrt(sum/tab.Length);
    Console.WriteLine($"Odchylenie standardowe: {sd}");

}
void zad3()
{
    Console.Write("Wpisz wymiar macierzy: ");
    int size = Convert.ToInt32(Console.ReadLine());
    int[,] m1 = new int[size,size];
    int[,] m2 = new int[size,size];
    int[,] wynik = new int[size,size];
    Random random = new Random();
    
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            m1[i,j] = random.Next(-10, 10);
            m2[i, j] = random.Next(-10, 10);
        }
    }

    show(m1,size,"Macierz 1:");
    show(m2,size, "Macierz 2:");

    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            wynik[i,j] = m1[i,j] + m2[i,j];
        }
    }

    show(wynik, size, "Dodawanie:");

    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            wynik[i, j] = m1[i, j] - m2[i, j];
        }
    }

    show(wynik, size, "Odejmowanie:");

    int x = 0;
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            for (int k = 0; k < size; k++)
            {
                x += (m1[i, k] * m2[k, j]);
            }
            wynik[i, j] = x;
            x = 0;
        }

    }

    show(wynik, size, "Mnożenie:");
}

void show(int[,] m, int size, string message)
{
    Console.WriteLine(message);
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            Console.Write($"{m[i, j]} ");
        }
        Console.WriteLine("");
    }
}

zad3();