package com.example.nacosserver.controller;


import com.alibaba.fastjson.JSON;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

import java.io.FileInputStream;
import java.util.List;

public class BaseController {


    public static void main(String[] args) {

       try{


           XWPFDocument document = new XWPFDocument(new FileInputStream("D:\\导入.docx")).getXWPFDocument();

           List<XWPFPictureData> allPictures = document.getAllPictures();

           for(XWPFPictureData ds : allPictures){
               byte[] data = ds.getData();
               String fileName = ds.getFileName();

               System.out.println(fileName);
           }


       }catch(Exception e){

       }


    }
}
