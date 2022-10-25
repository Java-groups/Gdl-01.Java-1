package com.softserve.util;

import com.softserve.dto.NewArticleDTO;
import com.softserve.exceptions.ArticleException;
import com.softserve.model.Article;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FireBaseProcess {
    /**
     *
     * @param articleImage image in multipart form data
     * @return image name when was uploaded successfully to firebase bucket
     */
    String upload(MultipartFile articleImage, Article article) throws ArticleException;
}
