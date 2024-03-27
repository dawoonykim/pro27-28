package com.myspring.common;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller("fileDownloadControllerThumbnail")
public class FileDownloadController2 {

	private static String CURR_IMAGE_REPO_PATH = "C:\\spring\\image_repo2";
	private static Logger logger = LoggerFactory.getLogger(FileDownloadController2.class);

	@RequestMapping(value = "/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response)
			throws Exception {

		logger.info("imageFileName : " + imageFileName);
		OutputStream out = response.getOutputStream();
		String downFile = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File file = new File(downFile);


		response.setHeader("Cache-Control", "no-cache");
	
		String encodedFileName = URLEncoder.encode(imageFileName, "UTF-8");
		response.addHeader("Content-disposition", "attachment; fileName=" + encodedFileName);

		System.out.println("서버에 있는 다운 파일" + downFile);
		
		// Thumbnail 처리
		// 확장자를 제외한 원본 이미지 파일의 이름 가져오기
		
		int lastIndex=imageFileName.lastIndexOf(".");
		String fileName=imageFileName.substring(0,lastIndex);
		
		File thumbnail=new File(CURR_IMAGE_REPO_PATH+"\\"+"thumbnail"+"\\"+fileName+".png");
		
		if(file.exists()) {
			thumbnail.getParentFile().mkdir();
			Thumbnails.of(file).size(500, 500).outputFormat("png").toFile(thumbnail);
		}

		// 파일에 들어갈 파일 인풋스트림객체 생성
//		FileInputStream in = new FileInputStream(file);
		FileInputStream in = new FileInputStream(thumbnail);
		byte[] buffer = new byte[1024 * 8];

		while (true) {
			int count = in.read(buffer);
			if (count == -1) {
				break;
			}

			out.write(buffer, 0, count);

		}

		in.close();
		out.close();

	}
}
