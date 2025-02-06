// Julia Tadic 2024-09-07

namespace Assignment_1_version_1.Residentials
{
    [Serializable]
    internal class Villa : Residential
    {
        //gardenHectar: Represents the size of the garden in hectar.
        private int gardenHectare;

        public Villa() : base() { gardenHectare = 1000; }

        public Villa(int gardenHectar) : base()
        {
            this.gardenHectare = gardenHectar;
        }
        public Villa(string ID, Address address, int squares, int rooms, int hectare, string image, Persons.Seller seller) : base(ID, address, squares, rooms, ResidentialType.Villa, image, seller)
        {
            gardenHectare = hectare;
        }

        // Overrides the ToString method to provide a string representation of the villa.
        // The format of the returned string is: "A beautiful villa at STREET in CITY.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "A beautiful villa at " + GetAddress().GetStreet() + " in " + GetAddress().GetCity();
        }

        //Provides a description of the hopital.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "A beatifull villa with " + GetNumOfRooms + " rooms. The neighbourhoos is family friendly and there are three elementery schools nearby. Welcome to drop in on the open house 30/11. For a private viewing contact apu@gmail.com";
        }
    }
}
