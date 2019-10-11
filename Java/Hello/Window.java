import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window {
    private JFrame window = null;
    private JMenuBar menuBar = null;
    private JPanel panelLogin = new JPanel();
    private JPanel panelChat = new JPanel();
    private Session session = new Session();

    public Window(String AppName, int width, int height)
    {
        window = new JFrame(AppName);
        window.setSize(width, height);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void login(Database db)
    {
        panelLogin.setLayout(null);
        JLabel labelError = new JLabel();
        labelError.setBounds(0, 0, 200, 15);
        panelLogin.add(labelError);

        JLabel labelName = new JLabel();
        labelName.setText("User");
        labelName.setBounds(85, 40, 46, 15);
        panelLogin.add(labelName);

        JTextField textName = new JTextField();
        textName.setHorizontalAlignment(JTextField.CENTER);
        textName.setBounds(125, 40, 100, 21);
        panelLogin.add(textName);

        JLabel labelPassword = new JLabel();
        labelPassword.setText("Password");
        labelPassword.setBounds(55, 70, 80, 15);
        panelLogin.add(labelPassword);

        JPasswordField fieldPassword = new JPasswordField();
        fieldPassword.setEchoChar('*');
        fieldPassword.setBounds(125, 70, 100, 21);
        panelLogin.add(fieldPassword);

        JButton buttonLogin = new JButton();
        buttonLogin.setText("Login");
        buttonLogin.setBounds(150, 110, 85, 20);
        ActionListener actionLogin = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(db.validate(textName.getText().toString(), new String(fieldPassword.getPassword())))
                {
                    labelError.setText("");
                    panelLogin.setVisible(false);
                    chat(textName.getText().toString(), db);
                }
                else
                {
                    labelError.setText("Wrong username or password.");
                }
            }
        };
        buttonLogin.addActionListener(actionLogin);
        panelLogin.add(buttonLogin);

        JButton buttonRegistry = new JButton();
        buttonRegistry.setText("Registry");
        buttonRegistry.setBounds(50, 110, 85, 20);
        ActionListener actionRegistry = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(db.registry(textName.getText().toString(), new String(fieldPassword.getPassword())))
                {
                    labelError.setText("");
                    panelLogin.setVisible(false);
                    chat(textName.getText().toString(), db);
                }
                else
                {
                    labelError.setText("Username is already have.");
                }
            }
        };
        buttonRegistry.addActionListener(actionRegistry);
        panelLogin.add(buttonRegistry);

        window.add(panelLogin);
        panelLogin.setVisible(true);
        window.setVisible(true);
    }
    
    public void chat(String user, Database db)
    {
        panelChat.setLayout(new BorderLayout());

        // Message history panel
        JScrollPane messageAeraPanel = new JScrollPane();
        JTextArea messageArea = new JTextArea();
        messageAeraPanel.setViewportView(messageArea);
        messageAeraPanel.setVisible(true);
        panelChat.add(messageAeraPanel, BorderLayout.CENTER);

        // Message sending panel
        JPanel messageSendPanel = new JPanel();
        messageSendPanel.setLayout(new BorderLayout());
        JTextField messageField = new JTextField();
        messageSendPanel.add(messageField, BorderLayout.CENTER);
        JButton messageSend = new JButton();
        messageSend.setText("Send");
        ActionListener sendMessage = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String message = messageField.getText().toString();
                messageArea.append("\n>> " + message);
                session.send(user, message);
                messageArea.append("\n" + session.read());
            }
        };
        messageSend.addActionListener(sendMessage);
        messageSendPanel.add(messageSend, BorderLayout.EAST);
        messageSendPanel.setVisible(true);
        panelChat.add(messageSendPanel, BorderLayout.SOUTH);

        // Menu bar
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        // Account menu
        JMenu accountMenu = new JMenu("Account");
        menuBar.add(accountMenu);

        JMenuItem nameItem = new JMenuItem("Change Username");
        accountMenu.add(nameItem);
        ActionListener changeUsername = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String newUsername = JOptionPane.showInputDialog(new JTextArea(), "Enter a new username");
                if (!newUsername.equals("") && !db.changeUsername(user, newUsername))
                {
                    JOptionPane.showMessageDialog(null, "Username is already exist.");
                }
            }
        };
        nameItem.addActionListener(changeUsername);

        JMenuItem passwdItem = new JMenuItem("Change Password");
        accountMenu.add(passwdItem);
        ActionListener changePassword = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String newPasswd = JOptionPane.showInputDialog(new JTextArea(), "Enter a new password");
                if (!newPasswd.equals(""))
                {
                    db.changePassword(user, newPasswd);
                }
            }
        };
        passwdItem.addActionListener(changePassword);

        JMenuItem deleteItem = new JMenuItem("Delete Account");
        accountMenu.add(deleteItem);
        ActionListener deleteAccount = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String confirm = JOptionPane.showInputDialog(new JTextArea(), "Enter your password to delete account.");
                if(!confirm.equals(""))
                {
                    if(db.delete(user, confirm))
                    {
                        logout();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Wrong password.");
                    }
                }
            }
        };
        deleteItem.addActionListener(deleteAccount);

        // Session Menu
        JMenu sessionMenu = new JMenu("Session");
        menuBar.add(sessionMenu);
        JMenuItem receiveItem = new JMenuItem("Receive");
        sessionMenu.add(receiveItem);
        ActionListener sessionReceive = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String temp = JOptionPane.showInputDialog(new JTextArea(), "Receive connection from port.");
                if(!temp.equals(""))
                {
                    try {
                        int port = Integer.parseInt(temp);
                        session.receive(port);
                        JOptionPane.showMessageDialog(null, "Connected.");
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Invalid port.");
                    }
                }
            }
        };
        receiveItem.addActionListener(sessionReceive);

        JMenuItem connectItem = new JMenuItem("Connect");
        sessionMenu.add(connectItem);
        ActionListener connectSession = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String temp = JOptionPane.showInputDialog(new JTextArea(), "Connect from port.");
                if(!temp.equals(""))
                {
                    try {
                        int port = Integer.parseInt(temp);
                        session.connect(port);
                        JOptionPane.showMessageDialog(null, "Connected.");
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Invalid port.");
                    }
                }
            }
        };
        connectItem.addActionListener(connectSession);

        // Exit menu
        JMenu exitMenu = new JMenu("Exit");
        menuBar.add(exitMenu);
        JMenuItem logoutItem = new JMenuItem("Logout");
        exitMenu.add(logoutItem);
        ActionListener actionLogout = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logout();
            }
        };
        logoutItem.addActionListener(actionLogout);

        panelChat.setVisible(true);
        window.add(panelChat);
    }

    public void logout()
    {
        session.close();
        menuBar.setVisible(false);
        panelChat.setVisible(false);
        panelLogin.setVisible(true);
    }
}
