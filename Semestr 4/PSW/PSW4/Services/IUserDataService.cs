using PSW4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW4.Services
{
    public interface IUserDataService
    {
        public bool findUserByLoginAndPassword(UserModel user);
        public bool findLogin(string login);
        public bool findEmail(string email);
        public void createNewUser(UserModel user);
        public UserModel getUserInfo(UserModel user);
        public List<UserModel> getAllUsers();
        public void deleteUserById(UserModel user);
        public void updateUserPassword(UserModel user, string password);
    }
}
