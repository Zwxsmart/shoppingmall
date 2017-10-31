package com.lanwantec.manage.utils;

import com.lanwantec.manage.ueditor.PathFormat;
import com.lanwantec.manage.ueditor.define.AppInfo;
import com.lanwantec.manage.ueditor.define.BaseState;
import com.lanwantec.manage.ueditor.define.State;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

public  class ImageUtils {


    /**
     * 文件扩展.
     */
    private static final String[] FILE_EXTS = { "JPG", "PNG", "GIF" };
    /**
     * Magic bytes in a file with above extension.
     */
    private static final byte[][] FILE_MAGS = new byte[][] {
            new byte[] { (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0 }, // JPG
            new byte[] { (byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47 }, // PNG
            new byte[] { (byte) 0x47, (byte) 0x49, (byte) 0x46, (byte) 0x38 } // GIF
    };

    /**
     * 将数组转换为字符串
     *
     * @param obj
     * @return
     */
    private static String arrayToString(Object[] obj) {
        if (obj == null || obj.length == 0)
            return null;
        String arrayStr = "";
        for (Object o : obj) {
            arrayStr += o.toString();
        }
        return arrayStr;
    }

    /**
     * 根据文件扩展名得到图片文件类型
     *
     * @param fileName
     *            file name
     * @return file format, null if unsupported.
     * @throws ImageFormatException
     */
    public static String getFileFormatByExt(String fileName) throws ImageFormatException {
        int dp = fileName.lastIndexOf(".");
        if (dp == -1)
            return null;
        String ext = fileName.substring(dp + 1).toUpperCase();
        if (ext.equals("JPEG"))
            ext = "JPG"; // JPEG is JPG
        if (arrayToString(FILE_EXTS).indexOf(ext) == -1)
            throw new ImageFormatException(fileName+":不支持此扩展名的图片！");
        return ext;
    }

    /**
     * 根据文件内容判断图片格式.
     *
     * @param contents
     *            file contents
     * @return file format, null if unsupported.
     */
    public static String getFileFormatByContent(byte[] contents) {
        for (int i = 0; i < FILE_MAGS.length; i++) {
            byte[] mag = FILE_MAGS[i];
            if (contents.length >= mag.length) {
                if (Arrays.equals(Arrays.copyOf(contents, mag.length), mag)) {
                    return FILE_EXTS[i];
                }
            }
        }
        return null;
    }

    /**
     * 判断是不是图片(非严格）
     *
     * @param resFile
     * @return
     * @throws IOException
     */
    public static boolean isImage(InputStream resFile,String fileName) throws IOException {
        // 判断是否为合格的图片文件
        try {
            if (arrayToString(FILE_EXTS).lastIndexOf(
                    getFileFormatByExt(fileName)) == -1)
                return false;
        } catch (ImageFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }

        ImageInputStream iis = null;
        boolean isImg = false;
        iis = ImageIO.createImageInputStream(resFile);
        if (iis != null) {
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (iter.hasNext()) {
                isImg = true;
            }
        }
        if (iis != null) {
            iis.close();
        }
        return isImg;
    }

    /**
     * 图片裁剪
     *
     * @param src
     * @param dest
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws IOException
     * @throws ImageFormatException
     */

    public static void cutImage(InputStream stream,String src, String dest, int x, int y,
                                int width, int height) throws IOException, ImageFormatException {

        if (ImageUtils.isImage(stream,src)==false) {
            throw new ImageFormatException(src+":无效的图片文件格式！");
        }

        String pictureType = "jpg";
        pictureType = getFileFormatByExt(src);

        Iterator iterator = ImageIO.getImageReadersByFormatName(pictureType);
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x, y, width, height);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, pictureType, new File(dest));

    }

    public static void cutImageCenter(InputStream stream, String src, String dest, int width, int height) throws IOException, ImageFormatException {

        if (ImageUtils.isImage(stream,src)==false) {
            throw new ImageFormatException(src+":无效的图片文件格式！");
        }

        String pictureType = "jpg";
        pictureType = getFileFormatByExt(src);

        BufferedImage sr = ImageIO.read(stream);
        int srcHeight = sr.getHeight(); // 源图宽度
        int srcWidth = sr.getWidth(); // 源图高度

        int x=0,y=0;
        if (srcHeight-height>0 && height>0){
            y=(srcHeight-height)/2;
        }
        if (srcWidth-width>0 && width>0){
            x=(srcWidth-width)/2;
        }
        if (x==0){
            width =srcWidth;
        }
        if (y==0){
            height = srcHeight;
        }

        Iterator iterator = ImageIO.getImageReadersByFormatName(pictureType);
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();

        Rectangle rect = new Rectangle(x, y, width, height);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, pictureType, new File(dest));

    }

    /**
     * 计算需要改变的尺寸
     * @param scaleRate
     * @return
     * @throws IOException
     * @throws ImageFormatException
     */
    private static BufferedImage changeSize(InputStream stream,double scaleRate) throws IOException, ImageFormatException{
        BufferedImage bi = ImageIO.read(stream);
        int srcHeight = bi.getHeight(); // 源图宽度
        int srcWidth = bi.getWidth(); // 源图高度
        if (srcWidth == 0 || srcHeight == 0) {
            throw new ImageFormatException("图片的尺寸为0！");
        }
        Double width = srcWidth * scaleRate;
        Double height = srcHeight * scaleRate;
        srcWidth = width.intValue();
        srcHeight = height.intValue();
        BufferedImage tag = new BufferedImage(srcWidth, srcHeight,BufferedImage.TYPE_INT_RGB);
        return tag;
    }

    /**
     * 计算需要改变的尺寸
     * @param srcImageFile
     * @return
     * @throws IOException
     * @throws ImageFormatException
     */
    private static BufferedImage changeSize(InputStream srcImageFile,int srcHeight,int srcWidth,int width,int height) throws IOException, ImageFormatException{
        BufferedImage bi = ImageIO.read(srcImageFile);
        if (srcWidth == 0 || srcHeight == 0) {
            throw new ImageFormatException("源图片的尺寸为0！");
        }
        if (width==0 && height==0){
            throw new ImageFormatException("设定图片的尺寸不能全为0！");
        }
        if (width==0){
            Double scale = height * 100.0 /srcHeight;
            srcWidth = (int) (srcWidth * scale/100);
            srcHeight =(int) (srcHeight * scale/100);
        }else if (height==0){
            Double scale = width * 100.0 /srcWidth;
            srcWidth = (int) (srcWidth * scale/100);
            srcHeight =(int) (srcHeight * scale/100);
        }else if (width<srcWidth || height<srcHeight){
            if (width*100/srcWidth <= height*100/srcHeight){
                srcHeight = srcHeight * 100*width /srcWidth;
                srcHeight = srcHeight /100;
                srcWidth = width;
            }else{
                srcWidth = srcWidth * 100* height /srcHeight;
                srcWidth = srcWidth /100;
                srcHeight = height;
            }
        }else{
            if (width*100/srcWidth > height*100/srcHeight){
                srcWidth = srcWidth * 100* height /srcHeight;
                srcWidth = srcWidth /100;
                srcHeight = height;
            }else{
                srcHeight = srcHeight * 100*width /srcWidth;
                srcHeight = srcHeight /100;
                srcWidth = width;
            }
        }

        BufferedImage tag = new BufferedImage(srcWidth, srcHeight,BufferedImage.TYPE_INT_RGB);
        return tag;
    }



    /**
     * 预处理png图片

     * @param tag
     * @param pictureType
     * @return
     */
    private static BufferedImage settleImage(Image image,BufferedImage tag,String pictureType){
        Graphics2D g = tag.createGraphics();
        if (pictureType.equalsIgnoreCase("png")) {
            tag = g.getDeviceConfiguration().createCompatibleImage(
                    tag.getWidth(), tag.getHeight(), Transparency.TRANSLUCENT);
            g.dispose();
            g = tag.createGraphics();
            g.setColor(new Color(255, 0, 0));
            g.setStroke(new BasicStroke(1));
        }
        g.drawImage(image, 0, 0, null); // 绘制
        g.dispose();
        return tag;
    }
    private static void drawImage(BufferedImage tag,Image image,String srcImageFile,String resultFile) throws ImageFormatException, IOException{
        String pictureType = "JPG";
        pictureType = getFileFormatByExt(srcImageFile);
        tag=settleImage(image,tag,pictureType);
        ImageIO.write(tag, pictureType, new File(resultFile));// 输出到文件流
    }
    /**
     * 按比例缩放图片
     *
     * @param srcImageFile
     *            源图片文件
     *            输出图片文件
     * @param scaleRate
     *            缩放比例
     * @throws IOException
     * @throws ImageFormatException
     */
    public static State scaleImage(InputStream stream, String srcImageFile, String resultFile,
                                   double scaleRate) throws IOException, ImageFormatException {
            State state = null;
        try {
            state = new BaseState(true);
            if (ImageUtils.isImage(stream,srcImageFile)==false) {
                throw new ImageFormatException(srcImageFile+":无效的图片文件格式！");
            }
            BufferedImage tag =changeSize(stream,scaleRate);
            BufferedImage bi = ImageIO.read(stream);
            Image image = bi.getScaledInstance(tag.getWidth(), tag.getHeight(),Image.SCALE_DEFAULT);
            drawImage(tag,image,srcImageFile,resultFile);
            state.putInfo("url", PathFormat.format(resultFile));
            return state;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }


    /**
     * 根据指定宽度、高度，计算自动比例
     * 例如：源图片宽度：800，高度：900，设定的宽度600，高度500，那么计算处理后的图片宽444，高500
     * @param srcImageFile
     * @param resultFile
     * @param width
     * @param height
     * @throws IOException
     * @throws ImageFormatException
     */
    public static State scaleImageWithWidthHeight(InputStream stream,int srcHeight,int srcWidth,String srcImageFile, String resultFile,
                                                       int width,int height) throws IOException, ImageFormatException {

        State state = null;
        try {
            state = new BaseState(true);
            if (ImageUtils.isImage(stream,srcImageFile)==false) {
                throw new ImageFormatException(srcImageFile+":无效的图片文件格式！");
            }
            BufferedImage tag =changeSize(stream,srcHeight,srcWidth,width,height);
            BufferedImage bi = ImageIO.read(stream);
            Image image = bi.getScaledInstance(tag.getWidth(), tag.getHeight(),Image.SCALE_DEFAULT);
            drawImage(tag,image,srcImageFile,resultFile);
            state.putInfo("url", PathFormat.format(resultFile));
            return state;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

}

class ImageFormatException extends Exception {

    public ImageFormatException(String message) {
        super(message);
    }

}




