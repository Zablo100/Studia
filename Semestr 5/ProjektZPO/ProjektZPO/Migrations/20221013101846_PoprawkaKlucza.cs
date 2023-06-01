using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ProjektZPO.Migrations
{
    public partial class PoprawkaKlucza : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_Comments_reportId",
                table: "Comments",
                column: "reportId");

            migrationBuilder.AddForeignKey(
                name: "FK_Comments_Reports_reportId",
                table: "Comments",
                column: "reportId",
                principalTable: "Reports",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Comments_Reports_reportId",
                table: "Comments");

            migrationBuilder.DropIndex(
                name: "IX_Comments_reportId",
                table: "Comments");
        }
    }
}
