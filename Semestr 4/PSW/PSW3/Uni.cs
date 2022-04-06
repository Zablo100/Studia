using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW3
{
    public class Uni
    {
        public Dictionary<Student, List<double>> students = new Dictionary<Student, List<double>>();
        List<double> possibleGrades = new List<double>() { 2, 3, 3.5, 4, 4.5, 5 };

        public void addStudent(Student student)
        {
            List<double> studentGrades = new List<double>();
            Console.Write("Wpisz ilość ocen: ");
            int numberOfGrades = Convert.ToInt32(Console.ReadLine());
            for (int i = 0; i < numberOfGrades; i++)
            {
                do
                {
                    Console.Write("Wpisz ocene: ");
                    double grade = Convert.ToDouble(Console.ReadLine());
                    if (possibleGrades.Contains(grade))
                    {
                        studentGrades.Add(grade);
                        break;
                    }
                    Console.WriteLine("Wpisana ocena jest nieprawidłowa!");
                } while (true);
            }
            students.Add(student, studentGrades);
        }

        public void removeStudent(int id)
        {
            foreach (Student student in students.Keys)
            {
                if (student.nrIndeksu == id)
                {
                    students.Remove(student);   
                }
            }
        }

        public double average(Student student)
        {
            return students[student].Average();
        }

        public double allAverage()
        {
            double wynik = 0;
            foreach (Student student in students.Keys)
            {
                wynik += average(student);
            }
            return wynik / students.Count();
        }
    }
}
