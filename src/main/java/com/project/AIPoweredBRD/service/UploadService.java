package com.project.AIPoweredBRD.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.AIPoweredBRD.entity.Scenario;
import com.project.AIPoweredBRD.repository.ScenarioRepository;

@Service
public class UploadService {

	@Autowired
	ScenarioRepository scenarioRepo;
	
	@Value("${file.upload_Dir}")
	private String uploadDir;
	private Path filePath;
	
	@Autowired
	private TextService textService;
	
	@Autowired
	private EmbeddingService embeddingService;
	
	
	public void uploadFile(MultipartFile file, String uploadedBy, String TicketId) {
		// TODO Auto-generated method stub
		Scenario newScenario= new Scenario();
		String fileName=file.getOriginalFilename();
		
		newScenario.setBRDName(fileName);
		newScenario.setTicketId(TicketId);
		newScenario.setUploadedBy(uploadedBy);
		
		newScenario.setUploadedDate(new Date(System.currentTimeMillis()));
		
		try {
		Path uploadPath= Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectory(uploadPath);
		}
		
		filePath= uploadPath.resolve(fileName);
		Files.copy(file.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
		
		newScenario.setUrl(filePath.toString());
		
		}catch (IOException e) {
			System.out.println("Unable to store file:"+ fileName +e.getMessage() );
		}
		
		String ExtractedText = textService.extractText(filePath.toFile());
		
		String embedding= embeddingService.GenerateEmbedding(ExtractedText);
		
		
		newScenario.setEmbedding(embedding);
		
		scenarioRepo.save(newScenario);
		
		
	}

	public String SearchUploader(String brdName) {
		// TODO Auto-generated method stub
		
		Scenario scenario= scenarioRepo.findByBRDName(brdName);
		
		if(scenario==null) {
			return "BRD not present in the system";
		}
		
		return scenario.getUploadedBy();
	}
	
	
	

	public String SearchURL(String bRDName) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	
	public List<Scenario> ExactSearch(String keyword) {
		
		List<Scenario> Scenarios=scenarioRepo.findByExactSearch(keyword);
		
		return Scenarios;
		
	}
	
	public List<Scenario> SemanticSearch(String text){
		
		String  queryEmbedding = embeddingService.GenerateEmbedding(text);
		
		JSONArray arr = new JSONArray(queryEmbedding);
	    float[] emb = new float[arr.length()];
	    for (int i = 0; i < arr.length(); i++) 
	    	emb[i] = (float) arr.getDouble(i);
	    
	    List<Scenario> allRecords = scenarioRepo.findAll();
	    Map<Scenario, Double> scenarioScores= new HashMap<>();
	    

	    for (Scenario r : allRecords) {
	        float[] recordEmbedding = embeddingService.parseEmbedding(r.getEmbedding()); // ✅ convert string → float[]
	        double score = (double) embeddingService.cosineSimilarity(emb, recordEmbedding);
	       // System.out.println(r.getTicketNumber() + " similarity: " + score);
	        
	        if(score>=0.5)
	        	scenarioScores.put(r, score);
	        
	    }
	    
	    return scenarioScores.entrySet().stream()
        .sorted(Map.Entry.<Scenario, Double>comparingByValue().reversed())
        .limit(5)  // top 5 most similar
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
	
		
	}
	
}
  