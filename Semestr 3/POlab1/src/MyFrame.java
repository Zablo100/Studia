import javax.swing.*;

public class MyFrame extends JFrame {
    MyFrame(){
        setSize(400,400);
        JButton btn = new JButton();
        add(btn);
        btn.addActionListener(new MyListener());
        setVisible(true);
    }

}
