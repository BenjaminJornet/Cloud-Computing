package root;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.appengine.labs.repackaged.org.json.JSONArray;




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
//			System.out.println(jsonString);
			
			String q="";
			
			if(jsonReceive != null){
				System.out.println("pas null jsonR");
			}
			else{
				jsonReceive = getJSONFromTaskQueueRequest(jsonString);
				if(jsonReceive != null){
					System.out.println("json pas null");
				}
			}
			System.out.println("(Servlet retrieve data) Request json " + jsonReceive);
			
			System.out.println(jsonReceive);
			resp.setContentType("application/json");
	        PrintWriter out= resp.getWriter();
	        out.print(jsonReceive);
	        out.flush();
	        out.close();
			
		}
		public JSONObject getJSONFromTaskQueueRequest(String req){
			jsonReceive = new JSONObject();
			JSONObject jsonPlan = new JSONObject();
			JSONObject jsonEx = new JSONObject();
			JSONArray jsonArrayEx = new JSONArray();
			JSONArray jsonArrayPlan = new JSONArray();

			URLDecoder u = new URLDecoder();
			String[] str;
			String s="";
			try {
				s = u.decode(req, "UTF-8");
				System.out.println(s);
				str=s.split("&");
				for(String st : str){
					String[] kv = st.split("=");
					String v =kv[1];
					String kv0 = kv[0];
					String[] k = kv0.split("\\[");
					String key = k[2].split("\\]")[0];
					
					if("plan".equals(k[0])){
						if(jsonPlan.containsKey(key)){
							jsonArrayPlan.put(jsonPlan);
							jsonPlan=new JSONObject();
						}
						jsonPlan.put(key, v);
						
					}
					if("ex".equals(k[0])){
						if(jsonEx.containsKey(key)){
							jsonArrayEx.put(jsonEx);
							jsonEx=new JSONObject();
						}
			
						jsonEx.put(key, v);	
					}
				}
				jsonArrayEx.put(jsonEx);
				jsonArrayPlan.put(jsonPlan);

				if(jsonPlan != null){
					jsonReceive.put("plan", jsonPlan);
				}
				else{
					jsonReceive.put("plan", "");
				}
				if(jsonEx != null){
					jsonReceive.put("ex", jsonArrayEx);
				}
				else{
					jsonReceive.put("ex", "");
				}
				
				
				System.out.println(jsonReceive);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			
//			if(req.contains("&")){
//				
//				String[] str = req.split("%");
//				String res = "";
//				
//				int i = 0;
//				for(String s : str){
//						if(i == 0){
//							
//						}
//						else{
//							String ascii = ""+s.substring(0, 2);
//							int asc = Integer.parseInt(ascii, 16);
//							res= res +(char)asc;
//						}
//					
//					i++;
//				}
				
//				String[] paramsplit = req.split("&");
//				for(String params : paramsplit){
//					String[] param = params.split("=");
//					String str="";
//					
//					for(String p : param){
//						System.out.println(p);
//						if(p.contains("%")){
////							String ascii = ""+p;
////							int asc = Integer.parseInt(ascii, 16);
////							str= str +(char)asc;
//							String[] ascii = p.split("%");
//							if("plan".equals(ascii[0])){
//								String asci = ""+p;
//								int asc = Integer.parseInt(asci, 16);
//								str= str +(char)asc;
//							}
//						}else{
//							str=str+p;
//						}
//						
//					}
//					System.out.println("str");
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
//					System.out.println(jsonReceive);
//					
//				}
//			}
//			if("".equals(jsonReceive.get("title")) && "".equals(jsonReceive.get("description")) && "".equals(jsonReceive.get("domain"))&&"".equals(jsonReceive.get("ex"))&& "".equals(jsonReceive.get("heure"))&& "".equals(jsonReceive.get("minute"))){
//				return null;
//			}
			return jsonReceive;
		}
}

