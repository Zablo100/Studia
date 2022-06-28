namespace PSW5.Models
{
    public class UserModel
    {
        public int id { get; set; }
        public string login { get; set; }
        public string password { get; set; }
        public string email { get; set; }
        public string name { get; set; }
        public string lastName { get; set; }

        public UserModel()
        {

        }

        public UserModel(int id, string login, string password, string email, string name, string lastName)
        {
            this.id = id;
            this.login = login;
            this.email = email;
            this.password = password;
            this.name = name;
            this.lastName = lastName;
        }

        public UserModel(string login)
        {
            this.login = login;
        }
    }


}
