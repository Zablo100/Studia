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
    }
}
