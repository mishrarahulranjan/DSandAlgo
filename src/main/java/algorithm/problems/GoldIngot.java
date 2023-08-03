package algorithm.problems;

import java.util.*;
public class GoldIngot{

     public static void main(String []args){
        Scanner s1 = new Scanner(System.in);
        System.out.println("Enter an integer");
        int inputNumber = s1.nextInt();
        
        System.out.println("Enter width & height");
        
        String width_height_str = s1.nextLine();
        String wdData[] = width_height_str.trim().split("\\s+");
        
        int height = Integer.parseInt(wdData[0]);
         int width = Integer.parseInt(wdData[1]);
         
         
         System.out.println("Enter length");
         
         String length_str = s1.nextLine();
         
         String lengthData[] = length_str.trim().split("\\s+");
         
         int cLength[] = new int[inputNumber];
         int index=0;
         int tVolume=0;
         
         for (String lengthD: lengthData){
             cLength[index]= Integer.parseInt(lengthD);
             tVolume =tVolume+( height*width* cLength[index]);
             index++;
         }
         
         //get total Volume of cuboid
         System.out.println("Total volume of individual cuboid is "+tVolume);
         
         int cVolume=0;
         
         //TODO: determine the cVolume
         int min=0;
         for (int i=0;i< cLength.length;i++){
            int j=0;
            for(;j<cLength.length;j++){
                if(cLength[i]>cLength[j]){
                    break;
                }
            }
            int minV =cLength[i]* j;
            
            if(cVolume < minV){
                cVolume=minV;
            }
             
         }
         
         int result= tVolume- cVolume;
         
         
          System.out.println("required result is is "+result);
        
        
        
     }
}