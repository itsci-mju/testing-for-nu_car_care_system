package TC01;
import java.text.DecimalFormat;



public class StartUp {

	/**/public static String vUrl, vUsername, vPassword;
	public static String vResult, vError, vflag;
	public static String xTDdata[][];

	public static void main(String[] args) throws Exception {
		String xTSdata[][];
		String xTCdata[][];
		String vKeyword, vIP1, vIP2;

		String xlPath_tc = "G://KDF_CarcareProject/TC01/TC_login_TC01.xlsx";
		String xlPath_ts = "G://KDF_CarcareProject/TC01/TS_login_TC01.xlsx";
		String xlPath_td = "G://KDF_CarcareProject/TC01/TD_login_TC01.xlsx";
		String xlPath = "TC01-login.xlsx";

		Driver myDriver = new Driver();
		Utility utility = new Utility();
		

		ManageExcel kdf = new ManageExcel();
		xTCdata = kdf.xlRead(xlPath, 0);
		xTSdata = kdf.xlRead(xlPath, 1);
		xTDdata = kdf.xlRead(xlPath, 2);

		for (int i = 1; i < xTCdata.length; i++) {
			if (xTCdata[i][4].equals("Y")) {
		 		vflag = "Pass";
		 		
		 		for (int k = 1; k < xTDdata.length; k++) {
					if (xTDdata[k][1].equals("Y")) {
						myDriver.getData(k);
						
					for (int j = 1; j <xTSdata.length; j++){
						if (xTCdata[i][1].equals(xTSdata[j][0])){	
							vKeyword = xTSdata[j][4];
							vIP1 = xTSdata[j][5];
							vIP2 = xTSdata[j][6];
							System.out.println("---" + vKeyword + "````" + vIP1 + "````" + vIP2);
							vResult = "Pass";
	                		vError = "No Error";
	                		
	                		vResult = myDriver.keyword_executor( vKeyword, vIP1, vIP2);
	                		
	                		if (vResult.equalsIgnoreCase("Pass")) {
	                			xTSdata[j][8] = "Pass";
	                		}else if (vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][8] = "Fail";
	                		}else {
	                			//get text
	                			
	                			if (vKeyword.equals("get_alert")) {
	                				xTSdata[j][8] = "Pass";
	                			
	                				if (vResult.equalsIgnoreCase(xTDdata[k][7])) {
	                					xTDdata[k][8] = "Pass";
		                				xTDdata[k][9] = "No Error";	            
		                				xTDdata[k][6] = vResult;
	    	                		}else {
	                					xTDdata[k][8] = "Fail";
		                				xTDdata[k][9] = "Not Match Expected Result";
		                				xTDdata[k][6] = vResult;
		                				Utility utl = new Utility();
	                					myDriver.getUtility().saveScreen(xTDdata[k][0]);
	                				}
	                			}
	                		}
	                		
							/*
							 * if (!vError.equals("No Error")){ vflag = "Fail";
							 * myDriver.getUtility().saveScreen(i); xTSdata[j][8] = vResult; xTDdata[k][12]
							 * = vResult; xTDdata[k][13] = vError; }
							 */
				        }
				   }
					
				xTCdata[i][5] = vflag;							
			}
		}	
		kdf.xlWrite(xlPath_tc, xTCdata, xTCdata.length, xTCdata[1].length);
		kdf.xlWrite(xlPath_ts, xTSdata, xTSdata.length, xTSdata[1].length);	
		kdf.xlWrite(xlPath_td, xTDdata, xTDdata.length, xTDdata[1].length);		
	  }		
    }
  }		
}