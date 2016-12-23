package com.kj133.util;
public class encrypt {
	final static int SOFT_BUILD=7788;
	final static int CLIENT_KEY=33;
	
	//解密
	public String Dencrypt(String s,String EncryptKey){
	    double nKey=SOFT_BUILD/CLIENT_KEY;
	    byte[] pbyte=null;
	    if(EncryptKey.length()==0){
	    	EncryptKey="63A9B8C19F10D547";
	    }
	        
	    long t=0x00000000;
	    
	    if(EncryptKey.length()>3){
			pbyte=EncryptKey.substring(0, 3).getBytes();
		}else{
			pbyte=EncryptKey.getBytes();
		}
		int nKeyLength=pbyte.length;
//	    byte[] pbyte=EncryptKey.getBytes();
//	    int nKeyLength=(EncryptKey.getBytes()).length;
	    long t1;
	    String Re="";
	    String c;
	    int i;
	    for(i=0;i<nKeyLength;++i)
	    {
	    	c="";
	        //c=(String)pbyte[i];
	    	int pb=pbyte[i];
	    	if(pb<0){
	    		pb=pb+256;
	    	}
	        t=t | pb;
	        if((i+1)%4==0)
	        {
	            t1=(int)(t/nKey);
	            Re=Re+Long.toHexString(t1);
//	            AnsiString s;
//	            s=IntToHex(t1,8);
//	            ShowMessage(s.c_str());
	            t=0x00000000;
	        }
	        t=t<<8;
	    }
	    i=i+1;
	    if(i<=4)
	    {
	        t1=(int)(t/nKey);
	        EncryptKey=Long.toHexString(t1);
	    }
	    else
	        EncryptKey=Re;
	    EncryptKey=EncryptKey.toUpperCase();
	    int el=EncryptKey.length();
	    if(el<8){
	    	for(int f=0;f<8-el;f++)
	    		EncryptKey='0'+EncryptKey;
	    }

	    Re="";
	    int KeyByte;
	    int ObjByte;
	    nKeyLength=EncryptKey.length();
	    int CycleIndex=s.length();
	    for(i=0;i<CycleIndex;i+=2)
	    {
	    	int p1=i%nKeyLength;
	    	KeyByte=HexToBin(EncryptKey.substring(p1, p1+2>nKeyLength?p1+1:p1+2));
	    	//ObjByte=HexToBin(s.substring(p1, p1+2>nKeyLength?p1+1:p1+2));
	    	ObjByte=HexToBin(s.substring(i, i+2>CycleIndex?i+1:i+2));
	        ObjByte=ObjByte^KeyByte;
	        Re=Re+(char)(ObjByte);
	    }		
		return Re;
	}	
	
	//加密
	public String Encrypt(String s,String EncryptKey){
	//var
/*	  nKey:       double;
	  t:          Dword;
	  nKeyLength: integer;
	  t1:         int64;
	  c:          char;
	  i:          integer;
	  KeyByte:    Byte;
	  ObjByte:    Byte;
	  CycleIndex: integer;
	  Re:string;*/
	//begin
	  String Re="";
	  byte[] pbyte=null;
	  double nKey=SOFT_BUILD/CLIENT_KEY;
	  if(EncryptKey.length()==0){
	    EncryptKey="63A9B8C19F10D547";
	  }
	  long t=0x00000000;
	  long t1;
	  if(EncryptKey.length()>3){
			pbyte=EncryptKey.substring(0, 3).getBytes();
		}else{
			pbyte=EncryptKey.getBytes();
		}
//	  byte[] pbyte=EncryptKey.getBytes();
	  int nKeyLength=pbyte.length;
	  int i;
	  for(i=0;i<nKeyLength;++i){
	    	int pb=pbyte[i];
	    	if(pb<0){
	    		pb=pb+256;
	    	}
	        t=t | pb;
	    if(((i+1)%4)==0){
	    t1=(int)(t/nKey);
	    Re=Re+Long.toHexString(t1);
	      t=0x00000000;
	    }
	    t= t << 8;
	  }
	  i=i+1;
	  if(i<=4){
	    t1=(int)(t/nKey);
	    EncryptKey=Long.toHexString(t1);
	  }
	  else
	    EncryptKey=Re;

	  Re="";
	  nKeyLength=EncryptKey.length();
	  EncryptKey=EncryptKey.toUpperCase();
	  int el=EncryptKey.length();
	  if(el<8){
		for(int f=0;f<8-el;f++)
		   EncryptKey='0'+EncryptKey;
	  }
	  int CycleIndex=s.length();
	  int KeyByte;
	  int ObjByte;
	  nKeyLength=EncryptKey.length();
	  for(i=0;i<CycleIndex;++i){
	    //HexToBin(PChar(MidStr(EncryptKey,(2*i-1) % nKeyLength,2)),@KeyByte,2);
	    
    	int p1=2*i % nKeyLength;
    	String pstr=EncryptKey.substring(p1, p1+2>nKeyLength?p1+1:p1+2);
    	KeyByte=HexToBin(pstr);
    	pstr=s.substring(i, i+1>CycleIndex?i:i+1);
    	ObjByte=pstr.getBytes()[0];
	    //ObjByte=HexToBin(pstr);
	    ObjByte=ObjByte^KeyByte;
	    
	    //解决整型转十六进制前位少"0"问题——开始
	    if(ObjByte <10){
	    	Re=Re+"0"+ObjByte;
	    }else{
	    	Re=Re+Integer.toHexString(ObjByte);
	    }
	    //解决整型转十六进制前位少"0"问题——结束
	    Re=Re.toUpperCase();
	  }
	  return Re;
	}
	
	public int HexToBin(String ss){

		int a=0;
		int b=0;
		String sss;
		for(int i=0;i<ss.length();i++){
			sss=ss.substring(i,i+1);
			if(sss.equals("A"))
				a=10;
			else if(sss.equals("B"))
				a=11;
			else if(sss.equals("C"))
				a=12;
			else if(sss.equals("D"))
				a=13;
			else if(sss.equals("E"))
				a=14;
			else if(sss.equals("F"))
				a=15;
			else
				a=Integer.parseInt(sss);	
			
			if(i==0){
				b=b+a*16;
			}
			else{
				b=b+a;
			}
		}
		return b;	
	}
	
	/*public static void main(String[] args){
		encrypt f=new encrypt();

		//解密
		//System.out.println(f.Dencrypt("314F71", "sys"));
		System.out.println(f.Dencrypt("74FC2B3", "mrj"));
		
		//加密
		//System.out.println(f.Encrypt("yxg090", "sys"));	
		System.out.println(f.Encrypt("mrj", "mrj"));
		
		String str="ASDQWE123fs1345";
		for(int i=1;i<str.length();i+=2){
			System.out.println(str.substring(i, i+2>str.length()?i+1:i+2));
		}
		
	}
*/
}
