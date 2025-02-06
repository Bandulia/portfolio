// Julia Tadic 2024-09-07

namespace Assignment_1_version_1.Residentials
{
    [Serializable]
    internal class Townhouse : Residential
    {
        //numOfFloors: Resprestents the number of floors in the townhouse
        //patio: Indicates wether the house has a patio or not
        private int numOfFloors;
        private bool patio;

        public Townhouse() : base() { numOfFloors = 2; patio = false; }
        public Townhouse(int floors, bool patio): base()
        {
            numOfFloors = floors;
            this.patio = patio;
        }
        public Townhouse(string ID, Address address, int squares, int rooms, int floors, bool patio, string image, Persons.Seller seller) : base(ID, address, squares, rooms, ResidentialType.Townhouse, image, seller)
        {
            numOfFloors = floors;
            this.patio = patio;
        }

        // Overrides the ToString method to provide a string representation of the townhouse.
        // The format of the returned string is: "Townhouse in CITY on STREET.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "Townhouse in " + GetAddress().GetCity() + " on " + GetAddress().GetStreet();
        }

        //Provides a description of the townhouse.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "A magestic townhouse in " + GetAddress().GetCity() + ". If you like to show off, this is the house for you! Welcome to book a viewing at apu@gmail.com";
        }
    }
}
