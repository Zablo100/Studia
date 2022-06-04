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
        public String Name { get; private set; }
        public AttendantStatus attendantStatus { get; private set; }
        public participationType participationType { get; private set; }
        public foodType foodType { get; private set; }
         
        
        public EventAttendeeModel(UserModel user, int type, int food, int status)
        {
            userId = user.Id;
            Login = user.Login;
            Email = user.Email;
            Name = user.Name;
            attendantStatus = (AttendantStatus)status;
            participationType = (participationType)type;
            foodType = (foodType)food;
        }
        
        
    }
}
