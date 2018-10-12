import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

class MatrixMultiplication implements Callable<Integer>
{
	private int n;
	private double a[][];
	private double b[][];
	private double sum;
	private int row;
	private int col;


	public MatrixMultiplication(int n, double a[][], double b[][], int row , int col )	{
		this.n = n;
		this.a = a;
		this.b = b;
		this.row = row;
		this.col = col;
		this.sum = 0;
	}

	public Integer call() throws Exception	{
		for (int k = 0 ; k < n ; k++) {
			
			sum = sum + a[row][k] * b[k][col];
		}
		return (int)sum;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException	{

		int n;
		int m,p;
		double[][] a, b, c;

		n = 300;
		m = 200;
		p = 400;
		a = new double[m][n];
		b = new double[n][p];
		c = new double[m][p];
		
		for(int i=0; i<m; i++)	{
			for(int j=0; j<n; j++) {
				a[i][j] = i+j;
			}
		}

		for(int i=0; i<n; i++) {
			for(int j=0; j<p; j++) {
				b[i][j] = i+j+5;
			}
		}

		for(int i=1; i<9; i++) {
			matriMul(i,a,b,c,m,n,p);
		}

	}
	public static void matriMul(int threads, double[][] a, double[][]b, double[][] c,int m, int n, int p) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		long start_time = System.nanoTime();
		Future<Integer> future;
		Future<Integer> futures[][] = new Future[m][p];

		for (int i=0; i<m; i++) {
			for (int j = 0; j < p; j++) {
				future = executor.submit(new MatrixMultiplication(n, a, b, i, j));
				futures[i][j] = future;
			}
		}

		for (int i=0; i<m; i++) {
			for (int j = 0; j < p; j++) {
				c[i][j] = futures[i][j].get();
			}
		}


		long end_time = System.nanoTime();
		double difference = (end_time - start_time)/1e6;
		executor.shutdown();
		System.out.println("Time taken with " + threads + " : " + difference);
	}
}