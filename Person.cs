using System.Net;
using System.Text.Json.Serialization;

// Julia Tadic 2024-09-09

namespace Assignment_1_version_1.Persons
{
    [Serializable]
    internal abstract class Person
    {
        [JsonInclude] 
        public string FirstName { get; set; }

        [JsonInclude] 
        public string LastName { get; set; }

        [JsonInclude]
        public Address Address { get; set; }

        public Person() 
        {
            SetFirstName("firstname");
            SetLastName("lastName");
            SetAddress(new Address());
        }

        public Person(string firstName, string lastName, Address address)
        {
            SetFirstName(firstName);
            SetLastName(lastName);
            SetAddress(address);            
        }

        public void SetFirstName(string name)
        {
            if (name == null || name == " ")
            {
                this.FirstName = "Unknown name";
            }
            else
            {
                this.FirstName = name;
            }
        }

        public string GetFirstName()
        {
            return this.FirstName;
        }

        public void SetLastName(string name)
        {
            if (name == null || name == " ")
            {
                this.LastName = "Unknown name";
            }
            else
            {
                this.LastName = name;
            }
        }

        public string GetLastName()
        {
            return this.LastName;
        }

        public void SetAddress(Address address)
        {
            if (address == null)
            {
                this.Address = new Address(Country.UnitedKingdom, City.London, "215-85", "Mainstreet");
            }
            else
            {
                this.Address = address;
            }
        }

        public Address GetAddress()
        {
            return this.Address;
        }
    }
}
