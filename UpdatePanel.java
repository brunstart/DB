import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UpdatePanel extends JPanel{

    private JLabel lbId;
    private JLabel lbPassword;
    private JLabel lbName;
    private JLabel lbEmail;
    private JLabel lbCategory;
    private JLabel lbCondition;
    private JLabel lbUpdate;
    private JLabel lbBlank;

    private JTextField tfId;
    private JPasswordField tfPassword;
    private JTextField tfName;
    private JTextField tfEmail;

    private JTextField tfIdUpdate;
    private JPasswordField tfPasswordUpdate;
    private JTextField tfNameUpdate;
    private JTextField tfEmailUpdate;

    private JButton btUpdate;
    private JButton btReset;

    private UpdatePanel panel;

    public UpdatePanel(){

        panel = this;
        setLayout(new GridLayout(6,3));

        lbCategory = new JLabel("종류", JLabel.CENTER);
        lbCondition = new JLabel("조건", JLabel.CENTER);
        lbUpdate = new JLabel("바꿀 값", JLabel.CENTER);
        lbId = new JLabel("ID : ", JLabel.CENTER);
        lbPassword = new JLabel("Password: ", JLabel.CENTER);
        lbName = new JLabel("Name : ",JLabel.CENTER);
        lbEmail = new JLabel("E-mail : ",JLabel.CENTER);
        lbBlank = new JLabel("");

        tfId = new JTextField();
        tfPassword = new JPasswordField();
        tfName = new JTextField();
        tfEmail = new JTextField();

        tfIdUpdate = new JTextField();
        tfPasswordUpdate = new JPasswordField();
        tfNameUpdate = new JTextField();
        tfEmailUpdate = new JTextField();

        btUpdate = new JButton("Update");
        btReset = new JButton("Reset");
        btUpdate.addActionListener(actionListener);
        btReset.addActionListener(actionListener);

        add(lbCategory, JLabel.CENTER);
        add(lbCondition, JLabel.CENTER_ALIGNMENT);
        add(lbUpdate, JLabel.CENTER_ALIGNMENT);
        add(lbId, JLabel.CENTER_ALIGNMENT);
        add(tfId);
        add(tfIdUpdate);
        add(lbPassword, JLabel.CENTER_ALIGNMENT);
        add(tfPassword);
        add(tfPasswordUpdate);
        add(lbName, JLabel.CENTER_ALIGNMENT);
        add(tfName);
        add(tfNameUpdate);
        add(lbEmail, JLabel.CENTER_ALIGNMENT);
        add(tfEmail);
        add(tfEmailUpdate);
        add(lbBlank);
        add(btUpdate, JLabel.CENTER_ALIGNMENT);
        add(btReset);

        setSize(400,200);
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent event) {
            Object src = event.getSource();
            if(src == btUpdate){
                Connection connection = null;
                Statement stmt = null;
                ResultSet rs = null;

                try{
                    connection = DatabaseManager.getConnection();
                    if(connection != null){
                        stmt = connection.createStatement();

                        String sql = "update member set id = '" + tfIdUpdate.getText() +
                                "', Password = '" + tfPasswordUpdate.getText() +
                                "', Name = '" + tfNameUpdate.getText() +
                                "', Email = '" + tfEmailUpdate.getText() +
                                "' where id = '" + tfId.getText() +
                                "' and Password = '" + tfPassword.getText() +
                                "'";

                        String sql2 = "select * from member where id = '" + tfId.getText() +
                                "' and Password = '" + tfPassword.getText() +
                                "'";

                        rs = stmt.executeQuery(sql2);

                        if(rs.next()) {
                            tfName.setText(rs.getString("Name"));
                            tfEmail.setText(rs.getString("Email"));
                        }
                        stmt.executeUpdate(sql);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally{
                    try{
                        if(stmt != null) stmt.close();
                        if(connection != null) connection.close();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                }
            }   else if(src == btReset){
                reset();
            }
        }
    };
    private void reset(){
        tfId.setText("");
        tfPassword.setText("");
        tfName.setText("");
        tfEmail.setText("");
        tfIdUpdate.setText("");
        tfPasswordUpdate.setText("");
        tfNameUpdate.setText("");
        tfEmailUpdate.setText("");
    }
}
