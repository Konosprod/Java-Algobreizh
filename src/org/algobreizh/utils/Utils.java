package org.algobreizh.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	/**
	 * Calcul le hash SHA-256 d'une chaine de caractère
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
	 * représentation hexadécimale
	 */
	final protected static char[] hexArray = "0123456789abcdef".toCharArray();
	
	
	/**
	 * Permet de convertir un tableau de bytes en chaine de caractère
	 * @param bytes Les bytes dont on veut la représentation en String
	 * @return Une chaine de caractère représentant le {@code bytes}
	 *
	 * Code found on StackOverflow
	 * Link :https://stackoverflow.com/a/9855338
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
	
	public static String formatDateFromSql(String dateString)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm");
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		
		try {
			d = sqlFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return format.format(d);
	}
}
