/*
 * Funcionario.java
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Funcionario extends Remote {
    double calculaReajuste(String nome, String cargo,double salario) throws RemoteException;
}
