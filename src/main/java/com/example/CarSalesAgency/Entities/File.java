package com.example.CarSalesAgency.Entities;


import jakarta.persistence.*;

@Entity
public class File {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String filename;
        private String contentType;
        private long size;

    @Lob
    @Column(length = 16777215) // Pour MySQL (16MB)
    private byte[] data;


    public File(String filename, String contentType, long size, byte[] data) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }

    public byte[] getData() {
        return data;
    }

    public File(){}
}
