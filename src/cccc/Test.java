package cccc;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * description
 *
 * @user cathy
 * @date 2019-11-17 14:43:48
 */
public class Test {
    public static void main(String[] args) {

        Test.commpressPicForSize("C:\\Users\\cathy\\Videos\\file\\ia_100004673.jpg",
                "C:\\Users\\cathy\\Desktop\\ceshi01.jpg", 100, 0.9); // 图片小于100kb

    }

    /**
     * 根据指定大小和指定精度压缩图片
     *
     * @param srcPath     源图片地址
     * @param desPath     目标图片地址
     * @param desFileSize 指定图片大小，单位kb
     * @param accuracy    精度，递归压缩的比率，建议小于0.9
     * @return
     */
    public static String commpressPicForSize(String srcPath, String desPath,
                                             long desFileSize, double accuracy) {
        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(srcPath)) {
            return null;
        }
        if (!new File(srcPath).exists()) {
            return null;
        }
        try {
            File srcFile = new File(srcPath);
            long srcFileSize = srcFile.length();
            System.out.println("源图片：" + srcPath + "，大小：" + srcFileSize / 1024
                    + "kb");

            // 1、先转换成jpg
            Thumbnails.of(srcPath).scale(1f).toFile(desPath);
            // 递归压缩，直到目标文件大小小于desFileSize
            commpressPicCycle(desPath, desFileSize, accuracy);

            File desFile = new File(desPath);
            System.out.println("目标图片：" + desPath + "，大小" + desFile.length()
                    / 1024 + "kb");
            System.out.println("图片压缩完成！");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return desPath;
    }

    /**
     * 图片压缩:按指定大小把图片进行缩放（会遵循原图高宽比例）
     * 并设置图片文件大小
     */
    private static void commpressPicCycle(String desPath, long desFileSize,
                                          double accuracy) throws IOException {
        System.out.println("2222222222222S");
        File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = srcFileJPG.length();
        // 2、判断大小，如果小于指定大小，不压缩；如果大于等于指定大小，压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return;
        }
        // 计算宽高
        BufferedImage bim = ImageIO.read(srcFileJPG);
        int srcWdith = bim.getWidth();
        int srcHeigth = bim.getHeight();
        int desWidth = new BigDecimal(srcWdith).multiply(
                new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeigth).multiply(
                new BigDecimal(accuracy)).intValue();

        Thumbnails.of(desPath).size(desWidth, desHeight)
                .outputQuality(accuracy).toFile(desPath);
        commpressPicCycle(desPath, desFileSize, accuracy);
    }
}

