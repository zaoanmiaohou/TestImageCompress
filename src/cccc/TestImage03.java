package cccc;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * description
 *
 * @user cathy
 * @date 2019-10-05 09:27:20
 */
public class TestImage03 {

    public static void main(String[] args) throws IOException {
        long timeStart = System.currentTimeMillis();
        String imgLocalUrl = "C:\\Users\\cathy\\Videos\\微信图片_201909290108132.JPG";

        byte[] imgBytes = getByteByPic(imgLocalUrl);
        byte[] resultImg = compressUnderSize(imgBytes,22 * 1024);
        byte2image(resultImg,"C:\\Users\\cathy\\Videos\\file\\ID09.jpg");
        long timeEnd = System.currentTimeMillis();
        System.out.println("耗时："+ (timeEnd - timeStart));
    }

    public static byte[] getByteByPic(String imageUrl) throws IOException{
        long start = System.currentTimeMillis();
        File imageFile = new File(imageUrl);
        InputStream inStream = new FileInputStream(imageFile);
        BufferedInputStream bis = new BufferedInputStream(inStream);
        BufferedImage bm = ImageIO.read(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 得到图片类型，jpg
        String type = imageUrl.substring(imageUrl.length() - 3);
        ImageIO.write(bm, type, bos);
        bos.flush();
        byte[] data = bos.toByteArray();
        System.out.println("文件转byte耗时: " + (System.currentTimeMillis() - start));
        return data;
    }
    /**
     * 将图片压缩到指定大小以内
     *
     * @param srcImgData 源图片数据
     * @param maxSize 目的图片大小
     * @return 压缩后的图片数据
     */
    public static byte[] compressUnderSize(byte[] srcImgData, long maxSize) {
        long start = System.currentTimeMillis();
        while(srcImgData.length > maxSize){
            try {
                srcImgData = compress(srcImgData);

            } catch (IOException e) {
                throw new IllegalStateException("压缩图片过程中出错，请及时联系管理员！", e);
            }
        }
        System.out.println("压缩耗时" + (System.currentTimeMillis() - start));
        return srcImgData;
    }

    /**
     * 按照 宽高 比例压缩
     *
     * @param srcImgData 待压缩图片输入流
     * @return 压缩后图片数据
     * @throws IOException 压缩图片过程中出错
     */
    public static byte[] compress(byte[] srcImgData) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
        // 源图宽度
        int width = (int) (bi.getWidth() * 0.9);
        // 源图高度
        int height = (int) (bi.getHeight() * 0.9);

        Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = tag.getGraphics();
//        g.setColor(Color.RED);
        // 绘制处理后的图
        g.drawImage(image, 0, 0, null);
        g.dispose();

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ImageIO.write(tag, "JPEG", bOut);

        return bOut.toByteArray();
    }

    /**
     * byte数组到图片
     */
    public static void byte2image(byte[] data,String path){
        long start = System.currentTimeMillis();
        if(data.length<3||path.equals("")) {
            return;
        }
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
        System.out.println("绘制耗时:" + (System.currentTimeMillis() - start));
    }
}
