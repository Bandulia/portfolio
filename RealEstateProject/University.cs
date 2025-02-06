// Julia Tadic 2024-09-06

namespace Assignment_1_version_1.Institutionals
{
    [Serializable]
    internal class University : Institutional
    {
        //numOfClassrooms: Represents the number of classrooms in the university.
        private int numOfClassrooms;

        public University() { }

        public University(string ID, Address address, int squares, int rooms, int classRooms, string image, Persons.Seller seller) : base(ID, address, squares, rooms, InstitutionalType.University, image, seller)
        {
            numOfClassrooms = classRooms;
        }

        // Overrides the ToString method to provide a string representation of the university.
        // The format of the returned string is: "A university in COUNTRY with NUM classrooms.".
        // This method is used by the controller to display the estate in a readable format.
        public override string ToString()
        {
            return "A univeristy in " + GetAddress().GetCountry() + " with " + GetNumOfRooms() + " classrooms etc.";
        }

        //Provides a description of the university.
        public override string GetDescription()
        {
            return "Seller: " + GetSeller().GetLastName() + ", " + GetSeller().GetFirstName() + "\n" + "One of the larges universities in " + GetAddress().GetCity() + ". There is 10 audiotoriums and 50 classrooms.Welcome to drop in on the open house 2/11. For a private viewing contact apu@gmail.com";
        }
    }
}
