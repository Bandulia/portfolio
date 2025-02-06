// Julia Tadic 2024-09-05

namespace Assignment_1_version_1.Commercials
{
    [Serializable]
    internal class Factory : Commercial
    {

        public Factory() : base(){ }

        public Factory(string ID, Address address, int squares, int rooms, string image, Persons.Seller seller) : base(ID, address, squares, rooms, CommercialType.Factory, image, seller) { }

        // Overrides the ToString method to provide a string representation of the factory.
        // The format of the returned string is: "Factory in COUNTRY with NUM rooms etc.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "Factory in " + GetAddress().GetCountry() + " with " + GetNumOfRooms() + " rooms etc.";
        }

        //Provides a description of the factory.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "A factory last owned by Arla. It has two storage departments and three productions. To book a viewing contact apu@gmail.com";
        }
    }
}
