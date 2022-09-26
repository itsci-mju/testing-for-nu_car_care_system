package TC01;
import org.openqa.selenium.NoSuchElementException;

public class Driver {
private Utility util = new Utility();
	
	public Utility getUtility() {
		return util;
	}

	public String keyword_executor(String vKeyword, String vIP1, String vIP2) throws Exception {
		
		String flag = "false";
	
		try {
			
			if (vKeyword.equals("browser_open")){		   
			    util.browser_open(getIP(vIP1));
			    flag = "True";
			    return "pass";
			}
			if (vKeyword.equals("radio_select")){
				util.radio_select(getIP(vIP1), getIP(vIP2));
				flag = "True";
			    return "pass";
			}
			
			if (vKeyword.equals("checkbox_set")){
				util.checkbox_set(getIP(vIP1), getIP(vIP2));
				flag = "True";
			    return "pass";
			}
			if (vKeyword.equals("list_select")){
				util.list_select(getIP(vIP1), getIP(vIP2));
				flag = "True";
			    return "pass";
			}
			if (vKeyword.equals("edit_input")){
				util.edit_input(getIP(vIP1), getIP(vIP2));
				flag = "True";
			    return "pass";
			}
			
			/*if (vKeyword.equals("file_inputName")){
				util.file_inputName(getIP(vIP1), getIP(vIP2));
				flag = "True";
			    return "pass";
			}*/
			
			if (vKeyword.equals("get_text")){
				flag = "True";
			    return util.get_text(getIP(vIP1));
			}
			if (vKeyword.equals("edit_inputById")){
				util.edit_inputById(getIP(vIP1), getIP(vIP2));
				flag = "True";
			    return "pass";
			}
			/*if (vKeyword.equals("edit_inputByName")){
				util.edit_inputByName(getIP(vIP1), getIP(vIP2));
				flag = "True";
			    return "pass";
			}*/
			
			if (vKeyword.equals("button_click")){
				util.button_click(getIP(vIP1));
				flag = "True";
			    return "pass";
			}
			if (vKeyword.equals("click_link")){
				util.click_link(getIP(vIP1));
				flag = "True";
			    return "pass";
			}
			if (vKeyword.equals("get_alert")){
				String result = util.get_alert();
				flag = "True";
			    return result;
			}
			if (vKeyword.equals("dialog_click")){
				boolean works = util.dialog_click();
				if (works) {
			        flag = "True";
		            return "pass";
			    }
			}
			if (vKeyword.equals("browser_close")){		   
			    util.browser_close();
			    flag = "True";
			    return "pass";
			}
			
			if (flag.equals("false")){
				System.out.println("Keyword is missing " + vKeyword);
				StartUp.vError = "Error";		
				return "False-Keyword Missing";
			  }
	    	}
			catch (NoSuchElementException e){
				System.out.println("Error is " + e);
				StartUp.vError = String.valueOf(e);
				
				return "Fail";
			}
			return "Unknown Keyword";
		}
		
		public String getIP(String vIP){ 
			
			if (vIP.equals("vUrl")){
				vIP = StartUp.vUrl;
			}
			if (vIP.equals("vUsername")){
				vIP = StartUp.vUsername;
			}
			if (vIP.equals("vPassword")){
				vIP = StartUp.vPassword;
			}

			
			
		  return vIP;
		}

		public void getData(int k) {
			StartUp.vUrl = StartUp.xTDdata[k][2];
			StartUp.vUsername = StartUp.xTDdata[k][4];
			StartUp.vPassword = StartUp.xTDdata[k][5];
		}
	}
