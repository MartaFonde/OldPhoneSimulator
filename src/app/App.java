package app;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        Phone p = new Phone();
        p.setSize(300,400);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
    }
}