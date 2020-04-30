import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel{

    private JLabel lbId;
    private JLabel lbPassword;

    private JTextField tfId;
    private JPasswordField tfPassword;

    private JButton btLogin;
    private JButton btReset;

    private LoginPanel panel;

    public LoginPanel(){
        panel = this;

        setLayout(new GridLayout(3,2));
        lbId = new JLabel("Id : ", JLabel.CENTER);
        lbPassword = new JLabel("Password : ", JLabel.CENTER);

        tfId = new JTextField();
        tfPassword = new JPasswordField();

        btLogin = new JButton("Login");
        btReset = new JButton("Reset");
        btLogin.addActionListener(actionListener);
        btReset.addActionListener(actionListener);

        add(lbId, JLabel.CENTER);
        add(tfId);
        add(lbPassword, JLabel.CENTER_ALIGNMENT);
        add(tfPassword);
        add(btLogin);
        add(btReset);

        setSize(400,200);
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent event) {
            Object src = event.getSource();
            if(src == btLogin){
                Connection connection = null;
                Statement stmt = null;
                ResultSet rs = null;
                try{
                    connection = DatabaseManager.getConnection();
                    if(connection != null){
                        stmt = connection.createStatement();
                        String sql = "select * from member where id ='" + tfId.getText() +
                                "' and password ='" + tfPassword.getText() + "'";
                        rs = stmt.executeQuery(sql);

                        if(rs.next())
                            JOptionPane.showMessageDialog(panel, "login success");
                        else
                            JOptionPane.showMessageDialog(panel, "login fail");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(stmt != null) stmt.close();
                        if(connection != null) connection.close();
                    }catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
            }else if(src == btReset){
                reset();
            }
        }
    };

    private void reset(){
        tfId.setText("");
        tfPassword.setText("");
    }
}
