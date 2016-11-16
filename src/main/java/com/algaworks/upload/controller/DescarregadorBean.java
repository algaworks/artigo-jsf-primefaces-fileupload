package com.algaworks.upload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class DescarregadorBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private StreamedContent streamedContent;
    
    public void descarregar(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        
        streamedContent = new DefaultStreamedContent(inputStream, 
                Files.probeContentType(file.toPath()), file.getName());
    }
    
    public StreamedContent getStreamedContent() {
        return streamedContent;
    }
}