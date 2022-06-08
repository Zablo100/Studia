using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW4.Models
{
    public class EventAttendeeModel
    {
        public int userId { get; private set; }
        public String Login { get; private set; }
        public String Email { get; private set; }
        public String Imie { get; private set; }
        public String Nazwisko { get; private set; }
        public AttendantStatus Status { get; private set; }
        public participationType Uczestnictwo { get; private set; }
        public foodType Wyżywienie { get; private set; }
         
        
        public EventAttendeeModel(UserModel user, int type, int food, int status)
        {
            userId = user.Id;
            Login = user.Login;
            Email = user.Email;
            Imie = user.Name;
            Nazwisko = user.Surname;
            Status = (AttendantStatus)status;
            Uczestnictwo = (participationType)type;
            Wyżywienie = (foodType)food;
        }
        
        
    }
}
