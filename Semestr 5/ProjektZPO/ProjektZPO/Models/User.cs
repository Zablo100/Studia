namespace ProjektZPO.Models
{
    public enum Role
    {
        user,
        mod,
        admin
    }
    public record User
    {
        public int id { get; init; }
        public string username { get; init; }
        public string password { get; init; }
        public ComputerModel? computer { get; set; }
        public Role role { get; init; }
    }

    public record UserRequest()
    {
        public int id { get; init; }
        public string username { get; init; }
        public string password { get; init; }
        public int pc { get; set; }
        public Role role { get; init; }
    }

}
