package com.project.AIPoweredBRD.service;

import java.io.File;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

@Service
public class TextService {

	public String extractText(File file) {
		// TODO Auto-generated method stub
		final Tika tika= new Tika();
		
		try {
			return tika.parseToString(file);
		}catch(Exception e) {
			System.out.println("Unable to extract data");
		}
		
		
		
		return null;
	}

}
