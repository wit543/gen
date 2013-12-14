package gen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

public class generator {

	static int[][] im = new int[100][100];
	static void  in(){
		for(int i =0; i<100;i++){
    		for(int j =0; j<100;j++){
    			im[i][j] =65280;
        	}	
    	}
	}
    public  String generate( String prefix ) throws Exception{

        int x,y = 0;
        int PIX_SIZE = 10;
        int X = 10;
        int Y = 10;

        BufferedImage bi = new BufferedImage( PIX_SIZE * X, PIX_SIZE * Y, 
                                              BufferedImage.TYPE_3BYTE_BGR );
        Graphics2D g = (Graphics2D)bi.getGraphics();
        String filename =  prefix+".jpg";      
		
        for( int i =0; i < X; i++ ){
            for( int j =0; j < Y; j++ ){
                x = i * PIX_SIZE;
                y = j * PIX_SIZE;
                Color c = new Color(im[i][j]);     
                
               g.setColor(c);
               g.fillRect( y, x , PIX_SIZE , PIX_SIZE );
            }
        }
        g.dispose();
        saveToFile( bi, new File( filename ) ); 
        return filename;
    }
    
    public static void saveToFile( BufferedImage img, File file ) throws IOException {
        ImageWriter writer = null;
        java.util.Iterator iter = ImageIO.getImageWritersByFormatName("jpg");      
        if( iter.hasNext() ){
            writer = (ImageWriter)iter.next();
        }    
        ImageOutputStream ios = ImageIO.createImageOutputStream( file );
        writer.setOutput(ios);
        ImageWriteParam param = new JPEGImageWriteParam( java.util.Locale.getDefault() );
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
        param.setCompressionQuality(0.98f);
        writer.write(null, new IIOImage( img, null, null ), param); 
    }
    
    public static void main( String args[] ) throws Exception {
    	in();
    	for( int k =0;k < 1000; k=k+1 ){
	       JPEGCreator.generate( Integer.toString(k));
	       im[0][0]=im[0][0]+1000000;
	       for(int i =0;i<100;i++){
	    	   for(int j =0;j<100;j++){
	        	   if(im[i][j]>=16777216){
	        		   for(int m =i;m>=0;m--){
	        	    	   for(int l =j;l>=0;l--){
	        	    		   im[m][l]=0;
	        	    	   }  	    	   
	        		   }
	        		   if(j<99){im[i][j+1]=im[i][j+1]+1000000;}
	        		   if(j==99){im[i+1][0]=im[i+1][0]+1000000;}
	        		break;
	        	   }
	           }
	       }
	       for(int i =0;i<100;i++){
	    	   for(int j =0;j<100;j++){
	    		   	System.out.print(im[i][j]+" ");
	    	   }
	    	   System.out.println();
	       }
	       
	       System.out.println("Image created " +k);	       
    	}
    }
}
