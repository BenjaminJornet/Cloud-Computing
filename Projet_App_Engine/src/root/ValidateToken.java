package root;

import java.util.Arrays;

import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.appengine.repackaged.com.google.api.client.http.HttpTransport;
import com.google.appengine.repackaged.com.google.api.client.http.javanet.NetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.jackson.JacksonFactory;

public class ValidateToken {
	private static final Object ANDROID_CLIENT_ID = null;
	private static final Object IOS_CLIENT_ID = null;
	private static final String APPS_DOMAIN_NAME = null;
	private static final Object CLIENT_ID = null;
	
	public ValidateToken() {
		HttpTransport transport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
		.setAudience(Arrays.asList(CLIENT_ID))
		.build();

		//(Receive idTokenString by HTTPS POST)

		GoogleIdToken idToken = verifier.verify();
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
	}
}
