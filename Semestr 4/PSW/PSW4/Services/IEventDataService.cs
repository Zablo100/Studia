using PSW4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW4.Services
{
    public interface IEventDataService
    {
        List<EventModel> getAllEvents();
        public void SignedUpForEvent(UserModel user, EventModel eventModel, int type, int foodType);
        public bool isUserAlreadySignUp(UserModel user, EventModel eventModel);
    }
}
