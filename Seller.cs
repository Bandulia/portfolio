using System.Text.Json.Serialization;

// Julia Tadic 2024-09-09

namespace Assignment_1_version_1.Persons
{
    [Serializable]
    internal class Seller : Person
    {
        [JsonIgnore]
        public EstateManager ListedEstates { get; set; }

        // Parameteriserad konstruktor
        [JsonConstructor]
        public Seller() : base () { this.ListedEstates = new EstateManager(); }

        public Seller(string firstName, string lastName, Address address) : base (firstName, lastName, address)
        {
            this.ListedEstates = new EstateManager();
        }

        public Seller(string sellerString) : base("", "", new Address())
        {
            // Förväntat format: "FirstName LastName, Address"
            var parts = sellerString.Split(new[] { ", " }, StringSplitOptions.None);
            if (parts.Length == 2)
            {
                var nameParts = parts[0].Split(' ');
                SetFirstName(nameParts[0]);
                SetLastName(nameParts[1]);
                SetAddress(new Address(parts[1]));                

                this.ListedEstates = new EstateManager(); // Sätt listan till en tom lista
            }
            else
            {
                throw new ArgumentException("Invalid seller format");
            }
        }

        public void addEstate(Estate.Estate estate)
        {
            ListedEstates.Add(estate);
        }

        public Estate.Estate getEstate(int index)
        {
            return ListedEstates.GetAt(index);
        }

        public void removeEstate(int index)
        {
            ListedEstates.DeleteAt(index);
        }

        public EstateManager getListedEstates()
        {
            return ListedEstates;
        }

        public void SetListedEstates(EstateManager listedEstates)
        {
            this.ListedEstates = listedEstates;
        }

        public void AddEstate(Estate.Estate estate)
        {
            ListedEstates.Add(estate);
        }

        public string GetFullName()
        {
            return GetFirstName() + " " + GetLastName();
        }

        public override string ToString()
        {
            return $"{GetFirstName()} {GetLastName()}, {GetAddress().ToString()}";
        }        
    }
}
