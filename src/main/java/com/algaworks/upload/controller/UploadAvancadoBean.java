package com.algaworks.upload.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.algaworks.upload.util.ArquivoUtil;

@ManagedBean
@ViewScoped
public class UploadAvancadoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<File> arquivos = new ArrayList<>();
    
    @PostConstruct
    public void postConstruct() {
        arquivos = new ArrayList<>(ArquivoUtil.listar());
    }
    
    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        
        try {
            File arquivo = ArquivoUtil.escrever(uploadedFile.getFileName(), uploadedFile.getContents());
            
            arquivos.add(arquivo);
            
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Upload completo", "O arquivo " + arquivo.getName() + " foi salvo!"));            
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
        }
    }
    
    public List<File> getArquivos() {
        return arquivos;
    }
}