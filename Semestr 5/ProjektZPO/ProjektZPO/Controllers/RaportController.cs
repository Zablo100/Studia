using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ProjektZPO.Data;
using ProjektZPO.Models;
using ProjektZPO.Services;
using System.Globalization;

namespace ProjektZPO.Controllers
{
    public record RaportRequest(string start, string end) { }
    public record RapoprtDatailRequest(string typ) { }
    public record RaportResponse(string pc, int ile);

    [Route("/api/raport")]
    [ApiController]
    public class RaportController : Controller
    {
        private ReportContext contex;

        public RaportController(ReportContext context)
        {
            this.contex = context;
        }


        [HttpPost]
        public async Task<IActionResult> getByDate([FromBody] RaportRequest request)
        {
            var cultureInfo = new CultureInfo("pl-PL");
            var rawResult = await contex.Reports.ToListAsync();


            var result = rawResult.Where(ticket =>
                DateOnly.FromDateTime(DateTime.Parse(ticket.date)) >= DateOnly.Parse(request.start) &&
                DateOnly.FromDateTime(DateTime.Parse(ticket.date)) <= DateOnly.Parse(request.end)).ToList();

            var group = result.GroupBy(ticket => ticket.komputer);
            List<RaportResponse> response = new List<RaportResponse>();

            foreach(var item in group)
            {
                response.Add(new RaportResponse(item.Key, item.Count()));
            }

            Logger.Log($"Nastąpiło utworzenie raportu dla okresu", $"{request.start}-{request.end}");
            return Ok(response.Take(10));          
        }

        [HttpPost("{name}")]
        public async Task<IActionResult> getRaportByUser(string name,[FromBody] RapoprtDatailRequest request)
        {
            List<ReportModel> result = null;
            if (request.typ == "user")
            {
                result = await contex.Reports.Where(ticket => ticket.user == name).ToListAsync();
            }
            else
            {
                result = await contex.Reports.Where(ticket => ticket.komputer == name).ToListAsync();
            }

            if (result == null)
            {
                return NotFound(name);
            }

            Logger.Log($"Nastąpiło utworzenie raportu dla", name);
            return Ok(result);
        }


        
    }
}
