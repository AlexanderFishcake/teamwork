package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MvcUtils {
	
	/**
	 * 단방향 암호화 알고리즘
	 * - md5
	 * - sha1 - 이것만 160byte
	 * - sha256
	 * - sha512
	 * 
	 * 1. MessageDigest : 단방향 암호화 처리
	 * 
	 * 2. Base64 인코딩 처리 : 암호화된 byte[](이진데이터)를 64개의 문자로 변환
	 * 
	 * @param parameter
	 * @return
	 */

	public static String getSha512(String password) {
		String encryptedPassword = null;
		
		//1. 암호화
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = null;
		try {
			bytes = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		md.update(bytes);
		byte[] encryptedBytes = md.digest(); //암호화 처리
		
		System.out.println("암호화 처리후 : "+new String(encryptedBytes));
		
		//2. 문자 인코딩 처리
		encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
		System.out.println("인코딩 처리후 : "+encryptedPassword);
		
		return encryptedPassword;
	}

}
