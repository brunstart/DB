import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane mainPanel;

    private JoinPanel joinPanel;
    private LoginPanel loginPanel;
    private SearchPanel searchPanel;
    private DeletePanel deletePanel;
    private UpdatePanel updatePanel;

    public MainFrame(){
        joinPanel = new JoinPanel();
        loginPanel = new LoginPanel();
        searchPanel = new SearchPanel();
        deletePanel = new DeletePanel();
        updatePanel = new UpdatePanel();

        mainPanel = new JTabbedPane();
        mainPanel.addTab("JOIN",joinPanel);
        mainPanel.addTab("LOGIN",loginPanel);
        mainPanel.addTab("SELECT",searchPanel);
        mainPanel.addTab("DELETE",deletePanel);
        mainPanel.addTab("UPDATE",updatePanel);

        add(mainPanel, BorderLayout.CENTER);

        setTitle("201413387 양승민");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setVisible(true);
    }
}
