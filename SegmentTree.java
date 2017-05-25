import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class SegmentTree{
    int st[];
    int n;
    
    SegmentTree(int n){
        this.n=n;
        int x=(int)Math.ceil(Math.log(n)/Math.log(2));
        x=2*(int)Math.pow(2, x)-1;
        st=new int[x];
    }
    
    int build(int[] ar,int start,int end,int node){
        
        if(start==end){
            st[node]=ar[start];
            return st[node];
        }
        
        int mid=start+(end-start)/2;
        
        st[node]=Math.min(build(ar,start,mid,2*node+1), build(ar,mid+1,end,2*node+2));
        
        return st[node];
    }
    
    int query(int left,int right){
        
        if(left<0 || right>n-1 || left>right){
            System.out.println("Invalid input");
            return -1;
        }
        
        return queryUtil(0,n-1,left,right,0);
    }
    
    int queryUtil(int start,int end,int left,int right,int node){
        
        if(start>=left && end<=right){
            return st[node];
        }
        
        if(start>right || end<left){
            return Integer.MAX_VALUE;
        }
        
        int mid=start+(end-start)/2;
        
        return Math.min(queryUtil(start,mid,left,right,2*node+1),queryUtil(mid+1,end,left,right,2*node+2));
    }
    
    int update(int start,int end,int index,int value,int node){
        if(index<start || index>end){
            return st[node];
        }
        
        if(start==end){
            if(index==start){
                st[node]=value;
            }
            return st[node];
        }
        
        int mid=start+(end-start)/2;
        st[node]=Math.min(update(start,mid,index,value,2*node+1), update(mid+1,end,index,value,2*node+2));
        
        return st[node];
    }
}

class Main{
    
    public static void main(String[] args){
        
             InputReader sc=new InputReader(System.in); 
             PrintWriter pw=new PrintWriter(System.out);
             
            int n=7;
            int ar[] = {1, 3, 2, 7, 9, 11, 4};
             
            SegmentTree st=new SegmentTree(n);
            st.build(ar, 0, n-1, 0);
            int index=3;
            int value=0;
            st.update(0,n-1,index,value,0);
            st.update(0,n-1,2,-1,0);
            System.out.println(st.query(3,6));
        
        
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
