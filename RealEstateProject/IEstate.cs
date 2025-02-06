// Julia Tadic 2024-09-05

namespace Assignment_1_version_1.Estate
{
    internal interface IEstate
    {
        // The must haves from the assignment       
        void SetAddress(Address address);
        Address GetAddress();
        void SetId(string ID);        
        string GetId();
    }
}
