using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

// Julia Tadic 2024-09-11

namespace Assignment_1_version_1.Payment
{
    internal class WesternUnion : Payment
    {
        private string name;
        private string email;

        public WesternUnion(string name, string email, decimal amount, Option option) : base(amount, option)
        {
            SetName(name);
            SetEmail(email);
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
