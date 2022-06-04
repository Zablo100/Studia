using PSW4.Models;
using PSW4.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;
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
    /// Logika interakcji dla klasy RegisterWindow.xaml
    /// </summary>
    public partial class RegisterWindow : Window
    {
        Services.UserDAO userDAO = new Services.UserDAO();
        string password;

        public RegisterWindow()
        {
            InitializeComponent();
        }

        private bool passwordCheck()
        {
            if (userPassword.Password == userPassword2.Password)
            {
                return true;
            }
            return false;
        }

        private bool isLoginAlreadyTaken()
        {
            if (userDAO.findLogin(userLogin.Text))
            {
                RegisterFaild.Text = "Nazwa użytkownika zajęta!";
                RegisterFaild.Visibility = Visibility.Visible;
                return true;
            }
            return false;
        }

        private bool isEmailAlreadyTaken()
        {
            if (userDAO.findEmail(userEmail.Text))
            {
                RegisterFaild.Text = "Adres email jest już w użyciu!";
                RegisterFaild.Visibility = Visibility.Visible;
                return true;
            }
            return false;
        }
        private bool isEmailValid(string email)
        {
            try
            {
                MailAddress m = new MailAddress(email);

                return true;
            }
            catch (FormatException)
            {
                return false;
            }
        }

        private bool RegisterCheck()
        {
            bool isUserValid = false;
            
            if(passwordCheck() && !isLoginAlreadyTaken() && !isEmailAlreadyTaken() && isEmailValid(userEmail.Text))
            {
                isUserValid = true;
            }
            return isUserValid;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if (RegisterCheck())
            {
                UserModel user = new UserModel(
                    userLogin.Text,
                    userPassword.Password,
                    userName.Text,
                    userLastName.Text,
                    userEmail.Text,
                    DateTime.Today
                    );
                userDAO.createNewUser(user);
                Close();
            }
        }

        private void TextBlock_MouseDown(object sender, MouseButtonEventArgs e)
        {
            DragMove();
        }

        private void closeBtn_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void closeBtn_MouseEnter(object sender, MouseEventArgs e)
        {
            closeBtn.Background = (Brush)new BrushConverter().ConvertFrom("#d90429");
        }

        private void closeBtn_MouseLeave(object sender, MouseEventArgs e)
        {
            closeBtn.Background = Brushes.DimGray;
        }

        private void ShowPasswordCheck_Checked(object sender, RoutedEventArgs e)
        {
            password = userPassword.Password;
            userPassword.Password = string.Empty;
            userPassword2.Password = string.Empty;
            userPassword.IsEnabled = false;
            userPassword2.IsEnabled = false;
            passwordText.Visibility = Visibility.Visible;
            passwordText2.Visibility = Visibility.Visible;
            passwordPlaceholder.Visibility = Visibility.Collapsed;
            passwordPlaceholder2.Visibility = Visibility.Collapsed;
            passwordText.Text = password;
            passwordText2.Text = password;
        }

        private void ShowPasswordCheck_Unchecked(object sender, RoutedEventArgs e)
        {
            userPassword.Password = password;
            userPassword2.Password = password;
            userPassword.IsEnabled = true;
            userPassword2.IsEnabled = true;
            passwordText.Visibility = Visibility.Collapsed;
            passwordText2.Visibility = Visibility.Collapsed;
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

        private void userPassword2_PasswordChanged(object sender, RoutedEventArgs e)
        {
            if (userPassword2.Password.Length == 0)
            {
                passwordPlaceholder2.Visibility = Visibility.Visible;
            }
            else
            {
                passwordPlaceholder2.Visibility = Visibility.Collapsed;
            }
        }
    }
}
