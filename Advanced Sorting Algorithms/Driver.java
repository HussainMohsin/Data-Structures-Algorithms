import java.io.*;
import java.util.*;

public class Driver{
	public static final int SIZE = 10000;
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String inputFile, outputFile;
		
		System.out.print("Specify the output filename: ");
		outputFile = input.nextLine();
		
		for(int i = 1 ; i <= 3 ; i++) {
			
			AdvancedSorts mSort = new AdvancedSorts(SIZE); 
			AdvancedSorts qSort = new AdvancedSorts(SIZE); 
			AdvancedSorts hSort = new AdvancedSorts(SIZE);
			
			do {
				System.out.print("\nEnter the file name you would like to read: ");
				inputFile = input.nextLine();
			}while((inputFile.compareTo("AscendingOrderData.txt") != 0) &&
					(inputFile.compareTo("DescendingOrderData.txt") != 0) &&
					(inputFile.compareTo("RandomOrderData.txt") != 0));
			
			readFile(inputFile, mSort, qSort, hSort);
			sortArrays(inputFile, mSort, qSort, hSort);
			
			switch(inputFile) {
				case "ascending.txt":
					writeFile(outputFile+"MergeAscend.txt ", mSort.getTheArray());
					writeFile(outputFile+"QuickAscend.txt ", qSort.getTheArray());
					writeFile(outputFile+"HeapAscend.txt ", hSort.getTheArray());
					break;
				case "descending.txt":
					writeFile(outputFile+"MergeDescend.txt ", mSort.getTheArray());
					writeFile(outputFile+"QuickDescend.txt ", qSort.getTheArray());
					writeFile(outputFile+"HeapDescend.txt ", hSort.getTheArray());
					break;
				case "random.txt":
					writeFile(outputFile+"MergeRandom.txt ", mSort.getTheArray());
					writeFile(outputFile+"QuickRandom.txt ", qSort.getTheArray());
					writeFile(outputFile+"HeapRandom.txt ", hSort.getTheArray());
			}
		}
		
	}
	private static void sortArrays(String file, AdvancedSorts mSort, AdvancedSorts qSort, AdvancedSorts hSort) {
		long start, finish;
		start = System.nanoTime();
		mSort.mergeSort();
		finish = System.nanoTime();
		System.out.println(file + " took " + (finish - start) + " nanoseconds to Merge sort.");
		
		start = System.nanoTime();
		qSort.quickSort();
		finish = System.nanoTime();
		System.out.println(file + " took " + (finish - start) + " nanoseconds to Quick sort.");
		
		start = System.nanoTime();
		hSort.heapSort();
		finish = System.nanoTime();
		System.out.println(file + " took " + (finish - start) + " nanoseconds to Heap sort.\n");
	}
	private static void readFile(String fileName, AdvancedSorts mSort, AdvancedSorts qSort, AdvancedSorts hSort) {
		try {
        	//TODO: use relative path
            File numbers = new File("C:\\Users\\Boulder\\Documents\\COSC311\\Assignment4\\src\\" + fileName);
            
            Scanner inFile = new Scanner(numbers); 
            while (inFile.hasNextInt()) { 
                int num = inFile.nextInt();
                
                mSort.insert(num); 
                qSort.insert(num);
                hSort.insert(num);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found"); 
        }
	}
	
	private static void writeFile(String fileName, int[] array) {
		File newFile = new File("C:\\Users\\Boulder\\Documents\\COSC311\\Assignment4\\src\\" + fileName );
		try {
			PrintWriter writer = new PrintWriter(newFile);
			for (int j = 0; j < array.length; j++) 
				writer.println(array[j]);
			writer.close();
			System.out.println(fileName + " has been written");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
