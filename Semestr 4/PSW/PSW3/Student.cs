using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW3
{
    public class Student
    {
        public int nrIndeksu { get; private set; }
        public string imie { get; private set; }
        public string nazwisko { get; private set; }
        public int rokStudiow { get; private set; }

        public Student(string imie, string naziwsko, int id, int rok)
        {
            this.imie = imie;
            this.nazwisko = naziwsko;
            this.rokStudiow = rok;
            this.nrIndeksu = id;
        }
        public void printStudent()
        {
            Console.WriteLine($"Imię: {imie} Nazwisko: {nazwisko} Numer Indeksu: {nrIndeksu} Rok: {rokStudiow}");
        }
    }
}
