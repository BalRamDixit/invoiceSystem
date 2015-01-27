package com.jjit.pdfgenerator;

public class NumberToWord {
	
	static String output="";
	public static String getWordOfNumber(long num)
	{
		long a;
		while(num>0)
		{
			if(num>=1000000000)
			{
				a=num/1000000000;
				print(a);
				output=output+"arab ";
				num=num%1000000000;
			}
			else if(num>=10000000)
			{
				a=num/10000000;
				print(a);
				output=output+"crore ";
				num=num%10000000;
			}
			else if(num>=100000)
			{
				a=num/100000;
				print(a);
				output=output+"Lakh ";
				num=num%100000;
			}
			else if(num>=1000)
			{
				a=num/1000;
				print(a);
				output=output+"Thousand ";
				num=num%1000;
			}
			else if(num>=100)
			{
				a=num/100;
				print(a);
				output=output+"Hundred ";
				num=num%100;
			}
			else
			{
				print(num);
				num=num/100;
			}
		}
		return output;
	}
	public static void print(long num)
	{
		long a;
		//output=output+"%d",num);
		if(num>=90 && num<=99)
		{
			a=num/90;
			output=output+"ninty ";
			num=num%10;
		}
		else if(num>=80 && num<=89)
		{
			a=num/80;
			output=output+"eighty ";
			num=num%10;
		}
		else if(num>=70 && num<=79)
		{
			a=num/70;
			output=output+"seventy ";
			num=num%10;
		}
		else if(num>=60 && num<=69)
		{
			a=num/60;
			output=output+"sixty ";
			num=num%10;
		}
		else if(num>=50 && num<=59)
		{
			a=num/50;
			output=output+"fifty ";
			num=num%10;
		}
		else if(num>=40 && num<=49)
		{
			a=num/40;
			output=output+"fourty ";
			num=num%10;
		}
		else if(num>=30 && num<=39)
		{
			a=num/30;
			output=output+"thirty ";
			num=num%10;
		}
		else if(num>=20 && num<=29)
		{
			a=num/20;
			output=output+"twenty ";
			num=num%10;
		}
		switch((int)num)
		{
			case 1:
				output=output+"one ";
				break;
			case 2:
				output=output+"two ";
				break;
			case 3:
				output=output+"three ";
				break;
			case 4:
				output=output+"four ";
				break;
			case 5:
				output=output+"five ";
				break;
			case 6:
				output=output+"six ";
				break;
			case 7:
				output=output+"seven ";
				break;
			case 8:
				output=output+"eight ";
				break;
			case 9:
				output=output+"nine ";
				break;
			case 10:
				output=output+"ten ";
				break;
			case 11:
				output=output+"eleven ";
				break;
			case 12:
				output=output+"twelve ";
				break;
			case 13:
				output=output+"thirteen ";
				break;
			case 14:
				output=output+"forteen ";
				break;
			case 15:
				output=output+"fifteen ";
				break;
			case 16:
				output=output+"sixteen ";
				break;
			case 17:
				output=output+"seventeen ";
				break;
			case 18:
				output=output+"eighteen ";
				break;
			case 19:
				output=output+"nineteen ";
				break;
		}
	}

}
