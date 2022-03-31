package new_dams;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface inter extends Remote{


	 public int verifyUser(String id) throws RemoteException;
	 public int adminOptions(String id,int x1) throws RemoteException;
	 public void addOptions(String id,int apTy,String apId,int c)throws RemoteException;
	 //public void removeOptions(String appointmentId,String[] doc,Integer ap_type) throws RemoteException;
	 public String removeAppointment(String appointmentId,String apTy) throws RemoteException;
	 public String listAppointment(String apTy,String appointmentId) throws RemoteException;
	 //public void listOptions(String id) throws RemoteException;
	 //public int patientOptions(String id) throws RemoteException;
	//public void bookOptions(String id) throws RemoteException;
	public String bookAppointment(String patient_id, String appointmentId, String appointmentType) throws RemoteException;
	public String getAppointmentSchedule (String patient_id) throws RemoteException;
	public String cancelAppointment (String patient_id, String appointment_id,int t)  throws RemoteException;
	public String swapAppointment (String patientID, String oldAppointmentID, int oldAppointmentType, String newAppointmentID,
						  String newAppointmentType) throws RemoteException;

}
