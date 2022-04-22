using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW4.Models
{
    public class UserModel
    {
        public int Id { get; set; }
        public string Login { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public Role Permission { get; set; }
        public DateTime RegisterDate { get; set; }

        public enum Role
        {
            user,
            admin
        }

        public UserModel()
        {

        }
        public UserModel(string Login, string Password)
        {
            this.Login = Login;
            this.Password = Password;
        }

        public UserModel(string Login, string Password, string Name,
            string Surname, string Email, DateTime RegisterDate)
        {
            this.Login = Login;
            this.Password = Password;
            this.Name = Name;
            this.Surname = Surname;
            this.Email = Email;
            this.RegisterDate = RegisterDate;
            Permission = Role.user;
        }
        public UserModel(int Id,string Login, string Password, string Name,
                        string Surname, string Email, Role Permission, DateTime RegisterDate)
        {
            this.Id = Id;
            this.Login = Login;
            this.Password = Password;
            this.Name = Name;
            this.Surname = Surname;
            this.Email = Email;
            this.RegisterDate = RegisterDate;
            this.Permission = Permission;
        }
    }

}
