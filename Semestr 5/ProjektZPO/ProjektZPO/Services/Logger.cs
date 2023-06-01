using System.Runtime.InteropServices;

namespace ProjektZPO.Services
{
    public static class Logger
    {
        public static void Log(string msg, [Optional]string options)
        {
            string msgToFile;
            if (options != null)
            {
                msgToFile = $"[{DateTime.Now}] {msg} [{options}]\n";
            }
            else
            {
                msgToFile = $"[{DateTime.Now}] {msg}\n";
            }

            writetoFile(msgToFile);

            Console.Write("[");
            Console.ForegroundColor = ConsoleColor.DarkYellow;
            Console.Write($"{DateTime.Now}");
            Console.ForegroundColor = ConsoleColor.White;
            Console.Write("] ");
            Console.Write(msg);
            if (options != null)
            {
                Console.Write(" [");
                Console.ForegroundColor = ConsoleColor.DarkCyan;
                Console.Write($"{options}");
                Console.ForegroundColor = ConsoleColor.White;
                Console.Write("]");
                msgToFile += $"[{options}]";
            }
            Console.Write("\n");
        }

        public static void Error(string msg)
        {
            string msgToFile = $"[{DateTime.Now}] {msg}\n";
            writetoFile(msgToFile);

            Console.ForegroundColor= ConsoleColor.DarkRed;
            Console.WriteLine("========================================================================================");
            Console.ForegroundColor = ConsoleColor.White;
            Console.Write("[");
            Console.ForegroundColor = ConsoleColor.DarkYellow;
            Console.Write($"{DateTime.Now}");
            Console.ForegroundColor = ConsoleColor.White;
            Console.Write("] ");
            Console.ForegroundColor = ConsoleColor.Red;
            Console.Write(msg);
            Console.Write("\n");
            Console.ForegroundColor = ConsoleColor.DarkRed;
            Console.WriteLine("========================================================================================");
        }

        private static async Task writetoFile(string msg)
        {
            string fileNamePC = "$C:\\Users\\Zablo\\OneDrive\\Pulpit\\Studia\\Git\\ProjektZPO\\ProjektZPO\\Services\\Logs\\[{DateTime.Now.ToShortDateString()}] Logs.txt";
            string fileName = $"C:\\Users\\Zablo\\source\\repos\\ProjektZPO\\ProjektZPO\\Services\\Logs\\[{DateTime.Now.ToShortDateString()}] Logs.txt";
            await File.AppendAllTextAsync(fileName, msg);

        }
    }
}
