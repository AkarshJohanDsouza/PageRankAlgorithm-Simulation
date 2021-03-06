import java.util.Scanner;
//calculates pageRank
public class RankCalc {
	static int outLinksCount=0;
	
	public static void main(String args[])
	{
		Scanner in= new Scanner(System.in);
		int n;
		n=in.nextInt();
		int mat[][]=new int[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				mat[i][j]=in.nextInt();
			}
		}
		Page pages[]=new Page[n];
		for(int i=0;i<n;i++)
		{
			pages[i]=new Page(i,n);
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				int k=mat[i][j];
				while(k>0)
				{
					pages[j].AddInLinks(pages[i]);
					k--;
				}
			}
		}
		for(int i=0;i<n;i++)
		{
			outLinksCount=0;
			for(int j=0;j<n;j++)
			{
				outLinksCount+=mat[i][j];
				pages[i].setOutLinks(outLinksCount);
			}
		}

		for(int i=0;i<n;i++)
		{
			System.out.println(pages[i].getId()+"	"+pages[i].getRank()+"	"+pages[i].getOutLinks());
			for(int j=0;j<pages[i].getInLinkNum();j++)
				System.out.println(pages[i].getInLinks()[j].getId());
			System.out.println();
		}
		PgRankCalc(pages);
	}
	
public static void PgRankCalc(Page[] pages)	
{
	double d=0.85;
	
	for(int i=0;i<1000;i++)
		for(int j=0;j<pages.length;j++)
		{
			double value1=calcValue(pages[j].getInLinks(),pages[j].getInLinkNum()-1);
			double value=(1-d)+d*(value1);
			pages[j].setRank(value);
		}
	display(pages);
}
	
public static double calcValue(Page[] inLinkPage,int n)
{
	if(n==0)
		return inLinkPage[0].getRank()/(double)inLinkPage[0].getOutLinks();
	
	else
	{
		return inLinkPage[n].getRank()/(double)inLinkPage[n].getOutLinks()+calcValue(inLinkPage,n-1);
	}
}

public static void display(Page[] pages)
{
	System.out.println();
	for(int i=0;i<pages.length;i++)
		System.out.println(i+"	"+pages[i].getRank()*100);
}
}
