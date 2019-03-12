
import com.phidgets.*;
import com.phidgets.event.*;

public class readRFID {
	public String theRFIDNumber;
	RFIDPhidget rfid;
	
	public readRFID() throws Exception {
            rfid = new RFIDPhidget();
		rfid.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae)
			{
				try
				{
					((RFIDPhidget)ae.getSource()).setAntennaOn(true);
					((RFIDPhidget)ae.getSource()).setLEDOn(true);
				}
				catch (PhidgetException ex) { }
				System.out.println("attachment of " + ae);
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
		rfid.addTagGainListener(new TagGainListener()
		{
			public void tagGained(TagGainEvent oe)
			{
				System.out.println("Tag Gained: " +oe.getValue() + " (Proto:"+ oe.getProtocol()+")");
			}
		});
		rfid.addTagLossListener(new TagLossListener()
		{
			public void tagLost(TagLossEvent oe)
			{
				System.out.println(oe);
			}
		});
		rfid.addOutputChangeListener(new OutputChangeListener()
		{
			public void outputChanged(OutputChangeEvent oe)
			{
				System.out.println(oe);
			}
		});

		rfid.openAny();
		System.out.println("waiting for RFID attachment...");
		rfid.waitForAttachment(10000);
		
		while (true) {
			boolean tagGained = rfid.getTagStatus();
			if (tagGained = true) {
				
			}
		}
	}

	public boolean tagPresent(boolean s) throws PhidgetException {
		return s = rfid.getTagStatus();
	}
	public String getTag() {
		
		String tagString = "";
		try {
			tagString = rfid.getLastTag();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tagString;
	}
	
//	public static String getRFIDNumber() {
//		return theRFIDNumber;
//	}

}
