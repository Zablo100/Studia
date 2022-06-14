using MySql.Data.MySqlClient;
using PSW5.Models;

namespace PSW5.Services
{
    public class UserDAO
    {
        readonly string connectionString = "Server=localhost;Port=3306;Database=psw5;Uid=root;Pwd=123";

        public bool findUserByLogin(UserModel user)
        {

            string sqlQuery = "SELECT * FROM psw5.user WHERE Login = @login AND Password = @password";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@login", user.login);
                cmd.Parameters.AddWithValue("@password", user.password);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    if (reader.HasRows)
                    {
                        return true;
                    }
                   
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                    
                }

                return false;
            }

        }

        
    }
}
