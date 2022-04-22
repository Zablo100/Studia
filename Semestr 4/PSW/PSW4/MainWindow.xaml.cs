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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace PSW4
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        string password;
        int loginAttempts = 3;
        Services.UserDAO userDAO = new Services.UserDAO();
        public MainWindow()
        {
            InitializeComponent();
        }

        private void ShowPasswordCheck_Checked(object sender, RoutedEventArgs e)
        {
            password = userPassword.Password;
            userPassword.Password = string.Empty;
            userPassword.IsEnabled = false;
            passwordText.Visibility = Visibility.Visible;
            passwordPlaceholder.Visibility = Visibility.Collapsed;
            passwordText.Text = password;
        }

        private void ShowPasswordCheck_Unchecked(object sender, RoutedEventArgs e)
        {
            userPassword.Password = password;
            userPassword.IsEnabled = true;
            passwordText.Visibility = Visibility.Collapsed;
        }

        private void userPassword_PasswordChanged(object sender, RoutedEventArgs e)
        {
            if (userPassword.Password.Length == 0)
            {
                passwordPlaceholder.Visibility = Visibility.Visible;
            }
            else
            {
                passwordPlaceholder.Visibility = Visibility.Collapsed;
            }
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

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Window window = new RegisterWindow();
            window.Show();
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            UserModel user = new UserModel(userName.Text, userPassword.Password);
            if (userDAO.findUserByLoginAndPassword(user))
            {
                showAppWindow(user);
            }
            else
            {
                loginAttempt();
            }
        }

        private void loginAttempt()
        {
            loginFaild.Text = $"Logowanie nieudane! ({loginAttempts}/3)";
            loginAttempts--;
            if (loginAttempts < 0)
            {
                LoginButton.IsEnabled = false;
                loginFaild.Text = "Zbyt duże ilość nieudanych prób logowania";
            }
        }

        private void showAppWindow(UserModel user)
        {
            user = userDAO.getUserInfo(user);
            if (user.Permission == UserModel.Role.user)
            {
                Window userWindow = new UserWindow(user);
                userWindow.Show();
                Close();
            }
            else if(user.Permission == UserModel.Role.admin)
            {
                Window window = new AdminWindow();
                window.Show();
                Close();
            }
        }
    }
}
