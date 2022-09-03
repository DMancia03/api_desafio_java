package sv.cuponera.dependientes.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class cesarCipher {

	public static String cipher(String clear ) {
		    MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				byte[] b = md.digest(clear.getBytes());
			    int size = b.length;
			    StringBuffer h = new StringBuffer(size);
			    //algoritmo y arreglo md5
			        for (int i = 0; i < size; i++) {
			            int u = b[i] & 255;
			                if (u < 16) {
			                    h.append("0" + Integer.toHexString(u));
			                }
			               else {
			                    h.append(Integer.toHexString(u));
			               }
			           }
			      //clave encriptada
			      return h.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ""; 
			}
		    
		    
	}
	public static String ceasarCipher(String text, int s) {
		 StringBuffer result= new StringBuffer();
		  
	        for (int i=0; i<text.length(); i++)
	        {
	            if (Character.isUpperCase(text.charAt(i)))
	            {
	                char ch = (char)(((int)text.charAt(i) +
	                                  s - 65) % 26 + 65);
	                result.append(ch);
	            }
	            else
	            {
	                char ch = (char)(((int)text.charAt(i) +
	                                  s - 97) % 26 + 97);
	                result.append(ch);
	            }
	        }
	        return result.toString();
	}
}
