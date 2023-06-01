using Microsoft.AspNetCore.Mvc;

namespace ProjektZPO.Models
{
    public enum Status
    {
        oczekujące,
        W_trakcie,
        zakończone

    }
    public class ReportModel
    {
        public int id { get; set; }
        public string? date { get; set; }
        public string user { get; set; }
        public string? komputer { get; set; }
        public string opis { get; set; }
        public string pracownik { get; set; }
        public string? status { get; set; }
        public List<CommentModel>? comments { get; set; }

        public ReportModel(int id, string date, string user, string komputer, string opis)
        {
            this.id = id;
            this.date = date;
            this.user = user;
            this.komputer = komputer;
            this.opis = opis;
            this.status = Status.oczekujące.ToString();
            this.pracownik = "Brak";
        }

    }
}
