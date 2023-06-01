using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ProjektZPO.Data;
using ProjektZPO.Services;
using System.Linq;

namespace ProjektZPO.Controllers
{
    [Route("/api/search")]
    public class searchController : Controller
    {
        private ReportContext context;
        public searchController(ReportContext context)
        {
            this.context = context;
        }

        [HttpGet("{query}")]
        public async Task<IActionResult> findCommentWithQuery(string query)
        {
            Logger.Log($"Nastąpiło wyszukanie frazy", query);
            var comments = await context.Comments.Where(c => c.comment.Contains(query)).ToListAsync();
            var response = comments.DistinctBy(c => c.reportId).ToList();

            return Ok(response.ConvertAll(r => r.reportId));
           
        }
    }
}
