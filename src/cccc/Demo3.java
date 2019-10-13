package cccc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * description
 *
 * @user cathy
 * @date 2019-09-28 22:25:59
 */
public class Demo3 {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String srcPath = "C:\\Users\\cathy\\Videos\\微信图片_201909290108132.JPG";
        File srcFile = new File(srcPath);
        BufferedImage read = ImageIO.read(srcFile);
        // 图片长宽像素点
        int width = read.getWidth();
        int height = read.getHeight();
        // 图片大小 KB
        double length = srcFile.length() / (double) 1024;
        // 图片宽高比（计算的目的是保持长高比不变，避免图片拉伸）
        double ratio = Math.ceil(width / (double)height);

        // 图片最终大小 KB
        int destLength = 22;
        // 图片最终长度 * 宽度
        double destHeightMultiWidth =  (destLength * width * height) / length;
        // 图片最终宽度
        double destHeight = Math.sqrt(destHeightMultiWidth / ratio);
        // 图片最终长度度
        double destWidth = destHeight * ratio;

        String destPath = "C:\\Users\\cathy\\Videos\\ImageDest08.jpg";

        thumbnail(srcFile, (int)destWidth, (int)destHeight, new FileOutputStream(destPath));
        System.out.println("总耗时: " + (System.currentTimeMillis() - start));
    }

    public static void thumbnail(File srcFile, int width, int height, OutputStream out) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(srcFile);
        Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = tag.getGraphics();
        graphics.setColor(Color.RED);
        // 绘制处理后的图
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        ImageIO.write(tag, "JPEG", out);
    }
}
