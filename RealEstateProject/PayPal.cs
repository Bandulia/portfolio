using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

// Julia Tadic 2024-09-11

namespace Assignment_1_version_1.Payment
{
    internal class PayPal : Payment
    {
        private string email;

        public PayPal(string email, decimal amount, Option option) : base(amount, option) 
        {
            SetEmail(email);
        }

        public void SetEmail(string email)
        {
            if (string.IsNullOrWhiteSpace(email))
            {
                this.email = email;
            }
            else
            {
                this.email = "unknown@gmail.com";
            }
        }

        public string GetEmail()
        {
            return this.email;
        }
    }
}
