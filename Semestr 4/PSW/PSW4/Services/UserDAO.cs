using MySql.Data.MySqlClient;
using PSW4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW4.Services
{
    public class UserDAO : IUserDataService
    {
        string connectionString = "Server=localhost;Port=3306;Database=psw4;Uid=root;Pwd=123";
        public bool findUserByLoginAndPassword(UserModel user)
        {
            bool loginSucces = false;
            string sqlQuery = "SELECT * FROM psw4.Users WHERE Login = @login AND Password = @password";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@login", user.Login);
                cmd.Parameters.AddWithValue("@password", user.Password);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    if (reader.HasRows)
                    {
                        loginSucces = true;
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }

            return loginSucces;
        }

        public bool findLogin(string login)
        {
            bool isUserExists = false;
            string sqlQuery = "SELECT * FROM psw4.Users WHERE Login = @login";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@login", login);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    if (reader.HasRows)
                    {
                        isUserExists = true;
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }

            return isUserExists;
        }

        public bool findEmail(string email)
        {
            bool EmailAlreadyTaken = false;
            string sqlQuery = "SELECT * FROM psw4.Users WHERE Email = @email";

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
                        EmailAlreadyTaken = true;
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }

            return EmailAlreadyTaken;
        }

        public void createNewUser(UserModel user)
        {
            string sqlQuery = "INSERT INTO psw4.Users (Login, Password, Email, Name, Surname, RegisterDate) " +
                "VALUES (@Login, @Password, @Email, @Name, @Surname, @RegisterDate)";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@Login", user.Login);
                cmd.Parameters.AddWithValue("@Password", user.Password);
                cmd.Parameters.AddWithValue("@Email", user.Email);
                cmd.Parameters.AddWithValue("@Name", user.Name);
                cmd.Parameters.AddWithValue("@Surname", user.Surname);
                cmd.Parameters.AddWithValue("@RegisterDate", user.RegisterDate);
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

        public UserModel getUserInfo(UserModel user)
        {
            string sqlQuery = "SELECT * FROM psw4.Users WHERE Login = @login";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                UserModel userInfo;
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@login", user.Login);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    reader.Read();
                    UserModel fullUserInfo = new UserModel(
                        (int)reader[0],
                        (string)reader[1],
                        (string)reader[2],
                        (string)reader[3],
                        (string)reader[4],
                        (string)reader[5],
                        (UserModel.Role)reader[6],
                        (DateTime)reader[7]);
                    userInfo = fullUserInfo;
                    return userInfo;
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }

            }
            return new UserModel();
        }
        public UserModel getUserInfoById(int id)
        {
            string sqlQuery = "SELECT * FROM psw4.Users WHERE Id = @id";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                UserModel userInfo;
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@id", id);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    reader.Read();
                    UserModel fullUserInfo = new UserModel(
                        (int)reader[0],
                        (string)reader[1],
                        (string)reader[2],
                        (string)reader[3],
                        (string)reader[4],
                        (string)reader[5],
                        (UserModel.Role)reader[6],
                        (DateTime)reader[7]);
                    userInfo = fullUserInfo;
                    return userInfo;
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }

            }
            return new UserModel();
        }
        
        public List<UserModel> getAllUsers()
        {
            List<UserModel> users = new List<UserModel>();
            string sqlQuery = "SELECT * FROM psw4.Users";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        UserModel user = new UserModel(
                            (int)reader[0],
                            (string)reader[1],
                            (string)reader[2],
                            (string)reader[3],
                            (string)reader[4],
                            (string)reader[5],
                            (UserModel.Role)reader[6],
                            (DateTime)reader[7]);
                        users.Add(user);
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }

                return users;

            }
        }

        public void deleteUserById(UserModel user)
        {
            string query = "DELETE FROM psw4.users WHERE Id = @id";
            using(MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand command = new MySqlCommand(query, connection);
                command.Parameters.AddWithValue("@id", user.Id);
                try
                {
                    connection.Open();
                    command.ExecuteNonQuery();
                }catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }

        public void updateUserPassword(UserModel user, string password)
        {
            string query = "UPDATE psw4.users SET password = @password WHERE Id = @id";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(query, connection);
                cmd.Parameters.AddWithValue("@password", password);
                cmd.Parameters.AddWithValue("@id", user.Id);
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
    }
}
