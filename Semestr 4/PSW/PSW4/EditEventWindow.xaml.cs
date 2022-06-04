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
    /// Logika interakcji dla klasy EditEventWindow.xaml
    /// </summary>
    public partial class EditEventWindow : Window
    {
        EventDAO eventDAO = new EventDAO();
        int id;
        public EditEventWindow(int id)
        {
            InitializeComponent();
            this.id = id;
            loadEventInfo();
        }
        private void windowFrame_MouseDown(object sender, MouseButtonEventArgs e)
        {
            DragMove();
        }

        private void closeBtn_Click(object sender, RoutedEventArgs e)
        {
            Close();
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

        private void eventName_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (eventName.GetLineLength(0) == 0)
            {
                namePlaceholder.Visibility = Visibility.Visible;
            }
            else
            {
                namePlaceholder.Visibility = Visibility.Collapsed;
            }
        }

        private void saveButton_Click(object sender, RoutedEventArgs e)
        {
            eventDAO.updateEvent(new EventModel(id, eventName.Text, Agenda.Text, eventTime.SelectedDate.Value));
            Close();
        }

        private void loadEventInfo()
        {
            EventModel e = eventDAO.getEventById(id);
            eventName.Text = e.Name;
            Agenda.Text = e.Agenda;
            eventTime.SelectedDate = e.EventDate;
        }
    }
}
