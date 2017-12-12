import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


public class ArithCoding {
	
	String msg = new String();
	 
	LinkedList<Double> interval_0;
	LinkedList<Double> interval_i;

	public HashMap<Character,Double> p_distribution=new HashMap<>();

	ArithCoding(String msg){
		//Instantiate starting interval
		this.interval_0=new LinkedList<Double>();
		interval_0.add(0.0);
		interval_0.add(1.0);
		
		this.msg=msg;
		//Instantiate probability distribution of symbols in msg
		for(int i=0;i<msg.length();i++){
			
			Character c_temp=msg.charAt(i);
			
			if(!p_distribution.containsKey(c_temp)){
				p_distribution.put(c_temp, 1.0);
			}
			else{
				Double p_value = p_distribution.get(c_temp);
				p_value++;
				p_distribution.put(c_temp, p_value);
			}	
			
		}
		
		//Calculate probability of occurrence dividing every symbol occurences for the total length of msg
		for (Map.Entry<Character, Double> entry : this.p_distribution.entrySet())
		{
	        
	        Character p_key=entry.getKey();
	        Double p_value=entry.getValue();
	        //System.out.println(p_key); 
		    //System.out.println(p_value);
	        //p_value = p_value/msg.length();
	        //p_distribution.replace(p_key,p_value);
	        // avoids a ConcurrentModificationException
	    }
	}
	
	public void get_interval(Character c_val){
		
		Double min_interval;
		Double max_interval;
		Double length_interval = interval_i.get(1) - interval_i.get(0);

		Double c_prob=0.0;
			
		int index=0;
		Boolean found_index=false;
		
		
		
		//get the mapping index of the current symbol
		for (Map.Entry<Character, Double> entry : this.p_distribution.entrySet())
		{
			if(entry.getKey().equals(c_val)){
	    		
	    		found_index=true;
	    		c_prob = entry.getValue();
	    		System.out.println("ciao fre");
		        System.out.println(c_prob); 

	    		
	    	}
			index++;
		}
	
		//evaluate new interval accouding to the character we are reading
		Double unit = length_interval/msg.length();

		Double c_slot = c_prob*unit;


		max_interval = interval_i.get(0)+(unit*index);
		min_interval = interval_i.get(0)+max_interval-c_slot;
		
		LinkedList<Double> new_interval=new LinkedList<>();
		new_interval.add(min_interval);
		new_interval.add(max_interval);

		interval_i=new_interval;
				
	}
	
	public void encode(){
		
		for(int i=0; i<msg.length();i++){
			
			Character c_val = msg.charAt(i);
			

			if(i==0)
				interval_i=interval_0;
			
			get_interval(c_val);
			
		}
		
		
	}
	
	public static void main( String argv[]){
		
		String msg="abcdea";
		ArithCoding AC=new ArithCoding(msg);
		AC.encode();
		
	}
	
}
