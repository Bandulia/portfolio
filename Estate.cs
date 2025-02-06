using System.Text.Json.Serialization;

// Julia Tadic 2024-09-05

namespace Assignment_1_version_1.Estate;

public enum LegalForm
{
    Ownership,
    Tenement,
    Rental
}
public enum Category
{
    Residential,
    Commercial,
    Institutional
}

//TAGIT BORT ABSTRAKT FÖR SERIALIZERINGEN
[Serializable]
internal class Estate : IEstate
{
    // id: Unique identifier for the estate
    // address: Address of the estate, including country, city, street, and ZIP code
    // squareMeters: Total area of the estate in square meters
    // numOfRooms: Number of rooms in the estate
    // form: Legal form or type of the estate, such as residential, commercial, or institutional
    // category: Category of the estate, which could include options like Residential, Commercial, or Institutional
    [JsonInclude]
    public string id {  get; set; }
    [JsonInclude]
    public Address address { get; set; }
    [JsonInclude]
    public int squareMeters { get; set; }
    [JsonInclude]
    public int numOfRooms {  get; set; }
    [JsonInclude]
    public LegalForm legalForm {  get; set; }
    [JsonInclude]
    public Category category {  get; set; }
    [JsonInclude]
    public string imagePath {  get; set; }
    [JsonInclude]
    public Persons.Seller seller { get; set; }

    public Estate() 
    {
        id = "E000";
        address = new Address();
        imagePath = "No image"; 
        squareMeters = 0;
        numOfRooms = 0;
    }

    public Estate(string ID, Address address, int squares, int rooms, string image, Persons.Seller seller)
    {
        this.id = ID;
        this.address = address;
        this.squareMeters = squares;
        this.numOfRooms = rooms;
        this.imagePath = image;
        this.seller = seller;

    }

    // Abstract method that returns information about the accommodation cost of the estate
    public virtual string AccommodationCost() { return "Accommodations"; }

    //Abstract method that overrides the ToString method to provide a more readable string        
    //public virtual string ToString() { return "Estate"; }

    //Returns a description of the estate as a string 
    public virtual string GetDescription()
    {
        return "Estate id: " + id + "\nAddress: " + GetAddress().ToString() + "\nEstate size: " + GetSquares() + "m2 \nNumber of rooms: " + GetNumOfRooms();
    }

    //Returns the type of estate (villa, shop, warehouse etc).
    public new virtual Enum GetType() { return null; }

    //Setters and getters for the estate fields:
    public void SetAddress(Address address)
    {
        this.address = address;
    }
    public Address GetAddress()
    {
        return address;
    }

    public void SetSquares(int squares)
    {
        squareMeters = squares;
    }
    public int GetSquares()
    {
        return squareMeters;
    }
    public void SetNumOfRooms(int rooms)
    {
        numOfRooms = rooms;
    }
    public int GetNumOfRooms()
    {
        return numOfRooms;
    }
    public void SetLegalForm(LegalForm form)
    {
        legalForm = form;
    }
    public LegalForm GetLegalForm()
    {
        return legalForm;
    }
    public void SetCategory(Category category)
    {
        this.category = category;
    }
    public virtual Category GetCategory()
    {
        return category;
    }

    public string GetImagePath()
    {
        return imagePath;
    }

    public void SetImagePath(string path)
    {
        imagePath = path;
    }

    public Persons.Seller GetSeller()
    {
        return this.seller;
    }

    public void SetSeller(Persons.Seller seller)
    {
        this.seller = seller;
    }

    public void SetId(string ID)
    {
        this.id = ID;
    }

    public string GetId()
    {
        return id;
    }
}
