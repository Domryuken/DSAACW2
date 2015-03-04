
package datastructuresandalgorithms2;

import java.util.Random;
import java.util.Scanner;

public class DataStructuresAndAlgorithms2 {
    
    public static void main(String[] args)
    {
        int [][] array = D2Array(10);
        System.out.println("D");
        DExperiment();
        System.out.println("");
        System.out.println("D1");
        D1Experiment();
        System.out.println("");
        System.out.println("D2");
        D2Experiment();
    }
    
    public static void DExperiment()
    {
        int m = 10;
        System.out.print("ArraySize:Mean:StandardDevation");
        for(int k=10;k<=4000;k+=m)
        {
            int reps = 100;
            // Record mean and std deviation of performing an operation
            // reps times
            double sum=0,s=0;
            double sumSquared=0;
            for(int i=0;i<reps;i++){
                int [][] array = basicArray(k);
                long t1=System.nanoTime();
                FindElement_D(array,array.length,-1);
                long t2=System.nanoTime()-t1;
                //Recording it in milli seconds to make it more interprettable
                sum+=(double)t2/1000000.0;
                sumSquared+=(t2/1000000.0)*(t2/1000000.0);
            }
            double mean=sum/reps;
            double variance=sumSquared/reps-(mean*mean);
            double stdDev=Math.sqrt(variance);
            System.out.print("\n" + k);
            System.out.print(":" + mean);
            System.out.print(":" + stdDev);
            
            if(k==100||k==1000)
            {
                m*=10;
            }
        }
    }
    
    public static void D1Experiment()
    {
        int m = 10;
        System.out.print("ArraySize:Mean:StandardDevation");
        for(int k=10;k<=4000;k+=m)
        {
            int reps = 100;
            // Record mean and std deviation of performing an operation
            // reps times
            double sum=0,s=0;
            double sumSquared=0;
            for(int i=0;i<reps;i++){
                int [][] array = basicArray(k);
                long t1=System.nanoTime();
                FindElement_D1(array,array.length,-1);
                long t2=System.nanoTime()-t1;
                //Recording it in milli seconds to make it more interprettable
                sum+=(double)t2/1000000.0;
                sumSquared+=(t2/1000000.0)*(t2/1000000.0);
            }
            double mean=sum/reps;
            double variance=sumSquared/reps-(mean*mean);
            double stdDev=Math.sqrt(variance);
            System.out.print("\n" + k);
            System.out.print(":" + mean);
            System.out.print(":" + stdDev);
            
            if(k==100||k==1000)
            {
                m*=10;
            }
        }
    }
    
    public static void D2Experiment()
    {
        int m = 10;
        System.out.print("ArraySize:Mean:StandardDevation");
        for(int k=10;k<=4000;k+=m)
        {
            int reps = 100;
            // Record mean and std deviation of performing an operation
            // reps times
            double sum=0,s=0;
            double sumSquared=0;
            for(int i=0;i<reps;i++){
                int [][] array = D2Array(k);
                long t1=System.nanoTime();
                FindElement_D2(array,array.length,2);
                long t2=System.nanoTime()-t1;
                //Recording it in milli seconds to make it more interprettable
                sum+=(double)t2/1000000.0;
                sumSquared+=(t2/1000000.0)*(t2/1000000.0);
            }
            double mean=sum/reps;
            double variance=sumSquared/reps-(mean*mean);
            double stdDev=Math.sqrt(variance);
            System.out.print("\n" + k);
            System.out.print(":" + mean);
            System.out.print(":" + stdDev);
            
            if(k==100||k==1000)
            {
                m*=10;
            }
        }
    }
    

    
    
    public  static boolean FindElement_D(int[][] A,int n, int p)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(A[i][j]==p)
                    return true;
                
            }
        }   
        return false;
    }
    
    
    public static boolean FindElement_D1(int[][] A,int n, int p)
    {
        int m = 0;
        for(int i=0;i<n;i++)
        {
            int top = n - 1;
            int mid = top/2;
            int bot = 0;
            while(bot <= top)
            {
                if(A[i][mid]==p)
                {
                    return true;
                }
                else if(A[i][mid]<p)
                {
                    bot = mid + 1;
                }
                else
                {
                    top = mid - 1;
                }
                mid = (top + bot)/2;

            }
        }
        return false;
    }
    
    public static boolean FindElement_D2(int[][] A,int n, int p)
    {
        int x = n-1;
        int y = 0;
        int m = 0;
        while(x>=0&&y<n)
        {
            if(A[y][x]==p)
            {
                return true;
            }
            else if(A[y][x]>p)
            {
                x--;
            }
            else
            {
                y++;
            }
        }
        return false;
    }
    
    /*
    sorts an array into non-descending order horizontally
    */
    public static int[][] sortAcross(int[][] array)
    {
        int n = array.length;
        for(int k=0;k<n;k++)
        {
            for(int i=0;i<n-1;i++)
            {
                int lowest=i;
                for(int j=i+1;j<n;j++)
                {
                    if(array[k][j]<array[k][lowest])
                    {
                        lowest=j;
                    }
                }
                if(lowest!=i)
                {
                    int temp = array[k][i];
                    array[k][i]=array[k][lowest];
                    array[k][lowest]=temp;
                }
            }
        }
        return array;
    }
    
    /*
    Sorts a given array into non-descending order vertically
    */
    public static int[][] sortDown(int[][] array)
    {
        int n = array.length;
        for(int k=0;k<n;k++)
        {
            for(int i=0;i<n-1;i++)
            {
                int lowest=i;
                for(int j=i+1;j<n;j++)
                {
                    if(array[j][k]<array[lowest][k])
                    {
                        lowest=j;
                    }
                }
                if(lowest!=i)
                {
                    int temp = array[i][k];
                    array[i][k]=array[lowest][k];
                    array[lowest][k]=temp;
                }
            }
        } 
        return array;
    }
    
    
    /*
    Creates an array of a given size and fills it with random numbers under a
    given size
    */
    public static int [][] createArray(int size, int max)
    {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        
        int [][]array = new int [size][size];
        
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                array[i][j]= random.nextInt(max);
            }
        }
        return array;
    }
    
    
    /*
    Created an array filled with 1's to be used when testing D and D1
    */
    public static int [][] basicArray(int size)
    {
        int [][] array = new int[size][size];
        for(int i = 0;i<size;i++)
        {
            for(int j = 0;j<size;j++)
            {
                array[i][j] = 1;
            }
        }
        return array;
    }
    
    /*
    Creates an array filled with 1's for except for the last line which is
    filled with 3's to be used when testing D2
    */
    public static int [][] D2Array(int size)
    {
        int [][] array = new int [size][size];
        for(int i = 0;i<size-1;i++)
        {
            for(int j = 0;j<size;j++)
            {
                array[i][j] = 1;
            }
        }
        for(int j = 0;j<size;j++)
        {
            array[size-1][j] = 3;
        }
        
        return array;      
        
        
    }
    
    
    /*
    method tests from the assignment sheet
    */
    public static void arrayTests(){
        
        int [] pVals = {4,12,110,5,6,111};
        
        int [][] array = {
            {1, 3, 7, 8, 8, 9, 12},
            {2, 4, 8, 9, 10, 30, 38},
            {4, 5, 10, 20, 29, 50, 60},
            {8, 10, 11, 30, 50, 60, 61},
            {11, 12, 40, 80, 90, 100, 111},
            {13, 15, 50, 100, 110, 112, 120},
            {22, 27, 61, 112, 119, 138, 153}
        };
        
        for(int i=0;i<pVals.length;i++)
        {
            System.out.println("looking for " + pVals[i]);
            System.out.println(FindElement_D(array,array.length,pVals[i]));
            System.out.println(FindElement_D1(array,array.length,pVals[i]));
            System.out.println(FindElement_D2(array,array.length,pVals[i]));
            System.out.println("");
        }
    }
}