// Julia Tadic 2024-09-07

namespace Assignment_1_version_1.Residentials
{
    [Serializable]
    internal class Rowhouse : Villa
    {
        public Rowhouse() : base () { }

        public Rowhouse(string ID, Address address, int squares, int rooms, int hectare, string image, Persons.Seller seller) : base(ID, address, squares, rooms, hectare, image, seller) { }

        // Overrides the ToString method to provide a string representation of the rowhouse.
        // The format of the returned string is: "A rowhouse in COUNTRY on STREET.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "A rowhouse in " + GetAddress().GetCountry() + " on " + GetAddress().GetStreet();
        }


        //Provides a description of the rowhouse.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "A recently renovated rowhouse in " + GetAddress().GetCity() + ". The neighbours are social and the neighbourhood often holds gatterings and social events! Welcome to drop in on the open house 3/11. For a private viewing contact apu@gmail.com";
        }

    }
}
