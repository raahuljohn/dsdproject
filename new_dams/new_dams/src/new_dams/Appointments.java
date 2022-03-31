package new_dams;

public class Appointments {
	String patientId,appointmentId,appointmentType;
		
	public String getpatientId()
	{
		return patientId;
	}
	
	public String getappointmentId()
	{
		return appointmentId;
	}
	
	public String getappointmentType()
	{
		return appointmentType;
	}
	
	public void setpatientId(String patientId)
	{
		this.patientId=patientId;
	}
	
	public void setappointmentId(String appointmentId)
	{
		this.appointmentId=appointmentId;
	}
	
	public void setappointmentType(String appointmentType)
	{
		this.appointmentType=appointmentType;
	}
}