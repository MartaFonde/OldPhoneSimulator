package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;


/**
 * Menu
 */
// public class Menu extends JFrame implements ActionListener{
//     private JMenuBar mnuPrincipal;
//     private JMenu mnuArchivo, mnuMovil, mnuOtros;
//     private JMenuItem mnuGrabar, mnuLeer, mnuReset, mnuSalir, mnuInfo;
//     Phone p;

//     public Menu(Phone phone) {
//         this.p = phone;
//         mnuGrabar = new JMenuItem("Grabar número");
//         mnuGrabar.setMnemonic('G');
//         mnuGrabar.addActionListener(this);
//         mnuLeer = new JMenuItem("Leer número");
//         mnuLeer.setMnemonic('L');
//         mnuLeer.addActionListener(this);
//         mnuArchivo = new JMenu("Archivo");
//         mnuArchivo.setMnemonic('A');
//         mnuArchivo.add(mnuGrabar);
//         mnuArchivo.add(mnuLeer);

//         mnuReset = new JMenuItem("Reset");
//         mnuReset.setMnemonic('R');
//         mnuReset.addActionListener(this);
//         mnuSalir = new JMenuItem("Salir");
//         mnuSalir.setMnemonic('S');
//         mnuSalir.addActionListener(this);
//         mnuMovil = new JMenu("Móvil");
//         mnuMovil.setMnemonic('M');
//         mnuMovil.add(mnuReset);
//         mnuMovil.addSeparator();
//         mnuMovil.add(mnuSalir);

//         mnuInfo = new JMenuItem("Acerca de...");
//         mnuInfo.setMnemonic('A');
//         mnuInfo.addActionListener(this);
//         mnuOtros = new JMenu("Otros");
//         mnuOtros.setMnemonic('O');
//         mnuOtros.add(mnuInfo);

//         mnuPrincipal = new JMenuBar();
//         mnuPrincipal.add(mnuArchivo);
//         mnuPrincipal.add(mnuMovil);
//         mnuPrincipal.add(mnuOtros);
//         p.setJMenuBar(mnuPrincipal);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {

//         if(e.getSource() == mnuGrabar){
//             try(PrintWriter f = new PrintWriter(new FileWriter("datos/telefonos.txt", true))){
//                 f.println(p.txfPantalla.getText());
//             } catch (IOException excep){
//                 JOptionPane.showMessageDialog(null, "Error al grabar número");
//             }
//         }
//         if(e.getSource() == mnuLeer){
//             String texto="<html><body>";
//             boolean correcto=true;
//             try(Scanner f = new Scanner(new File("datos/telefonos.txt"))){
//                 correcto=true;
//                 while(f.hasNext()){
//                     texto+=f.nextLine()+"<br>";
//                 }
//                 texto+="</body></html>";
//             } catch(IOException excep){
//                 correcto=false;
//                 JOptionPane.showMessageDialog(null, "Error al mostrar números");
//             }
//             if(correcto){
//                 JOptionPane.showMessageDialog(null, texto);
//             }
//         }
//         if(e.getSource() == mnuReset){
//             p.txfPantalla.setText("");
//         }
//         if(e.getSource() == mnuSalir){
//             System.exit(0);
//         } 
//         if(e.getSource() == mnuInfo){
//             JOptionPane.showMessageDialog(this, "Información: \nEsta aplicación simula un teléfono...\ndemás, podrás guardar y leer números. Toma!!!!\nAutor: Antonio Meucci");
//         }
//     }
//}