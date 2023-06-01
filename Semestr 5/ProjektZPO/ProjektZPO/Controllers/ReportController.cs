using Microsoft.AspNetCore.Mvc;
using ProjektZPO.Data;
using ProjektZPO.Models;
using Microsoft.EntityFrameworkCore;
using ProjektZPO.Services;

namespace ProjektZPO.Controllers
{
    public record acceptTicketRequest(String status, String emp);

    [Route("/api/reports")]
    [ApiController]
    public class ReportController : Controller
    {
        private ReportContext contex;
        public ReportController(ReportContext context)
        {
            this.contex = context;
        }

        [HttpGet]
        public async Task<IActionResult> getAllReports()
        {
            List<ReportModel> reports = await contex.Reports.ToListAsync();

            return Ok(reports);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> getReport(int id)
        {
            ReportModel? report = null;
            report = await contex.Reports.FindAsync(id);
            report.comments = await contex.Comments.Where(c => c.reportId == id).ToListAsync();
            Logger.Log("Nastąpiło wczytanie szczegółów zgłoszenia", id.ToString());
            return Ok(report);
        }

        [HttpPost]
        public async Task<IActionResult> createReport([FromBody] ReportModel report)
        {
            Logger.Log("Nastąpiło utworzenie nowego zgłoszenia!");
            report.date = DateTime.Now.ToShortDateString() + " " + DateTime.Now.ToShortTimeString();
            report.status = Status.oczekujące.ToString();

            contex.Reports.Add(report);
            await contex.SaveChangesAsync();

            return Ok();
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> changeStatus(int id, [FromBody] acceptTicketRequest data)
        {
            var status = Convert.ToInt32(data.status);
            Logger.Log("Nastąpiła zmiana statusu zgłoszenia", id.ToString());
            var report = contex.Reports.Find(id);
            if (status == 1)
            {
                report.status = "W trakcie";
                report.pracownik = data.emp;
            }
            else if(status == 2)
            {
                report.status = Status.zakończone.ToString();
            }

            contex.SaveChanges();

            return Ok();
        }

    }

}
