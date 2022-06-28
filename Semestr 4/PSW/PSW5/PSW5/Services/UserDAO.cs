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
        public void createUser(UserModel user)
        {
            string sqlQuery = "INSERT INTO psw5.user (Login, Password, Email, Name, LastName) " +
                    "VALUES (@Login, @Password, @Email, @Name, @Surname)";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@Login", user.login);
                cmd.Parameters.AddWithValue("@Password", user.password);
                cmd.Parameters.AddWithValue("@Email", user.email);
                cmd.Parameters.AddWithValue("@Name", user.name);
                cmd.Parameters.AddWithValue("@Surname", user.lastName);
                try
                {
                    connection.Open();
                    cmd.ExecuteNonQuery();

                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }
        public bool checkEmail(string email)
        {
            bool isEmailValid = true;
            string sqlQuery = "SELECT * FROM psw5.user WHERE Email = @email";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@email", email);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    if (reader.HasRows)
                    {
                        isEmailValid = false;
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }

            return isEmailValid;
        }

        
    }
}
