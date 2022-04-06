using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW3
{
    public class PSW
    {
        private static void zad1()
        {
            RGBController rgbController = new RGBController();
            Console.WriteLine("Kolor1: ");
            var x = rgbController.initColor();

            Console.WriteLine("Kolor2: ");
            var y = rgbController.initColor();

            var wynik = rgbController.mixColors(x, y);
            rgbController.showRGBValue(wynik);
        }

        private static void zad2()
        {
            Student x = new Student("Natan", "Zabłocki", 22334);
            Student y = new Student("Adam", "Kowalski", 22774);
            Student z = new Student("Gabriel", "Kulawiak", 39982);
            Uni politechnika = new Uni();
            politechnika.addStudent(x);
            politechnika.addStudent(y);
            politechnika.addStudent(z);

            foreach (Student student in politechnika.students.Keys)
            {
                student.printStudent();
            }

            politechnika.removeStudent(22334);

            Console.WriteLine("po usunięciu: ");
            foreach (Student student in politechnika.students.Keys)
            {
                student.printStudent();
            }
            Console.WriteLine($"Średnia ocen: {politechnika.allAverage()}");
        }
        public static void Main(String[] args)
        {
            zad1();
            
        }
    }
}
