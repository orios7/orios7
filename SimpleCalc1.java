package cop2805;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.event.*;

	public class SimpleCalc1 {
		
	private static void constructGUI()
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true); // Sets up default look
		MyFrame frame = new MyFrame();               // Sets up frame
		frame.setVisible(true);

	 
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {  // Calls contructGUI
		public void run() {
		constructGUI();
		}
	 });

	}
	}

	class MyButtonListener implements ActionListener {
		
		MyFrame fr; // gives access to public variables in MyFrame method
		
		public MyButtonListener (MyFrame frame)
		{
		fr = frame;	 // makes fr accessible to actionPerformed function
		
		}
		
		public void actionPerformed(ActionEvent e)
		{
			//JButton btn = (JButton) e.getSource();
			String firstNum = fr.firstNumber.getText();
			Double fN = Double.parseDouble(firstNum);
			String SecNum = fr.secondNumber.getText();
			Double sN = Double.parseDouble(SecNum);
			Double resultValue = null;
			String resultString;
			//String resultString = parse
			
			if (fr.operOption.getSelectedItem() == "Add") {  // Calculates addition
				resultValue = fN + sN;
				//System.out.println(resultValue);	
			}
			else if (fr.operOption.getSelectedItem() == "Subtract") { // Calculates Subtraction
				resultValue = fN - sN;
				//System.out.println(resultValue);
			}
			
			else if (fr.operOption.getSelectedItem() == "Multiply")  { // Calculates multiplication
				resultValue = fN * sN;
				//System.out.println(resultValue);
			}
			else if (fr.operOption.getSelectedItem() == "Divide") { // calculates division
				resultValue = fN / sN;
				//System.out.println(resultValue);			
			}
			//resultString = "" + resultValue; // converts Double to string
			
			fr.resultValue.setText("Result: " + "".valueOf(resultValue)); // Sends calculated value as STRING to resultValue JLabel in init function

		}

	}

	class MyFrame extends JFrame {
		
		public JTextField firstNumber; // Makes variables public accessible by other classes
		public JTextField secondNumber;
		public JLabel resultValue;
		public JButton calButton;
		public JComboBox operOption;
		
		public MyFrame() {
			super(); // calls the JFrame init()
			init();
		} 
		
		private void init() {
			firstNumber = new JTextField();                                 // 
			secondNumber = new JTextField();                                //
			String[] oper = { "Add", "Subtract", "Multiply", "Divide" };	// String Array for operOption JComboBox	
			operOption = new JComboBox(oper);                               // New instance of JComboBox
			JButton calButton = new JButton("Calculate");                   // New instance of JButton
			calButton.addActionListener(new MyButtonListener(this));        // Sets up listener
			
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Enables the ability close button
			this.setTitle("Simple Calculator"); 
			this.setLayout(new GridLayout(5, 2));
			this.add(new JLabel("First Number:"));
			this.add(firstNumber);                               // Adds firstNumber JTextField  
			this.add(new JLabel("Second Number:"));
			this.add(secondNumber);                              // Adds secondNumber JTextField  
			this.add(operOption);                                // Adds JComboBox for operator selection
			this.add(calButton);                                 // Adds Calculate JButton

			resultValue = new JLabel (""); // setup blank JLabel for calculated amount - done by actionPerformed function 
			this.add(resultValue); // adds result JLable to Frame
			
			int frameWidth = 250; // Sets Fame size
			int frameHeight = 150; // Sets Height size
			Dimension screenSize =
				Toolkit.getDefaultToolkit().getScreenSize(); // Retrieves monitor size
			this.setBounds((int) screenSize.getWidth()/2 - frameWidth, (int) screenSize.getHeight()/2 - frameHeight, // Sets calculator in the middle of the screen aprox
			frameWidth, 
			frameHeight);
		}
		
	}

