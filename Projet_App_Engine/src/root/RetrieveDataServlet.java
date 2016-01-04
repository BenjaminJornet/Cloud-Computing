package root;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;



	@SuppressWarnings("serial")
	public class RetrieveDataServlet extends HttpServlet{

		JSONObject jsonReceive;
		
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			resp.setContentType("application/json");
	        PrintWriter out= resp.getWriter();
	        out.print(jsonReceive);
	        out.flush();
	        out.close();
			
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			String jsonString = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);		

			System.out.println("(Servlet retrieve data) Request string " + jsonString);
			
			jsonReceive = (JSONObject) JSONValue.parse(jsonString);
			System.out.println(jsonString);
			
			String q="";
			
			if(jsonReceive != null){
				q = (String) jsonReceive.get("q");
			}
			else{
				jsonReceive = getJSONFromTaskQueueRequest(jsonString);
				if(jsonReceive != null){
					q = (String)jsonReceive.get("q");
				}
			}
			System.out.println("(Servlet retrieve data) Request json " + jsonReceive);
			
			
			if(q!=null){
				
				
			}
			System.out.println(jsonReceive);
			resp.setContentType("application/json");
	        PrintWriter out= resp.getWriter();
	        out.print(jsonReceive);
	        out.flush();
	        out.close();
			
		}
		public JSONObject getJSONFromTaskQueueRequest(String req){
			jsonReceive = new JSONObject();
			if(req.contains("&")){
				String[] paramsplit = req.split("&");
				for(String params : paramsplit){
					String[] param = params.split("=");
					
					//Reconstruction du json a partir de la requete
					
//					for(int i=0;i<param.length;i++){
//						
//							String str = "";
//							
//							System.out.println("yeu");
//							int j=0;
//							while(("%".equals(param[j])) == false){
//								if("%".equals(param[i+1].substring(j, j+1))){
//
//									String ascii = "" + param[i+1].substring(j+1, j+3) ;
//									int asc = Integer.parseInt(ascii, 16);
//									str= str +(char)asc;
//									j=j+2;
//								}
//								else{
//									str = str + param[i+1].substring(j, j+1);
//								}
//								j++;
//							}
//							char a = '"';
//							String b =""+a;
//							
//							System.out.println(str.toString());
//							str=str.replace("+", " ");
//							str=str.replace("\"", b );
//							System.out.println(str);
//
//							}
//						
//						
//					
//					
					System.out.println(jsonReceive);
					
				}
			}
//			if("".equals(jsonReceive.get("title")) && "".equals(jsonReceive.get("description")) && "".equals(jsonReceive.get("domain"))&&"".equals(jsonReceive.get("ex"))&& "".equals(jsonReceive.get("heure"))&& "".equals(jsonReceive.get("minute"))){
//				return null;
//			}
			return jsonReceive;
		}
}

