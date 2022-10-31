package TC02;

import java.text.DecimalFormat;

import KDF.ManageExcel;

public class StartUp {

	/**/public static String vUrl,vUser, vPass, vFirstName, vLastName, vEmail, vPhone,vPassword,vBirthday,vHomeNumber,vStreet,vSubDistrict,vDistrict,vProvince,vZipCode;
	public static String vResult, vError, vflag;
	public static String xTDdata[][];
	public static int passes, fails;
	public static double totalpass, totalfail;

	public static void main() throws Exception {
		String expRe;
		String xTSdata[][];
		String xTCdata[][];
		String vKeyword, vIP1, vIP2;

		String xlPath_tc = "G://KDF_CarcareProject/TC/TC02_Edit_Profile.xlsx";
		String xlPath_ts = "G://KDF_CarcareProject/TS/TS02_Edit_Profile.xlsx";
		String xlPath_td = "G://KDF_CarcareProject/TD/TD02_Edit_Profile.xlsx";
		String xlPath = "TC02-Edit Profile.xlsx";

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
	                		expRe = xTDdata[k][18];
	               
	                		vResult = myDriver.keyword_executor( vKeyword, vIP1, vIP2);
	                		
	                		if (vResult.equalsIgnoreCase("Pass")) {
	                			xTSdata[j][8] = "Pass";
	                		}else if (vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][8] = "Fail";
	                		}else {
	                			//get text
	                			if (vKeyword.equals("get_alert")) {
	                				xTSdata[j][8] = "Pass";
	                				if (vResult.equalsIgnoreCase(xTDdata[k][18])) {
	                					xTDdata[k][19] = "Pass";
		                				xTDdata[k][21] = "No Error";
		                				xTDdata[k][20] = vResult;
	                				}else {
	                					xTDdata[k][19] = "Fail";
	                					xTDdata[k][21] = "Not Match Expected Result";
		                				xTDdata[k][20] = vResult;
		                				Utility utl = new Utility();
	                					myDriver.getUtility().saveScreen(xTDdata[k][0]);
	                				}
	                				
	                				if (expRe.equals("กรุณากรอกชื่อให้เป็นภาษาไทยหรือภาษาอังกฤษ")) {   
	                					xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกชื่อให้เป็นภาษาไทยหรือภาษาอังกฤษ";
	    	    					} else if (expRe.equals("กรุณากรอกชื่อให้เป็นภาษาไทยหรือภาษาอังกฤษ ที่มีความยาวตั้งแต่ 2-30 ตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกชื่อให้เป็นตัวอักษรที่มีความยาวตั้งแต่ 2-30 ตัวอักษรเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกชื่อโดยจะต้องไม่มีเว้นวรรคระหว่างตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกชื่อโดยจะต้องไม่มีเว้นวรรคระหว่างตัวอักษรเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกนามสกุลให้เป็นภาษาไทยหรือภาษาอังกฤษ")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกนามสกุลให้เป็นภาษาไทยหรือภาษาอังกฤษเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกนามสกุลโดยจะต้องไม่มีเว้นวรรคระหว่างตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกนามสกุลโดยจะต้องไม่มีเว้นวรรคระหว่างตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกอีเมลให้เป็นภาษาอังกฤษ และตัวเลข")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกอีเมลให้เป็นภาษาอังกฤษ และตัวเลข";
	    	    					} else if (expRe.equals("กรุณากรอกอีเมลให้ถูกต้องตามรูปแบบอีเมล")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกอีเมลให้ถูกต้องตามรูปแบบอีเมล";
	    	    					} else if (expRe.equals("กรุณากรอกอีเมลให้เป็นภาษาอังกฤษ และตัวเลขที่มีความยาวตั้งแต่ 5-64 ตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกอีเมลให้เป็นภาษาอังกฤษ และตัวเลขที่มีความยาวตั้งแต่ 5-64 ตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกอีเมลโดยจะต้องไม่มีเว้นวรรคระหว่างตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกอีเมลโดยจะต้องไม่มีเว้นวรรคระหว่างตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกเบอร์โทรศัพท์ เป็นตัวเลขเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกเบอร์โทรศัพท์ เป็นตัวเลขเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกเบอร์โทรศัพท์ เป็นตัวเลข ที่มีความยาว 10 ตัวอักษรเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกเบอร์โทรศัพท์ เป็นตัวเลข ที่มีความยาว 10 ตัวอักษรเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกเบอร์โทรศัพท์เป็นตัวเลขตามรูปแบบต้องขึ้นต้นด้วย 06, 08, และ 09 เท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกเบอร์โทรศัพท์เป็นตัวเลขตามรูปแบบต้องขึ้นต้นด้วย 06, 08, และ 09 เท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกเบอร์โทรศัพท์ โดยจะต้องไม่มีเว้นวรรคระหว่างตัวเลข")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกเบอร์โทรศัพท์ โดยจะต้องไม่มีเว้นวรรคระหว่างตัวเลข";
	    	    					} else if (expRe.equals("กรุณากรอกรหัสผ่านให้เป็นตัวอักษรภาษาอังกฤษ และตัวเลขเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกรหัสผ่านให้เป็นตัวอักษรภาษาอังกฤษ และตัวเลขเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกรหัสผ่านให้เป็นตัวอักษรภาษาอังกฤษ หรือตัวเลขที่มีความยาวตั้งแต่ 4-16 ตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกรหัสผ่านให้เป็นตัวอักษรที่มีความยาวตั้งแต่ 4-16 ตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกรหัสผ่าน โดยจะต้องไม่มีเว้นวรรคระหว่างตัวเลข")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกรหัสผ่าน โดยจะต้องไม่มีเว้นวรรคระหว่างตัวเลข";
	    	    					} else if (expRe.equals("วันเกิดผู้ใช้ต้องมีอายุมากกว่า 18 ปีขึ้นไป")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้อายุผู้ใช้มีอายุ 18 ปีขึ้นไป";
	    	    					} else if (expRe.equals("กรุณากรอกถนนเป็นตัวอักษรภาษาไทย ภาษาอังกฤษ และตัวเลขเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกถนนเป็นตัวอักษรภาษาไทย ภาษาอังกฤษ และตัวเลขเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกถนนเป็นตัวอักษรภาษาไทย ภาษาอังกฤษ และตัวเลขที่มีความยาวตั้งแต่ 2-50 ตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกถนนให้เป็นตัวอักษรที่มีความยาวตั้งแต่ 2-50 ตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกตำบลเป็นตัวอักษรภาษาไทย และภาษาอังกฤษเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกตำบลเป็นตัวอักษรภาษาไทย ภาษาอังกฤษเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกตำบลเป็นตัวอักษรภาษาไทยและภาษาอังกฤษที่มีความยาวตั้งแต่ 2-50 ตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกตำบลให้เป็นตัวอักษรที่มีความยาวตั้งแต่ 2-50 ตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกอำเภอเป็นตัวอักษรภาษาไทย และภาษาอังกฤษเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกอำเภอเป็นตัวอักษรภาษาไทย ภาษาอังกฤษเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกอำเภอเป็นตัวอักษรภาษาไทย และภาษาอังกฤษที่มีความยาวตั้งแต่ 2-50 ตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกอำเภอให้เป็นตัวอักษรที่มีความยาวตั้งแต่ 2-50 ตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกจังหวัดเป็นตัวอักษรภาษาไทย และภาษาอังกฤษเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกจังหวัดเป็นตัวอักษรภาษาไทย และภาษาอังกฤษเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกจังหวัดเป็นตัวอักษรภาษาไทย และภาษาอังกฤษที่มีความยาวตั้งแต่ 2-50 ตัวอักษร")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกจังหวัดให้เป็นตัวอักษรที่มีความยาวตั้งแต่ 2-50 ตัวอักษร";
	    	    					} else if (expRe.equals("กรุณากรอกรหัสไปรษณีย์เป็นตัวเลข")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกกรอกรหัสไปรษณีย์เป็นตัวเลขเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกรหัสไปรษณีย์เป็นตัวเลข 5 หลักเท่านั้น")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกรหัสไปรษณีย์เป็นตัวเลข 5 หลักเท่านั้น";
	    	    					} else if (expRe.equals("กรุณากรอกรหัสไปรษณีย์เป็นตัวเลขให้ไม่มีช่องว่าง")) {      
		                				xTDdata[k][22] = "ควรแจ้งรายละเอียดให้สามารถกรอกรหัสไปรษณีย์เป็นตัวเลขให้ไม่มีช่องว่าง";
	    	    					} 
	                				
	                				System.out.println(StartUp.xTDdata[k][0]);
	            					System.out.println("Expected : " + StartUp.xTDdata[k][18]);
	            					System.out.println("Result : " + StartUp.xTDdata[k][19]);
	            					System.out.println("Actual : " + StartUp.xTDdata[k][20]);
	                				
	                				if (xTDdata[k][19] == "Fail") {
	    	                			fails += 1;
	    	    					} else if (xTDdata[k][19] == "Pass") {
	    	    						passes += 1;
	    	    					}
	                			}
	                		}
				        }
				   }
					DecimalFormat df = new DecimalFormat("#.##");
					if (fails != 0) {
						totalpass = 100.00 * (passes * 1.0 / (fails + passes));
						totalfail = 100.00 * (fails * 1.0 / (fails + passes));
						xTCdata[i][5] = passes + "";
						xTCdata[i][6] = fails + "";
						xTCdata[i][7] = df.format(totalpass) + "%";
						xTCdata[i][8] = df.format(totalfail) + "%";
						if (xTCdata[i][4].equals("High")) {
							if (totalpass < 95) {
								xTCdata[i][9] = "Fail";
							} else {
								xTCdata[i][9] = vflag;
							}
						} else if (xTCdata[i][4].equals("Medium")) {
							if (totalpass < 97) {
								xTCdata[i][9] = "Fail";
							} else {
								xTCdata[i][9] = vflag;
							}
						} else if (xTCdata[i][4].equals("Low")) {
							if (totalpass < 100) {
								xTCdata[i][9] = "Fail";
							} else {
								xTCdata[i][9] = vflag;
							}
						}
					} else if (passes == 0) {
						totalpass = 0;
						xTCdata[i][5] = passes + "";
						xTCdata[i][6] = fails + "";
						xTCdata[i][7] = df.format(totalpass) + "%";
						xTCdata[i][8] = df.format(totalfail) + "%";
						xTCdata[i][9] = "Fail";
					} else {
						totalpass = 100;
						xTCdata[i][5] = passes + "";
						xTCdata[i][6] = fails + "";
						xTCdata[i][7] = df.format(totalpass) + "%";
						xTCdata[i][8] = df.format(totalfail) + "%";
						xTCdata[i][9] = vflag;	
					}										
			}
		}	
		kdf.xlWrite(xlPath_tc, xTCdata, xTCdata.length, xTCdata[1].length);
		kdf.xlWrite(xlPath_ts, xTSdata, xTSdata.length, xTSdata[1].length);	
		kdf.xlWrite(xlPath_td, xTDdata, xTDdata.length, xTDdata[1].length);		
	  }		
			System.out.println("---done---");
    }
  }		
}