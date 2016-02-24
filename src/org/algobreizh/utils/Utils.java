package org.algobreizh.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	
	/**
	 * Calcul le hash SHA-256 d'une chaine de caract�re
	 * @param s La chaine dont on veut calculer le hash
	 * @return Le hash SHA-1 de la chaine {@code s}
	 */
	public static String calculStringSHA256(String s)
	{	
		String ret = "";
		
		try {
			ret = calculByteArraySHA256(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * Permet de faire la correspondance entre une valeur d'un byte et sa
	 * repr�sentation hexad�cimale
	 */
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	
	/**
	 * Permet de convertir un tableau de bytes en chaine de caract�re
	 * @param bytes Les bytes dont on veut la repr�sentation en String
	 * @return Une chaine de caract�re repr�sentant le {@code bytes}
	 */
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	/**
	 * Calcule le hash SHA-256 d'un tableau de byte
	 * @param message Le message dont on veut le hash
	 * @return Le hash SHA-256 du tableau de byte @{code message}
	 */
	private static String calculByteArraySHA256(byte[] message)
	{
		String ret = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update(message);
			
			byte[] res = md.digest();
			
			return bytesToHex(res);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

}
