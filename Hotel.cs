// Julia Tadic 2024-09-05

namespace Assignment_1_version_1.Commercials
{
    [Serializable]
    internal class Hotel : Commercial
    {    

        public Hotel() : base(){ }

        public Hotel(string ID, Address address, int squares, int rooms, string image, Persons.Seller seller) : base(ID, address, squares, rooms, CommercialType.Hotel, image, seller) { }

        // Overrides the ToString method to provide a string representation of the hotel.
        // The format of the returned string is: "A hotel in COUNTRY with NUM rooms etc.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "A hotel in " + GetAddress().GetCountry() + " with " + GetNumOfRooms() + " rooms etc.";
        }

        //Provides a description of the hotel.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "A charming hotel locaded at " + GetAddress().GetStreet();
        }

    }
}
