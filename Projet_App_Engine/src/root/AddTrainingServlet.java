package root;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.sun.xml.internal.ws.api.pipe.NextAction;

@SuppressWarnings("serial")
public class AddTrainingServlet extends HttpServlet{
	public static final String ADD_TRAIN_ENTITY_KEY="AddTraining";
	public static final String ADD_TRAIN_TITLE_ENTITY_PROPERTY="title";
	public static final String ADD_TRAIN_DESC_ENTITY_PROPERTY="description";

	private final static String TRAIN_KEY="trainkey";
	JSONObject jsonReceive;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String jsonString = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);		
		System.out.println("(Servlet add Training) Request string " + jsonString);
		
		
		
		String titre="";
		String desc = "";
		String domain ="";
		String ex = "";
		String h_dure ="";
		String m_dure = "";

		
		if(jsonReceive != null){
			titre = (String) jsonReceive.get("title");
			desc = (String) jsonReceive.get("description");
			domain = (String) jsonReceive.get("domain");
			ex = (String) jsonReceive.get("ex");
			h_dure =(String) jsonReceive.get("heure");
			m_dure = (String) jsonReceive.get("minute");

		}
		else{
			jsonReceive = getJSONFromTaskQueueRequest(jsonString);
			if(jsonReceive != null){
				titre = (String) jsonReceive.get("title");
				desc = (String) jsonReceive.get("description");
				domain = (String) jsonReceive.get("domain");
				ex = (String) jsonReceive.get("ex");
				h_dure =(String) jsonReceive.get("heure");
				m_dure = (String) jsonReceive.get("minute");
			
			}
		}

//		String heure = (Integer)jsonReceive.get("heure");
//		String title = (String)jsonReceive.get("title");
		System.out.println("(Servlet add Training) Request json " + jsonReceive);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity addTain = new Entity(ADD_TRAIN_ENTITY_KEY);
		
		addTain.setProperty(ADD_TRAIN_TITLE_ENTITY_PROPERTY, titre);
		addTain.setProperty(ADD_TRAIN_DESC_ENTITY_PROPERTY, desc);
		addTain.setProperty("domain", domain);
		addTain.setProperty("ex", ex);
		addTain.setProperty("heure", h_dure);
		addTain.setProperty("minute", m_dure);


		datastore.put(addTain); 
//		
//		
//		Cache cache=null;
//		
//		
//	    Map props = new HashMap();
//	    props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
//	    props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
//	    try {
//	      // Récupération du Cache
//	        CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
//	        // création/récupération du cache suivant des propriétés spécifiques
//	        cache = cacheFactory.createCache(props);
//	        // Si aucune propriété n'est spécifiée, 
//	        //créer/récupérer un cache comme ci-dessous
//	        //cache = cacheFactory.createCache(Collections.emptyMap());
//	     } catch (CacheException e) {
//	         // Traitement en cas d'erreur sur la récupération/configuration du cache
//	     }
//
//		
//	    String msg=getWelcomeMsg(datastore,cache);
//		PrintWriter out = resp.getWriter();
//		out.println(msg);
//		out.flush();
		System.out.println("(Servlet add Training) Request json " +jsonReceive);
		
		 resp.setContentType("application/json");
         PrintWriter out= resp.getWriter();
         out.println(jsonReceive);
         out.flush();
         out.close();
	}
	public JSONObject getJSONFromTaskQueueRequest(String req){
		jsonReceive = new JSONObject();
		if(req.contains("&")){
			String[] paramsplit = req.split("&");
			for(String params : paramsplit){
				String[] param = params.split("=");
				for(int i=0;i<param.length;i++){
					//Reconstruction de la valeur
//					String[] val = param[i+1].split("+");
//					StringBuilder ret_val = new StringBuilder();
//					for(String s : val){
//						ret_val.append(s)
//								.append(" ");
//					}
//					
					//Affectation au jsonReceive
					if("title".equals(param[i])){
						jsonReceive.put("title",param[i+1].replace("+", " "));
					}
					else if("description".equals(param[i])){
						jsonReceive.put("description", param[i+1].replace("+", " "));
					}
					else if("domain".equals(param[i])){
						jsonReceive.put("domain", param[i+1].replace("+", " "));
				
					}
					else if("ex".equals(param[i])){
//						String ex = param[i+1].replace("+", " ");
//						ex = ex.replace("%2C", ",");
//						ex = ex.replace("%5B", "[");
//						ex = ex.replace("%7B", "{");
//						ex = ex.replace("%3A", "{");
//						String str = "";
//						str = str+'"';
//						ex = ex.replace("%22", str);

						String str = "";
//						param[i+1] = param[i+1].replace("%","0x");

//						char[] tab = param[i+1].toCharArray();
						
						for(int j = 0; j < param[i+1].length();j++){
							if("%".equals(param[i+1].substring(j, j+1))){

								String ascii = "" + param[i+1].substring(j+1, j+3) ;
								int asc = Integer.parseInt(ascii, 16);
								str= str +(char)asc;
								j=j+2;
							}
							else{
								str = str + param[i+1].substring(j, j+1);
							}
						}
						char a = '"';
						String b =""+a;
						
						System.out.println(str.toString());
						str=str.replace("+", " ");
						str=str.replace("\"", b );
						
						jsonReceive.put("ex", str);
					}
					else if("heure".equals(param[i])){
						jsonReceive.put("heure", param[i+1].toString().replace("+", " "));
					}
					else if("minute".equals(param[i])){
						jsonReceive.put("minute", param[i+1].toString().replace("+", " "));
					}
			
				}
				
				
				
			}
		}
		if("".equals(jsonReceive.get("title")) && "".equals(jsonReceive.get("description")) && "".equals(jsonReceive.get("domain"))&&"".equals(jsonReceive.get("ex"))&& "".equals(jsonReceive.get("heure"))&& "".equals(jsonReceive.get("minute"))){
			return null;
		}
		return jsonReceive;
	}
	

}
