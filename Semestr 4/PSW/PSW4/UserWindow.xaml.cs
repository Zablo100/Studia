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
    /// Logika interakcji dla klasy UserWindow.xaml
    /// </summary>
    public partial class UserWindow : Window
    {
        string placeholder = "Typ uczestnictwa";
        EventDAO eventDAO = new EventDAO();
        List<EventModel> events = new List<EventModel>();
        UserModel user;
        public UserWindow(UserModel user)
        {
            this.user = user;
            InitializeComponent();
        }

        private void closeBtn_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void closeBtn_MouseEnter(object sender, MouseEventArgs e)
        {
            closeBtn.Background = (Brush)new BrushConverter().ConvertFrom("#d90429");
        }

        private void closeBtn_MouseLeave(object sender, MouseEventArgs e)
        {
            closeBtn.Background = Brushes.DimGray;
        }

        private void WindowFrame_MouseDown(object sender, MouseButtonEventArgs e)
        {
            DragMove();
        }

        private void eventListUI_Loaded(object sender, RoutedEventArgs e)
        {
            events = eventDAO.getAllEvents();
            foreach (EventModel eventModel in events)
            {
                eventListUI.Items.Add(eventModel);
            }
        }

        private void eventListUI_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            EventModel eventModel = eventListUI.SelectedItem as EventModel;
            AgendaUI.Text = eventModel.Agenda;
            EventDateUI.Text = eventModel.EventDate.ToString().Substring(0, 16);
            SignUp.IsEnabled = true;
        }

        private void SignUp_Click(object sender, RoutedEventArgs e)
        {
            EventModel eventModel = eventListUI.SelectedItem as EventModel;
            if (!eventDAO.isUserAlreadySignUp(user, eventModel))
            {
                eventDAO.SignedUpForEvent(user, eventModel, participationTypeUI.SelectedIndex, FoodTypUI.SelectedIndex);
            }
            
        }
    }
}
