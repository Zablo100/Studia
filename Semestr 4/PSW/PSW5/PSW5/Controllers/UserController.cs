using Microsoft.AspNetCore.Mvc;
using PSW5.Models;

namespace PSW5.Controllers
{
    public class UserController : Controller
    {
        public IActionResult Index(UserModel user)
        {
            return View();
        }
    }
}
