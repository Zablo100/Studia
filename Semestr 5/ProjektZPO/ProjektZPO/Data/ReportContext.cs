using Microsoft.EntityFrameworkCore;
using ProjektZPO.Models;

namespace ProjektZPO.Data
{
    public class ReportContext : DbContext
    {
        public ReportContext(DbContextOptions options) : base(options) { }

        public DbSet<ReportModel> Reports { get; set; }
        public DbSet<CommentModel> Comments { get; set; }
        public DbSet<ComputerModel> Computers { get; set; }

        public DbSet<User> Users { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CommentModel>()
                .HasOne(x => x.report)
                .WithMany(y => y.comments);

            modelBuilder.Entity<ComputerModel>()
                 .HasOne(x => x.user)
                .WithOne(y => y.computer);
        }
    }
}
