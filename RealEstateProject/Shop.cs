// Julia Tadic 2024-09-05

namespace Assignment_1_version_1.Commercials
{
    [Serializable]
    internal class Shop : Commercial
    {
        public Shop() : base() { }

        public Shop(string ID, Address address, int squares, int rooms, string image, Persons.Seller seller) : base(ID, address, squares, rooms, CommercialType.Shop, image, seller) { }

        // Overrides the ToString method to provide a string representation of the shop.
        // The format of the returned string is: "A shop in COUNTRY on STREET.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "A shop in " + GetAddress().GetCountry() + " on " + GetAddress().GetStreet();
        }

        //Provides a description of the shop.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "Currently a smal antique shop locaded at " + GetAddress().GetStreet() + ". Welcome to drop in at opening hours 10am-20pm!";
        }
    }
}
