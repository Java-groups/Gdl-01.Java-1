package com.softserve.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@RestController
@RequestMapping("/firebase")
public class FirebaseTest {
	
	private StorageOptions storageOptions;
	
	private final String bucketName;
	private final String projectId;
	private final String serviceAccountKey;
	
	public FirebaseTest(@Value("${firebase.bucket-name}") String bucketName,
						@Value("${firebase.project-id}") String projectId,
						@Value("${firebase.service-account-key}") String serviceAccountKey) {
		this.bucketName = bucketName;
		this.projectId = projectId;
		this.serviceAccountKey = serviceAccountKey;
	}
	
	@PostMapping("/upload")
	public String[] uploadTest(@RequestParam("file") MultipartFile multipartFile) {
		try {
			return upload(multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
			return new String[]{"Success"};
		}
	}

	private String[] upload(MultipartFile multipartFile) throws IOException {
		final String FILE_URL = "fileUrl";
		
		FileInputStream serviceAccount = new FileInputStream(serviceAccountKey);

		this.storageOptions = StorageOptions.newBuilder().setProjectId(projectId)
							 .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		File file = convertMultiPartToFile(multipartFile);
		Path filePath = file.toPath();
		
		String objectName = generateFileName(multipartFile);
		Storage storage = storageOptions.getService();

		BlobId blobId = BlobId.of(bucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
		
		storage.create(blobInfo, Files.readAllBytes(filePath));
		
		file.delete();
		return new String[] { FILE_URL, objectName };
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
        
		File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        
        fos.write(file.getBytes());
        fos.close();
        
        return convertedFile;
    }
	
	private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

}
