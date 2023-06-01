using Microsoft.AspNetCore.Mvc;
using ProjektZPO.Data;

namespace ProjektZPO.Controllers
{
    [Route("/api/computers")]
    [ApiController]
    public class ComputerController : Controller
    {
        private ReportContext context;

        public ComputerController(ReportContext context)
        {
            this.context = context;
        }


    }
}
