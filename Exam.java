
public  final class Exam
{
	public  final static int DEAD=0X00;
	
	public  final static int LIVE=0X01;
	
	public final static void main(String[] args)
	{
		Exam o=new Exam();
		o.Exam(5);
	}
	
	private void Exam(int Iteration)
	{
		int[][] board= {{DEAD,DEAD,DEAD,LIVE,DEAD},
						{DEAD,LIVE,DEAD,DEAD,DEAD},
						{DEAD,DEAD,LIVE,DEAD,DEAD},
						{DEAD,LIVE,DEAD,LIVE,DEAD},
						{DEAD,DEAD,LIVE,LIVE,DEAD}};
		
		
		System.out.println("output");
		printBoard(board);
		
		for(int i=0;i<Iteration;i++)
		{
			System.out.println();
			board=getNextBoard(board);
			printBoard(board);
		}
	}

	private void printBoard(int[][] board) 
	{
		for(int i=0,e=board.length;i<e;i++)
		{
			for(int j=0,f=board[i].length;j<f;j++)
			{
				System.out.print((board[i][j]+","));
			}
			System.out.println();
		}	
	}
	
	public int[][] getNextBoard(int[][] board)
	{
		if(board.length==0 || board[0].length==0)
		{
			throw new IllegalArgumentException("Board Must have positive amount of rows and column");
		}
		
		int row=board.length;
		int col=board[0].length;
		
		int[][] buf=new int[row][col];
		
		for(int r=0;r<row;r++)
		{
			for(int c=0;c<col;c++)
			{
				buf[r][c]=getNewCellState(board[r][c],getLiveNeighbour(r,c,board));
				
			}
		}
		return buf;
	}
	
	private int getLiveNeighbour(int crow, int ccol,int[][]board)
	{ 
		int live=0;
		int rend=Math.min(board.length, crow+2);
		int cend=Math.min(board[0].length, ccol+2);
		
		for(int r=Math.max(0, crow-1);r<rend;r++)
		{
			for(int c=Math.max(0, ccol-1);c<cend;c++)
			{
				if((r!=crow||c!=ccol) && board[r][c]==LIVE)
				{
					live++;
				}
			}
			
		}
		return live;
	}
	
	
	private int getNewCellState(int cstate,int live)
	{
		int nstate=cstate;
		switch(cstate)
		{
		 case LIVE:  
			 			if(live<2)
		               {
							nstate=DEAD;
		               }
					if(live==2 || live==3)
						{
							nstate=LIVE;
						}
					if(live>3)
		               {
							nstate=DEAD;
		               }
					
				   break;
				
		  case DEAD:  if(live==3)
		  			{
					  nstate=LIVE;
		  			}
		       break;
		   default:
			   throw new IllegalArgumentException("State of cell must be either live or dead");
		}
		
		
		return nstate;
		
	}
	
}
	

