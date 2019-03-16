import com.phidgets.*;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;
import com.phidgets.event.OutputChangeEvent;
import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;


public class InterFaceKit {
    InterfaceKitPhidget ik;
    public InterFaceKit() throws PhidgetException {
        ik = new InterfaceKitPhidget();
        ik.addAttachListener(new AttachListener() {
            public void attached(AttachEvent ae) {
                System.out.println("attachment of " + ae);
            }
        });
        ik.addDetachListener(new DetachListener() {
            public void detached(DetachEvent ae) {
                System.out.println("detachment of " + ae);
            }
        });
        ik.addErrorListener(new ErrorListener() {
            public void error(ErrorEvent ee) {
                System.out.println("error event for " + ee);
            }
        });
        ik.addInputChangeListener(new InputChangeListener() {
            public void inputChanged(InputChangeEvent oe) {
//		 System.out.println(oe);
            }
        });
        ik.addOutputChangeListener(new OutputChangeListener() {
            public void outputChanged(OutputChangeEvent oe) {
//		 System.out.println(oe);
            }
        });
        ik.addSensorChangeListener(new SensorChangeListener() {
            public void sensorChanged(SensorChangeEvent se) {
//		 System.out.println(se);
            }
        });
        ik.openAny();
        System.out.println("waiting for InterfaceKit attachment...");
        ik.waitForAttachment();
    }
    public InterFaceKit(int serialNo) throws PhidgetException {
        InterfaceKitPhidget ik = new InterfaceKitPhidget();
        //BEGIN INTERFACE LISTENER SECTION
        ik.addAttachListener(new AttachListener() {
            public void attached(AttachEvent ae) {
                System.out.println("attachment of " + ae);
            }
        });
        ik.addDetachListener(new DetachListener() {
            public void detached(DetachEvent ae) {
                System.out.println("detachment of " + ae);
            }
        });
        ik.addErrorListener(new ErrorListener() {
            public void error(ErrorEvent ee) {
                System.out.println("error event for " + ee);
            }
        });
        ik.addInputChangeListener(new InputChangeListener() {
            public void inputChanged(InputChangeEvent oe) {
//		 System.out.println(oe);
            }
        });
        ik.addOutputChangeListener(new OutputChangeListener() {
            public void outputChanged(OutputChangeEvent oe) {
//		 System.out.println(oe);
            }
        });
        ik.addSensorChangeListener(new SensorChangeListener() {
            public void sensorChanged(SensorChangeEvent se) {
//		 System.out.println(se);
            }
        });
        ik.open(serialNo);
        System.out.println("waiting for InterfaceKit attachment...");
        ik.waitForAttachment();
    }
    public void SetOut(int OutputNo,boolean OnOff) {
        try {
            ik.setOutputState(OutputNo, OnOff);
        } catch (PhidgetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public int GetIKValue(int No){
        int a=0;
        try {
            a = ik.getSensorValue(No);
        } catch (PhidgetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
}