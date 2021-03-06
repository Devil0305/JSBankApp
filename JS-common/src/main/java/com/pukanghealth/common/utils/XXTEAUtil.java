package com.pukanghealth.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class XXTEAUtil {

	/**
	 * Encrypt data with key.
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] data, byte[] key) {
		if (data.length == 0) {
			return data;
		}
		return toByteArray(encrypt(toIntArray(data, true), toIntArray(key, false)), false);
	}

	/**
	 * Decrypt data with key.
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		if (data.length == 0) {
			return data;
		}
		return toByteArray(decrypt(toIntArray(data, false), toIntArray(key, false)), true);
	}

	/**
	 * Encrypt data with key.
	 * 
	 * @param v
	 * @param k
	 * @return
	 */
	public static int[] encrypt(int[] v, int[] k) {
		int n = v.length - 1;

		if (n < 1) {
			return v;
		}
		if (k.length < 4) {
			int[] key = new int[4];

			System.arraycopy(k, 0, key, 0, k.length);
			k = key;
		}
		int z = v[n], y = v[0], delta = 0x9E3779B9, sum = 0, e;
		int p, q = 6 + 52 / (n + 1);

		while (q-- > 0) {
			sum = sum + delta;
			e = sum >>> 2 & 3;
			for (p = 0; p < n; p++) {
				y = v[p + 1];
				z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
			}
			y = v[0];
			z = v[n] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
		}
		return v;
	}

	/**
	 * Decrypt data with key.
	 * 
	 * @param v
	 * @param k
	 * @return
	 */
	public static int[] decrypt(int[] v, int[] k) {
		int n = v.length - 1;

		if (n < 1) {
			return v;
		}
		if (k.length < 4) {
			int[] key = new int[4];

			System.arraycopy(k, 0, key, 0, k.length);
			k = key;
		}
		int z = v[n], y = v[0], delta = 0x9E3779B9, sum, e;
		int p, q = 6 + 52 / (n + 1);

		sum = q * delta;
		while (sum != 0) {
			e = sum >>> 2 & 3;
		for (p = n; p > 0; p--) {
			z = v[p - 1];
			y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
		}
		z = v[n];
		y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
		sum = sum - delta;
		}
		return v;
	}

	/**
	 * Convert byte array to int array.
	 * 
	 * @param data
	 * @param includeLength
	 * @return
	 */
	private static int[] toIntArray(byte[] data, boolean includeLength) {
		int n = (((data.length & 3) == 0) ? (data.length >>> 2) : ((data.length >>> 2) + 1));
		int[] result;

		if (includeLength) {
			result = new int[n + 1];
			result[n] = data.length;
		} else {
			result = new int[n];
		}
		n = data.length;
		for (int i = 0; i < n; i++) {
			result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
		}
		return result;
	}

	/**
	 * Convert int array to byte array.
	 * 
	 * @param data
	 * @param includeLength
	 * @return
	 */
	private static byte[] toByteArray(int[] data, boolean includeLength) {
		int n = data.length << 2;

		;
		if (includeLength) {
			int m = data[data.length - 1];

			if (m > n) {
				return null;
			} else {
				n = m;
			}
		}
		byte[] result = new byte[n];

		for (int i = 0; i < n; i++) {
			result[i] = (byte) ((data[i >>> 2] >>> ((i & 3) << 3)) & 0xff);
		}
		return result;
	}

	/**
	 * 先XXXTEA加密，后Base64加密
	 * 
	 * @param vid
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String encrypt(String str,String key) throws UnsupportedEncodingException {
		String enVid = "";
		byte[] k = key.getBytes();
		byte[] v = str.getBytes();
		enVid = new String(Base64.encodeBase64(XXTEAUtil.encrypt(v, k)),"UTF-8");
		enVid = enVid.replace('+', '-');
		enVid = enVid.replace('/', '_');
		enVid = enVid.replace('=', '.');
		return enVid;
	}

	/**
	 * 先Base64解密，后XXXTEA解密
	 * 
	 * @param enVid
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String decrypt(String str,String key) throws UnsupportedEncodingException {
		String deVid = "";
		str = str.replace('-', '+');
		str = str.replace('_', '/');
		str = str.replace('.', '=');
		byte[] k = key.getBytes();
		byte[] v = Base64.decodeBase64(str);
		deVid = new String(XXTEAUtil.decrypt(v, k),"UTF-8");
		return deVid;
	}
	
	/**
	 * 一次性对多个字段进行加密
	 * @param key
	 * @param input
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String,String> encrypt(String key, String... input) throws UnsupportedEncodingException{
		Map<String, String> output = new HashMap<String, String>();
		for (int i = 0; i < input.length; i++) {  
            String[] temp = input[i].split(":"); 
            output.put(temp[0], encrypt(temp[1],key));
        }
		return output;
	}
	
	/**
	 * 一次性对多个字段进行解密
	 * @param key
	 * @param input
	 * @return
	 */
	public static Map<String,String> decrypt(String key, String... input){
		Map<String, String> output = new HashMap<String, String>();
		for (int i = 0; i < input.length; i++) {  
            String[] temp = input[i].split(":"); 
            try{
            	if(temp[1]==null||temp[1].equals("null")){
            		output.put(temp[0], "null");
            	}else{
            		output.put(temp[0], decrypt(temp[1],key));
            	}
            }catch(Exception e){
            	e.printStackTrace();
            	return null;
            }
        }
		return output;
	}

}