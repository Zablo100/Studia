﻿// <auto-generated />
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using ProjektZPO.Data;

#nullable disable

namespace ProjektZPO.Migrations
{
    [DbContext(typeof(ReportContext))]
    [Migration("20221013101846_PoprawkaKlucza")]
    partial class PoprawkaKlucza
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "6.0.10")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            modelBuilder.Entity("ProjektZPO.Models.CommentModel", b =>
                {
                    b.Property<int>("id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("comment")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("date")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<int>("reportId")
                        .HasColumnType("int");

                    b.HasKey("id");

                    b.HasIndex("reportId");

                    b.ToTable("Comments");
                });

            modelBuilder.Entity("ProjektZPO.Models.ReportModel", b =>
                {
                    b.Property<int>("id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("data")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("komputer")
                        .HasColumnType("longtext");

                    b.Property<string>("opis")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("status")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("user")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.HasKey("id");

                    b.ToTable("Reports");
                });

            modelBuilder.Entity("ProjektZPO.Models.CommentModel", b =>
                {
                    b.HasOne("ProjektZPO.Models.ReportModel", "report")
                        .WithMany("comments")
                        .HasForeignKey("reportId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("report");
                });

            modelBuilder.Entity("ProjektZPO.Models.ReportModel", b =>
                {
                    b.Navigation("comments");
                });
#pragma warning restore 612, 618
        }
    }
}
