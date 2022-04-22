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
        List<UserModel> users = new List<UserModel>();
        UserModel selectedUser;
        public AdminWindow()
        {
            InitializeComponent();
            loadUsers();
        }

        private void loadUsers()
        {
            users = userDAO.getAllUsers();
            UsersView.ItemsSource = users;
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
        }

        private void DeletUserBtn_Click(object sender, RoutedEventArgs e)
        {
            userDAO.deleteUserById(selectedUser);
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
    }
}
