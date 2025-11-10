package com.project.AIPoweredBRD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.AIPoweredBRD.entity.Scenario;
import com.project.AIPoweredBRD.service.UploadService;

@RestController
public class ScenarioController {

	@Autowired
	UploadService uploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, @RequestParam String uploadedBy,@RequestParam String TicketID) {
		
	uploadedBy="anirudh";
	uploadService.uploadFile(file, uploadedBy,TicketID);	
		
		
		return ResponseEntity.ok("File uploaded successfully");
	}
	
	
	@GetMapping("/searchUploader")
	public String SearchUploadedBy(@RequestParam  String BRDName) {
		
		String uploaderName=uploadService.SearchUploader(BRDName);
		return uploaderName;
		
	}
	
	@GetMapping("/searchUrl")
	public String SearchUrl(@RequestParam  String BRDName) {
		
		String url=uploadService.SearchURL(BRDName);
		return url;
		
	}
	
	@GetMapping("/search")
	public List<Scenario> SemanticSearch(@RequestParam String keyword) {
		
		List<Scenario> Scenarios =uploadService.SemanticSearch(keyword);
		
		return Scenarios;
		
	}
	
	
	
	
	
}
