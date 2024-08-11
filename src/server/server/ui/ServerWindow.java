package server.server.ui;

import server.server.domain.ServerController;
import server.server.ui.ServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//класс требуется разделить на GUI, controller и repository (смотри схему проекта)
public class ServerWindow extends JFrame  implements ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    ServerController server;
    private JButton btnStart, btnStop;

    private JTextArea log;

    public ServerWindow(){
        setting();
        createPanel();

        setVisible(true);
    }

    public void setServerController(ServerController server) {
        this.server = server;
    }

    private void setting () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
    }


    public void showMessage(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.setText("");
                server.stop();
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}
