using System.Text.Json.Serialization;
// Julia Tadic 2024-09-05

namespace Assignment_1_version_1
{
    public enum Country
    {
        UnitedStates,
        Canada,
        Germany,
        Japan,
        Brazil,
        Australia,
        India,
        SouthAfrica,
        UnitedKingdom,
        France
    }

    public enum City
    {
        NewYork,        // United States
        Toronto,        // Canada
        Berlin,         // Germany
        Tokyo,          // Japan
        SãoPaulo,       // Brazil
        Sydney,         // Australia
        Mumbai,         // India
        CapeTown,       // South Africa
        London,         // United Kingdom
        Paris           // France
    }

    [Serializable]
    internal class Address
    {
        [JsonInclude]
        private Country country;
        [JsonInclude]
        private City city;
        [JsonInclude]
        private string zipCode;
        [JsonInclude]
        private string street;
      
        public Address()
        {       
            // - country: The country where the address is located.
            // - city: The city within the country.
            // - zipCode: The postal code for the address.
            // - street: The specific street address.
            this.country = Country.UnitedStates;
            this.city= City.NewYork;
            this.zipCode = "215-85";
            this.street = "Unknown";
        }
        public Address(Country country, City city, string zip, string street)
        {            
            SetCity(city);
            SetCountry(country);
            SetZipCode(zip);
            SetStreet(street);
        }

        // Konstruktor som tar en sträng som argument för att underlätta inläsning av filer
        public Address(string addressString)
        {
            // Förväntat format: "Country: Street City ZipCode"
            var parts = addressString.Split(new[] { ": ", " " }, StringSplitOptions.None);
        
            if (parts.Length == 4)
            {
                this.country = (Country)Enum.Parse(typeof(Country), parts[0]);
                this.street = parts[1];
                this.city = (City)Enum.Parse(typeof(City), parts[2]);
                this.zipCode = parts[3];
            }
            else if (parts.Length > 4 || parts.Length < 4)
            {
                throw new ArgumentException("Invalid address format");
            }
        }

        // Getters and setters for the address fields:
        public void SetCountry(Country country)
        {
            this.country = country;
        }

        public Country GetCountry()
        {
            return this.country;
        }
        public void SetCity(City city)
        {
            this.city = city;
        }

        public City GetCity()
        {
            return this.city;
        }
        public void SetZipCode(string zip)
        {
            this.zipCode = zip;
        }

        public string GetZipCode()
        {
            return this.zipCode;
        }

        public void SetStreet(string street)
        {
            this.street = street;
        }

        public string GetStreet()
        {
            return this.street;
        }

        // Overrides the ToString method to provide a string representation of the address.
        // The format of the returned string is: "Country: Street City ZipCode".
        // This method is used by the controller to display the address in a readable format.
        public override string ToString()
        {
            return GetCountry() + ": " + GetStreet() + " " + GetCity() + " " + GetZipCode();
        }
    }
}
