using Assignment_1_version_1.Generics;
using System.Text.Json.Serialization;
//Julia Tadic 2024-10-04

namespace Assignment_1_version_1
{
    [Serializable]
    internal class EstateManager : ListManager<Estate.Estate>
    {
        // Dictionary with estates as values and their key beeing the estate seller fullname
        [JsonIgnore]
        private Dictionary<string, Estate.Estate> estates = new Dictionary<string, Estate.Estate>(); 

        // Using the baseclass ListManagers methods
        public EstateManager() : base() { }

        public EstateManager(EstateManager manager) : base(manager) { }

        public void AddToDictionary(string key, Estate.Estate estate)
        {
            if (estates.ContainsKey(key))
            {
                key += "1";
                AddToDictionary(key, estate);
                return;
            }
            estates.Add(key, estate);
        }

        public void RemoveFromDictionary(string key)
        {
            if (estates.ContainsKey(key))
            {
                estates.Remove(key);
            }
        }

        public Estate.Estate? Find(string key)
        {
            if (estates.ContainsKey(key))
            {
                return estates[key];
            }
            return null;
        }
    }
}
