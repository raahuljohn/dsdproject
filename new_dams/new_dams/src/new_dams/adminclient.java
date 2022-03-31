package new_dams;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class adminclient {

	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		Registry m_registry = LocateRegistry.getRegistry(8002);
		Registry q_registry = LocateRegistry.getRegistry(8003);
		Registry s_registry = LocateRegistry.getRegistry(8004);

        Scanner x = new Scanner(System.in);
       
        System.out.println("Welcome Admin.");
        System.out.println("Enter your ID : ");
        String id=x.next();
      //  System.out.println(id);
        
        //MONTREAL SERVER
        if(id.contains("MTL")) {
        	 inter i=(inter) m_registry.lookup("m_verify");
        	 
        	 int code=i.verifyUser(id);
           //  System.out.println(code);
             if(code == 7)
             {
            	 System.out.println("Invalid ID");
             }
          //   System.out.println("Welcome to Montreal Server");
             String province[] = {"MONTREAL","QUEBEC","SHERBROOKE"};
     
             int do_while=0;
             
             //size limit applies here
             do {
				 Scanner x1=new Scanner(System.in);
				 System.out.println("Welcome"+id);
				 System.out.println("1. Add Appointment");
				 System.out.println("2. Remove Appointment ");
				 System.out.println("3. List Appointment");
				 System.out.println("Enter 4 to exit");
				 System.out.println("Enter your Option : ");
					int pass = x1.nextInt();
				 int admin_op = i.adminOptions(province[code-4],pass);
             	switch(admin_op) {
             	case 1:
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					int apTy=x1.nextInt();
					System.out.println("Enter Appointment Id To Add  ");

					String apId=x1.next();

					System.out.println("Enter Capacity:");
					int c=x1.nextInt();

					i.addOptions(id,apTy,apId,c);
             		break;
             	case 2:
					String doc[] = {"Physician","Surgeon","Dental"};
					Scanner sc = new Scanner(System.in);
					int ap_type;
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					ap_type = sc.nextInt();
					System.out.println("Enter Appointment ID:");
					String ap_id;

					ap_id = sc.next();

					//i.removeOptions(ap_id,doc,ap_type);
					System.out.println(i.removeAppointment(ap_id,doc[ap_type-1]));

					break;
             	case 3:
					String doc1[] = {"Physician","Surgeon","Dental"};
					Scanner sc1 = new Scanner(System.in);
					int ap_type1;
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					ap_type1 = sc1.nextInt();
//					System.out.println("Enter Appointment ID:");
//					String ap_id1;
//					ap_id1 = sc1.next();

					System.out.println(i.listAppointment(doc1[ap_type1-1],id));
             		//i.listOptions(id);

             		break;
             	case 4:
             		do_while=4;
             		break;
             	}
             }while(do_while!=4);
             System.out.println("Goodbye.");
        }
        
        //QUEBEC
        if(id.contains("QUE")) {
       	 inter i=(inter) q_registry.lookup("q_verify");
       	 
       	 int code=i.verifyUser(id);
         //   System.out.println(code);
       	 if(code == 7)
         {
        	 System.out.println("Invalid ID");
         }
      //   System.out.println("Welcome to Montreal Server");
            String province[] = {"MONTREAL","QUEBEC","SHERBROOKE"};
        
           
            int do_while=0;
            //size limit applies here
            do {

				Scanner x1=new Scanner(System.in);
				System.out.println("Welcome to "+id);
				System.out.println("1. Add Appointment");
				System.out.println("2. Remove Appointment ");
				System.out.println("3. List Appointment");
				System.out.println("Enter 4 to exit");
				System.out.println("Enter your Option : ");
				int pass = x1.nextInt();
				int admin_op = i.adminOptions(province[code-4],pass);
            	switch(admin_op) {
            	case 1:
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					int apTy=x1.nextInt();
					System.out.println("Enter Appointment Id To Add  ");

					String apId=x1.next();

					System.out.println("Enter Capacity:");
					int c=x1.nextInt();

					i.addOptions(id,apTy,apId,c);

            		break;
            	case 2:
					String doc[] = {"Physician","Surgeon","Dental"};
					Scanner sc = new Scanner(System.in);
					int ap_type;
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					ap_type = sc.nextInt();
					System.out.println("Enter Appointment ID:");
					String ap_id;

					ap_id = sc.next();

				//	i.removeOptions(ap_id,doc,ap_type);
					System.out.println(i.removeAppointment(ap_id,doc[ap_type-1]));

					break;
            	case 3:
					String doc1[] = {"Physician","Surgeon","Dental"};
					Scanner sc1 = new Scanner(System.in);
					int ap_type1;
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					ap_type1 = sc1.nextInt();
//					System.out.println("Enter Appointment ID:");
//					String ap_id1;
//					ap_id1 = sc1.next();




					System.out.println(i.listAppointment(doc1[ap_type1-1],id));
					//i.listOptions(id);
					break;
            	case 4:
            		do_while=4;
            		break;
            		
        		
            	
            	}
            }while(do_while!=4);
            System.out.println("Goodbye.");
       }
        //SHERBROOKE
        if(id.contains("SHE")) {
       	 inter i=(inter) s_registry.lookup("s_verify");
       	 
       	 int code=i.verifyUser(id);
         //   System.out.println(code);
            if(code == 7)
            {
           	 System.out.println("Invalid ID");
            }
         //   System.out.println("Welcome to Montreal Server");
            String province[] = {"MONTREAL","QUEBEC","SHERBROOKE"};
           
            int do_while=0;
            //size limit applies here
            do {

				Scanner x1=new Scanner(System.in);
				System.out.println("Welcome to "+id);
				System.out.println("1. Add Appointment");
				System.out.println("2. Remove Appointment ");
				System.out.println("3. List Appointment");
				System.out.println("Enter 4 to exit");
				System.out.println("Enter your Option : ");
				int pass = x1.nextInt();
				int admin_op = i.adminOptions(province[code-4],pass);
            	switch(admin_op) {
            	case 1:
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					int apTy=x1.nextInt();
					System.out.println("Enter Appointment Id To Add  ");

					String apId=x1.next();

					System.out.println("Enter Capacity:");
					int c=x1.nextInt();

					i.addOptions(id,apTy,apId,c);
					break;
            	case 2:
					String doc[] = {"Physician","Surgeon","Dental"};
					Scanner sc = new Scanner(System.in);
					int ap_type;
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					ap_type = sc.nextInt();
					System.out.println("Enter Appointment ID:");
					String ap_id;

					ap_id = sc.next();

					//i.removeOptions(ap_id,doc,ap_type);
					System.out.println(i.removeAppointment(ap_id,doc[ap_type-1]));

					break;
            	case 3:
					String doc1[] = {"Physician","Surgeon","Dental"};
					Scanner sc1 = new Scanner(System.in);
					int ap_type1;
					System.out.println("Enter Appointment Type  ");
					System.out.println("1. Physician");
					System.out.println("2. Surgeon ");
					System.out.println("3. Dental");
					ap_type1 = sc1.nextInt();
//					System.out.println("Enter Appointment ID:");
//					String ap_id1;
//					ap_id1 = sc1.next();




					System.out.println(i.listAppointment(doc1[ap_type1-1],id));
					//i.listOptions(id);
					break;
            	case 4:
            		do_while=4;
            		break;
            		
        		
            	
            	}
            }while(do_while!=4);
            System.out.println("Goodbye.");
       }
        


	}

}
