package io.github.saviomisael.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "files")
public class File implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "file_data")
    private byte[] fileData;

    @Column(name = "content_type", length = 100)
    private String contentType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String extension) {
        this.contentType = extension;
    }
}
