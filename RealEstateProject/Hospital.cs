// Julia Tadic 2024-09-06

namespace Assignment_1_version_1.Institutionals
{
    [Serializable]
    internal class Hospital : Institutional
    {
        public Hospital() : base() {}

        public Hospital(string ID, Address address, int squares, int rooms, string image, Persons.Seller seller) : base(ID, address, squares, rooms, InstitutionalType.Hospital, image, seller) { }

        // Overrides the ToString method to provide a string representation of the hospital.
        // The format of the returned string is: "Hospital in COUNTRY with NUM rooms etc.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "Hospital in " + GetAddress().GetCountry() + " with " + GetNumOfRooms() + " rooms etc.";
        }

        //Provides a description of the hopital.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "A hospital in need of renovation. The buildning was established in 1930 and has been treating patients since the end of world war 2, 1945. For a private viewing contact apu@gmail.com";
        }
    }
}
