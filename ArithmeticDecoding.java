/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic_cod_dec;

/**
 *
 * @author paolabenedetti
 */
public class ArithmeticDecoding {
    
    private Double encoded_msg;
    private String decoded_msg; 
    private int origin_msg_length;
    
    ArithmeticDecoding(Double encoded_msg, int origin_msg_length){
        
        set_origin_msg_length(origin_msg_length);
        set_encoded_msg(encoded_msg);
        
    }
    
    public void set_encoded_msg(Double encoded_msg){
        this.encoded_msg = encoded_msg;
        System.out.println("MSG ENCODED:"+get_encoded_msg());
    }
    public void set_decoded_msg(String decoded_msg){
        this.decoded_msg=decoded_msg;
        System.out.println("MSG DECODED:"+get_decoded_msg());
    }
    public void set_origin_msg_length(int origin_msg_length){
        this.origin_msg_length=origin_msg_length;
        System.out.println("MSG ORIGIN LENGTH:"+get_decoded_msg());

    }
    public Double get_encoded_msg(){
        return this.encoded_msg;
    }
    public String get_decoded_msg(){
        return this.decoded_msg;
    }
    
    public void Decode(){
        
        System.out.println("\n\n\tSTARTING ENCODING\n=======================================================");
        
        Double encoded_msg = get_encoded_msg();
        String decoded_msg = get_decoded_msg();
        
        
        //TO-DO
        
        
        
        
        System.out.println("\nMSG ENCODED:"+get_encoded_msg());
        set_decoded_msg(decoded_msg);
        
        System.out.println("\n=======================================================\n=======================================================");
    }
    
    
}
