package root;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;


@SuppressWarnings("serial")
public class SearchDataStoreServlet2 extends HttpServlet{

	JSONObject jsonReceive;
	JSONObject jsonToSend;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonString = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);		

		System.out.println("(Servlet search data store) Request string " + jsonString);
		
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
		System.out.println("(Servlet search data store) Request json " + jsonReceive);
		
		
		if(q!=null){
			//Filter in datastore !
			// Recup�ration du service Datastore
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			FilterOperator[] filter ;  
			// Utilisation Query afin de rassembler les �l�ments a appeler/filter
			Query query = new Query("AddTraining");
			String keyword = req.getParameter("q");
			query.addFilter("ex", Query.FilterOperator.GREATER_THAN_OR_EQUAL, keyword);
			
			if(keyword==null){
				System.out.println("t'es null y a pas de q");
			}
			else{
				System.out.println("q=" + q);

			}

			// R�cup�ration du r�sultat de la requ�te � l�aide de PreparedQuery 
			PreparedQuery pq = datastore.prepare(query);
					
			String title_plan="";
			String titre_ex="";
			
			JSONArray jsonExo = new JSONArray();
			
			for (Entity result : pq.asIterable()) {
				titre_ex = (String) result.getProperty("ex");  
				for(Object o : (JSONArray) JSONValue.parse(titre_ex) ){
					jsonExo.add(o);
				}				
			}
			
			JSONObject ex = new JSONObject();
			JSONObject jsonExTitle = new JSONObject();

			
			int k =0;
			int size = jsonExo.size();
			String[] title_ex_ds= new String[size];
			
			for(int j=0; j<size;j++){
				
				ex=(JSONObject)jsonExo.get(j);
				
				String title_curr_ex=((String) ex.get("title")).toLowerCase();
				String heure = (String) ex.get("heure");
				String minute = (String) ex.get("minute");
				JSONObject jsonCurrEX = new JSONObject();
				if(title_curr_ex.contains((CharSequence) ((String) jsonReceive.get("q")).toLowerCase())){
					title_ex_ds[k] = title_curr_ex;
					jsonCurrEX.put("title", title_curr_ex);
					jsonCurrEX.put("heure", heure);
					jsonCurrEX.put("minute", minute);
					jsonExTitle.put("ex"+k, jsonCurrEX);

					k++;
				}
				
			}
			
//			int nbexfound=0;
//			for(String title : title_ex_ds){
//				nbexfound++;
//			}
			
			
			query = new Query("AddTraining");
			query.addFilter("title", Query.FilterOperator.GREATER_THAN_OR_EQUAL, req.getParameter("q"));

			// R�cup�ration du r�sultat de la requ�te � l�aide de PreparedQuery 
			pq = datastore.prepare(query);
			JSONObject jsonPlan = new JSONObject();
			JSONObject planTitleTime = new JSONObject();
			
			int nbplan=0;
			for (Entity result : pq.asIterable()) {
				title_plan = ((String) result.getProperty("title")).toLowerCase();  
				String heure = (String) result.getProperty("heure");
				String minute = (String) result.getProperty("minute");
				if(title_plan.contains((CharSequence) ((String) jsonReceive.get("q")).toLowerCase())){
					planTitleTime.put("title", title_plan);
					planTitleTime.put("heure",heure);
					planTitleTime.put("minute",minute);

					jsonPlan.put("plan"+nbplan, planTitleTime);
					nbplan++;
				}

				System.out.println("Plan : "+title_plan);
			}
			
			jsonToSend= new JSONObject();
			
			
			if(jsonExo != null){
				if(jsonExTitle!=null){
					jsonToSend.put("title_exo_found",jsonExTitle);
				}
			}else{
				System.out.println("jsonex null");
				
			}
			if(jsonPlan != null){
				jsonToSend.put("title_plan_found",jsonPlan);
			}
			else {
				System.out.println("jsonplan null");
			}
			
		}
		System.out.println(jsonToSend);
		resp.setContentType("application/json");
        PrintWriter out= resp.getWriter();
        out.print(jsonToSend);
        out.flush();
        out.close();
        resp.getWriter().println("eh non ");
		
	}
	public JSONObject getJSONFromTaskQueueRequest(String req){
		jsonReceive = new JSONObject();
		String[] param = req.split("=");
		
		if(param.length >1){
			int i =0;
			for(String para : param){
			//Affectation au jsonReceive
				if("q".equals(para)){
					if(!("".equals(param[i+1]))){
						jsonReceive.put("q",param[i+1].replace("+", " "));
					}
					else{
						jsonReceive.put("q","");
					}
				}
				i++;
			}
					
		}
		if(jsonReceive != null){
			if("".equals(jsonReceive.get("q"))){
				return new JSONObject();
			}
		}
		
		return jsonReceive;
	}
}
