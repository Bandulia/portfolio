using System.Collections;
using System.Text.Json.Serialization;

//Julia Tadic 2024-10-03


namespace Assignment_1_version_1.Generics
{
    [Serializable]
    internal class ListManager<T> 
    {
        [JsonInclude]
        public List<T> list;
        //public int Count;

        public ListManager()
        {
            list = new List<T>();
            //Count = 0; 
        }

        public ListManager(ListManager<T> other)
        {
            if (other == null)
            {
                throw new ArgumentNullException(nameof(other), "Cannot copy from a null object.");
            }

            list = new List<T>();

            foreach (T item in other.list)
            {
                if (!Add(item)) { throw new InvalidOperationException("Item is null when making a copy"); }             
            }

            //Count = list.Count;
        }


        public virtual bool Add(T type)
        {
            if (type == null)
            {
                return false; 
            }
            list.Add(type);
           // Count++; 
            return true;
        }

        public bool ChangeAt(T type, int index)
        {
            if (!CheckIndex(index))
            { 
                return false;
            }
            list.Add(type);
            return true;
        }

        public bool CheckIndex(int index)
        {
            return index >= 0 && index < list.Count;
        }

        public void DeleteAll()
        {
            list.Clear();
            //Count = 0;
        }

        public bool DeleteAt(int index)
        {
            if (CheckIndex(index))
            { 
                list.RemoveAt(index);
               // Count--;
                return true;
            }
            return false;
        }

        public T GetAt(int index)
        {
            if (!CheckIndex(index))
                throw new ArgumentOutOfRangeException(nameof(index), "Index is out of range");
            return list[index];
        }

        public string[] ToStringArray()
        {
            List<string> stringList = ToStringList();
            return stringList.ToArray();
        }

        public List<string> ToStringList()
        {
            List<string> stringList = new List<string>();
            foreach (var item in list)
            {
                stringList.Add(item.ToString());
            }
            return stringList;
        }

        public bool RemoveItem(T item)
        {
            return list.Remove(item);
        }

        public IEnumerator<T> GetEnumerator()
        {
            foreach (T item in list)
            {
                yield return item;
            }
        }

        public bool Contains(T item)
        {
            if (item == null)
            {
                return false;  
            }
            return list.Contains(item);
        }

        public int Count()
        {
            return list.Count;
        }
    }
}
