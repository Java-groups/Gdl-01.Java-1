package com.softserve.util.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.softserve.exceptions.ArticleException;
import com.softserve.model.Article;
import com.softserve.util.FireBaseProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Repository
public class FireBasePorcessImpl implements FireBaseProcess {

    private StorageOptions storageOptions;

    private final String bucketName;

    private final String projectId;

    private final String serviceAccountKey;

    public FireBasePorcessImpl(@Value("${firebase.bucket-name}") String bucketName,
                               @Value("${firebase.project-id}") String projectId,
                               @Value("${firebase.service-account-key}") String serviceAccountKey){
        this.bucketName = bucketName;
        this.projectId = projectId;
        this.serviceAccountKey = serviceAccountKey;
    }
    @Override
    public String upload(MultipartFile articleImage, Article article) throws ArticleException {



        try {
            FileInputStream serviceAccount = new FileInputStream(serviceAccountKey);
            this.storageOptions = StorageOptions.newBuilder().setProjectId(projectId)
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            File file = convertMultiPartToFile(articleImage);
            Path filePath = file.toPath();

            String articleImageName = generateFileName(articleImage);
            Storage storage = storageOptions.getService();

            BlobId blobId = BlobId.of(bucketName, articleImageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(articleImage.getContentType()).build();

            storage.create(blobInfo, Files.readAllBytes(filePath));

            file.delete();
            return  articleImageName;
        } catch (FileNotFoundException e) {
            log.error("Error with ServiceAccountKey -> {}", e);
            throw new ArticleException("Error when was uploading the article image.");
        } catch (IOException exception){
            log.error("Error when was trying create the image resource -> {}", exception);
            throw new ArticleException("Error when was uploading the article image.");
        }
    }

    /**
     *
     * @param file Article image in Multipart file
     * @return File created
     * @throws IOException
     */
    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);

        fos.write(file.getBytes());
        fos.close();

        return convertedFile;
    }

    /**
     *
     * @param articleMultipart Article image in Multipart file
     * @return Article image's name
     */
    private String generateFileName(MultipartFile articleMultipart) {
        return articleMultipart.getOriginalFilename().replace(" ", "_");
    }
}
