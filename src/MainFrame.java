
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public void initialize(User user){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0,2,5,5));
        infoPanel.add(new JLabel("NAME"));
        infoPanel.add(new JLabel(user.name));
        infoPanel.add(new JLabel("EMAIL"));
        infoPanel.add(new JLabel(user.email));
        infoPanel.add(new JLabel("PHONE"));
        infoPanel.add(new JLabel(user.phone));
        infoPanel.add(new JLabel("ADDRESS"));
        infoPanel.add(new JLabel(user.address));
        infoPanel.add(new JLabel("BALANCE AMOUNT"));
        infoPanel.add(new JLabel(user.balance));

        add(infoPanel,BorderLayout.NORTH);
        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100,650);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
