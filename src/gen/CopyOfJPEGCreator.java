package gen;

import java.util.Random;

import java.awt.Color;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.io.BufferedWriter;

import java.io.File;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.OutputStreamWriter;

import javax.imageio.IIOImage;

import javax.imageio.ImageIO;

import javax.imageio.ImageWriteParam;

import javax.imageio.ImageWriter;

import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

import javax.imageio.stream.ImageOutputStream;



/** Demo class that showcases creation of JPEG / JPG files from Java

 *  

 * @author Edmon Begoli

 */



public class CopyOfJPEGCreator{





    /* Creates a randomly generated two color JPEG and writes it to a file

     *

     */
	static int R=0,G=0,B=0;
	static Color c = new Color (R, G, B);
	static int[][] im = new int[100][100];
	static void  in(){
		for(int i =0; i<100;i++){
    		for(int j =0; j<100;j++){
    			im[i][j] =65280;
        	}	
    	}
	}
    public static String generate( String prefix ) throws Exception{
    	



        int x,y = 0;

       

        //image block size in pixels, 1 is 1px, use smaller values for 

        //greater granularity

        int PIX_SIZE = 1;



        //image size in pixel blocks

        int X = 100;

        int Y = 100;



        BufferedImage bi = new BufferedImage( PIX_SIZE * X, PIX_SIZE * Y, 

                                              BufferedImage.TYPE_3BYTE_BGR );

        Graphics2D g = (Graphics2D)bi.getGraphics();

        String filename =  prefix+".jpg";



        int ass=156;
       
       
		
        for( int i =0; i < X; i++ ){



            for( int j =0; j < Y; j++ ){


                x = i * PIX_SIZE;

                y = j * PIX_SIZE;

                 Color c = new Color (im[i][j]);
              
               
               g.setColor(c);
               
               

               //fil the rectangles with the pixel blocks in chosen color

               g.fillRect( y, x , PIX_SIZE , PIX_SIZE );

        

            }//end for j

                   	


        }//end for i
        
        R = c.getRed();
        G =c.getGreen();
        B=c.getBlue();
        if(R==255&&G==255&&B==255){
        		R=0;
        		G=0;
        		B=0;
        	}
    	if(G==255&&B==255){
    		B=0;
    		G=0;
    		R=R+1;
    	}
    	if(B==255){
    		B=0;
    		G=G+1;
    	}else{
    		B++;
    	}
        

        g.dispose();



        saveToFile( bi, new File( filename ) ); 

        return filename;



    }//end method  





    /** Saves jpeg to file

     *

     */

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

    

    }//end method 





    /**

     * Main method - use for demoing of how the JPEG generation works

     * this will create a test-img.jpg in the current directory

     */

    public static void main( String args[] ) throws Exception {
    	in();

    	for( int k =0;k < 1000; k=k+1 ){
       CopyOfJPEGCreator.generate( Integer.toString(k));
       
       im[0][0]=im[0][0]+10000;
       /*
       System.out.print(" "+R);
       System.out.print(" "+G);
       System.out.print(" "+B);
       System.out.print(" "+c);*/
       for(int i =0;i<100;i++){
    	   for(int j =0;j<100;j++){
        	   if(im[i][j]>=100000){
        		   
        		   for(int m =i;m>=0;m--){
        	    	   for(int l =j;l>=0;l--){
        	    		   im[m][l]=0;
        	    	   }  	    	   
        		   }
        		if(j<99){im[i][j+1]=im[i][j+1]+1;}
        		if(j==99){im[i+1][0]=im[i+1][0]+1;}
        		break;
        	   }
           }
       }for(int i =0;i<100;i++){
    	   for(int j =0;j<100;j++){
    		   	System.out.print(im[i][j]+" ");
    	   }
    	   System.out.println();
       }       
       System.out.println(" "+"Image created." +k);
       
    	}


    }//end main



}//end class 

