using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW4.Models
{
    public class EventModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Agenda { get; set; }
        public DateTime EventDate { get; set; }

        public EventModel(int Id, string Name, string Agenda, DateTime EventDate)
        {
            this.Id = Id;
            this.Name = Name;
            this.Agenda = Agenda;
            this.EventDate = EventDate;
        }


        public override string ToString()
        {
            return Name;
        }
    }
}
