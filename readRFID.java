import com.phidgets.*;
import com.phidgets.event.*;

public class readRFID {
static //	public String theRFIDNumber;
	RFIDPhidget rfid;
	
	public readRFID(int theRFIDSerial) throws Exception {
		//RFIDPhidget rfid;
		rfid = new RFIDPhidget();
		//add attach listener
		rfid.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae)
			{
				try
				{
					((RFIDPhidget)ae.getSource()).setAntennaOn(true);
					((RFIDPhidget)ae.getSource()).setLEDOn(true);
				}
				catch (PhidgetException ex) { }
			}
		});
		rfid.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		rfid.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		
		rfid.open(theRFIDSerial);
		rfid.waitForAttachment();
		System.out.println("working");
	}
	public readRFID() throws Exception {
		//RFIDPhidget rfid;
		rfid = new RFIDPhidget();
		//add attach listener
		rfid.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae)
			{
				try
				{
					((RFIDPhidget)ae.getSource()).setAntennaOn(true);
					((RFIDPhidget)ae.getSource()).setLEDOn(true);
				}
				catch (PhidgetException ex) { }
			}
		});
		rfid.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		rfid.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		rfid.openAny();
		rfid.waitForAttachment(1000);
		System.out.println("working");
	}
	
	public static String getTag() throws Exception {
		String tagString = "";
		if(rfid.getTagStatus()) {
			try {
				tagString = rfid.getLastTag();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tagString;
	}
	
//	public static String getRFIDNumber() {
//		return theRFIDNumber;
//	}

}
