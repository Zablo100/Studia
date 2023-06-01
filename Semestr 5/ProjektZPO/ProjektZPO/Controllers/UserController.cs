using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ProjektZPO.Data;
using ProjektZPO.Models;
using ProjektZPO.Services;

namespace ProjektZPO.Controllers
{
    public class LoginResponse{
        public string username { get; init; }
        public Role role { get; init; }
        public string pc { get; init; }

        public LoginResponse(User user, ComputerModel? computer)
        {
            username = user.username;
            role = user.role;
            if (computer != null)
            {
                pc = computer.name;
            }
            else
            {
                pc = "Brak";
            }
        }
    }

    [Route("/api/user")]
    [ApiController]
    public class UserController : Controller
    {
        public ReportContext context;
        public UserController(ReportContext context)
        {
            this.context = context;
        }

        [HttpPost]
        public async Task<IActionResult> logIn([FromBody] User user)
        {
            var user1 = (from u in context.Users
                        where u.username == user.username
                        select new
                        {
                            u.id,
                            u.password,
                            u.username,
                            u.role
                        }).FirstOrDefault();

            if (user1 == null)
            {
                return NotFound();
            }

            var computer = await context.Computers.Where(c => c.userId == user1.id).FirstOrDefaultAsync();
            var user2 = await context.Users.FindAsync(user1.id);

            LoginResponse loginResponse = new LoginResponse(user2, computer);

            if (user1.password == user.password)
            {
                Logger.Log("Nastąpiło logowanie na konto", user1.username);
                return Ok(loginResponse);
            }
            else
            {
                Logger.Log("Nastąpiło próba logowania na konto", user1.username);
                return BadRequest();
            }

        }

        [HttpGet("{name}")]
        public async Task<IActionResult> getReportsByPreson(string name)
        {
            List<ReportModel> reports = await context.Reports.ToListAsync();

            List<ReportModel> reportByUser = reports.Where(e => e.user == name).ToList();

            return Ok(reportByUser);
        }

        [HttpGet]
        public async Task<IActionResult> getEmployees()
        {
            var response = await context.Users.Where(u => u.role == Role.mod).ToListAsync();

            return Ok(response);
        }

    }
}
