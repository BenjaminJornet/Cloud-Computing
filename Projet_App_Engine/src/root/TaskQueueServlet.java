package root;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;


@SuppressWarnings("serial")
public class TaskQueueServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		String jsonString = IOUtils.toString(req.getInputStream());
//		System.out.println("(Servlet) Request string " + jsonString);
		
//		JSONObject jsonReceive = (JSONObject) JSONValue.parse(jsonString);
//		System.out.println(jsonString);
////		
//		Queue queue = QueueFactory.getDefaultQueue();
//		// Ajout d’une tache simple
//		TaskOptions task=TaskOptions.Builder.withUrl("/AddTrainingServlet").param(key, value);
//		queue.add(task);
//		// Ajout d’une tache simple avec des paramètres de configuration
//		Map<String, String> headers=new HashMap<String, String>();
//		headers.put("X-AppEngine-TaskName","task2");
//		headers.put("X-AppEngine-TaskRetryCount","4");
//		TaskOptions task2=TaskOptions.Builder.withUrl("/worker2").headers(headers);
//		queue.add(task2);
//		// Ajout d’une tache en spécifiant la méthode utilisée
//		TaskOptions
//		task3=TaskOptions.Builder.withUrl("/worker?a=b&c=d").method(Method.GET);
//		queue.add(task3);
//		//...
//		queue.deleteTask("task");
//		//...
//		queue.purge();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonString = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);		

		System.out.println("(Servlet) Request string " + jsonString);
		
		JSONObject jsonReceive = (JSONObject) JSONValue.parse(jsonString);
		System.out.println(jsonString);
		
		String titre="";
		String desc = "";
		String domain ="";
		JSONArray ex = null ;
		Long h_dure =null;
		Long m_dure = null;
		
		if(jsonReceive != null){
			titre = (String) jsonReceive.get("title");
			desc = (String) jsonReceive.get("description");
			domain = (String) jsonReceive.get("domain");
			ex = (JSONArray) jsonReceive.get("ex");
			h_dure =(Long) jsonReceive.get("heure");
			m_dure = (Long) jsonReceive.get("minute");
		}
		
		System.out.println(ex.toString());
		
		Queue queue = QueueFactory.getDefaultQueue();
//		// Ajout d’une tache simple
		TaskOptions task=TaskOptions.Builder.withUrl("/addtraining")
				.param("title", titre)
				.param("description", desc)
				.param("domain", domain)
				.param("ex",ex.toString())
				.param("heure", h_dure.toString())
				.param("minute", m_dure.toString());
				
		
		queue.add(task);
		
		resp.setContentType("application/json");
        PrintWriter out= resp.getWriter();
        out.println(jsonReceive);
        out.flush();
        out.close();
		
	}
	
	

}
