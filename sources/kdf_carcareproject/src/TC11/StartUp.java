package TC11;

import java.text.DecimalFormat;

import KDF.ManageExcel;

public class StartUp {

	/**/public static String vUrl, vUsername, vPassword, vProImage, vProname, vDetail, vPrice, vSD, vED;
	public static String vResult, vError, vflag;
	public static String xTDdata[][];
	public static int passes, fails;
	public static double priority, totalfail;

	public static void main(String[] args) throws Exception {
		String expRe;
		String xTSdata[][];
		String xTCdata[][];
		String vKeyword, vIP1, vIP2;

		String xlPath_tc = "G://KDF_CarcareProject/TC11/TC_edit_promotion_TC11.xlsx";
		String xlPath_ts = "G://KDF_CarcareProject/TC11/TS_edit_promotion_TC11.xlsx";
		String xlPath_td = "G://KDF_CarcareProject/TC11/TD_edit_promotion_TC11.xlsx";
		String xlPath = "TC11-edit promotion.xlsx";

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
							System.out.println("---" + vKeyword + "````" + vIP1 + "````" + vIP2+ "````" + xTDdata[k][0]);
							vResult = "Pass";
	                		vError = "No Error";
	                		expRe = xTDdata[k][13];
	                		vResult = myDriver.keyword_executor( vKeyword, vIP1, vIP2);
	                		
	                		if (vResult.equalsIgnoreCase("Pass")) {
	                			xTSdata[j][8] = "Pass";
	                		}else if (vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][8] = "Fail";
	                		}else {
	                			//get text
	                			if (vKeyword.equals("get_alert")) {
	                				xTSdata[j][8] = "Pass";
	                				if (vResult.equalsIgnoreCase(xTDdata[k][13])) {
	                					xTDdata[k][14] = "Pass";
		                				xTDdata[k][15] = "No Error";
		                				xTDdata[k][12] = vResult;
	    	                		}else if (expRe.equals("แก้ไขรายการโปรโมชั่นสำเร็จ!")) {
	                					xTDdata[k][14] = "Pass";
		                				xTDdata[k][15] = "No Error";	            
		                				xTDdata[k][12] = "แก้ไขรายการโปรโมชั่นสำเร็จ!";
	    	                		}else if (expRe.equals("กรุณาเพิ่มไฟล์รูปภาพโปรโมชั่นเป็นนามสกุล JPEG, JPG (.jpg) หรือ PNG (.png)")) {
	                					xTDdata[k][14] = "Pass";
		                				xTDdata[k][15] = "No Error";	            
		                				xTDdata[k][12] = "กรุณาเพิ่มไฟล์รูปภาพโปรโมชั่นเป็นนามสกุล JPEG, JPG (.jpg) หรือ PNG (.png)";
	    	                		}else {
	                					xTDdata[k][14] = "Fail";
		                				xTDdata[k][15] = "Not Match Expected Result";
		                				xTDdata[k][12] = vResult;
		                				Utility utl = new Utility();
	                					myDriver.getUtility().saveScreen(xTDdata[k][0]);
	                				}
	                				
	                				if (expRe.equals("กรุณากรอกราคาให้ถูกต้อง/ราคาต้องมีเครื่องหมาย ( , ) คั่นระหว่างตัวเลข ")) {   
	                					xTDdata[k][16] = "ควรมีแจ้งเตือน Alert ให้ผู้ใช้กรอกเครื่องหมาย (,) ระหว่างตัวเลข";
	    	    					} else if (expRe.equals("กรุณากรอกวันที่เริ่มต้นให้ถูกต้อง")) {      
		                				xTDdata[k][16] = "ควรมีการแจ้งรายละเอียดให้ผู้ใช้กรอกวันที่เริ่มต้นให้เป็นวันที่ในอนาคตเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกวันที่สิ้นสุดให้ถูกต้อง")) {      
		                				xTDdata[k][16] = "ควรมีการแจ้งรายละเอียดให้ผู้ใช้กรอกวันที่สิ้นสุดให้เป็นวันที่ในอนาคตเท่านั้น";
	    	    					}
	                				
	                				if (xTDdata[k][14] == "Fail") {
	    	                			fails += 1;
	    	    					} else if (xTDdata[k][14] == "Pass") {
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