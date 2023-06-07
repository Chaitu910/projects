package com.example.Member_Enrollment_at_POS;
import java.security.SecureRandom;
public class Activation_key_genarator {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVW";
	static SecureRandom rnd = new SecureRandom();

	String randomString(int len){
		//len=6;
	   StringBuilder sb = new StringBuilder(len);
	   for(int i = 0; i < len; i++)
	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
	   return sb.toString();
	}
	    public static void main(String args[]) {
	    	Activation_key_genarator a=new Activation_key_genarator();
	    	System.out.println(a.randomString(6));
	    }
}


