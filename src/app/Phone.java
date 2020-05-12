package app;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.awt.*;

public class Phone extends JFrame implements ActionListener {
    private String[] teclas = { "*", "0", "#" };
    private JButton[] btnTeclas;
    JTextField txfPantalla;
    private JButton btnReset;
    Menu mnu;
    private JMenuBar mnuPrincipal;
    private JMenu mnuArchivo, mnuMovil, mnuOtros;
    private JMenuItem mnuGrabar, mnuLeer, mnuReset, mnuSalir, mnuInfo;

    public Phone() {
        super("MOTOROLA");
        setLayout(null);

        btnTeclas = new JButton[12];
        int x = 70;
        int y = 140;
        for (int i = 0; i < btnTeclas.length; i++) {
            JButton b;
            if (i <= 8) {
                b = new JButton((i + 1) + "");
            } else {
                b = new JButton(teclas[i - 9]);
            }
            b.setSize(50, 50);
            b.setLocation(x, y);
            b.setBackground(Color.LIGHT_GRAY);
            b.addMouseListener(new MouseHandler());
            add(b);
            btnTeclas[i] = b;
            if ((i + 1) % 3 == 0) {
                x = 70;
                y += 50;
            } else {
                x += 50;
            }
        }

        txfPantalla = new JTextField(9);
        txfPantalla.setSize(170, 40);
        txfPantalla.setLocation(60, 20);
        txfPantalla.setEditable(false);
        txfPantalla.addKeyListener(new KeyHandler());
        add(txfPantalla);

        btnReset = new JButton("Reset");
        btnReset.setSize(btnReset.getPreferredSize());
        btnReset.setLocation(160, 80);
        btnReset.addMouseListener(new MouseHandler());
        add(btnReset);

        addKeyListener(new KeyHandler());

        mnuGrabar = new JMenuItem("Grabar número");
        mnuGrabar.setMnemonic('G');
        mnuGrabar.addActionListener(this);
        mnuLeer = new JMenuItem("Leer números");
        mnuLeer.setMnemonic('L');
        mnuLeer.addActionListener(this);
        mnuArchivo = new JMenu("Archivo");
        mnuArchivo.setMnemonic('A');
        mnuArchivo.add(mnuGrabar);
        mnuArchivo.add(mnuLeer);

        mnuReset = new JMenuItem("Reset");
        mnuReset.setMnemonic('R');
        mnuReset.addActionListener(this);
        mnuSalir = new JMenuItem("Salir");
        mnuSalir.setMnemonic('S');
        mnuSalir.addActionListener(this);
        mnuMovil = new JMenu("Móvil");
        mnuMovil.setMnemonic('M');
        mnuMovil.add(mnuReset);
        mnuMovil.addSeparator();
        mnuMovil.add(mnuSalir);

        mnuInfo = new JMenuItem("Acerca de...");
        mnuInfo.setMnemonic('A');
        mnuInfo.addActionListener(this);
        mnuOtros = new JMenu("Otros");
        mnuOtros.setMnemonic('O');
        mnuOtros.add(mnuInfo);

        mnuPrincipal = new JMenuBar();
        mnuPrincipal.add(mnuArchivo);
        mnuPrincipal.add(mnuMovil);
        mnuPrincipal.add(mnuOtros);
        this.setJMenuBar(mnuPrincipal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mnuGrabar) {
            try (PrintWriter f = new PrintWriter(new FileWriter("datos/telefonos.txt", true))) {
                f.println(txfPantalla.getText());
            } catch (IOException excep) {
                JOptionPane.showMessageDialog(this, "Error al grabar número");
            }
        }
        if (e.getSource() == mnuLeer) {
            String texto = "<html><body>";
            boolean correcto = true;
            try (Scanner f = new Scanner(new File("datos/telefonos.txt"))) {
                correcto = true;
                while (f.hasNext()) {
                    texto += f.nextLine() + "<br>";
                }
                texto += "</body></html>";
            } catch (IOException excep) {
                correcto = false;
                JOptionPane.showMessageDialog(this, "Error al mostrar números");
            }
            if (correcto) {
                JOptionPane.showMessageDialog(this, texto);
            }
        }
        if (e.getSource() == mnuReset) {
            resetear();
        }
        if (e.getSource() == mnuSalir) {
            System.exit(0);
        }
        if (e.getSource() == mnuInfo) {
            JOptionPane.showMessageDialog(this,
                    "Información: \nEsta aplicación simula un teléfono/agenda...\nPodrás guardar y leer números. Toma!!!!\nAutor: Antonio Meucci");
        }
    }

    public void resetear() {
        for (int i = 0; i < btnTeclas.length; i++) {
            btnTeclas[i].setBackground(Color.LIGHT_GRAY);
            txfPantalla.setText("");
        }
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            if (((JButton) e.getSource()).getBackground() != Color.MAGENTA && ((JButton) e.getSource()) != btnReset) {
                ((JButton) e.getSource()).setBackground(Color.GRAY);
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            if (((JButton) e.getSource()).getBackground() != Color.MAGENTA && ((JButton) e.getSource()) != btnReset) {
                ((JButton) e.getSource()).setBackground(Color.LIGHT_GRAY);
            }
        }

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (((JButton) e.getSource()) != btnReset) {
                ((JButton) e.getSource()).setBackground(Color.MAGENTA);
                txfPantalla.setText(txfPantalla.getText() + ((JButton) e.getSource()).getText());
            } else {
                resetear();
            }
            requestFocus();
        }
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyTyped(java.awt.event.KeyEvent e) {
            for (int i = 0; i < teclas.length + 9; i++) {
                if (e.getKeyChar() == btnTeclas[i].getText().charAt(0)) {
                    btnTeclas[i].setBackground(Color.magenta);
                    txfPantalla.setText(txfPantalla.getText() + e.getKeyChar());
                }
            }
        }
    }

}