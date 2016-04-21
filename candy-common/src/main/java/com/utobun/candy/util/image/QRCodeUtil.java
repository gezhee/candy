package com.utobun.candy.util.image;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {
    /**
     * @Title: parseQRcode
     * @Description: 解析二维码
     * @param src：二维码图片
     * @throws NotFoundException
     * @throws IOException：
     * @return: String 返回解析的文字
     */
    public static String parseQRcode(String src) throws NotFoundException, IOException{
        File file = new File(src);
        BufferedImage image = ImageIO.read(file);;
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer  binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
        Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
        return result.toString();
    }
    
    
    /**
     * @Title: createImage
     * @Description: 生成二维码图像流
     * @param content:二维码内容
     * @param logoPath：logo文件位置
     * @param needCompress：是否压缩
     * @return
     * @throws Exception：
     * @return: BufferedImage
     * @throws
     */
    private static BufferedImage createImage(String content,
            int qrCodeSize) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);
        
        BufferedImage image=MatrixToImageWriter.toBufferedImage(bitMatrix);
        return image;
    }  
    
    /**
     * @Title: insertImage
     * @Description: 二维码中插入图片
     * @param source：插入图片的二维码
     * @param imgPath：插入的图片路径
     * @param logoWidth：插入的logo宽度
     * @param logoHeight：插入的logo高度
     * @param qrCodesize：二维码的大小
     * @param needCompress：是否压缩
     * @throws Exception：
     * @return: void
     * @throws
     */
    private static void insertImage(BufferedImage source, String imgPath,
            int logoWidth, int logoHeight, int qrCodesize, boolean needCompress) throws Exception {  
        File file = new File(imgPath);
        if (!file.exists()) {
            return;
        }  
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > logoWidth) {
                width = logoWidth;
            }
            if (height > logoHeight) {
                height = logoHeight;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();
            src = image;
        }  
        // 插入LOGO  
        Graphics2D graph = source.createGraphics();
        int x = (qrCodesize - width) / 2;
        int y = (qrCodesize - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
    
    /**
     * @Title: encode
     * @Description:生成带logo的二维码
     * @param content：二维码内容
     * @param logoPath：logo文件位置
     * @param target：生成二维码的文件
     * @param charset：编码
     * @param logoWidth：logo的宽度
     * @param logoHeight：logo高度
     * @param qrCodesize：二维码大小
     * @param needCompress：是否压缩
     * @throws Exception：
     * @return: void
     * @throws
     */
    public static void generateLogoQRcode(QRParam param) throws Exception {
        String content = param.getContent();
        String logoPath = param.getLogoPath();
        String target = param.getTarget();
        String charset = param.getCharset();
        int qrCodesize = param.getQrCodesize();
        int logoWidth = param.getLogoWidth();
        int logoHeight = param.getLogoHeight();
        boolean needCompress = param.isNeedCompress();
        BufferedImage image = QRCodeUtil.createImage(content,qrCodesize);
        // 插入图片 
        QRCodeUtil.insertImage(image, logoPath,logoWidth,logoHeight,qrCodesize, needCompress);
        ImageIO.write(image, charset, new File(target));
    }
    
    /**
     * @throws Exception 
     * @Title: generateQR
     * @Description: 生成二维码
     * @param content：二维码内容
     * @param target：生成二维码文件夹
     * @param format：文件格式 jpg
     * @param width：二维码宽度
     * @param height：二维码高度
     * @throws WriterException
     * @throws IOException：
     * @return: void
     * @throws
     */
    public static void generateQRcode(String content, String target,
            String format, int qrCodesize) throws Exception{
        BufferedImage image = QRCodeUtil.createImage(content,qrCodesize);
        //生成二维码  
        ImageIO.write(image, format, new File(target));  
    }
    
    public QRParam getQRParam(){
        return new QRParam();
    }
    public class QRParam{
        private String content;
        private String logoPath;
        private int qrCodesize;
        private int logoWidth;
        private int logoHeight;
        private boolean needCompress;
        private String target;
        private String charset;
        
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public String getLogoPath() {
            return logoPath;
        }
        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }
        public int getQrCodesize() {
            return qrCodesize;
        }
        public void setQrCodesize(int qrCodesize) {
            this.qrCodesize = qrCodesize;
        }
        public int getLogoWidth() {
            return logoWidth;
        }
        public void setLogoWidth(int logoWidth) {
            this.logoWidth = logoWidth;
        }
        public int getLogoHeight() {
            return logoHeight;
        }
        public void setLogoHeight(int logoHeight) {
            this.logoHeight = logoHeight;
        }
        public boolean isNeedCompress() {
            return needCompress;
        }
        public void setNeedCompress(boolean needCompress) {
            this.needCompress = needCompress;
        }
        public String getTarget() {
            return target;
        }
        public void setTarget(String target) {
            this.target = target;
        }
        public String getCharset() {
            return charset;
        }
        public void setCharset(String charset) {
            this.charset = charset;
        }
    }
    
}
