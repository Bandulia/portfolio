// Julia Tadic 2024-09-05

namespace Assignment_1_version_1.Commercials
{
    [Serializable]
    internal class Warehouse : Commercial
    {
        public Warehouse() : base(){ }

        public Warehouse(string ID, Address address, int squares, int rooms, string image, Persons.Seller seller) : base(ID, address, squares, rooms, CommercialType.Warehouse, image, seller) { }

        // Overrides the ToString method to provide a string representation of the warehouse.
        // The format of the returned string is: "Warehouse in COUNTRY.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "Warehouse in " + GetAddress().GetCountry();
        }

        //Provides a description of the warehouse.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "A warehouse in " + GetAddress().GetCity() + ". Welcome book a private viewing at apu@gmail.com";
        }
    }
}
