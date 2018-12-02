package com.fiuady.vision.vision.SaveFaces;

import net.bytebuddy.implementation.bytecode.Throw;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class SaveFaces implements  SaveFacesListener {
    private Mat toSave;
    private String fileName;
    private String filePath;
    static private String BASE_PATH = "Images\\";

    public SaveFaces() {
    }

    public SaveFaces(Mat toSave) {
        this.toSave = toSave;
    }

    public SaveFaces(Mat toSave, String fileName, String filePath) {
        this.toSave = toSave;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public SaveFaces(Mat toSave, String fileName) {
        this.toSave = toSave;
        this.fileName = fileName;
    }


    public Mat getToSave() {
        return toSave;
    }

    public void setToSave(Mat toSave) {
        this.toSave = toSave;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void makePath(){
        filePath = BASE_PATH + fileName;
    }

    @Override
    public String saveImage() {
        boolean save = Imgcodecs.imwrite(filePath, toSave);
        if(!save){
            return "No se pudo guardar la imagen";
        }else{
            return "Se guardó la imagen correctamente";
        }
    }
}
