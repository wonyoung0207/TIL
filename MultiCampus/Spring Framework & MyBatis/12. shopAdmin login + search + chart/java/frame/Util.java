package com.multi.frame;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static void saveFile(MultipartFile mf) {
		String dir = "C:\\spring\\shopAdmin\\src\\main\\resources\\static\\img\\";
		String dir2 = "C:\\spring\\shop\\src\\main\\resources\\static\\img\\";
		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
			FileOutputStream fo = 
					new FileOutputStream(dir+imgname);
			fo.write(data);
			fo.close();
			
			FileOutputStream fo2 = 
					new FileOutputStream(dir2+imgname);
			fo2.write(data);
			fo2.close();
		}catch(Exception e) {
			
		}
	}
}




