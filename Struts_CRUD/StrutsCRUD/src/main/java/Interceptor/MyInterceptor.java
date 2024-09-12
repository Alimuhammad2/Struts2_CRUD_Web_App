package Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
public class MyInterceptor implements Interceptor{

	 private static final Logger logger = LogManager.getLogger(MyInterceptor.class);
	 
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation ai) throws Exception {
		
		// Get the HTTP request object
        HttpServletRequest request = ServletActionContext.getRequest();
       
        // Retrieve the client's IP address
        String ipAddress = request.getRemoteAddr();
        
        // Optionally, store the IP address in the request or session for later use
        request.setAttribute("clientIP", ipAddress);
        

        // Continue with the action invocation
        return ai.invoke();
		
		
		
	/*	System.out.println("Before action: " + ai.getAction().getClass().getName());

        // Continue with action invocation
        String result = ai.invoke();

        System.out.println("After action: " + ai.getAction().getClass().getName());
  
        return result;*/
	}

}
