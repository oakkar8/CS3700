import java.io.*;
import java.util.*;
 
abstract class Tree  {
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
 
public class Huffman {
	
    public static Tree buildTree(int[] charFreqs) {
        PriorityQueue<Tree> trees = new PriorityQueue<Tree>(charFreqs.length, (a,b)->a.frequency-b.frequency);

        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new Leaf(charFreqs[i], (char)i));
       
        while (trees.size() > 1) {
            Tree a = trees.poll();
            Tree b = trees.poll();
            trees.add(new HuffmanNode(a, b));
        }
        return trees.poll();
    }

    public static void buildTable(Tree tree, StringBuffer prefix,Hashtable<String, String>table) {
        
        if (tree instanceof Leaf) {
            Leaf leaf = (Leaf)tree;
//            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
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
    public static void main(String[] args) throws IOException {
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
        System.out.println("time taken to build tree  : "+(System.currentTimeMillis() - start));
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
        System.out.println("time taken to build file  : "+(System.currentTimeMillis() - start));
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