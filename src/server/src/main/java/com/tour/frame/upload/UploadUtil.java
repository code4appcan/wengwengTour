package com.tour.frame.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 上传图片　工具类 大图片路径,生成小图片路径, 
 * 大图片文件名,生成小图片文名, 
 * 生成小图片宽度,生成小图片高度, 是否等比缩放(默认为true))
 * 
 * @author lanyuan
 * @Email：mmm333zzz520@163.com
 * @date：2014-12-05
 */
public class UploadUtil
{
	private String imagePath = "/uploadFile/" + new SimpleDateFormat("yyyyMMddHH").format(new Date()) + "";// 配置图片路径

	/**
	 * 
	 * @param getUpload
	 *            路径
	 * @param getUploadContentType
	 *            文件类型
	 * @param getUploadFileName
	 *            原文件名
	 * @return
	 * @throws IOException
	 */
	public void uploadImage1(HttpServletRequest request,MultipartFile file, String getUploadFileName) throws IOException
	{

		String getImagePath = request.getSession().getServletContext().getRealPath(imagePath);

		File image = new File(getImagePath);
		if (!image.exists())
		{
			image.mkdir();
		}

		// 得到文件的新名字
		String fileNewName = generateFileName(getUploadFileName);

		// 最后返回图片路径
		imagePath = imagePath + "/" + fileNewName;

		BufferedImage srcBufferImage = ImageIO.read(file.getInputStream());
		BufferedImage scaledImage;
		ScaleImage scaleImage = ScaleImage.getInstance();
		int yw = srcBufferImage.getWidth();
		int yh = srcBufferImage.getHeight();
		int w = 400, h = 300;
		// 如果上传图片 宽高 比 压缩的要小 则不压缩
		if (w > yw && h > yh)
		{
			FileOutputStream fos = new FileOutputStream(getImagePath + "/" + fileNewName);

			FileInputStream fis = (FileInputStream) file.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
			{
				fos.write(buffer, 0, len);
			}
		}
		else
		{
			scaledImage = scaleImage.imageZoomOut(srcBufferImage, w, h);
			FileOutputStream out = new FileOutputStream(getImagePath + "/" + fileNewName);
			ImageIO.write(scaledImage, "jpeg", out);

		}
	}


	public String getImagepath()
	{
		return imagePath;
	}

	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}
}