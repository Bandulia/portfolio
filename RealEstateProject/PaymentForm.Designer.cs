namespace Assignment_1_version_1
{
    partial class PaymentForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            paymentLabel = new Label();
            comboBox1 = new ComboBox();
            PayPalPanel = new Panel();
            payPalTextBox = new TextBox();
            label1 = new Label();
            bankPanel = new Panel();
            accountTextBox = new TextBox();
            bankNameTextBox = new TextBox();
            label2 = new Label();
            label3 = new Label();
            westernunionPanel = new Panel();
            emailTextBox = new TextBox();
            nameTextBox = new TextBox();
            label5 = new Label();
            label4 = new Label();
            button1 = new Button();
            panel1 = new Panel();
            label8 = new Label();
            label7 = new Label();
            offerPanel = new Panel();
            offerButton = new Button();
            feedbackLabel = new Label();
            amountLabel = new Label();
            amountTextBox = new TextBox();
            PayPalPanel.SuspendLayout();
            bankPanel.SuspendLayout();
            westernunionPanel.SuspendLayout();
            panel1.SuspendLayout();
            offerPanel.SuspendLayout();
            SuspendLayout();
            // 
            // paymentLabel
            // 
            paymentLabel.AutoSize = true;
            paymentLabel.Location = new Point(50, 77);
            paymentLabel.Name = "paymentLabel";
            paymentLabel.Size = new Size(207, 20);
            paymentLabel.TabIndex = 0;
            paymentLabel.Text = "Choose you payment method:";
            // 
            // comboBox1
            // 
            comboBox1.FormattingEnabled = true;
            comboBox1.Location = new Point(50, 109);
            comboBox1.Name = "comboBox1";
            comboBox1.Size = new Size(207, 28);
            comboBox1.TabIndex = 1;
            comboBox1.SelectedIndexChanged += comboBox1_SelectedIndexChanged;
            // 
            // PayPalPanel
            // 
            PayPalPanel.Controls.Add(payPalTextBox);
            PayPalPanel.Controls.Add(label1);
            PayPalPanel.Location = new Point(34, 143);
            PayPalPanel.Name = "PayPalPanel";
            PayPalPanel.Size = new Size(250, 202);
            PayPalPanel.TabIndex = 2;
            // 
            // payPalTextBox
            // 
            payPalTextBox.Location = new Point(16, 70);
            payPalTextBox.Name = "payPalTextBox";
            payPalTextBox.Size = new Size(207, 27);
            payPalTextBox.TabIndex = 1;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(16, 47);
            label1.Name = "label1";
            label1.Size = new Size(120, 20);
            label1.TabIndex = 0;
            label1.Text = "Enter your email:";
            // 
            // bankPanel
            // 
            bankPanel.Controls.Add(accountTextBox);
            bankPanel.Controls.Add(bankNameTextBox);
            bankPanel.Controls.Add(label2);
            bankPanel.Controls.Add(label3);
            bankPanel.Location = new Point(31, 146);
            bankPanel.Name = "bankPanel";
            bankPanel.Size = new Size(250, 202);
            bankPanel.TabIndex = 3;
            // 
            // accountTextBox
            // 
            accountTextBox.Location = new Point(19, 150);
            accountTextBox.Name = "accountTextBox";
            accountTextBox.Size = new Size(207, 27);
            accountTextBox.TabIndex = 3;
            // 
            // bankNameTextBox
            // 
            bankNameTextBox.Location = new Point(19, 71);
            bankNameTextBox.Name = "bankNameTextBox";
            bankNameTextBox.Size = new Size(207, 27);
            bankNameTextBox.TabIndex = 2;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(19, 44);
            label2.Name = "label2";
            label2.Size = new Size(154, 20);
            label2.TabIndex = 0;
            label2.Text = "Enter the banks name:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(19, 124);
            label3.Name = "label3";
            label3.Size = new Size(190, 20);
            label3.TabIndex = 1;
            label3.Text = "Enter your account number:";
            // 
            // westernunionPanel
            // 
            westernunionPanel.Controls.Add(emailTextBox);
            westernunionPanel.Controls.Add(nameTextBox);
            westernunionPanel.Controls.Add(label5);
            westernunionPanel.Controls.Add(label4);
            westernunionPanel.Location = new Point(25, 128);
            westernunionPanel.Name = "westernunionPanel";
            westernunionPanel.Size = new Size(250, 202);
            westernunionPanel.TabIndex = 4;
            // 
            // emailTextBox
            // 
            emailTextBox.Location = new Point(19, 150);
            emailTextBox.Name = "emailTextBox";
            emailTextBox.Size = new Size(207, 27);
            emailTextBox.TabIndex = 3;
            // 
            // nameTextBox
            // 
            nameTextBox.Location = new Point(19, 70);
            nameTextBox.Name = "nameTextBox";
            nameTextBox.Size = new Size(207, 27);
            nameTextBox.TabIndex = 2;
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new Point(19, 47);
            label5.Name = "label5";
            label5.Size = new Size(145, 20);
            label5.TabIndex = 1;
            label5.Text = "Enter your full name:";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(28, 127);
            label4.Name = "label4";
            label4.Size = new Size(120, 20);
            label4.TabIndex = 0;
            label4.Text = "Enter your email:";
            // 
            // button1
            // 
            button1.Location = new Point(77, 351);
            button1.Name = "button1";
            button1.Size = new Size(146, 29);
            button1.TabIndex = 6;
            button1.Text = "Process Payment ";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // panel1
            // 
            panel1.Controls.Add(label8);
            panel1.Controls.Add(label7);
            panel1.Location = new Point(12, 12);
            panel1.Name = "panel1";
            panel1.Size = new Size(353, 393);
            panel1.TabIndex = 7;
            // 
            // label8
            // 
            label8.AutoSize = true;
            label8.Location = new Point(82, 166);
            label8.Name = "label8";
            label8.Size = new Size(195, 20);
            label8.TabIndex = 1;
            label8.Text = "Your payment whent through";
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.Font = new Font("Segoe UI", 12F);
            label7.Location = new Point(15, 201);
            label7.Name = "label7";
            label7.Size = new Size(328, 28);
            label7.TabIndex = 0;
            label7.Text = "Congratulations on your new estate!";
            // 
            // offerPanel
            // 
            offerPanel.Controls.Add(offerButton);
            offerPanel.Controls.Add(feedbackLabel);
            offerPanel.Controls.Add(amountLabel);
            offerPanel.Controls.Add(amountTextBox);
            offerPanel.Location = new Point(0, 3);
            offerPanel.Name = "offerPanel";
            offerPanel.Size = new Size(350, 390);
            offerPanel.TabIndex = 2;
            // 
            // offerButton
            // 
            offerButton.Location = new Point(134, 339);
            offerButton.Name = "offerButton";
            offerButton.Size = new Size(94, 29);
            offerButton.TabIndex = 3;
            offerButton.Text = "Confirm";
            offerButton.UseVisualStyleBackColor = true;
            offerButton.Click += offerButton_Click;
            // 
            // feedbackLabel
            // 
            feedbackLabel.AllowDrop = true;
            feedbackLabel.Location = new Point(19, 220);
            feedbackLabel.Name = "feedbackLabel";
            feedbackLabel.Size = new Size(309, 113);
            feedbackLabel.TabIndex = 2;
            feedbackLabel.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // amountLabel
            // 
            amountLabel.AutoSize = true;
            amountLabel.Location = new Point(124, 143);
            amountLabel.Name = "amountLabel";
            amountLabel.Size = new Size(115, 20);
            amountLabel.TabIndex = 1;
            amountLabel.Text = "Enter your offer:";
            // 
            // amountTextBox
            // 
            amountTextBox.Location = new Point(76, 175);
            amountTextBox.Name = "amountTextBox";
            amountTextBox.Size = new Size(201, 27);
            amountTextBox.TabIndex = 0;
            // 
            // PaymentForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(376, 413);
            Controls.Add(PayPalPanel);
            Controls.Add(bankPanel);
            Controls.Add(westernunionPanel);
            Controls.Add(comboBox1);
            Controls.Add(paymentLabel);
            Controls.Add(button1);
            Controls.Add(offerPanel);
            Controls.Add(panel1);
            Name = "PaymentForm";
            Text = "Payment";
            PayPalPanel.ResumeLayout(false);
            PayPalPanel.PerformLayout();
            bankPanel.ResumeLayout(false);
            bankPanel.PerformLayout();
            westernunionPanel.ResumeLayout(false);
            westernunionPanel.PerformLayout();
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            offerPanel.ResumeLayout(false);
            offerPanel.PerformLayout();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label paymentLabel;
        private ComboBox comboBox1;
        private Panel PayPalPanel;
        private Panel bankPanel;
        private Panel westernunionPanel;
        private Label label1;
        private Label label3;
        private Label label2;
        private Label label5;
        private Label label4;
        private TextBox payPalTextBox;
        private TextBox accountTextBox;
        private TextBox bankNameTextBox;
        private TextBox emailTextBox;
        private TextBox nameTextBox;
        private Button button1;
        private Panel panel1;
        private Label label7;
        private Label label8;
        private Panel offerPanel;
        private Label feedbackLabel;
        private Label amountLabel;
        private TextBox amountTextBox;
        private Button offerButton;
    }
}