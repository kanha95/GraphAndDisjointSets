import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


class edge implements Comparable<edge>{
    int x,wt;
    edge(int x,int wt){
        this.x=x;
        this.wt=wt;
    }
    
    public int compareTo(edge that){
        return Integer.compare(this.wt, that.wt);
    }
    
}
class graph{
    int v;
    LinkedList<edge>[] adj;
    
    graph(int v){
        this.v=v;
        adj=new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i]=new LinkedList();
        }
    }
    
    void addEdge(int x,int y,int wt){
        adj[x].add(new edge(y,wt));
        adj[y].add(new edge(x,wt));
    }
    int[] pred;
    int[] cost;
    void mst(){
        boolean[] visited=new boolean[v];
        pred=new int[v];
        cost=new int[v];
        PriorityQueue<edge> pq=new PriorityQueue<>();
        
        Arrays.fill(cost, Integer.MAX_VALUE);
        
        cost[0]=0;
        pred[0]=-1;
        
        pq.add(new edge(0,0));
        long res=0;
        while(!pq.isEmpty()){
            edge e=pq.poll();
            
            int u=e.x;
            //System.out.println(e.wt);
            if(visited[u]) continue;
               
            visited[u]=true;
            res+=e.wt;
            for(edge ed:adj[u]){
                if(!visited[ed.x] && ed.wt<cost[ed.x]){
                    cost[ed.x]=ed.wt;
                    
                    pred[ed.x]=u;
                    //System.out.println(u+" "+ed.x);
                    pq.add(new edge(ed.x,ed.wt));
                }
            }
            
            
            
            
        }
        //System.out.println(res);
        
    }
    
    void dfsUtil(int[][] res){
        
        for(int i=0;i<v;i++){
            dfs(i,res);
        }
        
    }
   
    class dpair{
        int x,y;
        dpair(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    void dfs(int start,int[][] res){
        boolean[] vis=new boolean[v];
        
        
        Stack<dpair> s=new Stack<>();
        
        s.push(new dpair(start,0));
        
        while(!s.isEmpty()){
           int x=s.peek().x;
           int wt=s.peek().y;
           s.pop();
           vis[x]=true;
           
           for(edge e:adj[x]){
               if(!vis[e.x]){
                   res[start][e.x]=wt+e.wt;
                   s.push(new dpair(e.x,res[start][e.x]));
               }
           }
           
               
        }
        
        
        
    }
    
    
}

class Main{
    
    public static void main(String[] args){
        InputReader sc=new InputReader(System.in);
        PrintWriter pw=new PrintWriter(System.out);
        int t=sc.nextInt();
        
        for(int uu=1;uu<=t;uu++){
            int n=sc.nextInt();
            int p=sc.nextInt();
            int m=sc.nextInt();
            
            
            graph g=new graph(n);
            
            for (int i = 0; i < p; i++) {
                g.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt());
            }
            
            g.mst();
            
            graph g1=new graph(n);
            
            for (int i = 1; i < n; i++) {
                g1.addEdge(i, g.pred[i], g.cost[i]);
            }
            int[][] res=new int[n][n];
            g1.dfsUtil(res);
            pw.println("Case: "+uu);
            for (int i = 0; i < m; i++) {
                int u=sc.nextInt()-1;
                int v=sc.nextInt()-1;
                
               
                pw.println(res[u][v]);    
                    
                }
                pw.flush();
               
            }
            
            
        }
        
        
    }
    
    



 class InputReader
	{
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;
		
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
		
		public int read()
		{
			if (numChars==-1) 
				throw new InputMismatchException();
			
			if (curChar >= numChars)
			{
				curChar = 0;
				try 
				{
					numChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
				
				if(numChars <= 0)				
					return -1;
			}
			return buf[curChar++];
		}
	 
		public String nextLine()
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
		}
		public int nextInt()
		{
			int c = read();
			
			while(isSpaceChar(c)) 
				c = read();
			
			int sgn = 1;
			
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			
			int res = 0;
			do 
			{
				if(c<'0'||c>'9') 
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c)); 
			
			return res * sgn;
		}
		
		public long nextLong() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			long res = 0;
			
			do 
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c));
				return res * sgn;
		}
		
		public double nextDouble() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') 
			{
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, nextInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') 
			{
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) 
				{
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, nextInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}
		
		public String readString() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do 
			{
				res.appendCodePoint(c);
				c = read();
			} 
			while (!isSpaceChar(c));
			
			return res.toString();
		}
	 
		public boolean isSpaceChar(int c) 
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	 
		public String next() 
		{
			return readString();
		}
		
		public interface SpaceCharFilter 
		{
			public boolean isSpaceChar(int ch);
		}
	}
