package SFF_Reader.src.sff_handler;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import javax.imageio.metadata.*;

import uk.co.mmscomputing.imageio.sff.SFFImageReader;
import uk.co.mmscomputing.imageio.sff.SFFImageReaderSpi;


public class sff_handler {
    
    protected void addImage(String fn, BufferedImage img){
      
        Object md=img.getProperty("iiometadata");
        if((md!=Image.UndefinedProperty)&&(md!=null)&&(md instanceof IIOMetadata)){
        }
    
        System.out.println("Image.Width ="+img.getWidth());
        System.out.println("Image.Height ="+img.getHeight());
        System.out.println("Image.Type ="+img.getType());

        // output image to file
        try{
            File outputfile=new File(fn + ".png");
            ImageIO.write(img, "png", outputfile);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    
      }
    
      public  void open(String filename)throws IOException{
        long time=System.currentTimeMillis();

        ImageReader reader = new SFFImageReader(new SFFImageReaderSpi());
        File f=new File(filename);
        ImageInputStream iis=ImageIO.createImageInputStream(f);
        try{
          reader.setInput(iis,true);
          try{
            // Multiple images in SFF file will be separated
            for(int i=0; true; i++){
              IIOMetadata md=reader.getImageMetadata(i);
              System.out.println("metadata ="+md);
              String fn = f.getName();
              String[] fn_split=fn.split("\\.");
              addImage(fn_split[0] + "_"+i,reader.read(i));
            }
          }catch(IndexOutOfBoundsException ioobe){}
        }catch(Error e){
          System.out.println("9\b"+getClass().getName()+".open:\n\t"+e);
          e.printStackTrace();
          throw e;
        }finally{
          iis.close();
        }
        time=System.currentTimeMillis()-time;
        System.out.println("Opened : "+filename);
        System.out.println("Time used to load images : "+time);
      }

}
