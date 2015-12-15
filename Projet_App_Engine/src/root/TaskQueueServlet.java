package root;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;


@SuppressWarnings("serial")
public class TaskQueueServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String jsonString = IOUtils.toString(req.getInputStream());
		System.out.println("(Servlet) Request string " + jsonString);
		
		JSONObject jsonReceive = (JSONObject) JSONValue.parse(jsonString);
		System.out.println(jsonString);
//		
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
		super.doPost(req, resp);
		
		String jsonString = IOUtils.toString(req.getInputStream());
		System.out.println("(Servlet) Request string " + jsonString);
		
		JSONObject jsonReceive = (JSONObject) JSONValue.parse(jsonString);
		System.out.println(jsonString);
		
		Queue queue = QueueFactory.getDefaultQueue();
//		// Ajout d’une tache simple
		TaskOptions task=TaskOptions.Builder.withUrl("/addtraining")
				.param("title", (String) jsonReceive.get("title"))
				.param("description", (String) jsonReceive.get("description"))
				.param("heure", (String) jsonReceive.get("heure"))
				.param("minute", (String) jsonReceive.get("minute"));
				
		
		queue.add(task);
//		
	}
	
	

}
