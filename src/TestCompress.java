import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestCompress {

	public static void main(String[] args) {
		
	}
//	
//	/**
//	 2      *  -> base64
//	 3      * @param imgFile
//	 4      * @return
//	 5      * @throws IOException
//	 6      */
//	public static String getImageStr(String imgFile) throws IOException {
//		 InputStream inputStream = null;
//		 byte[] data = null;
//	     
//		inputStream = new FileInputStream(imgFile);
//		data = new byte[inputStream.available()];
//		inputStream.read(data);
//		inputStream.close();
//		  
//		// 加密
//		BASE64Encoder encoder = new BASE64Encoder();
//		return encoder.encode(data);
//	}
//	
//	/**
//	 2      * base64 -> 
//	 3      * @param imgStr
//	 4      * @param path
//	 5      * @return
//	 6      * @throws IOException
//	 7      */
//	public static boolean generateImage(String imgStr, String path) throws IOException {
//		if (imgStr == null){
//			return false;
//		}
//		BASE64Decoder decoder = new BASE64Decoder();
//		
//		//解密
//		byte[] b = decoder.decodeBuffer(imgStr);
//		// 处理数据
//		for (int i = 0; i < b.length; ++i) {
//			if (b[i] < 0) {
//				b[i] += 256;
//			}
//		}
//		OutputStream out = new FileOutputStream(path);
//		out.write(b);
//		out.flush();
//		out.close();
//		return true;
//	}
}
