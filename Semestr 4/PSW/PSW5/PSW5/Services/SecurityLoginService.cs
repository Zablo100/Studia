using PSW5.Models;

namespace PSW5.Services
{
    public class SecurityLoginService
    {
        UserDAO userDAO = new UserDAO();

        public SecurityLoginService()
        {
               
        }

        public bool isValid(UserModel user)
        {
            return userDAO.findUserByLogin(user);
        }
        public bool isEmailValid(string emial)
        {
            return userDAO.checkEmail(emial);
        }

        public bool createNewUser(UserModel user)
        {
            if (!isValid(user) && isEmailValid(user.email))
            {
                userDAO.createUser(user);
                return true;
            }
            return false;
        }
    }
}
