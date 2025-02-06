using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

// Julia Tadic 2024-09-11

namespace Assignment_1_version_1.Payment
{
    public enum Option
    {
        PayPal, 
        Bank, 
        WesternUnion
    }
    internal abstract class Payment
    {
        decimal amount;
        Option option;

        public Payment()
        {
            SetAmount(100000);
            SetOption(Option.Bank);
        }

        public Payment(decimal amount, Option option) 
        {
            SetAmount(amount);
            SetOption(option);
        }

        void SetAmount(decimal amount)
        {
            this.amount = amount;   
        }

        decimal GetAmount()
        {
            return this.amount;
        }

        void SetOption(Option option)
        {
            this.option = option;
        }

        Option GetOption()
        {
            return this.option;
        }
    }
}
