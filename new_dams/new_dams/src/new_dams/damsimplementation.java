package new_dams;

import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class damsimplementation extends UnicastRemoteObject implements inter{
	protected damsimplementation() throws RemoteException {
		super();
	}
	public static Logger logger = Logger.getLogger("logs");
//	ArrayList<Appointments> arr_app = new ArrayList<Appointments>();

	//MAIN HASH
	//HashMap<String,List> hash_map = new HashMap<String,List>();
	
	//arraylist for patientID
	ArrayList<Appointments> m_patient_array = new ArrayList<Appointments>();
	ArrayList<Appointments> q_patient_array = new ArrayList<Appointments>();
	ArrayList<Appointments> s_patient_array = new ArrayList<Appointments>();

	//3 server hashmaps
	HashMap<String,List> m_hash_map = new HashMap<String,List>();
	HashMap<String,List> q_hash_map = new HashMap<String,List>();
	HashMap<String,List> s_hash_map = new HashMap<String,List>();
	public String swapAppointment (String patientID, String oldAppointmentID, int oldAppointmentType, String newAppointmentID,
								 String newAppointmentType) throws RemoteException {
		String output = "";
		if(patientID.contains("MTL")) {
			ArrayList<String> get_array = new ArrayList<String>();

			for (int i = 0; i < m_patient_array.size(); i++) {
				//	System.out.println(m_patient_array.get(i));
				if(m_patient_array.get(i).getpatientId().equals(patientID))
				{
					get_array.add(m_patient_array.get(i).getappointmentId());
					//System.out.println(m_patient_array.get(i).getappointmentId());
				}
			}
			if(get_array.contains(oldAppointmentID)) {
				System.out.println("CANCELLING OLD APPOINTMENT");
				cancelAppointment( patientID, oldAppointmentID,oldAppointmentType);

				System.out.println("BOOKING NEW APPOINTMENT");
				System.out.println(patientID+newAppointmentID+newAppointmentType);
				//bookOptions(patientID);
				bookAppointment(patientID,newAppointmentID,newAppointmentType);
			}
			getAppointmentSchedule(patientID);
			output = "Appointment swapped from "+oldAppointmentID+" to "+newAppointmentID;



		}
		//QUEBEC
		if(patientID.contains("QUE")) {
			ArrayList<String> get_array = new ArrayList<String>();

			for (int i = 0; i < q_patient_array.size(); i++) {
				//	System.out.println(m_patient_array.get(i));
				if(q_patient_array.get(i).getpatientId().equals(patientID))
				{
					get_array.add(q_patient_array.get(i).getappointmentId());
					//System.out.println(m_patient_array.get(i).getappointmentId());
				}
			}
			if(get_array.contains(oldAppointmentID)) {
				System.out.println("CANCELLING OLD APPOINTMENT");
				cancelAppointment( patientID, oldAppointmentID,oldAppointmentType);

				System.out.println("BOOKING NEW APPOINTMENT");

				bookOptions(patientID);
			}
			getAppointmentSchedule(patientID);
			output = "Appointment swapped from "+oldAppointmentID+" to "+newAppointmentID;


		}
		//SHERBROOKE
		if(patientID.contains("SHE")) {
			ArrayList<String> get_array = new ArrayList<String>();

			for (int i = 0; i < s_patient_array.size(); i++) {
				//	System.out.println(m_patient_array.get(i));
				if(s_patient_array.get(i).getpatientId().equals(patientID))
				{
					get_array.add(s_patient_array.get(i).getappointmentId());
					//System.out.println(m_patient_array.get(i).getappointmentId());
				}
			}
			if(get_array.contains(oldAppointmentID)) {
				System.out.println("CANCELLING OLD APPOINTMENT");
				cancelAppointment( patientID, oldAppointmentID,oldAppointmentType);

				System.out.println("BOOKING NEW APPOINTMENT");

				bookOptions(patientID);
			}
			getAppointmentSchedule(patientID);
			output = "Appointment swapped from "+oldAppointmentID+" to "+newAppointmentID;

		}
		return output;

	}
	@Override
	public int verifyUser(String id) throws RemoteException {
		if(id.contains("MTLP"))
		{
			return 1;
		}
		else if(id.contains("QUEP"))
		{
			return 2;

		}
		else if(id.contains("SHEP"))
		{
			return 3;

		}
		else if(id.contains("MTLA"))
		{
			return 4;

		}
		else if(id.contains("QUEA"))
		{
			return 5;

		}
		else if(id.contains("SHEA"))
		{
			return 6;

		}
		else
		{
			return 7;
		}
		
	}

	@Override
	public int adminOptions(String id,int x1) throws RemoteException {
		// TODO Auto-generated method stub
		int op = x1;

        if(op==1)
        {
            return 1;

        }
        else if(op==2)
        {
            return 2;

        }
        else if(op==3)
        {
            return 3;

        }
        else if(op==4){
        	return 4;
        }
        return 0;
	}

	@Override
	public void addOptions(String id,int apTy,String apId,int c) throws RemoteException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String doc[] = {"Physician","Surgeon","Dental"};
		Appointments ap= new Appointments();
		System.out.println(addAppointment(apId,doc[apTy-1],c));


		//System.out.println("Appointment Added ");
		try
		{
			FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
			logger.addHandler(fh);
			SimpleFormatter f= new SimpleFormatter();
			fh.setFormatter(f);
			logger.info("Appointment Added in Montreal Server : "+apId);
		}
		catch(Exception e)
		{
			//logger.log(Level.WARNING,"Exception",e);
		}

	}

	public String addAppointment(String appointmentId,String apTy,int c) throws RemoteException{

		String output = "";

		//MONTREAL
		if(appointmentId.contains("MTL")) {
			HashMap<String,Integer> sub = new HashMap<String,Integer>();
			ArrayList<HashMap> sublist;
			sub.put(appointmentId, c);
			if(m_hash_map.containsKey(apTy)) {
				sublist = (ArrayList<HashMap>) m_hash_map.get(apTy);
				sublist.add(sub);
				
			}
			else {
				sublist = new ArrayList<HashMap>();
				sublist.add(sub);
			}
					
			m_hash_map.put(apTy, sublist);

			System.out.println(m_hash_map);
			output="Appointment created for "+appointmentId+" with capacity:"+c;

		}
		//QUEBEC
		if(appointmentId.contains("QUE")) {
			HashMap<String,Integer> sub = new HashMap<String,Integer>();
			ArrayList<HashMap> sublist;
			sub.put(appointmentId, c);
			if(q_hash_map.containsKey(apTy)) {
				sublist = (ArrayList<HashMap>) q_hash_map.get(apTy);
				sublist.add(sub);
				
			}
			else {
				sublist = new ArrayList<HashMap>();
				sublist.add(sub);
			}
					
			q_hash_map.put(apTy, sublist);

			System.out.println(q_hash_map);
		//	String output;
			output="Appointment created for "+appointmentId+" with capacity:"+c;
			//return output;
		}
		//SHERBROOKE
		if(appointmentId.contains("SHE")) {
			HashMap<String,Integer> sub = new HashMap<String,Integer>();
			ArrayList<HashMap> sublist;
			sub.put(appointmentId, c);
			if(s_hash_map.containsKey(apTy)) {
				sublist = (ArrayList<HashMap>) s_hash_map.get(apTy);
				sublist.add(sub);
				
			}
			else {
				sublist = new ArrayList<HashMap>();
				sublist.add(sub);
			}
					
			s_hash_map.put(apTy, sublist);

			System.out.println(s_hash_map);
			//String output;
			output="Appointment created for "+appointmentId+" with capacity:"+c;
		//	System.out.println(output);
		}
		//System.out.println(output);
		return output;
	}
	
		
		
	public void removeOptions(String appointmentId,String[] doc,Integer ap_type) throws RemoteException {
//		String doc[] = {"Physician","Surgeon","Dental"};
//		Scanner sc = new Scanner(System.in);
//		int ap_type;
//		System.out.println("Enter Appointment Type  ");
//		System.out.println("1. Physician");
//		System.out.println("2. Surgeon ");
//		System.out.println("3. Dental");
//		ap_type = sc.nextInt();
//		System.out.println("Enter Appointment ID:");
//		String ap_id;
//
//		ap_id = sc.next();

		//System.out.println(removeAppointment(appointmentId,doc[ap_type-1]));
	}
public void listOptions(String appointmentId) throws RemoteException {
		String doc[] = {"Physician","Surgeon","Dental"};
		Scanner sc = new Scanner(System.in);
		int ap_type;
		System.out.println("Enter Appointment Type  ");
		System.out.println("1. Physician");
		System.out.println("2. Surgeon ");
		System.out.println("3. Dental");
		ap_type = sc.nextInt();
		
		
		
		System.out.println(listAppointment(doc[ap_type-1],appointmentId));
	}
	public String listAppointment(String apTy,String appointmentId) throws RemoteException{
	//MONTREAL
		String output="";
		if(appointmentId.contains("MTL")) {
			//display all appointments
			ArrayList<HashMap> list_small = new ArrayList<HashMap>();
			if(m_hash_map.containsKey(apTy)) {
				list_small = (ArrayList<HashMap>) m_hash_map.get(apTy);
				
			}
		//	System.out.println(apTy+" "+list_small);
			output=apTy+" "+list_small;
			try
			{
				FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
				logger.addHandler(fh);
				SimpleFormatter f= new SimpleFormatter();
				fh.setFormatter(f);
				logger.info("Appointment Listed in Montreal Server : "+list_small);
			}
			catch(Exception e)
			{
				//logger.log(Level.WARNING,"Exception",e);
			}
		}
	//QUEBEC
		if(appointmentId.contains("QUE")) {
			//display all appointments
			ArrayList<HashMap> list_small = new ArrayList<HashMap>();
			if(q_hash_map.containsKey(apTy)) {
				list_small = (ArrayList<HashMap>) q_hash_map.get(apTy);
				
			}
			//System.out.println(apTy+" "+list_small);
			output=apTy+" "+list_small;

			try
			{
				FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
				logger.addHandler(fh);
				SimpleFormatter f= new SimpleFormatter();
				fh.setFormatter(f);
				logger.info("Appointment Listed in Quebec Server : "+list_small);
			}
			catch(Exception e)
			{
				//logger.log(Level.WARNING,"Exception",e);
			}
		}
	//Sherbrooke
		if(appointmentId.contains("SHE")) {
			//display all appointments
			ArrayList<HashMap> list_small = new ArrayList<HashMap>();
			if(s_hash_map.containsKey(apTy)) {
				list_small = (ArrayList<HashMap>) s_hash_map.get(apTy);
				
			}
			//System.out.println(apTy+" "+list_small);
			output=apTy+" "+list_small;

			try
			{
				FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
				logger.addHandler(fh);
				SimpleFormatter f= new SimpleFormatter();
				fh.setFormatter(f);
				logger.info("Appointment Listed in Sherbrooke Server : "+list_small);
			}
			catch(Exception e)
			{
				//logger.log(Level.WARNING,"Exception",e);
			}
		}
		return output;
}
public String removeAppointment(String appointmentId,String apTy) throws RemoteException{
	String output="";
	//MONTREAL
	if(appointmentId.contains("MTL")) {
		if(m_hash_map.containsKey(apTy)) {
			
			ArrayList<HashMap> list =(ArrayList<HashMap>) m_hash_map.get(apTy);
		//	System.out.println(list);
			int flag =0;
			while(list.size() > flag) {
				if(list.get(flag).containsKey(appointmentId)) 
				{
					list.get(flag).remove(appointmentId);
					
	
				}
				flag = flag + 1;
			}
			//System.out.println(list);
			output=list+"\nAppointment removed for ID: "+appointmentId;
		}
		try
		{
			FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
			logger.addHandler(fh);
			SimpleFormatter f= new SimpleFormatter();
			fh.setFormatter(f);
			logger.info("Appointment Removed from Montreal Server : "+appointmentId);
		}
		catch(Exception e)
		{
			//logger.log(Level.WARNING,"Exception",e);
		}
		
	}
	
	//QUEBEC
		if(appointmentId.contains("QUE")) {
			if(q_hash_map.containsKey(apTy)) {
				
				ArrayList<HashMap> list =(ArrayList<HashMap>) q_hash_map.get(apTy);
				System.out.println(list);
				int flag =0;
				while(list.size()!=flag) {
					if(list.get(flag).containsKey(appointmentId)) 
					{
						list.get(flag).remove(appointmentId);
						
		
					}
					flag = flag + 1;
				}
				System.out.println(list);
				output="Appointment removed for ID: "+appointmentId;

			}
			try
			{
				FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
				logger.addHandler(fh);
				SimpleFormatter f= new SimpleFormatter();
				fh.setFormatter(f);
				logger.info("Appointment Removed from Quebec Server : "+appointmentId);
			}
			catch(Exception e)
			{
				//logger.log(Level.WARNING,"Exception",e);
			}
			
		}
	//SHERBROOKE
		if(appointmentId.contains("SHE")) {
			if(s_hash_map.containsKey(apTy)) {
				
				ArrayList<HashMap> list =(ArrayList<HashMap>) s_hash_map.get(apTy);
				System.out.println(list);
				int flag =0;
				while(list.size()!=flag) {
					if(list.get(flag).containsKey(appointmentId)) 
					{
						list.get(flag).remove(appointmentId);
						
		
					}
					flag = flag + 1;
				}
				System.out.println(list);
				output="Appointment removed for ID: "+appointmentId;

			}
			try
			{
				FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
				logger.addHandler(fh);
				SimpleFormatter f= new SimpleFormatter();
				fh.setFormatter(f);
				logger.info("Appointment Removed from Sherbrooke Server : "+appointmentId);
			}
			catch(Exception e)
			{
				//logger.log(Level.WARNING,"Exception",e);
			}
		}
		return output;
}

//BOOKING FOR PATIENT
public void bookOptions(String id) throws RemoteException{
	Scanner sc=new Scanner(System.in);
	String[] type = {"Physician", "Surgeon", "Dental"};
	String[] slot = {"M", "A", "E"};


	System.out.println("1. Physician");
	System.out.println("2. Surgeon");
	System.out.println("3. Dental");
	System.out.println("Enter Appointment Type :");


	int t=sc.nextInt();

	System.out.println("1. Morning");
	System.out.println("2. Afternoon");
	System.out.println("3. Evening");
	System.out.println("Enter Slot for Appointment :");

	int s=sc.nextInt();

	System.out.println("Enter Date of appointment in ddmmyy format :");
	String d=sc.next();


	String appointmentId=id.substring(0,3)+slot[s-1]+d;

	if(bookAppointment(id,appointmentId,type[t-1]).equals(true))
	{
		System.out.println("Appointment Booked! Your appointment ID is "+appointmentId);
		try
		{
			FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
			logger.addHandler(fh);
			SimpleFormatter f= new SimpleFormatter();
			fh.setFormatter(f);
			logger.info("Appointment Booked : "+appointmentId);
		}
		catch(Exception e)
		{
			//logger.log(Level.WARNING,"Exception",e);
		}
	}
	else {
		System.out.println("Enter proper Appointment ID");
	}

}

//BOOKING FOR PATIENT - CONTI

public String bookAppointment(String patient_id, String appointmentId, String appointmentType) throws RemoteException{
	// TODO Auto-generated method stub
	Appointments ap= new Appointments();

	//MONTREAL
	if(appointmentId.contains("MTL")) {
		ap.setpatientId(patient_id);
		ap.setappointmentId(appointmentId);
		ap.setappointmentType(appointmentType);
		
		ArrayList<HashMap> list;
		
		
		if(m_hash_map.containsKey(appointmentType)) {
			System.out.println("first IF");
			list =(ArrayList<HashMap>) m_hash_map.get(appointmentType);
			//System.out.println(list);
			int flag =0;
			while(list.size() > flag) {
				if(list.get(flag).containsKey(appointmentId)) 
				{
					//System.out.println(list);
					int temp = (int) list.get(flag).get(appointmentId);
					if(temp > 0) {
						list.get(flag).replace(appointmentId, temp-1);
						m_patient_array.add(ap);
						System.out.println(list);
						return "true";
					}

				}
				else {
					System.out.println("appointment full");
				}
				flag = flag + 1;
			}
			
		}
		else
		{
			return "false";
		}

		return "false";
	}
	
	//QUEBEC
	 if(appointmentId.contains("QUE")) {
		ap.setpatientId(patient_id);
		ap.setappointmentId(appointmentId);
		ap.setappointmentType(appointmentType);
		
		ArrayList<HashMap> list;
		
		
		if(q_hash_map.containsKey(appointmentType)) {
			System.out.println("first IF");
			list =(ArrayList<HashMap>) q_hash_map.get(appointmentType);
			//System.out.println(list);
			int flag =0;
			while(list.size()!=flag) {
				if(list.get(flag).containsKey(appointmentId)) 
				{
					//System.out.println(list);
					int temp = (int) list.get(flag).get(appointmentId);
					list.get(flag).replace(appointmentId, temp-1);
					q_patient_array.add(ap);
					System.out.println(list);
					return "true";

				}
				flag = flag + 1;
			}
			
		}
		else
		{
			return "false";
		}

		return "false";
	}
	
	//SHERBROOKE
	 if(appointmentId.contains("SHE")) {
		ap.setpatientId(patient_id);
		ap.setappointmentId(appointmentId);
		ap.setappointmentType(appointmentType);
		
		ArrayList<HashMap> list;
		
		
		if(s_hash_map.containsKey(appointmentType)) {
			System.out.println("first IF");
			list =(ArrayList<HashMap>) s_hash_map.get(appointmentType);
			//System.out.println(list);
			int flag =0;
			while(list.size()!=flag) {
				if(list.get(flag).containsKey(appointmentId)) 
				{
					//System.out.println(list);
					int temp = (int) list.get(flag).get(appointmentId);
					list.get(flag).replace(appointmentId, temp-1);
					s_patient_array.add(ap);
					System.out.println(list);
					return "true";

				}
				flag = flag + 1;
			}
			
		}
		else
		{
			return "false";
		}

		return "false";
	}
	return "false";

}
public String getAppointmentSchedule (String patient_id) throws RemoteException{
	//MONTREAL
	String output="";
	if(patient_id.contains("MTL")) {
		ArrayList<String> get_array = new ArrayList<String>();
	
		for (int i = 0; i < m_patient_array.size(); i++) {
		//	System.out.println(m_patient_array.get(i));
			if(m_patient_array.get(i).getpatientId().equals(patient_id))
			{
				get_array.add(m_patient_array.get(i).getappointmentId());
				//System.out.println(m_patient_array.get(i).getappointmentId());
			}
		}
		System.out.println(get_array);
		output = "Your Appointments:-\n"+get_array;

	}
	//QUEBEC
	 if(patient_id.contains("QUE")) {
			ArrayList<String> get_array = new ArrayList<String>();
			
			for (int i = 0; i < q_patient_array.size(); i++) {
			//	System.out.println(m_patient_array.get(i));
				if(q_patient_array.get(i).getpatientId().equals(patient_id))
				{
					get_array.add(q_patient_array.get(i).getappointmentId());
					//System.out.println(m_patient_array.get(i).getappointmentId());
				}
			}
			System.out.println(get_array);
		}
		//SHERBROOKE
	if(patient_id.contains("SHE")) {
			ArrayList<String> get_array = new ArrayList<String>();
			
			for (int i = 0; i < s_patient_array.size(); i++) {
			//	System.out.println(m_patient_array.get(i));
				if(s_patient_array.get(i).getpatientId().equals(patient_id))
				{
					get_array.add(s_patient_array.get(i).getappointmentId());
					//System.out.println(m_patient_array.get(i).getappointmentId());
				}
			}
			System.out.println(get_array);
		}
	return output;
	}
public String cancelAppointment (String patient_id, String appointment_id,int t) throws RemoteException{
	//MONTREAL
	String output="";
	if(appointment_id.contains("MTL")) {
		String[] type = {"Physician", "Surgeon", "Dental"};
		//String[] slot = {"M", "A", "E"};

		Scanner sc = new Scanner(System.in);
//		System.out.println("1. Physician");
//		System.out.println("2. Surgeon");
//		System.out.println("3. Dental");
//		System.out.println("Enter Appointment Type :");

		ArrayList<String> app_now = new ArrayList <String>();
		for(int i = 0;i < m_patient_array.size();i++)
		{
			if(m_patient_array.get(i).getpatientId().equals(patient_id)) {
				app_now.add(m_patient_array.get(i).getappointmentId());			}
		}
		System.out.println(app_now);
		//int t=sc.nextInt();
		String phy_type = type[t-1];
		for (int i = 0; i < m_patient_array.size(); i++) {
			String pat_id = m_patient_array.get(i).getpatientId();
			String app_id = m_patient_array.get(i).getappointmentId();
			if(pat_id.equals(patient_id) && app_id.equals(appointment_id) )
			{
				ArrayList<HashMap> list = (ArrayList<HashMap>) m_hash_map.get(type[t-1]);		

			//	list =(ArrayList<HashMap>) 
				int flag=0;
				while(list.size()!=flag)
				{
					if(list.get(flag).containsKey(appointment_id))
					{
						int n=((int) list.get(flag).get(appointment_id));
						list.get(flag).replace(appointment_id, n+1);
						System.out.println("old list "+list.get(flag));
						

					}
					flag=flag+1;
				}
				m_patient_array.remove(i);
				try
				{
					FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
					logger.addHandler(fh);
					SimpleFormatter formatter= new SimpleFormatter();
					fh.setFormatter(formatter);
					logger.info("Appointment Canceled in Montreal Server : "+appointment_id);
				}
				catch(Exception e)
				{
					//logger.log(Level.WARNING,"Exception",e);
				}
				System.out.println("Appointment "+app_id+" Canceled");
				output = "Appointment "+app_id+" Canceled";
			}
		}

		
	}
	//QUEBEC
		if(appointment_id.contains("QUE")) {
			String[] type = {"Physician", "Surgeon", "Dental"};
//			//String[] slot = {"M", "A", "E"};
//
//			Scanner sc = new Scanner(System.in);
//			System.out.println("1. Physician");
//			System.out.println("2. Surgeon");
//			System.out.println("3. Dental");
//			System.out.println("Enter Appointment Type :");
//
//
//			int t=sc.nextInt();
			String phy_type = type[t-1];
			for (int i = 0; i < q_patient_array.size(); i++) {
				String pat_id = q_patient_array.get(i).getpatientId();
				String app_id = q_patient_array.get(i).getappointmentId();
				if(pat_id.equals(patient_id) && app_id.equals(appointment_id) )
				{
					ArrayList<HashMap> list = (ArrayList<HashMap>) q_hash_map.get(type[t-1]);		

				//	list =(ArrayList<HashMap>) 
					int flag=0;
					while(list.size()!=flag)
					{
						if(list.get(flag).containsKey(appointment_id))
						{
							int n=((int) list.get(flag).get(appointment_id));
							list.get(flag).replace(appointment_id, n+1);
							System.out.println("old list "+list.get(flag));
							

						}
						flag=flag+1;
					}
					q_patient_array.remove(i);
					try
					{
						FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
						logger.addHandler(fh);
						SimpleFormatter formatter= new SimpleFormatter();
						fh.setFormatter(formatter);
						logger.info("Appointment Canceled in Quebec Server : "+appointment_id);
					}
					catch(Exception e)
					{
						//logger.log(Level.WARNING,"Exception",e);
					}
					System.out.println("Appointment "+app_id+" Canceled");
					output = "Appointment "+app_id+" Canceled";

				}
			}
		}
		//SHERBROOKE
		if(appointment_id.contains("SHE")) {
			String[] type = {"Physician", "Surgeon", "Dental"};
			//String[] slot = {"M", "A", "E"};

//			Scanner sc = new Scanner(System.in);
//			System.out.println("1. Physician");
//			System.out.println("2. Surgeon");
//			System.out.println("3. Dental");
//			System.out.println("Enter Appointment Type :");
//
//
//			int t=sc.nextInt();
			String phy_type = type[t-1];
			for (int i = 0; i < s_patient_array.size(); i++) {
				String pat_id = s_patient_array.get(i).getpatientId();
				String app_id = s_patient_array.get(i).getappointmentId();
				if(pat_id.equals(patient_id) && app_id.equals(appointment_id) )
				{
					ArrayList<HashMap> list = (ArrayList<HashMap>) s_hash_map.get(type[t-1]);		

				//	list =(ArrayList<HashMap>) 
					int flag=0;
					while(list.size()!=flag)
					{
						if(list.get(flag).containsKey(appointment_id))
						{
							int n=((int) list.get(flag).get(appointment_id));
							list.get(flag).replace(appointment_id, n+1);
							System.out.println("old list "+list.get(flag));
							

						}
						flag=flag+1;
					}
					s_patient_array.remove(i);
					try
					{
						FileHandler fh= new FileHandler("C:\\Users\\Raahul John\\Documents\\LOGS\\logfile.log",true);
						logger.addHandler(fh);
						SimpleFormatter formatter= new SimpleFormatter();
						fh.setFormatter(formatter);
						logger.info("Appointment Canceled in Sherbrooke Server : "+appointment_id);
					}
					catch(Exception e)
					{
						//logger.log(Level.WARNING,"Exception",e);
					}
					System.out.println("Appointment "+app_id+" Canceled");
					output = "Appointment "+app_id+" Canceled";

				}
			}
		}
	return output;
}
}
