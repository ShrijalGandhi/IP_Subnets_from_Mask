/*
	IP SUBNET
*/

import java.io.*;

class IP
{
	int x[];

	IP()
	{
	x=new int[8];
	}
}


class DataOfIpSubnet
{
	static InputStreamReader r;
	static BufferedReader input;

	static IP ip[];

	static int mask;
	String ip_address;
	static int mask_array[];


	DataOfIpSubnet()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		System.out.println("Enter the ip address");	
		ip_address=input.readLine();

		System.out.println("Enter mask");
		mask=Integer.parseInt(input.readLine());

	ip=new IP[4];
		for(int i=0;i<4;i++)
		ip[i]=new IP();

	mask_array=new int[32];

	convertBinary();
	}

	void convertBinary()
	{
	String temp;
	int pointer=-1;
	int index=0;
	int i;
		for(i=0;i<3;i++)
		{
		pointer=ip_address.indexOf('.',pointer+1);
		//System.out.println(pointer);DEBUGGING
	
		temp=ip_address.substring(index,pointer);
		//System.out.println(temp);DEBUGGING

		index=pointer+1;
	
		converter(temp,i);		
		}

		temp=ip_address.substring(index,ip_address.length());
		//System.out.println(temp);DEBUGGING
		converter(temp,i);

		createMask();

	}

	void converter(String temp,int index)
	{
	String t;
	int value;

		value=Integer.parseInt(temp);

		t=Integer.toBinaryString(value);
		int pointer=7;
		
		for(int i=t.length()-1;i>=0;i--)
		ip[index].x[pointer--]=(t.charAt(i))%2;
	
	}

	void createMask()
	{
	/*--------------------------FIRST ADDRESS--------------------------------------------------*/

		for(int i=0;i<mask;i++)
		mask_array[i]=1;

		
		//for(int i=0;i<32;i++)		DEBUGGING
		//System.out.print(mask_array[i]);

		System.out.println("---------------------FIRST ADDRESS---------------------");
		operation('&');

	/*--------------------------LAST ADDRESS--------------------------------------------------*/

		for(int i=0;i<32;i++)
		if(mask_array[i]==0)	//since first address mask is found for last address complement the
		mask_array[i]=1;	//mask instead of writing new logic for it
		else			//	for(int i=mask;i<32;i++)
		mask_array[i]=0;	//	mask_array[i]=1;	
						
		System.out.println("-----------------LAST ADDRESS---------------");
		operation('|');
	}					

	void operation(char symbol)
	{
	IP temp[];
	temp=ip;
	int j,pointer,index;

	index=j=pointer=0;
	
		switch(symbol)
		{
		case '&':while(index<4)
			{
				while(j<8)
				temp[index].x[j++]&=mask_array[pointer++];

				index++;
				j=0;
			}
		display(temp);
		break;

		case '|':while(index<4)
			{
				while(j<8)
				temp[index].x[j++]|=mask_array[pointer++];

				index++;
				j=0;
			}
		display(temp);
		break;
		}
	}

	void display(IP temp[])
	{
	String t=new String("");
	int value;
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<8;j++)
			t+=Integer.toString(temp[i].x[j]);

		value=Integer.parseInt(t,2);

		if(i<3)
		System.out.print(value+".");
		else
		System.out.print(value);

		t="";
		}

	System.out.println("");
	}

	
}

class IpSubnet
{
	public static void main(String args[])throws IOException
	{
	DataOfIpSubnet d=new DataOfIpSubnet();
	}
}