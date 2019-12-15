/*
 * Server.java
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject
                    implements Funcionario {
   private String nome;
   private String cargo;
   private double salario;

  public Server() throws RemoteException {
       super();
  }
	
   public void setNome(String nome) {
       this.nome = nome;
   }
   public String getNome() {
	   return this.nome;
   }
   public void setCargo(String cargo) {
       this.cargo = cargo;
   }
   public void setSalario(double salario) {
       this.salario = salario;
   }
   public double getSalario() {
       return this.salario;
   }
   
   public double calculaReajuste(String nome, String cargo,double salario){
	   setNome(nome);
	   setCargo(cargo);
	   setSalario(salario);
	   double reajuste= 0.0;
	   
	   if (this.cargo.compareTo("operador") == 0){
			reajuste = this.salario*0.2;
			System.out.println("operador reajuste " + reajuste);
	   }
	   if (this.cargo.compareTo("programador")== 0){
			reajuste = salario*0.18;
			System.out.println("programador reajuste "+ reajuste);
	   }
	   salario = salario+reajuste;
	   System.out.println("reajuste %f"+ reajuste);
	   return salario;
   } 
   

    public static void main(String args[]) {

        try {
          Funcionario obj = new Server();
          Registry registry = LocateRegistry.getRegistry("localhost");
	      registry.rebind("Funcionario", obj);
	      System.out.println("funcionario bound in registry");
        } catch (Exception e) {
	      System.out.println("funcionario Server err: " + e.getMessage());
	      e.printStackTrace();
        }
    }
}
