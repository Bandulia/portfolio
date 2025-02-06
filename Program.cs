
// Julia Tadic 2024-09-07

namespace Assignment_1_version_1
{
    internal static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {            
            // To customize application configuration such as set high DPI settings or default font,
            // see https://aka.ms/applicationconfiguration.
            ApplicationConfiguration.Initialize();
            MainForm form = new MainForm();
            Application.Run(new MainForm());          

        }
    }
}