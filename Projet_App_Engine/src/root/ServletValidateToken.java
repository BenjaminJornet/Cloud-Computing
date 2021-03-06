package root;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.appengine.repackaged.com.google.api.client.http.HttpTransport;
import com.google.appengine.repackaged.com.google.api.client.http.javanet.NetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.jackson.JacksonFactory;

@SuppressWarnings("serial")
public class ServletValidateToken extends HttpServlet{

	private static final String CLIENT_ID =null;
	private static final String APPS_DOMAIN_NAME = null;
	private static final String ANDROID_CLIENT_ID = null;
	private static final String IOS_CLIENT_ID = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idTokenString = IOUtils.toString(req.getInputStream());
		//GoogleIdToken idToken = req.getInputStream();
		//IOUtils.toBufferedInputStream(req.getInputStream());
		HttpTransport transport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
		.setAudience(Arrays.asList(CLIENT_ID))
		.build();

		//(Receive idTokenString by HTTPS POST)

		//String idTokenString = jsonString; // = (String) idToken;
		GoogleIdToken idToken;
		try {

			idToken = verifier.verify(idTokenString);
			//idToken.setType(idTokenString);

			if (idToken != null) {
				Payload payload = idToken.getPayload();
				if (payload.getHostedDomain().equals(APPS_DOMAIN_NAME)
						// If multiple clients access the backend server:
						&& Arrays.asList(ANDROID_CLIENT_ID, IOS_CLIENT_ID).contains(payload.getAuthorizedParty())) {
					System.out.println("User ID: " + payload.getSubject());
				} else {
					System.out.println("Invalid ID token.");
				}
			} else {
				System.out.println("Invalid ID token.");
			}
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
