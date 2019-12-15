/*
 * Aplicativo.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class Aplicativo extends JFrame{

   private JLabel nomeLabel;
   private JLabel cargoLabel;
   private JLabel salarioLabel;
   private JTextField nomeField,cargoField,salarioField, mensagemField, mensagemField2;
   private JButton reajusteButton;

   public Aplicativo(String t)
   {
      super(t);

      Container cont = getContentPane();
      cont.setLayout( new FlowLayout() );
      JPanel c = new JPanel();
      c.setLayout( new GridLayout(3,1) );
      JPanel c2 = new JPanel();
      c2.setLayout( new GridLayout(4,4) );
      nomeLabel = new JLabel( "Nome:" );
      nomeField = new JTextField( 10 );
      
      cargoLabel = new JLabel( "Cargo:" );
      cargoField = new JTextField( 10 );
      
      salarioLabel = new JLabel( "Salario:" );
      salarioField = new JTextField( 10 );
      
      mensagemField = new JTextField( 30 );
      mensagemField2 = new JTextField( 30 );
      mensagemField.setEditable(false);
      mensagemField2.setEditable(false);
      reajusteButton = new JButton("Calcula Reajuste");
      reajusteButton.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent e ){
			  String nome = nomeField.getText();
			  String cargo = cargoField .getText(); 
              double salario = Double.parseDouble(salarioField .getText());
              Funcionario obj = null;
              try {
                  Registry registry = LocateRegistry.getRegistry("localhost");                   
                  obj = (Funcionario) registry.lookup("Funcionario");
                  salario = obj.calculaReajuste(nome,cargo,salario);
                  mensagemField.setText("O salario reajustado e de : " + salario );
             
              } catch (Exception ex) {
                  System.out.println("Aplicativo exception: " +
                                          ex.getMessage());
                  ex.printStackTrace();
              }

            }
         }
      );

      c2.add( nomeLabel );
      c2.add( nomeField );
      
      c2.add( cargoLabel );
      c2.add( cargoField );
      
      c2.add( salarioLabel );
      c2.add( salarioField );
      
      c.add( reajusteButton );
      c.add( mensagemField );
      //c.add( mensagemField2 );
      cont.add(c2);
      cont.add(c);
   }


   public static void main(String[] args) {

        Aplicativo frame = new Aplicativo("Calcula reajuste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,250);
        frame.setVisible(true);
    }
}


