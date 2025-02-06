// Julia Tadic 2024-09-06

namespace Assignment_1_version_1.Institutionals
{
    [Serializable]
    internal class School : Institutional
    {
        //sportHall: Indicates wether the school has a sports hall/gym or not
        private bool sportHall;

        public School(bool sportHall): base()
        {
            this.sportHall = sportHall;
        }

        public School(string ID, Address address, int squares, int rooms, bool sportHall, string image, Persons.Seller seller) : base(ID, address, squares, rooms, InstitutionalType.School, image, seller)
        {
            this.sportHall = sportHall;
        }

        // Overrides the ToString method to provide a string representation of the school.
        // The format of the returned string is: "School at STREET in COUNTY".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "School at " + GetAddress().GetStreet() + " in " + GetAddress().GetCountry();
        }


        //Provides a description of the school.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "An old school with a lot of history in " + GetAddress().GetCity() + ". The chalkboards and the lookers are originals from 1940. \n Welcome to drop in on the open house 30/11. For a private viewing contact apu@gmail.com";
        }
    }
}
