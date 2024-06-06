package com.blog.app.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//filename
		String name = file.getOriginalFilename();
		
		//random filename generate
		String randomId = UUID.randomUUID().toString();
		String filename1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		String filepath = path+File.separator+filename1;
			
		File f = new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
			
		System.out.println("test1");
		Files.copy(file.getInputStream(), Paths.get(filepath));
		System.out.println("test2");
		return filename1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullpath = path+File.separator+fileName;
		InputStream is = new FileInputStream(fullpath);
		return is;
	}

}
