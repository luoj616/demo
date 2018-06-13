package demo.luojun.com.demo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 *  RSA 加密工具
 * @author lei.p
 * 
 */
public class RSAEncryptionUtil {
	public static final String RSA_PUBLIC ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC7n4WxGOL+J94agHzcgelb4EhUM6jle1TxScN8l2yd6FIn5rM/iIb6n6iwJmjR0wVt9cY9g1Rdx8UeVaSQqXGcEZtmmhdhrjAkZPh/yNy0QWOalhVThr+reM1Ct1P+N5adbIf01e3biL7Fhy4O8w2JaX4jRAsjVmRyZLUMpXMhnQIDAQAB";
	public static final String suffixRed = "_r";

	public static final String ALGORITHM = "RSA";
	/**
	 * 填充策略，<B>加密和解密的策略必须一致</B><br/>
	 * NoPadding：不会对明文进行随机数填充保证同一明文每次加密后的密文均相同<br/>
	 * PKCS1Padding：会对明文进行随机数填充保证同一明文每次加密后的密文都不相同
	 */
	public static final String PADDING = "RSA/NONE/PKCS1Padding";

	/**
	 * 随机生成密钥对
	 * @param keySize
	 * @return
	 */
	public static KeyPair generateKeyPair(int keySize)  {
//		Security.addProvider(
//				new org.bouncycastle.jce.provider.BouncyCastleProvider());
		final KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("无此算法", e);
		} 
		keyGen.initialize(keySize);
		return keyGen.generateKeyPair();
	}

	/**
	 * 根据密钥对生成PEM格式编码的公钥字符串
	 * @param keyPair
	 * @return
	 */
	public static String generatePublicKeyStrWithPEM(KeyPair keyPair) {
		PublicKey publicKey = keyPair.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		return Base64.encode(publicKeyBytes);
//		return Base64.encodeBase64String(publicKeyBytes);
	}

	/**
	 * 根据密钥对生成PEM格式编码的私钥字符串
	 * @param keyPair
	 * @return
	 */
	public static String generatePrivateKeyStrWithPEM(KeyPair keyPair) {
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		return Base64.encode(privateKeyBytes);
//		return Base64.encodeBase64String(privateKeyBytes);
	}

	/**
	 * 根据密钥对生成DER格式编码的公钥文件
	 * @param keyPair
	 * @param publicKeyFilePath
	 */
	public static void generatePublicKeyFileWithDER(
			KeyPair keyPair, String publicKeyFilePath) {
		File publicKeyFile = new File(publicKeyFilePath);
		if (publicKeyFile.getParentFile() != null) {
			publicKeyFile.getParentFile().mkdirs();
		}
		PublicKey publicKey = keyPair.getPublic();
		X509EncodedKeySpec ksp =
				new X509EncodedKeySpec(publicKey.getEncoded());
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(publicKeyFilePath);
			fos.write(ksp.getEncoded());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据密钥对生成DER格式编码的私钥文件
	 * @param keyPair
	 * @param privateKeyFilePath
	 */
	public static void generatePrivateKeyFileWithDER(
			KeyPair keyPair, String privateKeyFilePath) {
		File privateKeyFile = new File(privateKeyFilePath);
		if (privateKeyFile.getParentFile() != null) {
			privateKeyFile.getParentFile().mkdirs();
		}
		PrivateKey privateKey = keyPair.getPrivate();
		PKCS8EncodedKeySpec ksp =
				new PKCS8EncodedKeySpec(privateKey.getEncoded());
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(privateKeyFilePath);
			fos.write(ksp.getEncoded());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从PEM编码格式的公钥字符串中加载公钥
	 * @param publicKeyStr 公钥字符串(PEM编码)
	 */
	public static PublicKey loadPublicKeyWithPEM(String publicKeyStr) {
		
		return loadPublicKey(Base64.decode(publicKeyStr));
//		return loadPublicKey(Base64.decodeBase64(publicKeyStr));
	}

	/**
	 * 从PEM编码格式的私钥字符串中加载私钥
	 * @param privateKeyStr 私钥字符串(PEM编码)
	 */
	public static PrivateKey loadPrivateKeyWithPEM(String privateKeyStr) {
		return loadPrivateKey(Base64.decode(privateKeyStr));
//		return loadPrivateKey(Base64.decodeBase64(privateKeyStr));
	}

	/**
	 * 从PEM编码格式的公钥文件中加载公钥
	 * @param keyFilePath 公钥文件(PEM编码)
	 */
	public static PublicKey loadPublicKeyWithPEMByFile(String keyFilePath) {
		String publicKeyStr = getStringByFileWithPEM(keyFilePath);
		return loadPublicKeyWithPEM(publicKeyStr);
	}

	/**
	 * 从PEM编码格式的私钥文件中加载公钥
	 * @param keyFilePath 私钥文件(PEM编码)
	 */
	public static PrivateKey loadPrivateKeyWithPEMByFile(String keyFilePath) {
		String privateKeyStr = getStringByFileWithPEM(keyFilePath);
		return loadPrivateKeyWithPEM(privateKeyStr);
	}

	private static String getStringByFileWithPEM(String keyFilePath) {
		InputStream keyFileInputStream =
				RSAEncryptionUtil.class.getResourceAsStream(keyFilePath);
		BufferedReader br = null;
		StringBuilder publicKeyBuilder = null;
		try {
			if (new File(keyFilePath).exists()) {
				br = new BufferedReader(new FileReader(keyFilePath));
			} else {
				br = new BufferedReader(
						new InputStreamReader(keyFileInputStream));
			}
			publicKeyBuilder = new StringBuilder();
			String bufferLine = br.readLine();
			while (bufferLine != null) {
				if (bufferLine.charAt(0) != '-') {
					publicKeyBuilder.append(bufferLine);
				}
				bufferLine = br.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("密钥文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return publicKeyBuilder.toString();
	}

	/**
	 * 从DER编码格式的公钥文件中加载公钥
	 * @param publicKeyFilePath 公钥文件(DER编码)
	 */
	public static PublicKey loadPublicKeyWithDER(String publicKeyFilePath) {
		byte[] publicKeyBytes = getBytesByFile(publicKeyFilePath);
		return loadPublicKey(publicKeyBytes);
	}

	/**
	 * 从DER编码格式的私钥文件中加载私钥
	 * @param privateKeyFilePath 私钥文件(DER编码)
	 */
	public static PrivateKey loadPrivateKeyWithDER(String privateKeyFilePath) {
		byte[] privateKeyBytes = getBytesByFile(privateKeyFilePath);
		return loadPrivateKey(privateKeyBytes);
	}

	private static PublicKey loadPublicKey(byte[] keyBytes) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			return keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("无此算法", e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("公钥非法", e);
		} catch (NullPointerException e) {
			throw new RuntimeException("公钥数据为空", e);
		}
	}

	private static PrivateKey loadPrivateKey(byte[] keyBytes) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			return keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("无此算法", e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("私钥非法", e);
		} catch (NullPointerException e) {
			throw new RuntimeException("私钥数据为空", e);
		}
	}

	private static byte[] getBytesByFile(String keyFilePath) {
		byte[] publicKeyBytes;
		ByteArrayOutputStream bos =
				new ByteArrayOutputStream();
		BufferedInputStream in = null;
		try {
			if (new File(keyFilePath).exists()) {
				in = new BufferedInputStream(new FileInputStream(keyFilePath));
			} else {
				in = new BufferedInputStream(
						RSAEncryptionUtil.class.getResourceAsStream(keyFilePath));
			}
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			publicKeyBytes = bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException("读取密钥文件异常", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return publicKeyBytes;
	}

	/**
	 * 执行数据加密
	 * @param plainData 明文数据字节数组
	 * @param publicKey 公钥
	 * @return 密文数据字节数组
	 */
	public static byte[] encrypt(byte[] plainData, PublicKey publicKey) {
		return doCipher(plainData, publicKey, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 执行数据加密
	 * @param plainData 明文数据字节数组
	 * @param publicKey 公钥
	 * @return 经过base64处理后的密文字符串
	 */
	public static String encrypt2Base64String(byte[] plainData, PublicKey publicKey) {
		return Base64.encode(doCipher(plainData, publicKey, Cipher.ENCRYPT_MODE));
//		return Base64.encodeBase64String(doCipher(plainData, publicKey, Cipher.ENCRYPT_MODE));
	}

	/**
	 * 执行数据解密
	 * @param cipherData 密文数据字节数组
	 * @param privateKey 私钥
	 * @return 明文数据字节数组
	 */
	public static byte[] decrypt(byte[] cipherData, PrivateKey privateKey) {
		return doCipher(cipherData, privateKey, Cipher.DECRYPT_MODE);
	}

	/**
	 * 执行数据解密

	 * @param privateKey 私钥
	 * @return 明文数据字节数组
	 */
	public static byte[] decryptByBase64String(
			String cipherTextByBase64, PrivateKey privateKey) {
		return doCipher(Base64.decode(cipherTextByBase64),privateKey, Cipher.DECRYPT_MODE);
//		return doCipher(Base64.decodeBase64(cipherTextByBase64),privateKey, Cipher.DECRYPT_MODE);
	}

	private static byte[] doCipher(byte[] inData, Key key, int cipherMode) {
		if (key == null) {
			throw new RuntimeException("密钥为空, 请设置");
		}
		byte[] outData;
		try {
			/*Security.addProvider(
					new org.bouncycastle.jce.provider.BouncyCastleProvider());*/
			final Cipher cipher = Cipher.getInstance(PADDING);
			cipher.init(cipherMode, key);
			outData = cipher.doFinal(inData);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("无此加密算法", e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("无此Padding", e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException("密钥非法,请检查", e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException("输入数据长度非法", e);
		} catch (BadPaddingException e) {
			throw new RuntimeException("输入数据数据已损坏", e);
		}
		return outData;
	}

	/**
	 * sign the request info. 对请求信息进行签名
	 * 
	 * @param content
	 *            待签名信息
	 * @throws Exception
	 */
	public static String sign(String content) {
		byte[] temp= content.getBytes();
		PublicKey publicKey= RSAEncryptionUtil.loadPublicKeyWithPEM(RSA_PUBLIC);
		return RSAEncryptionUtil.encrypt2Base64String(temp, publicKey);
	
	}
}