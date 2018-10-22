import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.*;
 
abstract class Tree {
    public final int frequency; // the frequency of this tree
    public Tree(int freq){     	
    	frequency = freq; 
    }

} 
class Leaf extends Tree {
    public final char value; // the character this leaf represents
 
    public Leaf(int freq, char val) {
        super(freq);
        value = val;
    }
}
 
class HuffmanNode extends Tree {
    public final Tree left, right;
 
    public HuffmanNode(Tree l, Tree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
 
public class HuffmanParallel {
	
    public static Tree buildTree(int[] charFreqs) throws InterruptedException {
        PriorityBlockingQueue<Tree> trees = new PriorityBlockingQueue<Tree>(charFreqs.length, (a,b)->a.frequency-b.frequency);
        Thread[] threads = new Thread[4];
        int size = charFreqs.length/4;
        
    	int nextstop =size;
        int nextloop=0;
       
        for(int i=0;i<threads.length;i++) {
        	final int start =nextloop;
        	final int stop = nextstop;
        	threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = start; j < stop ; j++)
			            if (charFreqs[j] > 0)
			            	trees.put(new Leaf(charFreqs[j], (char)j));					
				}
			});
        	nextloop=(size*(i+1))+1;
        	nextstop = ((i+2)*size);
        	threads[i].start();        	
        }   
        for(Thread t: threads) {
        	t.join();
        }
        while (trees.size() > 1) {
            Tree a = trees.take();
            Tree b = trees.take();
            trees.put(new HuffmanNode(a, b));
        }
        return trees.take();
    }

    public static void buildTable(Tree tree, StringBuffer prefix,Hashtable<String, String>table) {
        
        if (tree instanceof Leaf) {
            Leaf leaf = (Leaf)tree;
            table.put(String.valueOf(leaf.value), prefix.toString());
 
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            prefix.append('0');
            buildTable(node.left, prefix,table);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            buildTable(node.right, prefix,table);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        String temp = "";
		String input ="";
    	BufferedReader read = new BufferedReader(new FileReader("input.txt"));
    	
    	while((temp=read.readLine()) != null) {
    		input+=temp+"\n";
    	}
        int[] charFreqs = new int[256];
        
        for (char c : input.toCharArray()) {
            charFreqs[c]++;
        }
        long start = System.currentTimeMillis();
        Tree tree = buildTree(charFreqs);
        System.out.println("time taken to build time in parallel : "+(System.currentTimeMillis() - start));
        
        Hashtable<String, String> table = new Hashtable<>();
        buildTable(tree, new StringBuffer(),table);
      
        StringBuffer temp2 = new StringBuffer();
        start = System.currentTimeMillis();
        for(char c : input.toCharArray()) {
        	temp2.append(table.get(String.valueOf(c)));
        }
        
        BitOutputStream bout = new BitOutputStream(new BufferedOutputStream(new FileOutputStream("result.txt")));
        String data= temp2.toString();
        
        for(int i=0;i<temp2.length();i++) {
        	if(data.charAt(i) == '0') {
        		bout.write(false);
        	}else {
        		bout.write(true);
        	}
        }      
        System.out.println("time taken to save it in file in parallel : "+(System.currentTimeMillis() - start));
        bout.close();
        read.close();
   
        
        File inFile = new File ("input.txt");
        File resultFile = new File("result.txt");
        
        System.out.println("before : "+inFile.length());
        System.out.println("after : "+resultFile.length());
        System.out.print((int)(resultFile.length()*1.0/inFile.length() *100 ));
        System.out.println(" percentage smaller");
       
    }
}