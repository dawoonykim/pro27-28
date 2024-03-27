package com.myspring.common;

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

//@Controller("fileDownloadController")
public class FileDownloadController {

	private static String CURR_IMAGE_REPO_PATH = "C:\\spring\\image_repo";
	private static Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

	@RequestMapping(value = "/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response)
			throws Exception {

		logger.info("imageFileName : " + imageFileName);
		OutputStream out = response.getOutputStream();
		String downFile = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File f = new File(downFile);


		response.setHeader("Cache-Control", "no-cache");
	
		String encodedFileName = URLEncoder.encode(imageFileName, "UTF-8");
		response.addHeader("Content-disposition", "attachment; fileName=" + encodedFileName);

		System.out.println("서버에 있는 다운 파일" + downFile);
		

		// 파일에 들어갈 파일 인풋스트림객체 생성
		FileInputStream in = new FileInputStream(f);

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
