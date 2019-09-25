
import java.util.*;
import java.io.*;

class edge implements Comparable<edge>{
	int id;
	long wt;
	edge(int id, long wt){
		this.id = id;
		this.wt = wt;
	}
	
	public int compareTo(edge that){
	    return Long.compare(this.wt, that.wt);
	}
}

class graph{
	int v;
	LinkedList<edge>[] adj;
	
	graph(int n){
	    v = n;
		adj = new LinkedList[n];
		
		for(int i=0;i<n;i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	void addEdge(int i, int j, long wt) {
		adj[i].add(new edge(j,wt));
		//adj[j].add(new edge(i,wt));
	}
	
	void djikstra(int source) {
		
		PriorityQueue<edge> pq = new PriorityQueue();
		long[] dist = new long[v];
		boolean[] visited = new boolean[v];
		
		for(int i=0;i<v;i++) {
			dist[i] = (long)10e+9;
		}
		dist[source] = 0;
		
		
		pq.add(new edge(source, 0));
		
		while(!pq.isEmpty()) {
			edge temp = pq.poll();
			
			visited[temp.id] = true;
			
			for(edge e: adj[temp.id]) {
				if(!visited[e.id] && dist[temp.id] + e.wt < dist[e.id]) {
					dist[e.id] = dist[temp.id] + e.wt;
					pq.add(new edge(e.id, dist[e.id]));
				}
			}
			
		
			
		}
		
	    for(int i=1;i<v;i++){
		    System.out.print(dist[i]+" ");
		}
		
		
	}
	
}


public class TestClass{

   
		public static void main (String[] args) throws Exception
		 {
		 
		  //Scanner sc = new Scanner(System.in);
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String[] tt = br.readLine().trim().split(" ");
		int n = Integer.parseInt(tt[0]);
		int m = Integer.parseInt(tt[1]);
		
		graph g = new graph(n);
		
		for(int i=0; i<m; i++){
		    String[] ss = br.readLine().trim().split(" ");
		    
		    g.addEdge(Integer.parseInt(ss[0])-1, Integer.parseInt(ss[1])-1, Long.parseLong(ss[2]));
		}
		
		 g.djikstra(0);
		 }
    
    
}
