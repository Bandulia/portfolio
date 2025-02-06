using Assignment_1_version_1.Estate;

// Julia Tadic 2024-09-06

namespace Assignment_1_version_1.Institutionals
{
    public enum InstitutionalType
    {
        Hospital,
        University,
        School
    }
    internal class Institutional : Estate.Estate
    {
        //institutionalType: Indicates wether the estate is a hospital, university or school.
        private InstitutionalType institutionalType;

        public Institutional():base(){}

        public Institutional(string ID, Address address, int squares, int rooms, InstitutionalType type, string image, Persons.Seller seller) : base(ID, address, squares, rooms, image, seller)
        {
            institutionalType = type;
        }

        //Provides a string with information about accommodation costs.
        public override string AccommodationCost()
        {
            throw new NotImplementedException();
        }


        public void SetInstitutionalType(InstitutionalType type)
        {
            institutionalType = type;
        }

        //Returns the enum institutional type (hospital, university, school)
        public InstitutionalType GetInstitutionalType()
        {
            return institutionalType;
        }

        //Returns the enum institutional type (hospital, university, school)
        public override Enum GetType()
        {
            return institutionalType;
        }

        //Returns the estate category institutional.
        public override Category GetCategory()
        {
            return Category.Institutional;
        }
    }
}
