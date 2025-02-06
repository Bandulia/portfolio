using System.Text.Json.Serialization;

// Julia Tadic 2024-09-09

namespace Assignment_1_version_1.Persons
{
    [Serializable]
    internal class Buyer : Person
    {
        [JsonIgnore]
        public EstateManager estatesOfInterest;

        [JsonConstructor]
        public Buyer() : base() { this.estatesOfInterest = new EstateManager(); }

        public Buyer(string firstName, string lastName, Address address) : base(firstName, lastName, address)
        {
            this.estatesOfInterest = new EstateManager();
        }

        public void addInterest(Estate.Estate estate)
        {
            if (!estatesOfInterest.Contains(estate))
            {
                estatesOfInterest.Add(estate);
            }
        }

        public Estate.Estate getEstate(int index)
        {
            return estatesOfInterest.GetAt(index);
        }

        public void removeIntrest(Estate.Estate estate)
        {
            if (estatesOfInterest.Contains(estate))
            {
                estatesOfInterest.RemoveItem(estate);
            }
        }

        public EstateManager getInterstList()
        {
            return new EstateManager(estatesOfInterest);
        }

        public void SetIntrestList(EstateManager intrests)
        {
            this.estatesOfInterest = intrests;
        }
        
        public void AddEstate(Estate.Estate estate)
        {
            estatesOfInterest.Add(estate);
        }

        public bool interestExist(Estate.Estate estate)
        {
            return estatesOfInterest.Contains(estate);
        }
    }
}
