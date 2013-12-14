package gen;

import java.awt.Color;

public class test {
	
	  public static void main( String args[] ) throws Exception {
		  System.out.print("1");
		  for(int j =0;j<50;j++){
			System.out.print("{");
			 for(int i =0;i<1000;i++){
				 System.out.print("0");
				 if(i<99){
					 System.out.print(",");
				 }
			 }
			 System.out.print("},");
			 System.out.println();
			
		  }
		   System.out.print("1000");
	  }
}
