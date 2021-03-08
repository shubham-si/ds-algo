package test;
import java.util.Scanner;

public class PatternQ
{
	public static void main(String args[])
	{
		int n, i, j, space = 1;
		
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		int maxAlpha = 97 + n - 1;
		int maxSpace = (n*2-1)*2-1; 	// (3*2-1)*2-1 = 9 total spaces including char's
		space = maxSpace/2 ;
		
		for (j = 1; j<= n; j++)
		{
			int alpha = maxAlpha;
			int term = 2*j-1;
			for (i = 1; i<= space; i++)
			{
				System.out.print("-");
			}
			for (i = 1; i <= term; i++)
			{
				System.out.print((char)alpha);
				alpha = (i<j) ? --alpha : ++alpha;
				if (i < term) {
					System.out.print("-");
				}
			}
			for (i = 1; i<= space; i++)
			{
				System.out.print("-");
			}
			System.out.println("");
			space-=2;
		}

		space = 2;
		for (j = 1; j<= n - 1; j++)
		{	int alpha = maxAlpha;
			int term = 2 * (n - j) - 1;
			for (i = 1; i<= space; i++)
			{
				System.out.print("-");
			}
			for (i = 1; i<= term; i++)
			{
				System.out.print((char)alpha);
				if (i < term) {
					System.out.print("-");
				}
				alpha = (i<=term/2) ? --alpha : ++alpha;
			}
			for (i = 1; i<= space; i++)
			{
				System.out.print("-");
			}
			System.out.println("");
			space+=2;
		}
	}
}
