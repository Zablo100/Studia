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
    }
}
