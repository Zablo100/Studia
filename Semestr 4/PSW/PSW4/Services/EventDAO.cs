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
        string connectionString = "Server=localhost;Port=3306;Database=psw4;Uid=root;Pwd=123";
        public List<EventModel> getAllEvents()
        {
            List<EventModel> events = new List<EventModel>();

            string Query = "SELECT * FROM psw4.Events";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(Query, connection);

                try
                {
                    connection.Open();

                    MySqlDataReader reader = cmd.ExecuteReader();
                    while (reader.Read())
                    {
                        events.Add(new EventModel((int)reader[0], (string)reader[1],
                            (string)reader[2], (DateTime)reader[3]));
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }

                return events;
            }
        }
        public EventModel getEventById(int id)
        {
            string Query = "SELECT * FROM psw4.Events WHERE Id = @Id ";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(Query, connection);
                cmd.Parameters.AddWithValue("@Id", id);

                try
                {
                    connection.Open();

                    MySqlDataReader reader = cmd.ExecuteReader();
                    reader.Read();
                    EventModel eventInfo = new EventModel((int)reader[0], (string)reader[1],
                            (string)reader[2], (DateTime)reader[3]);

                    return eventInfo;
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }

                return null;
            }
        }
        public void SignedUpForEvent(UserModel user, EventModel eventModel, int type, int foodType)
        {
            string Query = "INSERT INTO psw4.participation (UserId, EventId, Type, FoodType) VALUES (@userId, @eventId, @type, @foodType)";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(Query, connection);
                cmd.Parameters.AddWithValue("@userId", user.Id);
                cmd.Parameters.AddWithValue("@eventId", eventModel.Id);
                cmd.Parameters.AddWithValue("@type", type);
                cmd.Parameters.AddWithValue("@foodType", foodType);
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
        public List<EventAttendeeModel> getEventAttendees(EventModel eventModel, int status)
        {
            UserDAO userDAO = new UserDAO();
            List<EventAttendeeModel> lista = new List<EventAttendeeModel>();

            string sqlQuery = "SELECT * FROM psw4.participation WHERE EventId = @eventId AND Status = @status";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@eventId", eventModel.Id);
                cmd.Parameters.AddWithValue("@status", status);
                try
                {
                    connection.Open();
                    MySqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        UserModel user = userDAO.getUserInfoById((int)reader[1]);
                        EventAttendeeModel eventAttendeeModel = new EventAttendeeModel(user, (int)reader[3], (int)reader[4], (int)reader[5]);
                        lista.Add(eventAttendeeModel);

                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }

            return lista;
        }
        public void changeStatus(int userId, int eventId, int status)
        {
            string Query = "UPDATE psw4.participation SET status = @status WHERE UserId = @userId AND EventId = @eventId";

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(Query, connection);
                cmd.Parameters.AddWithValue("@status", status);
                cmd.Parameters.AddWithValue("@userId", userId);
                cmd.Parameters.AddWithValue("@eventId", eventId);
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
        public void deleteEvent(int id)
        {
            string query = "DELETE FROM psw4.events WHERE Id = @id";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand command = new MySqlCommand(query, connection);
                command.Parameters.AddWithValue("@id", id);
                try
                {
                    connection.Open();
                    command.ExecuteNonQuery();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }
        public void createEvent(EventModel eventInfo)
        {
            string sqlQuery = "INSERT INTO psw4.events (EventName, Agenda, EventDate) " +
                "VALUES (@Name, @Agenda, @Date)";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(sqlQuery, connection);
                cmd.Parameters.AddWithValue("@Name", eventInfo.Name );
                cmd.Parameters.AddWithValue("@Agenda", eventInfo.Agenda );
                cmd.Parameters.AddWithValue("@Date", eventInfo.EventDate );
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
        public void updateEvent(EventModel eventInfo)
        {
            string query = "UPDATE psw4.events SET EventName = @Name, Agenda = @Agenda, EventDate = @Date WHERE id = @id";
            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand cmd = new MySqlCommand(query, connection);
                cmd.Parameters.AddWithValue("@Name", eventInfo.Name);
                cmd.Parameters.AddWithValue("@Agenda", eventInfo.Agenda);
                cmd.Parameters.AddWithValue("@Date", eventInfo.EventDate);
                cmd.Parameters.AddWithValue("@id", eventInfo.Id);
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
