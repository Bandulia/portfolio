using Assignment_1_version_1.Persons;

// Julia Tadic 2024-09-09

namespace Assignment_1_version_1
{
    [Serializable]
    internal class Useraccount
    {
        public Seller SellerIdentity { get; set; }  // Publika egenskaper istället för privata fält
        public Buyer BuyerIdentity { get; set; }


        public Useraccount() 
        {
            SellerIdentity = new Seller();
            BuyerIdentity = new Buyer();
        }

        public Useraccount(Seller seller, Buyer buyer)
        {
            SellerIdentity = seller;
            BuyerIdentity = buyer;
        }

        public Seller GetSeller()
        {
            return SellerIdentity;
        }

        public Buyer GetBuyer()
        {
            return BuyerIdentity;
        }
    }
}
