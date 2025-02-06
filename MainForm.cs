using RealEstateBLL;
using RealEstateBLL.Estate;
using RealEstateBLL.Persons;
using System.Diagnostics;

namespace Assignment_1_version_1
{
    public partial class MainForm : Form
    {      
        private EstateManager matchingEstates;
        private EstateManager allEstates;
        private Useraccount useraccount;
        private Estate selectedEstate;
        private bool saved;
        private object saveFileDialog;
        private FileManager fileManager; 

        public object PreserveReferenceHandling { get; private set; }

        public MainForm()
        {
            InitializeComponent();

            saved = false;

            fileManager = null;

            welcomePanel.Visible = false;
            findEstatePanel.Visible = false;
            listEstatePanel.Visible = false;
            backButton.Visible = false;
            logginPanel.Visible = true;

            //initializeation for combo boxes
            initCategoryComboBox1();
            initCategoryComboBox();
            initMinRoomsComboBox(10);
            initMaxRoomsComboBox(1);
            initNumRoomsComboBox();
            initMaxSqrComboBox(5);
            initMinSqrComboBox(200);
            initSqrComboBox();
            initCountryComboBox();
            initCityComboBox();
            initImagePathComboBox();
            initLogginComboBoxes();

            AddIntitialEstates();

            logginCityComboBox.SelectedIndex = 1; 
            logginCountryComboBox.SelectedIndex = 1;
            logginStreetTextBox.Text = "Dirigentgatan";
            logginZipTextBox.Text = "255-88";
            firstNameTextBox.Text = "Julia";
            lastNameTextBox.Text = "Tadic";
        }

        // Displays the find estate panel and hides the other panels
        private void findEstateButton_Click(object sender, EventArgs e)
        {
            welcomePanel.Visible = false;
            findEstatePanel.Visible = true;
            backButton.Visible = true;
            backButton.BringToFront();
            updateEstateListBox();
            noImageLabel.Visible = true;
            pictureBox1.Image = null;
        }

        // Displays the list estate panel and hides the other panels
        private void listEstateButton_Click(object sender, EventArgs e)
        {
            resetControls(listEstatePanel);
            welcomePanel.Visible = false;
            listEstatePanel.Visible = true;
            backButton.Visible = true;
            if (useraccount != null)
            {
                sellerLabel.Text = " Seller: " + useraccount.GetSeller().GetLastName() + ", " + useraccount.GetSeller().GetFirstName();
                listedEstatesLabel.Text = GetSellerList();
            }
            backButton.BringToFront();
        }

        // Displays the welcome panel and hides the other panels
        private void backButton_Click(object sender, EventArgs e)
        {
            listEstatePanel.Visible = false;
            findEstatePanel.Visible = false;
            welcomePanel.Visible = true;
            backButton.Visible = false;
            interestInfoLabel.Text = " ";

            interestsComboBox.Items.Clear();
            interestsComboBox.Text = null;
            interestInfoLabel.Text = null;

            if (useraccount != null)
            {
                foreach (RealEstateBLL.Estate.Estate estate in useraccount.GetBuyer().getInterstList())
                {
                    interestsComboBox.Items.Add(estate);
                }
            }
        }

        // The category determines the type of estate that will be created, this updates the alternatives in the typeComboBox
        private void categoryComboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            updateTypeComboBox();
        }

        // Checks all of the relevent controls before adding the estate to the list allEstates
        private void addEstateButton_Click(object sender, EventArgs e)
        { 
            if (categoryComboBox1.SelectedIndex == -1) { return; }
            if (countryComboBox.SelectedIndex == -1) { return; }
            if (cityComboBox.SelectedIndex == -1) { return; }
            if (sqrMetersComboBox.SelectedIndex == -1) { return; }
            if (numRoomsComboBox.SelectedIndex == -1) { return; }
            if (string.IsNullOrEmpty(streetTextBox.Text)) {  return; }
            if (string.IsNullOrEmpty(zipTextBox.Text)) { return; }

            Category category = (Category)categoryComboBox1.SelectedItem;
            Country country = (Country)countryComboBox.SelectedItem;
            City city = (City)cityComboBox.SelectedItem;

            Enum type; 

            switch (category)
            {
                case Category.Institutional:
                    type = getInstitutionalType();
                    break;
                case Category.Residential:
                    type = getResidentialType();
                    break;
                case Category.Commercial:
                    type = getCommercialType();
                    break;
                default:
                    type = null;
                    break;
            }
            int sqrMeters = (int)sqrMetersComboBox.SelectedItem;
            int numRooms = (int)numRoomsComboBox.SelectedItem;

            string image = (string)imagePathComboBox.SelectedItem;

            Address address = new Address(country, city, streetTextBox.Text, zipTextBox.Text);

            Estate estate = CreateEstate(category, address, sqrMeters, numRooms,
                                elevatorCheckBox1.Checked, balconyCheckBox1.Checked, patioCheckBox1.Checked,
                                sportsCheckBox1.Checked, image, type.ToString(), useraccount.GetSeller());
            allEstates.Add(estate);
            allEstates.AddToDictionary(useraccount.SellerIdentity.GetFullName(), estate);
            useraccount.GetSeller().addEstate(estate);

            updateEstateListBox();

            resetControls(listEstatePanel);
            listedEstatesLabel.Text = GetSellerList();

            saved = false;
        }

        // Clears then reinitializes all of the controls the given panel
        private void resetControls(Control panel)
        {
            foreach (Control control in panel.Controls)
            {
                if (control is ComboBox comboBox)
                {
                    comboBox.SelectedIndex = -1;
                }
                if (control is TextBox textbox)
                {
                    textbox.Clear();
                }
                if (control is PictureBox pictureBox)
                {
                    pictureBox.Image = null; 
                }
            }                    
        }

        // Fills in all the contols in the list estate panel with the selected estates info and removes the it from the list allEstates
        private void changeButton_Click(object sender, EventArgs e)
        {
            if (estateListBox.SelectedIndex != -1)
            {
                welcomePanel.Visible = false;
                findEstatePanel.Visible = false;
                listEstatePanel.Visible = true;
                backButton.Visible = true;
                backButton.BringToFront();

                countryComboBox.SelectedItem = selectedEstate.GetAddress().GetCountry();
                cityComboBox.SelectedItem = selectedEstate.GetAddress().GetCity();
                streetTextBox.Text = selectedEstate.GetAddress().GetStreet();
                zipTextBox.Text = selectedEstate.GetAddress().GetZipCode();
                categoryComboBox1.SelectedItem = selectedEstate.GetCategory();
                typeComboBox.SelectedItem = selectedEstate.GetType();
                sqrMetersComboBox.SelectedItem = selectedEstate.GetSquares();
                numRoomsComboBox.SelectedItem = selectedEstate.GetNumOfRooms();

                allEstates.RemoveItem(selectedEstate);
                saved = false;
            }
        }

        // Initializes the CategoryComboBox with the categories Commercial, Residential and Institutional
        private void initCategoryComboBox()
        {
            CategoryComboBox.Items.Add(RealEstateBLL.Estate.Category.Commercial);
            CategoryComboBox.Items.Add(RealEstateBLL.Estate.Category.Residential);
            CategoryComboBox.Items.Add(RealEstateBLL.Estate.Category.Institutional);
            CategoryComboBox.SelectedIndex = 0;
        }

        // Initializes the CategoryComboBox1 with the categories Commercial, Residential and Institutional
        private void initCategoryComboBox1()
        {
            categoryComboBox1.Items.Add(RealEstateBLL.Estate.Category.Commercial);
            categoryComboBox1.Items.Add(RealEstateBLL.Estate.Category.Residential);
            categoryComboBox1.Items.Add(RealEstateBLL.Estate.Category.Institutional);
            categoryComboBox1.SelectedIndex = 0;
        }

        // Initializes the sqrMetersComboBox with the numbers from 5 to 200
        private void initSqrComboBox()
        {
            for (int i = 5; i <= 200; i = i + 5)
            {
                sqrMetersComboBox.Items.Add(i);
            }
        }

        // Initializes the minSqrComboBox with the numbers from 1 to max
        private void initMinSqrComboBox(int max)
        {
            for (int i = 5; i <= max; i = i + 5)
            {
                minSqrComboBox.Items.Add(i);
            }
        }

        // Initializes the maxSqrComboBox with the numbers from min to 200
        private void initMaxSqrComboBox(int min)
        {
            for (int i = min; i <= 200; i = i + 5)
            {
                maxSqrCcomboBox.Items.Add(i);
            }
        }

        // Initializes the numRoomsComboBox with the numbers from 1 to 10
        private void initNumRoomsComboBox()
        {
            for (int i = 1; i <= 10; i++)
            {
                numRoomsComboBox.Items.Add(i);
            }
        }

        // Initializes the maxRoomComboBox with the numbers from min to 10
        private void initMaxRoomsComboBox(int min)
        {
            for (int i = min; i <= 10; i++)
            {
                maxRoomComboBox.Items.Add(i);
            }
        }

        // Initializes the minRoomComboBox with the numbers from 1 to max
        private void initMinRoomsComboBox(int max)
        {
            for (int i = 1; i <= max; i++)
            {
                minRoomComboBox.Items.Add(i);
            }
        }

        // Updates the listbox with the listed estates in allEstates
        private void updateEstateListBox()
        {
            estateListBox.Items.Clear();

            foreach (string estate in allEstates.ToStringList())
            {
                estateListBox.Items.Add(estate);
            }
        }

        // Initializes the countryComboBox with all the countries in the enum Country
        private void initCountryComboBox()
        {
            foreach (RealEstateBLL.Country country in Enum.GetValues(typeof(RealEstateBLL.Country)))
            {
                countryComboBox.Items.Add(country);
            }
        }

        // Initializes the cityComboBox with all the cities in the enum City
        private void initCityComboBox()
        {
            foreach (RealEstateBLL.City city in Enum.GetValues(typeof(RealEstateBLL.City)))
            {
                cityComboBox.Items.Add(city);
            }
        }

        // Returns the type of the institutional estate choosen in the typeComboBox
        public InstitutionalType getInstitutionalType()
        {
            Category category = (Category)categoryComboBox1.SelectedItem;
            if (category == Category.Institutional)
            {
                return (InstitutionalType)typeComboBox.SelectedItem;
            }
            else
            {
                return InstitutionalType.University;
            }
        }

        // Return the type of the residential estate choosen in the typeComboBox
        public ResidentialType getResidentialType()
        {
            Category category = (Category)categoryComboBox1.SelectedItem;
            if (category == Category.Residential)
            {
                return (ResidentialType)typeComboBox.SelectedItem;
            }
            else
            {
                return ResidentialType.Villa;
            }
        }

        // Return the type of the commercial estate choosen in the typeComboBox
        public CommercialType getCommercialType()
        {
            Category category = (Category)categoryComboBox1.SelectedItem;
            if (category == Category.Commercial)
            {
                return (CommercialType)typeComboBox.SelectedItem;
            }
            else
            {
                return CommercialType.Shop;
            }
        }

        // Clears and updates the items in the typeComboBox based on the selected category in categoryComboBox1
        private void updateTypeComboBox()
        {
            typeComboBox.SelectedIndex = -1;
            typeComboBox.Items.Clear();

            if (categoryComboBox1.SelectedIndex != -1)
            {
                Category category = (Category)categoryComboBox1.SelectedItem;

                if (category == Category.Institutional)
                {
                    foreach (InstitutionalType institut in Enum.GetValues(typeof(InstitutionalType)))
                    {
                        typeComboBox.Items.Add(institut);
                    }
                }
                else if (category == Category.Residential)
                {
                    foreach (ResidentialType resident in Enum.GetValues(typeof(ResidentialType)))
                    {
                        typeComboBox.Items.Add(resident);
                    }
                }
                else if (category == Category.Commercial)
                {
                    foreach (CommercialType commercial in Enum.GetValues(typeof(CommercialType)))
                    {
                        typeComboBox.Items.Add(commercial);
                    }
                }
            }
        }

        // Displays the description of the selected estate in the informationLabel and setts the estate to the selected estate
        private void estateListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (estateListBox.SelectedIndex != -1 && estateListBox.SelectedIndex < allEstates.Count())
            {
                selectedEstate = allEstates.GetAt(estateListBox.SelectedIndex);
                AddImagePictureToPictureBox(selectedEstate.GetImagePath());
                informationLabel.Text = selectedEstate.GetDescription() + "\nNumber of rooms: " + selectedEstate.GetNumOfRooms() + "\nSize (square meter): " + selectedEstate.GetSquares() + "\nAddress: " + selectedEstate.GetAddress().ToString();
                if (useraccount != null)
                {
                    interestedCheckBox.Checked = useraccount.GetBuyer().interestExist(selectedEstate);
                }
            }
        }

        // Delets the selected estate from the list allEstates
        private void deleteButton_Click(object sender, EventArgs e)
        {
            allEstates.RemoveItem(selectedEstate);
            allEstates.RemoveFromDictionary(selectedEstate.GetSeller().GetFullName(), selectedEstate);
            useraccount.GetBuyer().removeIntrest(selectedEstate);
            useraccount.GetSeller().removeEstate(selectedEstate);
            updateEstateListBox();
            informationLabel.Text = " ";
            saved = false;
        }

        // Filters the estates based on the requirements in the findEstatePanel
        private void requirementButton_Click(object sender, EventArgs e)
        {
            Category category = (Category)CategoryComboBox.SelectedItem;
            int maxRoom = maxRoomComboBox.SelectedItem != null ? (int)maxRoomComboBox.SelectedItem : 10;
            int minRoom = minRoomComboBox.SelectedItem != null ? (int)minRoomComboBox.SelectedItem : 1;
            int maxSqr = maxSqrCcomboBox.SelectedItem != null ? (int)maxSqrCcomboBox.SelectedItem : 200;
            int minSqr = minSqrComboBox.SelectedItem != null ? (int)minSqrComboBox.SelectedItem : 1;

            matchingEstates = new EstateManager(allEstates);

            for (int i = matchingEstates.Count() - 1; i >= 0; i--)
            {
                if (matchingEstates.GetAt(i).GetNumOfRooms() > maxRoom)
                {
                    matchingEstates.DeleteAt(i);
                    continue;
                }
                if (matchingEstates.GetAt(i).GetNumOfRooms() < minRoom)
                {
                    matchingEstates.DeleteAt(i);
                    continue;
                }
                if (matchingEstates.GetAt(i).GetSquares() > maxSqr)
                {
                    matchingEstates.DeleteAt(i);
                    continue;
                }
                if (matchingEstates.GetAt(i).GetSquares() < minSqr)
                {
                    matchingEstates.DeleteAt(i);
                    continue;
                }
                if (CategoryComboBox.SelectedIndex != -1)
                {
                    if (matchingEstates.GetAt(i).GetCategory() != category)
                    {
                        matchingEstates.DeleteAt(i);
                    }
                }
            }
            estateListBox.Items.Clear();
            foreach (string estate in matchingEstates.ToStringList())
            {
                estateListBox.Items.Add(estate);
            }
        }

        // Displays the image choosen in the imagePathComboBox in pictureBox1
        public void AddImagePictureToPictureBox(string fileName)
        {
            if (!string.IsNullOrWhiteSpace(fileName) && fileName != "No image")
            {
                // Get the directory of the executable (where the app is running)
                string appPath = AppDomain.CurrentDomain.BaseDirectory;

                // Combine the application path with the relative Images folder and file name
                string imagePath = System.IO.Path.Combine(appPath, "Images", fileName);

                // Check if the file exists in the combined path
                if (System.IO.File.Exists(imagePath))
                {
                    // Load the image into the picture box
                    pictureBox1.Image = Image.FromFile(imagePath);
                    noImageLabel.Visible = false;  // Hide the label if the image is successfully loaded
                }
                else
                {
                    // If the image does not exist, clear the picture box and show the error message
                    pictureBox1.Image = null;
                    noImageLabel.Visible = true;
                }
            }
            else
            {
                // If the path is invalid or "No image" is selected, clear the picture box and show the label
                pictureBox1.Image = null;
                noImageLabel.Visible = true;
            }
        }

        // Initializes the comboboxes in the list estate panel
        private void initImagePathComboBox()
        {
            imagePathComboBox.Items.Clear();
            imagePathComboBox.Items.Add("apartment.JPG");
            imagePathComboBox.Items.Add("villa.JPG");
            imagePathComboBox.Items.Add("townhouse.JPG");
            imagePathComboBox.Items.Add("shop.JPG");
            imagePathComboBox.Items.Add("factory.JPG");
            imagePathComboBox.Items.Add("warehouse.JPG");
            imagePathComboBox.Items.Add("university.JPG");
            imagePathComboBox.Items.Add("hospital.JPG");
            imagePathComboBox.Items.Add("school.JPG");
            imagePathComboBox.Items.Add("No image ");
            imagePathComboBox.SelectedItem = "No image";
        }

        // Updates the image in pictureBox2 based on the selected item in imagePathComboBox
        private void imagePathComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Check if a valid item is selected
            if (imagePathComboBox.SelectedIndex != -1 &&
                imagePathComboBox.SelectedItem != null &&
                imagePathComboBox.SelectedItem.ToString() != "No image")
            {
                // Get the selected file name from the combo box
                string fileName = imagePathComboBox.SelectedItem.ToString();

                // Get the directory where the application is running
                string appPath = AppDomain.CurrentDomain.BaseDirectory;

                // Combine the application path with the "Images" folder and the selected file name
                string imagePath = System.IO.Path.Combine(appPath, "Images", fileName);

                // Check if the image file exists at the constructed path
                if (System.IO.File.Exists(imagePath))
                {
                    // Load the image into pictureBox2 if it exists
                    pictureBox2.Image = Image.FromFile(imagePath);
                }
                else
                {
                    // Clear pictureBox2 and handle missing image with a default state
                    pictureBox2.Image = null;
                }
            }
            else
            {
                // Clear the picture box if no valid image is selected
                pictureBox2.Image = null;
            }
        }

        // Initializes the comboboxes in the logginPanel
        private void initLogginComboBoxes()
        {
            foreach (RealEstateBLL.Country country in Enum.GetValues(typeof(RealEstateBLL.Country)))
            {
                logginCountryComboBox.Items.Add(country);
            }
            foreach (RealEstateBLL.City city in Enum.GetValues(typeof(RealEstateBLL.City)))
            {
                logginCityComboBox.Items.Add(city);
            }
        }

        // Creates the useraccount given the information in the logginPanel
        private void submitButton_Click(object sender, EventArgs e)
        {
            City city;
            Country country;
            if (logginCountryComboBox.SelectedIndex != -1)
            {
                country = (Country)logginCountryComboBox.SelectedItem;
            }
            else { return; }
            if (logginCityComboBox.SelectedIndex != -1)
            {
                city = (City)logginCityComboBox.SelectedItem;
            }
            else { return; }
            string Zip = logginZipTextBox.Text;
            string Street = logginStreetTextBox.Text;
            if (string.IsNullOrWhiteSpace(Zip) || string.IsNullOrWhiteSpace(Street))
            {
                return;
            }
            RealEstateBLL.Address address = new RealEstateBLL.Address(country, city, Zip, Street);
            string firstname = firstNameTextBox.Text;
            string lastname = lastNameTextBox.Text;
            if (string.IsNullOrWhiteSpace(firstname))
            {
                firstname = "Mask";
            }
            if (string.IsNullOrWhiteSpace(lastname))
            {
                lastname = "Lat";
            }

            interestsComboBox.Items.Clear();
            interestsComboBox.Text = null;

            useraccount = new RealEstateBLL.Useraccount(new RealEstateBLL.Persons.Seller(firstname, lastname, address), new RealEstateBLL.Persons.Buyer(firstname, lastname, address));

            findEstatePanel.Visible = false;
            listEstatePanel.Visible = false;
            logginPanel.Visible = false;
            backButton.Visible = false;
            welcomePanel.Visible = true;
        }

        // Adds the selected estate to the buyer's interest list
        private void interestedCheckBox_CheckedChanged(object sender, EventArgs e)
        {
            if (interestedCheckBox.Checked)
            {
                useraccount.GetBuyer().addInterest(selectedEstate);
            }
            else
            {
                useraccount.GetBuyer().removeIntrest(selectedEstate);
            }
            saved = false;
        }

        // Displays the description of the selected estate in the interestsComboBox
        private void interestsComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (interestsComboBox.SelectedIndex != -1)
            {
                Estate estate = (Estate)interestsComboBox.SelectedItem;
                interestInfoLabel.Text = estate.GetDescription();
            }
        }

        // Opens up the payment form and then deletes the estate from the list allEstates
        private void buyButton_Click(object sender, EventArgs e)
        {
            if (estateListBox.SelectedIndex != -1)
            {
                PaymentForm form2 = new PaymentForm();
                form2.Show();
                allEstates.RemoveItem(selectedEstate);
                allEstates.RemoveFromDictionary(selectedEstate.GetSeller().GetFullName(), selectedEstate);
                updateEstateListBox();
                informationLabel.Text = " ";
                pictureBox1.Image = null;
                saved = false;
            }
        }

        // Opens the "save as" in the menu strip (unnecessari i know) 
        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (fileManager == null)
            {
                fileToolStripMenuItem.ShowDropDown();
                saveAsToolStripMenuItem.ShowDropDown();
            }
            else
            {
                saved = true; 
                Seller seller = useraccount.GetSeller();
                fileManager.Save(seller.GetFirstName(), seller.GetLastName(), seller.GetAddress().ToString(), seller.getListedEstates(), useraccount.GetBuyer().getInterstList(), allEstates);
            }
        }

        // Exit the application
        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        // Retrives the text file to load from and then calles "LoadfromTextFile" to load the data
        private void textFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog openFileDialog = new OpenFileDialog())
            {
                openFileDialog.Filter = "Text Files (*.txt)|*.txt|All Files (*.*)|*.*";
                openFileDialog.Title = "Open a Data File";

                if (openFileDialog.ShowDialog() == DialogResult.OK)
                {
                    useraccount = new Useraccount();
                    fileManager = new FileManager(FileType.Text, openFileDialog.FileName);
                    List<object> info = fileManager.Load(FileType.Text, openFileDialog.FileName);

                    allEstates = (EstateManager)info[0];
                    useraccount.SellerIdentity = (Seller)info[1];
                    useraccount.BuyerIdentity = (Buyer)info[2];

                    interestsComboBox.Items.Clear();
                    interestsComboBox.Text = null;

                    foreach (Estate estate in useraccount.GetBuyer().getInterstList())
                    {
                        interestsComboBox.Items.Add(estate);
                    }

                    findEstatePanel.Visible = false;
                    listEstatePanel.Visible = false;
                    logginPanel.Visible = false;
                    backButton.Visible = false;
                    welcomePanel.Visible = true;

                    saved = true;
                }
            }
        }

        // Retrives the JSON file to load from and then calles "LoadFromJsonFile" to load the data
        private void jsonToolStripMenuItem_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog openFileDialog = new OpenFileDialog())
            {
                openFileDialog.Filter = "JSON Files (*.json)|*.json|Text Files (*.txt)|*.txt|All Files (*.*)|*.*";
                openFileDialog.Title = "Open a Data File";

                if (openFileDialog.ShowDialog() == DialogResult.OK)
                {
                    fileManager = new FileManager(FileType.Json, openFileDialog.FileName);
                    useraccount = new Useraccount();

                    List<Object> info = fileManager.Load(FileType.Json, openFileDialog.FileName);

                    allEstates = (EstateManager)info[0];
                    useraccount.SellerIdentity = (Seller)info[1];
                    useraccount.BuyerIdentity = (Buyer)info[2];

                    interestsComboBox.Items.Clear();
                    interestsComboBox.Text = null;

                    foreach (Estate estate in useraccount.GetBuyer().getInterstList())
                    {
                        interestsComboBox.Items.Add(estate);
                    }

                    findEstatePanel.Visible = false;
                    listEstatePanel.Visible = false;
                    logginPanel.Visible = false;
                    backButton.Visible = false;
                    welcomePanel.Visible = true;

                    saved = true;
                }
            }
        }

        // Retrives the text file to save to and then calles "SaveToTextFile" to save the data
        private void textFileToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (useraccount != null)
            {
                using (SaveFileDialog saveFileDialog = new SaveFileDialog())
                {
                    // Visa textfiler och alla filer
                    saveFileDialog.Filter = "Text Files (*.txt)|*.txt|All Files (*.*)|*.*";
                    saveFileDialog.Title = "Save Data As";

                    if (saveFileDialog.ShowDialog() == DialogResult.OK)
                    {
                        fileManager = new FileManager(FileType.Text, saveFileDialog.FileName);
                        fileManager.Save(useraccount.GetSeller().GetFirstName(), useraccount.GetSeller().GetLastName(), useraccount.GetSeller().GetAddress().ToString(), useraccount.GetSeller().getListedEstates(), useraccount.GetBuyer().getInterstList(), allEstates);
                        saved = true;
                    }
                }
            }
        }

        // Retrives the JSON file to save to and then calles "SaveToJsonFile" to save the data
        private void jsonToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (useraccount != null)
            {
                using (SaveFileDialog saveFileDialog = new SaveFileDialog())
                {
                    saveFileDialog.Filter = "JSON Files (*.json)|*.json|Text Files (*.txt)|*.txt|All Files (*.*)|*.*";
                    saveFileDialog.Title = "Save Data As";

                    if (saveFileDialog.ShowDialog() == DialogResult.OK)
                    {
                        fileManager = new FileManager(FileType.Json, saveFileDialog.FileName);
                        fileManager.Save(useraccount.GetSeller().GetFirstName(), useraccount.GetSeller().GetLastName(), useraccount.GetSeller().GetAddress().ToString(), useraccount.GetSeller().getListedEstates(), useraccount.GetBuyer().getInterstList(), allEstates);
                        saved = true;
                    }
                }
            }
        }

        // Retrives the XML file to save to and then calles "SaveToXml1" to save the data
        private void XmlSaveToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (useraccount != null)
            {
                using (SaveFileDialog saveFileDialog = new SaveFileDialog())
                {
                    saveFileDialog.Filter = "XML Files (*.xml)|*.xml|All Files (*.*)|*.*";
                    saveFileDialog.Title = "Save Data As";

                    if (saveFileDialog.ShowDialog() == DialogResult.OK)
                    {
                        fileManager = new FileManager(FileType.Xml, saveFileDialog.FileName);
                        fileManager.Save(useraccount.GetSeller().GetFirstName(), useraccount.GetSeller().GetLastName(), useraccount.GetSeller().GetAddress().ToString(), useraccount.GetSeller().getListedEstates(), useraccount.GetBuyer().getInterstList(), allEstates);
                        saved = true;
                    }
                }
            }
        }

        // Retrives the XML file to load from and then calles "LoadFromXml" to load the data
        private void XmlLoadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog openFileDialog = new OpenFileDialog())
            {
                openFileDialog.Filter = "XML Files (*.xml)|*.xml|All Files (*.*)|*.*";
                openFileDialog.Title = "Open a Data File";

                if (openFileDialog.ShowDialog() == DialogResult.OK)
                {
                    useraccount = new Useraccount();
                    fileManager = new FileManager(FileType.Xml, openFileDialog.FileName);

                    List<Object> info = fileManager.Load(FileType.Xml, openFileDialog.FileName);

                    allEstates = (EstateManager)info[0];
                    useraccount.SellerIdentity = (Seller)info[1];
                    useraccount.BuyerIdentity = (Buyer)info[2];

                    interestsComboBox.Items.Clear();
                    interestsComboBox.Text = null;

                    foreach (Estate estate in useraccount.GetBuyer().getInterstList())
                    {
                        interestsComboBox.Items.Add(estate);
                    }

                    findEstatePanel.Visible = false;
                    listEstatePanel.Visible = false;
                    logginPanel.Visible = false;
                    backButton.Visible = false;
                    welcomePanel.Visible = true;

                    saved = true;
                }
            }
        }

        //Creates an estate object based on the provided details and category.
        private Estate CreateEstate(Category category, Address address, int squares, int rooms, bool elevator, bool balcony, bool patio, bool sportHall, string image, string type, Seller seller)
        {
            Estate estate;

            switch (category)
            {
                case Category.Institutional:
                    estate = CreateInstitutionalEstate(type, sportHall);
                    break;
                case Category.Residential:
                    estate = CreateResidentialEstate(type, balcony, elevator, patio);
                    break;
                case Category.Commercial:
                    estate = CreateCommercialEstate(type);
                    break;
                default:
                    estate = null;
                    break;
            }

            if (estate != null)
            {
                estate.SetAddress(address);
                estate.SetId(GenerateEstateId("123"));
                estate.SetSquares(squares);
                estate.SetNumOfRooms(rooms);
                estate.SetImagePath(image);                
                estate.SetSeller(seller);  

                return estate;
            }
            return new Estate(); 
        }

        // Returns a formatted string with the listed estates of the seller.
        internal string GetSellerList()
        {
            if (useraccount != null)
            {
                EstateManager list = useraccount.GetSeller().getListedEstates();
                if (list.Count() == 0)
                {
                    return " ";
                }
                string text = "Listed estates: ";
                bool firstItem = true;

                foreach (Estate estate in list)
                {
                    if (!firstItem)
                    {
                        text += ", ";
                    }
                    text += estate.GetType().ToString();
                    firstItem = false;
                }

                return text;
            }
            return " ";
        }

        // Creates a commercial object based on the provided details and category.
        private Estate CreateCommercialEstate(string type)
        {
            CommercialType commercialType = (CommercialType)Enum.Parse(typeof(CommercialType), type);

            switch (commercialType)
            {
                case CommercialType.Factory:
                    return new RealEstateBLL.Commercials.Factory();
                case CommercialType.Shop:
                    return new RealEstateBLL.Commercials.Shop();
                case CommercialType.Warehouse:
                    return new RealEstateBLL.Commercials.Warehouse();
                case CommercialType.Hotel:
                    return new RealEstateBLL.Commercials.Hotel();
                default:
                    return null;
            }
        }

        // Creates a resideltial object based on the provided details and category.
        private Estate CreateResidentialEstate(string type, bool balcony, bool elevator, bool patio)
        {
            ResidentialType residentialType = (ResidentialType)Enum.Parse(typeof(ResidentialType), type);

            switch (residentialType)
            {
                case ResidentialType.Townhouse:
                    return new RealEstateBLL.Residentials.Townhouse(2, patio);
                case ResidentialType.Villa:
                    return new RealEstateBLL.Residentials.Villa(2000);
                case ResidentialType.Apartment:
                    return new RealEstateBLL.Residentials.Apartment(balcony, elevator);
                default:
                    return null;
            }
        }

        // Creates an institutional object based on the provided details and category.
        private Estate CreateInstitutionalEstate(string type, bool sportHall)
        {
            InstitutionalType institutionalType = (InstitutionalType)Enum.Parse(typeof(InstitutionalType), type);

            switch (institutionalType)
            {
                case InstitutionalType.Hospital:
                    return new RealEstateBLL.Institutionals.Hospital();
                case InstitutionalType.University:
                    return new RealEstateBLL.Institutionals.University();
                case InstitutionalType.School:
                    return new RealEstateBLL.Institutionals.School(sportHall);
                default:
                    return null;
            }
        }

        // Generates a unique estate ID based on the provided zip code.
        private string GenerateEstateId(string zip)
        {
            Random random = new Random();
            return "E" + zip + random.Next(0, 10).ToString();
        }

        //Restarts the application or asks the user if they want to save the current data
        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (saved)
            {
                restart();
            }
            else
            {
                DialogResult result = MessageBox.Show("Do you want to proceed without saving the current data?", "Confirm",
                MessageBoxButtons.YesNo, MessageBoxIcon.Question);

                if (result == DialogResult.Yes)
                {
                    restart();
                }
            }
        }

        //Used for the implementation of the dictionary in assignment 2
        private void SellerNameTextBox_TextChanged(object sender, EventArgs e)
        {
            String name = SellerNameTextBox.Text;

            if (string.IsNullOrEmpty(SellerNameTextBox.Text))
            {
                updateEstateListBox();
            }
            else
            {
                estateListBox.Items.Clear();

                List<Estate> estates = allEstates.Find(name);

                if (estates != null)
                {
                    foreach (Estate estate in estates)
                    {
                        estateListBox.Items.Add(estate);
                    }
                }
            }
        }

        //Restarts the application when user chooses "new" in the menu strip
        private void restart()
        {
            saved = false;

            useraccount = null;

            fileManager = null;

            interestsComboBox.Items.Clear();
            interestsComboBox.Text = null;
            interestInfoLabel.Text = null;

            logginCityComboBox.Text = null;
            logginCountryComboBox.Text = null;
            logginZipTextBox.Text = null;
            logginStreetTextBox.Text = null;
            firstNameTextBox.Text = null;
            lastNameTextBox.Text = null;

            welcomePanel.Visible = false;
            findEstatePanel.Visible = false;
            listEstatePanel.Visible = false;
            backButton.Visible = false;
            logginPanel.Visible = true;

            AddIntitialEstates();
        }

        //Initializes pre-create estates at start/restart
        private void AddIntitialEstates()
        {
            //Add initial estates
            allEstates = new EstateManager();

            Address sellerAddress = new Address(Country.France, City.Paris, "233-99", "Realestatestreet");
            RealEstateBLL.Residentials.Villa villa = new RealEstateBLL.Residentials.Villa("E123", new Address(Country.Japan, City.Tokyo, "111-22", "Cherryblom.st."), 150, 5, 200, "villa.JPG", new RealEstateBLL.Persons.Seller("Hans", "Ericsson", sellerAddress));
            RealEstateBLL.Commercials.Warehouse warehouse = new RealEstateBLL.Commercials.Warehouse("E124", new Address(Country.France, City.Paris, "123-45", "Baugette.st."), 200, 8, "warehouse.JPG", new RealEstateBLL.Persons.Seller("Amanda", "Andersson", sellerAddress));
            RealEstateBLL.Institutionals.University univerity = new RealEstateBLL.Institutionals.University("E125", new Address(Country.India, City.Mumbai, "323-55", "Spiceitup.st."), 200, 10, 70, "university.JPG", new RealEstateBLL.Persons.Seller("Emil", "Persson", sellerAddress));
            RealEstateBLL.Residentials.Townhouse townhouse = new RealEstateBLL.Residentials.Townhouse("E126", new Address(Country.Canada, City.CapeTown, "999-23", "Snowflake.st."), 80, 4, 2, true, "townhouse.JPG", new RealEstateBLL.Persons.Seller("Lovisa", "Hermansson", sellerAddress));
            RealEstateBLL.Commercials.Shop shop = new RealEstateBLL.Commercials.Shop("E127", new Address(Country.Australia, City.Sydney, "757-22", "Spidernest.st."), 40, 1, "shop.JPG", new RealEstateBLL.Persons.Seller("Edvig", "Leven", sellerAddress));
            RealEstateBLL.Residentials.Apartment apartment = new RealEstateBLL.Residentials.Apartment("E128", new Address(Country.Brazil, City.SãoPaulo, "888-21", "Momondo.st"), 25, 2, true, true, "apartment.JPG", new RealEstateBLL.Persons.Seller("Per", "Göransson", sellerAddress));

            allEstates.Add(villa);
            allEstates.Add(townhouse);
            allEstates.Add(apartment);
            allEstates.Add(warehouse);
            allEstates.Add(univerity);
            allEstates.Add(shop);
        }

    }
}
