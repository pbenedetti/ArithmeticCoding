/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic_cod_dec;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author paolabenedetti
 */
public class ArithmeticCoding {

    /**
     * @param args the command line arguments
     */
    HashMap<Character,Integer> occurrences = new HashMap<>();
    HashMap<Character,Integer> alphabet = new HashMap<>();
    HashMap<Character,Double> interval = new HashMap<>();
    
    
    
    Double inf_indx_bound;
     Double sup_indx_bound;
     Double interval_length;
     Double interval_unit;
     int msg_length;
     String msg;
     Double msg_encoded;
     String msg_decoded;

    
    ArithmeticCoding(String msg, Double inf, Double sup){

        System.out.println("\nINITIALIZING ARITHMETIC ENCODER\n===============================");
        set_msg(msg);
        this.msg_length = get_msg().length();
        this.inf_indx_bound = inf;
        this.sup_indx_bound = sup;
        set_interval_length();
        set_interval_unit();
        
        this.msg_encoded = null;
        this.msg_decoded = null;
        
        Character tmp_letter;
        Integer tmp_occurrence = 1;
        
        
        for(int i = 0; i < get_msg_length() ; i++){
            
            tmp_letter = msg.charAt(i);

            
            //add occurrences of each letter
            if(occurrences.containsKey(tmp_letter)){
                
                occurrences.replace(tmp_letter, tmp_occurrence+1);
                
            }else{
               
                occurrences.put(tmp_letter, tmp_occurrence);
                alphabet.put(tmp_letter, alphabet.size()+1);
                
            }
            
        }
        
        System.out.println("\tINF:"+get_inf());
        System.out.println("\tSUP:"+get_sup());

        System.out.println("\tMSG LENGTH:"+get_msg_length());
        occurrencesToString();
        rangesToString();
        System.out.println("\n===============================\n===============================");
    }
    
    public String get_msg(){
        return this.msg;
    }
    public Double get_encoded_msg(){
        return this.msg_encoded;
    }
    public String get_decoded_msg(){
        return this.msg_decoded;
    }
    public Double get_interval_length(){
        return this.interval_length;
    }
    public Double get_interval_unit(){
        return this.interval_unit;
    }
    public Integer get_msg_length(){
        return this.msg_length;
    }
    public Double get_sup(){
       return this.sup_indx_bound;
    }
    public Double get_inf(){
       return this.inf_indx_bound;
    }
    public Double get_unit(){
        return this.interval_unit;
    }
    public HashMap<Character,Integer> get_occurrences(){
        return this.occurrences;
    }
    public void set_sup(Double new_sup){
        System.out.println("\tNEW SUP:"+new_sup);
        this.sup_indx_bound = new_sup;
    }
    public void set_inf(Double new_inf){
        System.out.println("\tNEW INF:"+new_inf);
        this.inf_indx_bound = new_inf;
    }
    public void set_interval_length(){
        this.interval_length = get_sup()-get_inf();
        System.out.println("\n\tNEW INTERVAL LEN:"+ get_interval_length());
    }
    public void set_interval_unit(){
        this.interval_unit = get_interval_length() / get_msg_length();
        System.out.println("\tNEW INTERVAL UNIT:"+ get_interval_unit());
    }
    
    public void set_new_env(Double new_inf, Double new_sup){
        
        set_inf(new_inf);
        set_sup(new_sup);
        set_interval_length();
        set_interval_unit();
        
    }
    
    public void set_msg(String msg){
        this.msg = msg;
        System.out.println("\nMSG:"+get_msg());
    }
    
    public void set_encoded_msg(Double encoded_msg){
        this.msg_encoded = encoded_msg;
        System.out.println("MSG ENCODED:"+get_encoded_msg());
    }
    public void set_decoded_msg(String decoded_msg){
        this.msg_decoded = decoded_msg;
        System.out.println("MSG ENCODED:"+get_decoded_msg());
    }
    public void occurrencesToString(){
        
        System.out.println("\n\tPROBABILITIES:");
        for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
            Character letter = entry.getKey();
            Integer tot = get_msg_length();
            Integer occ = entry.getValue();
            
            float prob = occ / (float) tot;
            System.out.println("\tletter:"+letter+"="+ prob);

        }

    }
    
    public void rangesToString(){
        
        Double inf = get_inf();
        Double sup = get_sup();
        Double unit = get_interval_unit();
        
        Double inf_p = 0.0;
        Double sup_p = 0.0;
        
        System.out.println("\n\tRANGES:");
        
        int i=0;
        for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
            
            Character letter = entry.getKey();
            Integer occ = entry.getValue();
            Double u = get_unit();
            
            
            if(i==0)
                inf_p = inf;
            else
                inf_p = sup_p;
             
            sup_p = inf_p + (u*occ);

            i++;
            System.out.println("\tletter:"+letter+"["+inf_p+","+sup_p+"]" );

        }
        
        
    }
    
    public Double get_distance(Character letter){
    
        Double count_occ=0.0;
        
        for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
            
            if(entry.getKey()==letter)
                return count_occ;
            else
                count_occ += entry.getValue();
        }
        
        return 10000.0;
    }
    public void Encode(){
        
        System.out.println("\n\n\tSTARTING ENCODING\n=======================================================");
        Double inf = 0.0;
        Double sup = 0.0;
        Double unit = 0.0;
        
        Double new_inf = 0.0;
        Double new_sup = 0.0;
        
        Double count_occ = 0.0;
        Character tmp_letter;
        Integer index = 0;
        Integer occ = 0;
        
        String msg = get_msg();
        
        for(int i = 0; i < msg.length() ; i++){
            
            inf = get_inf();
            sup = get_sup();
            unit = get_unit();
            
            tmp_letter = msg.charAt(i);
            index = alphabet.get(tmp_letter);
            occ = occurrences.get(tmp_letter);
            
            System.out.println("\n\tREADING:"+tmp_letter+"\n\t----------");
            
            //return how many slot have to switch to find new_inf value
            count_occ = get_distance(tmp_letter);
            
            
            
            new_inf = inf +( unit * count_occ );
            new_sup = new_inf + (occ * unit);
            
            set_new_env(new_inf,new_sup);
            
        }
        
        Double encoded_msg = ((new_sup-new_inf)/2)+new_inf;
        
        System.out.println("\nMSG:"+get_msg());
        set_encoded_msg(encoded_msg);
        rangesToString();

        System.out.println("\n=======================================================\n=======================================================");
    }
    
    
    
    public static void main(String[] args) {
        
        
        ArithmeticCoding AE = new ArithmeticCoding("babc",0.0,1.0);
        AE.Encode();
        Double encoded_msg = AE.get_encoded_msg();
        HashMap<Character,Integer> occurrences = AE.get_occurrences();
        //int origin_msg_length  = AE.get_msg_length();
        //ArithmeticDecoding ED = new ArithmeticDecoding(encoded_msg,origin_msg_length);

        
    }
    
}
