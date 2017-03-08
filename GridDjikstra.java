import java.io.*;
import java.util.*;
public final class capture_castle
{
    static Scanner sc=new Scanner(System.in);
    static PrintWriter out=new PrintWriter(System.out);
    static long[][] dis;
    static final String s1="YES",s2="NO";

    public static void main(String args[]) throws Exception
    {
        int t=sc.nextInt();
        while(t>0)
        {
            int n=sc.nextInt(),m=sc.nextInt();
            long[][] a=new long[n][m],dis=new long[n][m];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    a[i][j]=sc.nextLong();
                    dis[i][j]=Long.MAX_VALUE;
                }
            }
            int x=sc.nextInt()-1,y=sc.nextInt()-1;
            long min2=sc.nextLong();
            PriorityQueue<Node> pq=new PriorityQueue<Node>();
            pq.add(new Node(0,0,a[0][0]));
            dis[0][0]=a[0][0];
            while(pq.size()>0)
            {
                Node curr=pq.poll();
                int i=curr.i,j=curr.j;
                if(i-1>=0 && dis[i-1][j]>dis[i][j]+a[i-1][j])
                {
                    dis[i-1][j]=dis[i][j]+a[i-1][j];
                    pq.add(new Node(i-1,j,dis[i-1][j]));
                }
                if(i+1<n && dis[i+1][j]>dis[i][j]+a[i+1][j])
                {
                    dis[i+1][j]=dis[i][j]+a[i+1][j];
                    pq.add(new Node(i+1,j,dis[i+1][j]));
                }
                if(j-1>=0 && dis[i][j-1]>dis[i][j]+a[i][j-1])
                {
                    dis[i][j-1]=dis[i][j]+a[i][j-1];
                    pq.add(new Node(i,j-1,dis[i][j-1]));
                }
                if(j+1<m && dis[i][j+1]>dis[i][j]+a[i][j+1])
                {
                    dis[i][j+1]=dis[i][j]+a[i][j+1];
                    pq.add(new Node(i,j+1,dis[i][j+1]));
                }
            }
            long ans=min2-dis[x][y];
            if(ans>0)
            {
                out.println(s1+"\n"+ans);
            }
            else
            {
                out.println(s2);
            }
            t--;
        }
        out.close();
    }
}
class Node implements Comparable<Node>
{
    int i,j;
    long cost;
    public Node(int i,int j,long cost)
    {
        this.i=i;
        this.j=j;
        this.cost=cost;
    }
    public int compareTo(Node x)
    {
        return Long.compare(this.cost,x.cost);
    }
}
