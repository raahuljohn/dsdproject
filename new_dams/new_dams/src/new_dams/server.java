package new_dams;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class server {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException{
		// TODO Auto-generated method stub
		//damsimplementation dam = new damsimplementation();
		inter i = new damsimplementation();
        Registry m_registry = LocateRegistry.createRegistry(8002);
        m_registry.bind("m_verify", i);
        System.out.println(" MServer is started...");
        Registry q_registry = LocateRegistry.createRegistry(8003);
        q_registry.bind("q_verify", i);
        System.out.println(" QServer is started...");
        Registry s_registry = LocateRegistry.createRegistry(8004);
        s_registry.bind("s_verify", i);
        System.out.println(" SServer is started...");

	}

}
