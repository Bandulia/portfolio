// Julia Tadic 2024-09-07

namespace Assignment_1_version_1.Residentials
{
    [Serializable]
    internal class Apartment : Residential
    {
        // balcony: Indicates whether the apartment has a balcony (true if it does, false otherwise)
        // elevator: Indicates whether there is an elevator in the apartment complex (true if there is, false otherwise)
        private bool balcony;
        private bool elevator;

        public Apartment() : base() { balcony = false; elevator = false; }

        public Apartment(bool balcony, bool elevator) : base()
        {
            this.balcony = balcony;
            this.elevator = elevator;
        }

        public Apartment(string ID, Address address, int squares, int rooms, bool balcony, bool elevator, string image, Persons.Seller seller) : base(ID, address, squares, rooms, ResidentialType.Apartment, image, seller)
        {
            this.balcony = balcony;
            this.elevator = elevator;
        }

        // Overrides the ToString method to provide a string representation of the apartment.
        // The format of the returned string is: "Attractive apartment in CITY on STREET".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "Attractive apartment in " + GetAddress().GetCity() + " on " + GetAddress().GetStreet();
        }

        // Provides a string description of the apartment that varies depending on the apartment attributes         
        public override string GetDescription()
        {
            if (balcony && elevator)
            {
                return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "An attractive apartment locaded in the thriving city " + GetAddress().GetCity() + ". The rooms are airy and the kitchen is newly renovated! This apartment also have a balcony. \nThere is an elevator in the buildning.\n Welcome to drop in on the open house 29/11. For a private viewing contact apu@gmail.com \n" + base.GetDescription();
            }
            else if (balcony)
            {
                return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "An attractive apartment locaded in the thriving city " + GetAddress().GetCity() + ". The rooms are airy and the kitchen is newly renovated! This apartment also have a balcony. \nWelcome to drop in on the open house 29/11. For a private viewing contact apu@gmail.com \n" + base.GetDescription();
            }
            else if (elevator)
            {
                return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "An attractive apartment locaded in the thriving city " + GetAddress().GetCity() + ". The rooms are airy and the kitchen is newly renovated! \nThere is an elevator in the buildning.\n Welcome to drop in on the open house 29/11. For a private viewing contact apu@gmail.com \n" + base.GetDescription();
            }
            else
            {
                return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "An attractive apartment locaded in the thriving city " + GetAddress().GetCity() + ". The rooms are airy and the kitchen is newly renovated! \n Welcome to drop in on the open house 29/11. For a private viewing contact apu@gmail.com \n" + base.GetDescription();
            }
        }
    }
}
