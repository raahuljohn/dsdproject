package new_dams;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.*;



public class patientclient {
	public static Logger logger = Logger.getLogger("logs");

	public static int patientOptions(String id) throws RemoteException {
		// TODO Auto-generated method stub
		Scanner x=new Scanner(System.in);
		System.out.println("Welcome to "+id);
		System.out.println("1. Book Appointment");
		System.out.println("2. Get Appointment ");
		System.out.println("3. Cancel Appointment");
		System.out.println("4. Swap Appointment");
		System.out.println("Enter 5 to exit");
		System.out.println("Enter your Option : ");

		int op=x.nextInt();

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
		else if(op==5){
			return 5;
		}
		return 0;
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		Registry m_registry = LocateRegistry.getRegistry(8002);
		Registry q_registry = LocateRegistry.getRegistry(8003);
		Registry s_registry = LocateRegistry.getRegistry(8004);

        Scanner x = new Scanner(System.in);
       

        
        System.out.println("Welcome Patient.");
        System.out.println("Enter your ID : ");
        String id=x.next();
      //  System.out.println(id);
        //MONTREAL
        if(id.contains("MTL")) {
        	 inter i=(inter) m_registry.lookup("m_verify");
        	 int code=i.verifyUser(id);
          
             if(code == 7)
             {
            	 System.out.println("Invalid ID");
             }
             
             String province[] = {"MONTREAL","QUEBEC","SHERBROOKE"};
             System.out.println(province[code-1]);
            
             int do_while=0;
             //size limit applies here
             do {
             	int patient_op = patientOptions(province[code-1]);
             	switch(patient_op) {
             	case 1:
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

					if(i.bookAppointment(id,appointmentId,type[t-1]).equals("true"))
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

				//	i.bookOptions(id);
             		break;
             	case 2:
             		System.out.println(i.getAppointmentSchedule(id));
             		break;
             	case 3:
             		//Scanner x = new Scanner(System.in);
             		System.out.println("Enter the Appointment ID:");
             		String appoint_id;
             		appoint_id = x.next();
					//String[] type = {"Physician", "Surgeon", "Dental"};
					//String[] slot = {"M", "A", "E"};

					//Scanner sc = new Scanner(System.in);
					System.out.println("1. Physician");
					System.out.println("2. Surgeon");
					System.out.println("3. Dental");
					System.out.println("Enter Appointment Type :");
					int t1=x.nextInt();

					System.out.println(i.cancelAppointment(id,appoint_id,t1));
             		break;

				 case 4:
					 //swap function call
					 //String[] type = {"Physician", "Surgeon", "Dental"};

					 String patient_id,new_app_id,old_app_id,new_app_type;
					 Integer old_app_type;
					 System.out.println("Enter the Patient ID:");
					 patient_id = x.next();
					 System.out.println("Enter the Old Appointment Type:");
					 System.out.println("1. Physician");
					 System.out.println("2. Surgeon");
					 System.out.println("3. Dental");
					 old_app_type = x.nextInt();
					 System.out.println("Enter the Old Appointment ID:");
					 old_app_id = x.next();
					 System.out.println("Type the New Appointment Type:");

					 new_app_type = x.next();
					 System.out.println("Enter the New Appointment ID:");
					 new_app_id = x.next();
					 System.out.println(i.swapAppointment(patient_id,old_app_id, old_app_type, new_app_id, new_app_type));
					 break;


             	case 5:
             		do_while=5;
             		break;
             	
             	
             	}
             }while(do_while!=5);
             System.out.println("Goodbye.");

     	}
        //QUEBEC
        if(id.contains("QUE")) {
        	 inter i=(inter) q_registry.lookup("q_verify");
        	 int code=i.verifyUser(id);
        	  //  System.out.println(code);
             if(code == 7)
             {
            	 System.out.println("Invalid ID");
             }             
             String province[] = {"MONTREAL","QUEBEC","SHERBROOKE"};
             System.out.println(province[code-1]);
            
             int do_while=0;
             //size limit applies here
             do {
             	int patient_op = patientOptions(province[code-1]);
             	switch(patient_op) {
             	case 1:
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

					if(i.bookAppointment(id,appointmentId,type[t-1]).equals("true"))
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

					//	i.bookOptions(id);
					break;
             	case 2:
             		i.getAppointmentSchedule(id);
             		break;
             	case 3:
					//Scanner x = new Scanner(System.in);
					System.out.println("Enter the Appointment ID:");
					String appoint_id;
					appoint_id = x.next();
					//String[] type = {"Physician", "Surgeon", "Dental"};
					//String[] slot = {"M", "A", "E"};

					//Scanner sc = new Scanner(System.in);
					System.out.println("1. Physician");
					System.out.println("2. Surgeon");
					System.out.println("3. Dental");
					System.out.println("Enter Appointment Type :");
					int t1=x.nextInt();

					i.cancelAppointment(id,appoint_id,t1);
					break;
					case 4:
						//swap function call
						//String[] type = {"Physician", "Surgeon", "Dental"};

						String patient_id,new_app_id,old_app_id,new_app_type;
						Integer old_app_type;
						System.out.println("Enter the Patient ID:");
						patient_id = x.next();
						System.out.println("Enter the Old Appointment Type:");
						System.out.println("1. Physician");
						System.out.println("2. Surgeon");
						System.out.println("3. Dental");
						old_app_type = x.nextInt();
						System.out.println("Enter the Old Appointment ID:");
						old_app_id = x.next();
						System.out.println("Type the New Appointment Type:");

						new_app_type = x.next();
						System.out.println("Enter the New Appointment ID:");
						new_app_id = x.next();
						System.out.println(i.swapAppointment(patient_id,old_app_id, old_app_type, new_app_id, new_app_type));
						break;


					case 5:
						do_while=5;
						break;


				}
			 }while(do_while!=5);
             System.out.println("Goodbye.");

     	}
        //SHERBROOKE
        if(id.contains("SHE")) {
        	 inter i=(inter) s_registry.lookup("s_verify");
        	 int code=i.verifyUser(id);
        	  //  System.out.println(code);
             if(code == 7)
             {
            	 System.out.println("Invalid ID");
             }
             String province[] = {"MONTREAL","QUEBEC","SHERBROOKE"};
             System.out.println(province[code-1]);
            
             int do_while=0;
             int choice =0;
             //size limit applies here
             do {
             	int patient_op = patientOptions(province[code-1]);
             	switch(patient_op) {
             	case 1:
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

					if(i.bookAppointment(id,appointmentId,type[t-1]).equals("true"))
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

					//	i.bookOptions(id);
					break;
             	case 2:
             		i.getAppointmentSchedule(id);
             		break;
             	case 3:
					//Scanner x = new Scanner(System.in);
					System.out.println("Enter the Appointment ID:");
					String appoint_id;
					appoint_id = x.next();
					//String[] type = {"Physician", "Surgeon", "Dental"};
					//String[] slot = {"M", "A", "E"};

					//Scanner sc = new Scanner(System.in);
					System.out.println("1. Physician");
					System.out.println("2. Surgeon");
					System.out.println("3. Dental");
					System.out.println("Enter Appointment Type :");
					int t1=x.nextInt();

					i.cancelAppointment(id,appoint_id,t1);
					break;
					case 4:
						//swap function call
						//String[] type = {"Physician", "Surgeon", "Dental"};

						String patient_id,new_app_id,old_app_id,new_app_type;
						Integer old_app_type;
						System.out.println("Enter the Patient ID:");
						patient_id = x.next();
						System.out.println("Enter the Old Appointment Type:");
						System.out.println("1. Physician");
						System.out.println("2. Surgeon");
						System.out.println("3. Dental");
						old_app_type = x.nextInt();
						System.out.println("Enter the Old Appointment ID:");
						old_app_id = x.next();
						System.out.println("Type the New Appointment Type:");

						// System.out.println("Enter Appointment Type :");

						new_app_type = x.next();
						System.out.println("Enter the New Appointment ID:");
						new_app_id = x.next();
						System.out.println(i.swapAppointment(patient_id,old_app_id, old_app_type, new_app_id, new_app_type));
						break;


					case 5:
						do_while=5;
						break;


				}
			 }while(do_while!=5);
             System.out.println("Goodbye.");

     	}
    }



}
