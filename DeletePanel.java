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


public class DeletePanel extends JPanel {

    private JLabel lbId;
    private JLabel lbPassword;
    private JLabel lbName;
    private JLabel lbEmail;

    private JTextField tfId;
    private JPasswordField tfPassword;
    private JTextField tfName;
    private JTextField tfEmail;

    private JButton btDelete;
    private JButton btReset;

    private DeletePanel panel;

    public DeletePanel(){

        panel = this;
        setLayout(new GridLayout(5,2));

        lbId = new JLabel("ID : ", JLabel.CENTER);
        lbPassword = new JLabel("Password: ", JLabel.CENTER);
        lbName = new JLabel("Name : ",JLabel.CENTER);
        lbEmail = new JLabel("E-mail : ",JLabel.CENTER);

        tfId = new JTextField();
        tfPassword = new JPasswordField();
        tfName = new JTextField();
        tfEmail = new JTextField();

        btDelete = new JButton("Delete");
        btReset = new JButton("Reset");
        btDelete.addActionListener(actionListener);
        btReset.addActionListener(actionListener);

        add(lbId, JLabel.CENTER);
        add(tfId);
        add(lbPassword, JLabel.CENTER_ALIGNMENT);
        add(tfPassword);
        add(lbName, JLabel.CENTER_ALIGNMENT);
        add(tfName);
        add(lbEmail, JLabel.CENTER_ALIGNMENT);
        add(tfEmail);
        add(btDelete, JLabel.CENTER_ALIGNMENT);
        add(btReset);

        setSize(400,200);
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent event) {
            Object src = event.getSource();
            if(src == btDelete){
                Connection connection = null;
                Statement stmt = null;
                ResultSet rs = null;

                try{
                    connection = DatabaseManager.getConnection();
                    if(connection != null){
                        stmt = connection.createStatement();

                        String sql = "delete from member where id = '" + tfId.getText() +
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
    }
}
