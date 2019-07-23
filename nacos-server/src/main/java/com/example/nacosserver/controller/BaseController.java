package com.example.nacosserver.controller;



import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;



import java.io.*;
import java.util.List;

public class BaseController {


    public static void main(String[] args) {


        File svg = new File("D:\\导入.svg");

        File png = new File("D:\\副本.png");
        InputStream in = null;
        OutputStream out = null;
        try {

            in = new FileInputStream(svg);
            out = new FileOutputStream(png);
            out = new BufferedOutputStream(out);

            Transcoder transcoder = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(in);
            TranscoderOutput output = new TranscoderOutput(out);
            transcoder.transcode(input, output);
        }catch(Exception e){
            e.printStackTrace();
        } finally {

        }


    }


}
