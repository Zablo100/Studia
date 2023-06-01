using Microsoft.AspNetCore.Mvc;
using System.Text.Json.Serialization;

namespace ProjektZPO.Models
{
    public class CommentModel
    {
        public int id { get; set; }
        public string comment { get; set; }
        public string date { get; set; }
        public int reportId { get; set; }

        [JsonIgnore]
        public ReportModel? report { get; set; }

        public CommentModel(int id,string comment, int reportId)
        {
            this.id = id;
            this.comment = comment;
            this.reportId = reportId;
            date = DateTime.Now.ToShortDateString();
        }
    }
}
