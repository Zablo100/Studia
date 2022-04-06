using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW3
{
    public class RGBController
    {
        public RGB initColor()
        {
            int[] colors = {0,0,0};
            String[] colorName = { "Czerwony", "Zielony", "Niebieski" };
            Console.WriteLine("Wpisz wartości następujących kolorów");
            for (int i = 0; i < 3; i++)
            {
                do
                {
                    Console.Write($"{colorName[i]}: ");
                    colors[i] = Convert.ToInt32(Console.ReadLine());
                    if (validColor(colors[i]))
                    {
                        break;
                    }
                    Console.WriteLine("Musisz podać wartość z zakresu 0-255");
                } while (true);
            }
            return new RGB(colors[0], colors[1], colors[2]);
        }

        public void showRGBValue(RGB color)
        {
            Console.WriteLine($"[{color.rValue}, {color.gValue}, {color.bValue}]");
        }
        
        public RGB mixColors(RGB color1, RGB color2)
        {
            int[] colorValue = {0,0,0};
            colorValue[0] = (color1.rValue + color2.rValue)/2;
            colorValue[1] = (color1.gValue + color2.gValue)/2;
            colorValue[2] = (color1.bValue + color2.bValue)/2;

            for (int i = 0; i < 3; i++)
            {
                if (colorValue[i] > 255)
                {
                    colorValue[i] = 255;
                }
            }

            return new RGB(colorValue[0], colorValue[1], colorValue[2]);
        }
        private bool validColor(int value)
        {
            if (value >= 0 && value <= 255)
            {
                return true;
            }
            return false;
        }
    }
}
