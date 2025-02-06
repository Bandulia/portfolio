//Julia Tadic 2024-10-03

namespace Assignment_1_version_1.Generics
{
    /* Interface for implementation by manager classes hosting a collection
     * of the type List<T> where T can be any objecct type. */     
    internal interface IListManager<T> : IEnumerable<T>
    {
        // Return the number of items in the collection list
        int Count { get; }

        // Add an object to the collection list
        bool Add(T type);

        // Replace an object at a specified position with a new object
        bool ChangeAt(T type, int index);

        // Check if the index is valid 
        bool CheckIndex(int index);

        // Delete all of the objects from the collection list
        void DeleteAll();

        // Remove an object from the collection list at a given position
        bool DeleteAt(int index);

        // Return an object from a specified position in the list
        T GetAt(int index);

        // Returns the collection list as a array of strings 
        string[] ToStringArray();

        // Returns the collection list as a list of strings 
        List<string> ToStringList();
    }
}

