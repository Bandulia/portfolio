using Assignment_1_version_1.Estate;

// Julia Tadic 2024-09-05

namespace Assignment_1_version_1.Commercials;

public enum CommercialType
{
    Shop,
    Warehouse,
    Factory, 
    Hotel
}

internal class Commercial : Estate.Estate
{
    //commercialType: Indicates wether the estate is a shop, warehouse or factory.
    private CommercialType commercialType;


    public Commercial() : base() {}

    public Commercial(string ID, Address address, int squares, int rooms, CommercialType type, string image, Persons.Seller seller) : base(ID, address, squares, rooms, image, seller)
    {
        commercialType = type;
    }

    //Provides a string with information about accommodation costs.
    public override string AccommodationCost()
    {
        throw new NotImplementedException();
    }

    //Returns the enum commercial type (Shop, Warehouse, Factory)
    public override Enum GetType()
    {
        return commercialType;
    }

    //Returns the estate category commercial
    public override Category GetCategory()
    {
        return Category.Commercial;
    }
}
