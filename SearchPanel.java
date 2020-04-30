import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class SearchPanel extends JPanel{
    private JLabel lbId;
    private JLabel lbPassword;
    private JLabel lbName;
    private JLabel lbEmail;

    private JTextField tfId;
    private JPasswordField tfPassword;
    private JTextField tfName;
    private JTextField tfEmail;

    private JButton btSearch;
    private JButton btReset;

    private SearchPanel panel;

    public SearchPanel(){
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

        btSearch = new JButton("Search");
        btReset = new JButton("Reset");
        btSearch.addActionListener(actionListener);
        btReset.addActionListener(actionListener);

        add(lbId, JLabel.CENTER);
        add(tfId);
        add(lbPassword, JLabel.CENTER_ALIGNMENT);
        add(tfPassword);
        add(lbName, JLabel.CENTER_ALIGNMENT);
        add(tfName);
        add(lbEmail, JLabel.CENTER_ALIGNMENT);
        add(tfEmail);
        add(btSearch, JLabel.CENTER_ALIGNMENT);
        add(btReset);

        setSize(400,200);
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent event) {
            Object src = event.getSource();
            if(src == btSearch){
                Connection connection = null;
                Statement stmt = null;
                ResultSet rs = null;

                try{
                    connection = DatabaseManager.getConnection();
                    if(connection != null){
                        stmt = connection.createStatement();

                        String sql = "select * from member where id = '" + tfId.getText() +
                                "' and Password = '" + tfPassword.getText() +
                                "'";

                        rs = stmt.executeQuery(sql);

                        if(rs.next()) {
                            tfName.setText(rs.getString("Name"));
                            tfEmail.setText(rs.getString("Email"));
                        }else
                            JOptionPane.showMessageDialog(panel, "Select fail");
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
