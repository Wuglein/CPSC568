package iRobotCreate;

/* For invaluable instructions on how to run this in Eclipse, please refer to page 8
 * of the Casa IRobot User Manual on the iRobot section of the CASA website. Use
 * the modified wallMeasure.lisp script that should HOPEFULLY be accompanying this push
 */

import iRobotCreate.iRobotCommands.Sensor;
import casa.ML;
import casa.URLDescriptor;
import casa.abcl.ParamsMap;
import casa.conversation2.SubscribeClientConversation;
import casa.exceptions.IllegalOperationException;
import casa.ui.AgentUI;
import iRobotCreate.IRobotState;
import jade.semantics.lang.sl.grammar.Term;

public class SassyRobot extends StateBasedController { //extending StateBased Controller
													   //is needed for setState and getState
													   //This will be very useful!

	public SassyRobot(ParamsMap params, AgentUI ui) throws Exception {
		super(params, ui);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeAfterRegistered(boolean registered) {
		super.initializeAfterRegistered(registered);
		
		/* For what will go here, please refer to the Controller class in iRobotCreate.
		   We need to subscribe our controller to the sensor readings we care about. Controller
		   has one for bumps and wheel drops already. These act like observers, of a sort.
		   After the controller gets a notification, it fires an appropriate method. We have
		   different states we'll be in, but we can use getCurrentState() that comes with
		   StateBasedController and call the handleEvent method that each IRobotState
		   needs to declare. For an example of what I'm talking about, look at the commented out 
		   onBumpsAndWheelDrop method included in LineFollower.
		*/
		
		setState(goForTheRecord);
	}
	
	/*This state just spins around in place fairly slowly. Not very interesting but this is just
	 * to show what can be done.
	*/
	IRobotState goForTheRecord = new IRobotState("goForTheRecord") {
		@Override
		public void enterState() {
			//memorize this form because you can be darned sure we're going to be using this a lot.
			sendMessage(ML.REQUEST, ML.EXECUTE, server, ML.LANGUAGE, "lisp", ML.CONTENT, "(progn () (irobot.mode 2) (irobot.drive 70))");
		}
		public void handleEvent(Sensor sensor, short shortness) {
			switch (sensor) {
			case BumpsAndWheelDrops:
				int deg = 0;
				switch (shortness & 3) {
				case 0: //no bumps
					deg = 0;
					break;
				case 1: //right bump
					deg = 90;
					break;
				case 2: //left bump
					deg = -90;
					break;
				case 3: //both bumps
					deg = 180;
				}
				
				sendMessage(ML.REQUEST, ML.EXECUTE, server 
						  ,ML.LANGUAGE, "lisp"
						  ,ML.CONTENT, "(progn () (irobot.drive 0) (irobot.rotate-deg 180) (irobot.drive 100))"
						  ); 
				
				break;
			}
		}
	};
	
	@Override
	protected void onBumpsAndWheelDrops(int val) {
		getCurrentState().handleEvent(Sensor.BumpsAndWheelDrops, (short)val);
	}
}
