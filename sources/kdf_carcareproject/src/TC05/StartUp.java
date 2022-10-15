package TC05;

import java.text.DecimalFormat;

import KDF.ManageExcel;

public class StartUp {

	/**/public static String vUrl, vFirstName, vLastName, vEmail, vPhone,vUsername,vPassword,vBirthday,vHomeNumber,vStreet,vSubDistrict,vDistrict,vProvince,vZipCode,vCarSize,vCarType,vCarNumber;
	public static String vResult, vError, vflag;
	public static String xTDdata[][];
	public static int passes, fails;
	public static double priority, totalfail;

	public static void main(String[] args) throws Exception {
		String expRe;
		String xTSdata[][];
		String xTCdata[][];
		String vKeyword, vIP1, vIP2;

		String xlPath_tc = "G://KDF_CarcareProject/TC05/TC_add_member_TC05.xlsx";
		String xlPath_ts = "G://KDF_CarcareProject/TC05/TS_add_member_TC05.xlsx";
		String xlPath_td = "G://KDF_CarcareProject/TC05/TD_add_member_TC05.xlsx";
		String xlPath = "TC05-add member.xlsx";

		Driver myDriver = new Driver();

		ManageExcel kdf = new ManageExcel();
		xTCdata = kdf.xlRead(xlPath, 0);
		xTSdata = kdf.xlRead(xlPath, 1);
		xTDdata = kdf.xlRead(xlPath, 2);

		for (int i = 1; i < xTCdata.length; i++) {
			if (xTCdata[i][3].equals("Y")) {
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
	                		expRe = xTDdata[k][21];
	                		vResult = myDriver.keyword_executor( vKeyword, vIP1, vIP2);
	                		
	                		if (vResult.equalsIgnoreCase("Pass")) {
	                			xTSdata[j][8] = "Pass";
	                		}else if (vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][8] = "Fail";
	                		}else {
	                			//get text
	                			if (vKeyword.equals("get_alert")) {
	                				xTSdata[j][8] = "Pass";
	                				if (vResult.equalsIgnoreCase(xTDdata[k][21])) {
	                					xTDdata[k][22] = "Pass";
		                				xTDdata[k][23] = "No Error";
		                				xTDdata[k][20] = vResult;
	                				}else {
	                					xTDdata[k][22] = "Fail";
	                					xTDdata[k][23] = "Not Match Expected Result";
		                				xTDdata[k][20] = vResult;
		                				Utility utl = new Utility();
	                					myDriver.getUtility().saveScreen(xTDdata[k][0]);
	                				}
	                				if (expRe.equals("กรุณากรอกอีเมลให้เป็นภาษาอังกฤษ และตัวเลข")) {   
	                					xTDdata[k][24] = "ควรมีการแจ้งรายละเอียดให้ผู้ใช้กรอกได้เพียงตัวอักษรภาษาอังกฤษ และตัวเลขเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกเบอร์โทรศัพท์ เป็นตัวเลขเท่านั้น")) {      
		                				xTDdata[k][24] = "ควรมีการแจ้งรายละเอียดให้ผู้ใช้กรอกเบอร์โทรศัพท์เป็นตัวเลขเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกเบอร์โทรศัพท์เป็นตัวเลขตามรูปแบบต้องขึ้นต้นด้วย 06, 08, และ 09 เท่านั้น")) {      
		                				xTDdata[k][24] = "ควรมีการแจ้งรายละเอียดให้ผู้ใช้กรอกเบอร์โทรศัพท์ขึ้นต้นด้วย 06, 08, 09เท่านั้น";
	    	    					}else if (expRe.equals("กรุณากรอกวันเกิดให้ถูกต้อง")) {      
		                				xTDdata[k][24] = "ควรมีการแจ้งรายละเอียดให้ผู้ใช้ทราบว่าวันเกิดไม่สามารถกรอกปีปัจจุบันได้";
	    	    					}
	                				
	                				if (xTDdata[k][22] == "Fail") {
	    	                			fails += 1;
	    	    					} else if (xTDdata[k][22] == "Pass") {
	    	    						passes += 1;
	    	    					}
	                			}
	                		}

				        }
				   }
					DecimalFormat df = new DecimalFormat("#.##");
					if (fails != 0) {
						priority = 100.00 * (passes * 1.0 / (fails + passes));
						totalfail = 100.00 * (fails * 1.0 / (fails + passes));
						xTCdata[i][5] = passes + "";
						xTCdata[i][6] = fails + "";
						xTCdata[i][7] = df.format(priority) + "%";
						xTCdata[i][8] = df.format(totalfail) + "%";
						if (xTCdata[i][4].equals("High")) {
							if (priority < 95) {
								xTCdata[i][9] = "Fail";
							} else {
								xTCdata[i][9] = vflag;
							}
						} else if (xTCdata[i][4].equals("Medium")) {
							if (priority < 97) {
								xTCdata[i][9] = "Fail";
							} else {
								xTCdata[i][9] = vflag;
							}
						} else if (xTCdata[i][4].equals("Low")) {
							if (priority < 100) {
								xTCdata[i][9] = "Fail";
							} else {
								xTCdata[i][9] = vflag;
							}
						}
					} else if (passes == 0) {
						priority = 0;
						xTCdata[i][5] = passes + "";
						xTCdata[i][6] = fails + "";
						xTCdata[i][7] = df.format(priority) + "%";
						xTCdata[i][8] = df.format(totalfail) + "%";
						xTCdata[i][9] = "Fail";
					} else {
						priority = 100;
						xTCdata[i][5] = passes + "";
						xTCdata[i][6] = fails + "";
						xTCdata[i][7] = df.format(priority) + "%";
						xTCdata[i][8] = df.format(totalfail) + "%";
						xTCdata[i][9] = vflag;	
					}														
			}
		}	
		kdf.xlWrite(xlPath_tc, xTCdata, xTCdata.length, xTCdata[1].length);
		kdf.xlWrite(xlPath_ts, xTSdata, xTSdata.length, xTSdata[1].length);	
		kdf.xlWrite(xlPath_td, xTDdata, xTDdata.length, xTDdata[1].length);		
	  }		
    }
  }		
}