using System.Text.Json.Serialization;

namespace ProjektZPO.Models
{
    public class ComputerModel
    {
        public int id { get; set; }
        public string name { get; set; }
        public int? userId { get; set; }
        [JsonIgnore]
        public User? user { get; set; } 

        public ComputerModel(int id, string name, int? userId)
        {
            this.id = id;
            this.name = name;
            if (userId == null)
            {
                this.userId = 0;
            }
            else
            {
                this.userId = userId;
            }
            
        }

    }
}
