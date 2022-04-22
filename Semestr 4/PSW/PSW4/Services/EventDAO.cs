using MySql.Data.MySqlClient;
using PSW4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW4.Services
{
    public class EventDAO : IEventDataService
    {
        string connectionString = "Server=localhost;Port=3305;Database=psw4;Uid=root;Pwd=123";
        public List<EventModel> getAllEvents()
        {
            List<EventModel> events = new List<EventModel>();

            string Query = "SELECT * FROM psw4.Events";

            using(MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(Query, connection);

                try
                {
                    connection.Open();

                    MySqlDataReader reader = cmd.ExecuteReader();
                    while (reader.Read())
                    {
                        events.Add(new EventModel((int)reader[0], (string)reader[1],
                            (string)reader[2], (DateTime)reader[3] ));
                    }
                }catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }

                return events;
            }
        }
        public void SignedUpForEvent(UserModel user, EventModel eventModel)
        {
            string Query = "INSERT INTO psw4.participation (UserId, EventId) VALUES (@userId, @eventId)";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(Query, connection);
                cmd.Parameters.AddWithValue("@userId", user.Id);
                cmd.Parameters.AddWithValue("@eventId", eventModel.Id);
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
        public bool isUserAlreadySignUp(UserModel user, EventModel eventModel)
        {
            string sqlQuery = "SELECT * FROM psw4.participation WHERE UserId = @userId AND EventId = @eventId";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@userId", user.Id);
                cmd.Parameters.AddWithValue("@eventId", eventModel.Id);
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
            }
            return false;
        }
    }
}
