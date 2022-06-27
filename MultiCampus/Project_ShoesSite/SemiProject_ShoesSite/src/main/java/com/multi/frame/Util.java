package com.multi.frame;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static void saveFile(MultipartFile mf) {
		String dir = "C:\\semiproject\\Team2-Semi_Project3\\Team2-Semi_Project\\SemiProject_ShoesSite\\src\\main\\resources\\static\\assets\\image\\";
		byte [] data;
		String filename = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
			FileOutputStream fo = 
					new FileOutputStream(dir+filename);
			fo.write(data);
			fo.close();
		}catch(Exception e) {
			
		}
		
	}
	
}




