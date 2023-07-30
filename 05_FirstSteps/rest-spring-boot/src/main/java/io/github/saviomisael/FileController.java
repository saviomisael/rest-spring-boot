package io.github.saviomisael;

import io.github.saviomisael.models.File;
import io.github.saviomisael.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class FileController {
    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/files", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity saveFile(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
        if (file.getSize() / 1024 / 1024 > 5) {
            return ResponseEntity.badRequest().body("File size must be no more than 5 megabytes");
        }

        File fileDomain = new File();
        fileDomain.setFileData(file.getBytes());
        fileDomain.setContentType(file.getContentType());

        this.fileService.saveFile(fileDomain);
        return ResponseEntity.created(new URI("/files")).body("File created with id " + fileDomain.getId() + ".");
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity serveFile(@PathVariable("fileId") Integer fileId) {
        var file = this.fileService.getFileById(fileId);

        var resource = new ByteArrayResource(file.getFileData());
        var filename = file.getId() + "." + file.getContentType().split("/")[1];

        return ResponseEntity.ok().contentType(MediaType.valueOf(file.getContentType()))
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.inline().filename(filename).build().toString()) // inline show in browser / attachment download file
                .body(resource);
    }
}
