import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class gui1 extends JFrame implements ActionListener
{
JTextField t1,t2,t3,t4,t5,t6,t7;
JButton b,b1;
JLabel l,l1,l2,l3,l4,l5,l6,l7;
JPanel panel;

	public gui1()
	{   
		setBounds(450, 290, 1014, 720);
	    setResizable(false);
	    panel = new JPanel();
	    panel .setBorder(new EmptyBorder(5, 5, 5, 5));
	    panel .setBackground(Color.decode("#CCF381"));
		setContentPane(panel);
		panel .setLayout(null);

		l=new JLabel("Create Account");
	    l.setFont(new Font("Impact", Font.PLAIN, 50));
	    l.setBounds(350, 52, 325, 50);
	    panel.add(l);

		l1=new JLabel("Name");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    l1.setBounds(200, 150, 300, 30);
	    panel.add(l1);

		t1= new JTextField(20);
		t1.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    t1.setBounds(500, 150, 300, 30);
	    panel.add(t1);

		l2=new JLabel("Email");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    l2.setBounds(200, 250, 300, 30);
	    panel.add(l2);

		t2= new JTextField(20);
		t2.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    t2.setBounds(500, 250, 300, 30);
	    panel.add(t2);

        l3=new JLabel("Phone no");
		l3.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    l3.setBounds(200, 200, 300, 30);
	    panel.add(l3);

		t3= new JTextField(20);
		t3.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    t3.setBounds(500, 200, 300, 30);
	    panel.add(t3);

        l4=new JLabel("Address");
		l4.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    l4.setBounds(200, 300, 300, 30);
	    panel.add(l4);

		t4= new JTextField(20);
		t4.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    t4.setBounds(500, 300, 300, 30);
	    panel.add(t4);

        l5=new JLabel("Card NO(*10 num)");
		l5.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    l5.setBounds(200, 350, 300, 30);
	    panel.add(l5);

		t5= new JTextField(20);
		t5.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    t5.setBounds(500, 350, 300, 30);
	    panel.add(t5);

        l6=new JLabel("PIN(*4 num)");
		l6.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    l6.setBounds(200, 400, 300, 30);
	    panel.add(l6);

		t6= new JTextField(20);
		t6.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    t6.setBounds(500, 400, 300, 30);
	    panel.add(t6);

		l7=new JLabel("First Deposit");
		l7.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    l7.setBounds(200, 450, 300, 30);
	    panel.add(l7);

		t7= new JTextField(20);
		t7.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    t7.setBounds(500, 450, 300, 30);
	    panel.add(t7);

		b= new JButton("Submit");
		b.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    b.setBounds(600, 500, 200, 60);
	    panel.add(b);

        b1= new JButton("Clear");
		b1.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    b1.setBounds(180, 500, 200, 60);
	    panel.add(b1);

		b.addActionListener(this);
		b1.addActionListener(this);
		setTitle("Create Account");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
	}
	private void registerUser() {
        String name = t1.getText();
        String email = t2.getText();
        String phone = t3.getText();
        String address = t4.getText();
        String cardno = t5.getText();
        String pin = t6.getText();
		String balance = t7.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || cardno.isEmpty() || pin.isEmpty() || balance.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(name, email, phone, address, cardno,pin,balance);
        if (user != null) {
            JOptionPane.showMessageDialog(this,"Registration Successfull");
			dispose();
			new gui();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

	public User user;
	private User addUserToDatabase(String name,String email,String phone,String address,String cardno,String pin,String balance){
		User user=null;
		final String DB_URL = "jdbc:mysql://localhost/atm?serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD ="";

		try{
				Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

				Statement stmt=conn.createStatement();
				String sql = "INSERT INTO login(name,email,phone,address,cardno,pin,balance)"+"VALUES(?,?,?,?,?,?,?)";
				PreparedStatement prepS=conn.prepareStatement(sql);
				prepS.setString(1,name);
				prepS.setString(2,email);
				prepS.setString(3,phone);
				prepS.setString(4,address);
				prepS.setString(5,cardno);
				prepS.setString(6,pin);
				prepS.setString(7,balance);

				int addedRows = prepS.executeUpdate();
				if(addedRows>0){
					user= new User();
					user.name=name;
					user.email=email;
					user.phone=phone;
					user.address=address;
					user.cardno=cardno;
					user.pin=pin;
					user.balance=balance;
				}

				stmt.close();
				conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return user;	
	}


    public static void main (String args[])
	{
		
    	new gui1();
}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
}