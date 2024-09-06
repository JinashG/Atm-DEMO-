import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.border.EmptyBorder;

 class gui extends JFrame implements ActionListener
{
JTextField t1,t2;
JButton b,b1;
JRadioButton r1,r2;
JLabel l,l1,l2;
JPanel panel;

	public gui()
	{   
		setBounds(450, 190, 1014, 597);
	    setResizable(false);
	    panel = new JPanel();
	    panel .setBorder(new EmptyBorder(5, 5, 5, 5));
	    panel .setBackground(Color.decode("#8AAAE5"));
		setContentPane(panel);
		panel .setLayout(null);

		l=new JLabel("ATM");
	    l.setFont(new Font("Impact", Font.PLAIN, 50));
	    l.setBounds(475, 52, 325, 50);
	    panel.add(l);

		l1=new JLabel("Card no:");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 42));
	    l1.setBounds(200, 150, 325, 50);
	    panel.add(l1);

		t1= new JTextField(20);
		t1.setFont(new Font("Tahoma", Font.PLAIN, 42));
	    t1.setBounds(400, 150, 325, 50);
	    panel.add(t1);

		l2=new JLabel("Pin:");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 42));
	    l2.setBounds(200, 250, 325, 50);
	    panel.add(l2);

		t2= new JTextField(20);
		t2.setFont(new Font("Tahoma", Font.PLAIN, 42));
	    t2.setBounds(400, 250, 325, 50);
	    panel.add(t2);

		b= new JButton("Ok");
		b.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    b.setBounds(600, 460, 200, 60);
	    panel.add(b);

        b1= new JButton("Clear");
		b1.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    b1.setBounds(180, 460, 200, 60);
	    panel.add(b1);

		b.addActionListener(this);
		b1.addActionListener(this);
		setTitle("ATM");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		String cardno = t1.getText();
	    String pin = t2.getText();

		User user=getAuthenticatedUser(cardno,pin);

		if(user!=null){
			MainFrame mf=new MainFrame();
			mf.initialize(user);
			dispose();
		}
		else{
			JOptionPane.showMessageDialog(gui.this,
			"Card Num or Password Invalid",
			"Try Again",
			JOptionPane.ERROR_MESSAGE);
		}
	    int len = cardno.length();
		int len1 = pin.length();
		if (len != 10) {
			JOptionPane.showMessageDialog(b, "Enter a valid Card number");
		}
		if (len1 != 4) {
			JOptionPane.showMessageDialog(b, "Enter a valid pin");
		}
		if(e.getSource()==b1)
        {
			t1.setText("");
			t2.setText("");
        }
		
	}
	private User getAuthenticatedUser(String cardno, String pin) {
		User user = null;

		final String DB_URL = "jdbc:mysql://localhost/atm?serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD ="";

		try{
			Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			String sql ="SELECT * FROM login WHERE cardno=? AND pin=?";
			PreparedStatement prepS=conn.prepareStatement(sql);
			prepS.setString(1,cardno);
			prepS.setString(2,pin);

			ResultSet resS=prepS.executeQuery();
			if(resS.next()){
				user = new User();
				user.name= resS.getString("Name");
				user.email= resS.getString("Email");
				user.phone= resS.getString("Phone");
				user.address= resS.getString("Address");
				user.balance=resS.getString("Balance");

			}
			prepS.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Database Connection Failed!!");
		}
		return user;
	}
}
public class ATM 
{
    public static void main (String args[])
	{}
}

