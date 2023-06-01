using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ProjektZPO.Data;
using ProjektZPO.Models;
using ProjektZPO.Services;

var builder = WebApplication.CreateBuilder(args);

var frontend = "_frontend";

// Add services to the container.
builder.Services.AddCors(cors =>
{
    cors.AddPolicy(name: frontend, policy =>
    {
        policy.WithOrigins("http://127.0.0.1:5555",
            "http://127.0.0.1:5500",
            "http://127.0.0.1:5555",
            "http://localhost:4200")
        .AllowAnyHeader()
        .AllowAnyMethod();
    });
});

builder.Services.AddDbContext<ReportContext>(options =>
{
    options.UseMySql(builder.Configuration.GetConnectionString("MariaDbConnectionString"),
        ServerVersion.AutoDetect(builder.Configuration.GetConnectionString("MariaDbConnectionString"))
        );
});




builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

app.MapGet("/api/admin/users", async (ReportContext context) =>
{
    var users = await context.Users.ToListAsync();
    foreach(var user in users)
    {
        user.computer = context.Computers.Where(c => c.userId == user.id).FirstOrDefault();
    }

    return users;
});

app.MapGet("api/admin/pc", async (ReportContext context) => await context.Computers.ToListAsync());

app.MapPut("api/admin/user", async (ReportContext context, [FromBody] UserRequest request) =>
{
    var user = new User
    {
        id = request.id,
        username = request.username,
        password = request.password,
        computer = null,
        role = request.role,
    };

    if (request.pc != 0)
    {
        var pc = await context.Computers.FindAsync(request.pc);
        pc.user = user;
        pc.userId = user.id;

        context.Computers.Update(pc);
    }
    else
    {
        var pc = await context.Computers.Where(e => e.userId == request.id).FirstOrDefaultAsync();
        if (pc != null)
        {
            pc.user = null;
            pc.userId = null;

            context.Computers.Update(pc);
        }
    }

    context.Users.Update(user);
    context.SaveChanges();

    Logger.Log("Nast¹pi³a edycja u¿ytkownika", user.username);
});

app.MapDelete("api/admin/user/{id}", async (ReportContext context, int id) =>
{
    var user = await context.Users.FindAsync(id);

    context.Users.Remove(user);
    await context.SaveChangesAsync();

    Logger.Log("Usuniêto u¿ytkownika", user.username);
});

app.MapPost("api/admin/user", async (ReportContext context, [FromBody] UserRequest request) =>
{
    var user = new User
    {
        id = request.id,
        password = request.password,
        username = request.username,
        role = request.role,
        computer = null,
    };

    context.Users.Add(user);

    if (request.pc != 0)
    {
        var pc = await context.Computers.FindAsync(request.pc);
        pc.user = user;
        pc.userId = user.id;
    }

    await context.SaveChangesAsync();
    Logger.Log($"Utworzono nowego u¿ytkownika", user.username);
});

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.ConfigureExpectionHandler();

app.UseCors(frontend);

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
