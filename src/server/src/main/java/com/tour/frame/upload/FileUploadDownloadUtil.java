package com.tour.frame.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jettison.json.JSONObject;

import net.coobird.thumbnailator.Thumbnails;

public class FileUploadDownloadUtil {
	// private static final Logger log =
	// LoggerFactory.getLogger(FileUploadDownloadUtil.class);
	/**
	 * 上传文件到服务器
	 * 
	 * @param request
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static String upload(HttpServletRequest request, String type) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String saveFileName = null;
		if (isMultipart) {
			String savePath = request.getSession().getServletContext().getRealPath("/") + "/upload/";
			String tempPath = request.getSession().getServletContext().getRealPath("/") + "/upload/temp/";
			File saveFile = new File(savePath);
			File tempFile = new File(tempPath);
			if (!saveFile.isDirectory())
				saveFile.mkdirs();
			if (!tempFile.isDirectory())
				tempFile.mkdirs();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 4);
			factory.setRepository(tempFile);
			ServletFileUpload uploader = new ServletFileUpload(factory);
			uploader.setSizeMax(20 * 1024 * 1024);
			List<FileItem> fileItems = uploader.parseRequest(request);
			for (FileItem item : fileItems) {

				if (item.isFormField()) {
					// funName=item.getString();
				} else {
					// String fileName=item.getName();
					// String
					// fix=fileName.substring(fileName.lastIndexOf(".")+1);
					String fix = type;
					Date nowDate = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
					String fileName = sdf.format(nowDate);

					fileName += System.currentTimeMillis();
					fileName += "." + fix;
					saveFileName = "/upload/" + fileName;

					File file = new File(savePath + fileName);
					item.write(file);
				}
			}
		}
		return saveFileName;
	}

	/**
	 * 上传图片 到指定文件夹
	 * 
	 * @param request
	 * @param type
	 *            后缀名
	 * @param dir
	 *            文件夹名称
	 * @param dir
	 *            文件名称
	 * @return
	 * @throws Exception
	 */
	public static synchronized String uploadPic2Path(HttpServletRequest request, String type, String dir, String name)
			throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String saveFileName = null;
		String imagePath = "WEB-INF/static/upload/" + dir + "/";
		String x = request.getParameter("length");
		String y = request.getParameter("wide");
		if (isMultipart) {
			String headShowServicePath = request.getSession().getServletContext().getRealPath("/") + imagePath;
			Date nowDate = new Date();
			String fileName = name + nowDate.getTime();
			File headShowFile = new File(headShowServicePath);
			if (!headShowFile.isDirectory())
				headShowFile.mkdirs();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 4);
			factory.setRepository(headShowFile);
			ServletFileUpload uploader = new ServletFileUpload(factory);
			uploader.setSizeMax(20 * 1024 * 1024);
			List<FileItem> fileItems = uploader.parseRequest(request);
			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					// funName=item.getString();
				} else {
					String fix = type;
					fileName += "." + fix;
					saveFileName = imagePath + fileName;
					File file = new File(headShowServicePath + fileName);
					item.write(file);
				}
			}
			// 压缩图片
			if (x != null && !"".equals(x) && y != null && !"".equals(y)) {
				saveFileName = thumbnailatorImage(imagePath, fileName, type, Integer.parseInt(x), Integer.parseInt(y));
			}
		}
		return saveFileName;
	}

	/**
	 * 上传头像
	 * 
	 * @param request
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static String uploadHeadShow(HttpServletRequest request, String type, String name) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String saveFileName = null;
		String imagePath = "WEB-INF/static/upload/headshow/";
		String x = request.getParameter("length");
		String y = request.getParameter("wide");
		if (isMultipart) {
			String headShowServicePath = request.getSession().getServletContext().getRealPath("/") + imagePath;
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName = name + sdf.format(nowDate);
			File headShowFile = new File(headShowServicePath);
			if (!headShowFile.isDirectory())
				headShowFile.mkdirs();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 4);
			factory.setRepository(headShowFile);
			ServletFileUpload uploader = new ServletFileUpload(factory);
			uploader.setSizeMax(20 * 1024 * 1024);
			List<FileItem> fileItems = uploader.parseRequest(request);
			for (FileItem item : fileItems) {

				if (item.isFormField()) {
					// funName=item.getString();
				} else {
					String fix = type;
					fileName += "." + fix;
					saveFileName = imagePath + fileName;
					File file = new File(headShowServicePath + fileName);
					item.write(file);
				}
			}
			// 压缩图片
			if (x != null && !"".equals(x) && y != null && !"".equals(y)) {
				saveFileName = thumbnailatorImage(imagePath, fileName, type, Integer.parseInt(x), Integer.parseInt(y));
			}
		}
		return saveFileName;
	}

	/**
	 * 上传分享图片
	 * 
	 * @param request
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static JSONObject uploadArticleImage(HttpServletRequest request, String type) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		JSONObject saveFileName = new JSONObject();
		String imagePath = "";
		String x = request.getParameter("length");
		String y = request.getParameter("wide");
		if ("4".equals(type)) {
			// 分享上传图片路径
			imagePath = "/upload/articleimage/";
		} else if ("5".equals(type)) {
			// 链接上传图片路径
			imagePath = "/upload/linkimage/";
		} else {
			// 头像上传图片路径
			imagePath = "/upload/headshow/";
		}
		if (isMultipart) {
			String headShowServicePath = request.getSession().getServletContext().getRealPath("/") + imagePath;
			File headShowFile = new File(headShowServicePath);
			if (!headShowFile.isDirectory())
				headShowFile.mkdirs();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 4);
			factory.setRepository(headShowFile);
			ServletFileUpload uploader = new ServletFileUpload(factory);
			uploader.setSizeMax(20 * 1024 * 1024);
			List<FileItem> fileItems = uploader.parseRequest(request);
			for (FileItem item : fileItems) {
				UUID uuid = UUID.randomUUID();
				String fileName = uuid.toString();
				if (item.isFormField()) {
					// funName=item.getString();
				} else {
					String fix = type;
					fileName += "." + fix;
					saveFileName.put(uuid.toString(), imagePath + fileName);
					File file = new File(headShowServicePath + fileName);
					item.write(file);
				}
				// 压缩图片
				if (x != null && !"".equals(x) && y != null && !"".equals(y)) {
					String thumbnailatorName = thumbnailatorImage(imagePath, fileName, type, Integer.parseInt(x),
							Integer.parseInt(y));
					saveFileName.put("thumbnailatorImage", thumbnailatorName);
				}
			}
		}
		return saveFileName;
	}

	/**
	 * 上传压缩压缩并保存图片
	 * 
	 * @param oldSavePath
	 *            原文件路径
	 * @param oldFileName
	 *            原文件名称
	 * @param fix
	 *            文件类型
	 * @param x
	 *            需要压缩的宽度
	 * @param y
	 *            需要压缩的长度
	 * @return
	 * @throws IOException
	 */
	public static String thumbnailatorImage(String oldSavePath, String oldFileName, String fix, int x, int y)
			throws IOException {
		// Thumbnail读取并压缩图片
		BufferedImage waterMarkBufferedImage = Thumbnails.of(oldSavePath + oldFileName)
				// Thumbnail的方法,压缩图片
				.size(x, y)
				// 读取成BufferedImage对象
				.asBufferedImage();
		// 把内存中的图片写入到指定的文件中
		String savePath = oldSavePath + x + "-" + y + "/";
		File saveFile = new File(savePath);
		if (!saveFile.isDirectory())
			saveFile.mkdirs();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 4);
		factory.setRepository(saveFile);
		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setSizeMax(20 * 1024 * 1024);

		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString();
		fileName += "." + fix;
		String saveFileName = savePath + fileName;
		File fileOutPut = new File(saveFileName);
		ImageIO.write(waterMarkBufferedImage, fix, fileOutPut);
		return saveFileName;
	}

	/**
	 * 下载压缩压缩并保存图片
	 * 
	 * @param oldSavePath
	 *            原文件路径
	 * @param oldFileName
	 *            原文件名称
	 * @param fix
	 *            文件类型
	 * @param x
	 *            需要压缩的宽度
	 * @param y
	 *            需要压缩的长度
	 * @return
	 * @throws IOException
	 */
	public static String downloadThumbnailatorImage(String servicePath, String uri, int x, int y) throws IOException {
		// 校验图片是否存在
		String uriSubPath = uri.substring(0, uri.lastIndexOf("/") + 1);// 文件名以前，服务器以后
		String fileName = uri.substring(uri.lastIndexOf("/") + 1, uri.length());// 文件名
		String getThumbnailatorPath = servicePath + uriSubPath + x + "-" + y + "/";
		String saveFileName = getThumbnailatorPath + fileName;
		File downFilePath = new File(getThumbnailatorPath);// 压缩以后的文件夹
		File downFile = new File(saveFileName);// 压缩以后的文件
		if (downFilePath.isDirectory() && downFile.exists()) {
			return saveFileName;
		} else {
			// Thumbnail读取并压缩图片
			// log.error(servicePath+uri);
			BufferedImage waterMarkBufferedImage = Thumbnails.of(servicePath + uri)
					// Thumbnail的方法,压缩图片
					.size(x, y)
					// 读取成BufferedImage对象
					.asBufferedImage();
			if (!downFilePath.isDirectory()) {
				downFilePath.mkdirs();
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 4);
			factory.setRepository(downFilePath);
			ServletFileUpload uploader = new ServletFileUpload(factory);
			uploader.setSizeMax(20 * 1024 * 1024);
			File fileOutPut = new File(saveFileName);
			ImageIO.write(waterMarkBufferedImage, "jpg", fileOutPut);
		}
		return saveFileName;
	}
}
