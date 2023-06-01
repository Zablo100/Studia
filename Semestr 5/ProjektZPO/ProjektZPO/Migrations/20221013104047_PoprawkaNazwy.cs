using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ProjektZPO.Migrations
{
    public partial class PoprawkaNazwy : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "data",
                table: "Reports",
                newName: "date");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "date",
                table: "Reports",
                newName: "data");
        }
    }
}
