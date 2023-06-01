using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ProjektZPO.Data;
using ProjektZPO.Models;
using ProjektZPO.Services;

namespace ProjektZPO.Controllers
{
    [Route("/api/comments")]
    [ApiController]
    public class CommentController : Controller
    {
        private ReportContext context;

        public CommentController(ReportContext context)
        {
            this.context = context;
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> getComments(int id)
        {
            var comments = await context.Comments.Where(c => c.reportId == id).ToListAsync();
            return Ok(comments);
        }

        [HttpPost]
        public void createReport([FromBody] CommentModel comment)
        {
            Logger.Log("Nastąpiło utworzenie nowego komentarza do zgłoszenia", comment.reportId.ToString());
            comment.date = DateTime.Now.ToShortDateString() + " " + DateTime.Now.ToShortTimeString();
            context.Comments.Add(comment);
            context.SaveChanges();
        }
    }
}
