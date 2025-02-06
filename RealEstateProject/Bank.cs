// Julia Tadic 2024-09-11

namespace Assignment_1_version_1.Payment
{

    internal class Bank : Payment
    {
        private string name;
        private string accountnumber; 

        public Bank(string name, string accountNbr, decimal amount, Option option): base(amount, option) 
        {
            SetAccountnumber(accountNbr);
            SetName(name);
        }

        public void SetName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
            {
                this.name = name;
            }
            else
            {
                this.name = "Unknown";
            }
        }

        public string GetName()
        {
            return this.name;
        }

        public void SetAccountnumber(string account)
        {
            if (string.IsNullOrWhiteSpace(account))
            {
                this.accountnumber = account;
            }
            else
            {
                this.accountnumber = "000000000";
            }
        }

        public string GetAccountnumber()
        {
            return this.accountnumber;
        }
    }
}
