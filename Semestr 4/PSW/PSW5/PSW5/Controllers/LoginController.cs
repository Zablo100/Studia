using Microsoft.AspNetCore.Mvc;
using PSW5.Models;
using PSW5.Services;

namespace PSW5.Controllers
{
    public class LoginController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult ProcessLogin(UserModel userModel)
        {
            SecurityLoginService securityCheck = new SecurityLoginService();

            if (securityCheck.isValid(userModel))
            {
                return RedirectToAction("Index", "User");
            }
            else
            {
                return View("LogInFail", userModel);
            }
        }

        public IActionResult Register()
        {
            return View("Register");
        }

        public IActionResult RegisterUser(UserModel user)
        {
            SecurityLoginService securityCheck = new SecurityLoginService();

            if (securityCheck.createNewUser(user))
            {
                return RedirectToAction("Index", "Login");
            }
            else
            {
                return View("RegisterFail");
            }
        }

    }
}
