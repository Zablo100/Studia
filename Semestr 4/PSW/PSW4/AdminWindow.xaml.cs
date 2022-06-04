using PSW4.Models;
using PSW4.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace PSW4
{
    /// <summary>
    /// Logika interakcji dla klasy AdminWindow.xaml
    /// </summary>
    public partial class AdminWindow : Window
    {
        UserDAO userDAO = new UserDAO();
        EventDAO eventDAO = new EventDAO();
        List<UserModel> users = new List<UserModel>();
        List<EventModel> events = new List<EventModel>();
        List<EventAttendeeModel> eventAttendees = new List<EventAttendeeModel>();
        UserModel selectedUser;
        int selectedEvent = 0;
        bool isLoaded = false;
        int userId;
        public AdminWindow()
        {
            InitializeComponent();
            loadUsers();
            loadEvents();
        }

        private void loadUsers()
        {
            users = userDAO.getAllUsers();
            UsersView.ItemsSource = users;
        }

        private void loadEvents()
        {
            events = eventDAO.getAllEvents();
            eventListUI.ItemsSource = events;
            EventsList.ItemsSource = events;
        }

        private void windowFrame_MouseDown(object sender, MouseButtonEventArgs e)
        {
            this.DragMove();
        }

        private void closeBtn_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void minBtn_Click(object sender, RoutedEventArgs e)
        {
            WindowState = WindowState.Minimized;
        }

        private void closeBtn_MouseEnter(object sender, MouseEventArgs e)
        {
            closeBtn.Background = (Brush)new BrushConverter().ConvertFrom("#d90429");
        }

        private void closeBtn_MouseLeave(object sender, MouseEventArgs e)
        {
            closeBtn.Background = Brushes.DimGray;
        }

        private void minBtn_MouseEnter(object sender, MouseEventArgs e)
        {
            minBtn.Background = Brushes.Gray;
        }

        private void minBtn_MouseLeave(object sender, MouseEventArgs e)
        {
            minBtn.Background = Brushes.DimGray;
        }

        private void UsersView_SelectedCellsChanged(object sender, SelectedCellsChangedEventArgs e)
        {
            try
            {
                int index = UsersView.SelectedIndex;
                selectedUser = users[index];
                showButtons();
            }catch (ArgumentOutOfRangeException ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        private void ChangePasswordBtn_Click(object sender, RoutedEventArgs e)
        {
            Window window = new ChangePasswordWindow(selectedUser);
            window.Show();
            loadUsers();
        }

        private void DeletUserBtn_Click(object sender, RoutedEventArgs e)
        {
            userDAO.deleteUserById(selectedUser);
            loadUsers();
        }

        private void RefreshData_Click(object sender, RoutedEventArgs e)
        {
            loadUsers();
        }

        private void showButtons()
        {
            ChangePasswordBtn.IsEnabled = true;
            DeletUserBtn.IsEnabled = true;
            ChangeRoleBtn.IsEnabled = true;
            ShowUserEventsBtn.IsEnabled = true;
            RefreshData.IsEnabled = true;
        }

        private void eventListUI_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            try
            {
                selectedEvent = eventListUI.SelectedIndex;
                getEventAttendees(events[selectedEvent], 0);
                isLoaded = true;
                eventStatusUI.IsEnabled = true;
            }
            catch (ArgumentOutOfRangeException ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        private void getEventAttendees(EventModel eventInfo, int status)
        {
            eventAttendees = eventDAO.getEventAttendees(eventInfo, status);
            EventUsersList.ItemsSource = eventAttendees;
        }

        private void AcceptUser_Click(object sender, RoutedEventArgs e)
        {
            eventDAO.changeStatus(userId, selectedEvent+1, 1);
        }

        private void RejectUser_Click(object sender, RoutedEventArgs e)
        {
            eventDAO.changeStatus(userId, selectedEvent+1 , 2);
        }

        private void eventStatusUI_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (isLoaded)
            {
                getEventAttendees(events[selectedEvent], eventStatusUI.SelectedIndex);
            }
        }

        private void EventUsersList_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            try
            {
                int index = EventUsersList.SelectedIndex;
                userId = eventAttendees[index].userId;
                AcceptUser.IsEnabled = true;
                RejectUser.IsEnabled = true;
            }
            catch (ArgumentOutOfRangeException ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        private void createEvent_Click(object sender, RoutedEventArgs e)
        {
            Window window = new NewEventWindow();
            window.Show();
        }

        private void editeEvent_Click(object sender, RoutedEventArgs e)
        {
            Window window = new EditEventWindow(selectedEvent);
            window.Show();
            loadEvents();
        }

        private void deleteEvent_Click(object sender, RoutedEventArgs e)
        {
            eventDAO.deleteEvent(selectedEvent);
        }

        private void EventsList_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            deleteEvent.IsEnabled = true;
            editeEvent.IsEnabled = true;

            selectedEvent = EventsList.SelectedIndex + 1;
        }
    }
}
