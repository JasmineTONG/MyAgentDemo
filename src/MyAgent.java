import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;


public class MyAgent extends Agent {

	private static final long serialVersionUID = 1L;

	  private String noun;
	  private String attribute;

	  
	  protected void setup() {
	    System.out.println("MyAgent ("+getAID().getName()+") says hello.");
	    Object[] args = getArguments();
	    String argStr = (String)args[0];
	    String[] array = argStr.split(" ");
	    System.out.println("array length: "+array.length );
	    if (array != null && array.length > 0) {
	      noun = array[0];
	      System.out.println("array[0]: "+noun );
	      attribute = array[1];
	      System.out.println("Noun: "+ noun);
	      System.out.println("Attribute: "+ attribute);
	      System.out.println("MyAgent is searching...\n");
	    }
	    else {
	      System.out.println("Input error, try again");
	      doDelete();
	    }
	    Behaviour behaviour = new SemSearchBehaviour(noun, attribute);
	    addBehaviour(behaviour);
	    behaviour.action();
	  }

	  protected void takeDown() {
	    System.out.println("MyAgent ("+getAID().getName()+") says goodbye.");
	  }
	}
