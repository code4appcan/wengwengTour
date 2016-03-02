package com.tour.frame.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileBean {
	
	
	private String photoId;
	private String photoName;
	 public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	/** 
     * 用日期和随机数格式化文件名避免冲突 
     * @param fileName 
     * @return 
     */  
	public String generateFileName(String fileName) {  
        System.out.println(fileName);  
        SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");  
        String formatDate = sf.format(new Date());  
        int random = new Random().nextInt(10000);  
        int position = fileName.lastIndexOf(".");  
        String extension = fileName.substring(position);  
        return formatDate + random + extension;  
    } 
    
    public static void  main(String args[])
    {
    	FileBean fb=new FileBean();
    	String teston=fb.generateFileName("test.jpg");
    	System.out.println(teston);  
    }

}
