using Assignment_1_version_1.Payment;
using UtilitiesLib;

namespace Assignment_1_version_1
{
    partial class PaymentForm : Form
    {
        NumberConverter converter = new NumberConverter();

        public PaymentForm()
        {
            InitializeComponent();
            initPaymentComboBox();

            paymentLabel.Visible = false;
            comboBox1.Visible = false;
            button1.Visible = false;

            panel1.Visible = false;
            bankPanel.Visible = false;
            westernunionPanel.Visible = false;
            PayPalPanel.Visible = false;

            offerPanel.Visible = true;
        }

        private void initPaymentComboBox()
        {
            foreach (Option option in Enum.GetValues(typeof(Option)))
            {
                comboBox1.Items.Add(option);
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox1.SelectedIndex != -1)
            {
                Option option = (Option)comboBox1.SelectedItem;

                switch (option)
                {
                    case Option.Bank:
                        PayPalPanel.Visible = false;
                        westernunionPanel.Visible = false;
                        bankPanel.Visible = true;
                        break;
                    case Option.PayPal:
                        PayPalPanel.Visible = true;
                        westernunionPanel.Visible = false;
                        bankPanel.Visible = false;
                        break;
                    case Option.WesternUnion:
                        PayPalPanel.Visible = false;
                        westernunionPanel.Visible = true;
                        bankPanel.Visible = false;
                        break;
                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {                         
            if (comboBox1.SelectedIndex != -1)
            {
                Option option = (Option)comboBox1.SelectedItem;

                switch (option)
                {
                    case Option.Bank:
                        handleBankInfo();
                        break;
                    case Option.PayPal:
                        handlePayPalInfo();
                        break;
                    case Option.WesternUnion:
                        handleWestInfo();
                        break;
                }
            }      
        }

        private void handleWestInfo()
        {
            string email = emailTextBox.Text;
            string name = nameTextBox.Text;

            if (string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(name))
            {
                MessageBox.Show("Please fill in all required fields before trying to process the payment.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                DisplayCongrats();
            }
        }

        private void handlePayPalInfo()
        {
            string email = payPalTextBox.Text;

            if (string.IsNullOrWhiteSpace(email))
            {
                MessageBox.Show("Please fill in all required fields before trying to process the payment.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                DisplayCongrats();
            }
        }

        private void handleBankInfo()
        {
            string account = accountTextBox.Text;
            string name = bankNameTextBox.Text;

            if (string.IsNullOrWhiteSpace(account) || string.IsNullOrWhiteSpace(name))
            {
                MessageBox.Show("Please fill in all required fields before trying to process the payment.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                DisplayCongrats();
            }
        }

        private void DisplayCongrats()
        {
            paymentLabel.Visible = false;
            comboBox1.Visible = false;
            button1.Visible = false;
            PayPalPanel.Visible = false;
            westernunionPanel.Visible = false;
            bankPanel.Visible = false;
            panel1.Visible = true;
        }

        private void offerButton_Click(object sender, EventArgs e)
        {
            string amount = amountTextBox.Text;
            int result;
            bool valid;

            valid = converter.ConvertStringToInteger(amount, out result);

            if (valid)
            {
                valid = converter.ConvertStringToInteger(amount, 1000000, 10000000, out result);

                if (!valid)
                {
                    if (result < 1000000)
                    {
                        feedbackLabel.Text = "The seller denied your cheap offer.\n The estate is worth more then 1,000,000!";
                    }
                    else
                    {
                        feedbackLabel.Text = "Okey rich guy, we aren't trying to robb you... \n The estate isn't worth more then 10,000,000.";
                    }
                }
                else
                {
                    offerPanel.Visible = false;

                    paymentLabel.Visible = true;
                    comboBox1.Visible = true;
                    button1.Visible = true;
                }
            }
            else
            {
                feedbackLabel.Text = "Please enter a valid number! " + amountTextBox.Text + " isn't a valid number...";
            }
        }
    }
}
