package pkg;
import org.junit.Test;
import java.io.IOException;
import org.junit.Ignore;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;

public class commandTest {

@Test
public void testsendPOSTWrongUsername() throws IOException{
	command cmd = new command();
	String email = "wrong1@ab.com";
	String password = "password";
	String Plan = "Business";
    cmd.setValues( email, password);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    Assert.assertEquals(2, cmd.sendPOST(url));
}

@Test
public void testsendPOSTWrongPassword() throws IOException {
	command cmd = new command();
	String email = "2015csb1031@iitrpr.ac.in";
	String password = "password2";
	String Plan = "Business";
    cmd.setValues( email, password);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    Assert.assertEquals(2, cmd.sendPOST(url));
}

@Test
public void testsendPOSTRightUsernamePassword() throws IOException {
	command cmd = new command();
	String email = "2015csb1031@iitrpr.ac.in";
	String password = "password";
	String Plan = "Business";
    cmd.setValues( email, password);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    Assert.assertEquals(1, cmd.sendPOST(url));
}

@Test
public void testsendPOSTWrongPlan() throws IOException {
	command cmd = new command();
	String email = "user25@ab.com";
	String password = "password";
	String Plan = "Business";
    cmd.setValues( email, password);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    Assert.assertEquals(2, cmd.sendPOST(url));
}


@Test
public void testsendPOSTRightPlan() throws IOException {
	command cmd = new command();
	String email = "user25@ab.com";
	String password = "password";
	String Plan = "Ultimate";
    cmd.setValues( email, password);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    Assert.assertEquals(1, cmd.sendPOST(url));
}


@Test
public void testsendGETWrongUsername() throws IOException {
	command cmd = new command();
	String email = "wrong1@ab.com";
	String password = "password";
	String Plan = "Business";
    cmd.setValues( email, password);
    String url2="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    cmd.sendPOST(url2);

	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
    Assert.assertEquals(2, cmd.sendGET(url));
}

@Test
public void testsendGETWrongPassword() throws IOException {
	command cmd = new command();
	String email = "2015csb1031@iitrpr.ac.in";
	String password = "password2";
	String Plan = "Business";
    cmd.setValues( email, password);
    String url2="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    cmd.sendPOST(url2);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
    Assert.assertEquals(2, cmd.sendGET(url));
}



@Test
public void testsendGETWrongPlan() throws IOException {
	command cmd = new command();
	String email = "user25@ab.com";
	String password = "password";
	String Plan = "Business";
    cmd.setValues( email, password);
    String url2="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    cmd.sendPOST(url2);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
    Assert.assertEquals(2, cmd.sendGET(url));
}


@Test
public void testsendGETRightPlan() throws IOException {
	command cmd = new command();
	String email = "user25@ab.com";
	String password = "password";
	String Plan = "Ultimate";
    cmd.setValues( email, password);
    String url2="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    cmd.sendPOST(url2);
	String url="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
    Assert.assertEquals(1, cmd.sendGET(url));
}


 @Test
public void testPlanStandard() throws IOException {
	String email = "user2@ab.com";
	String password = "user";
	String Plan = "Standard";
	String postURL = "http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    String getURL = "http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
    final AtomicInteger invokeCountersendPOST = new AtomicInteger();
    final AtomicInteger invokeCountersendGET = new AtomicInteger();

    command cmd = new command() {
    
    public int sendPOST(String string) throws IOException{
    	if(string.equals(postURL)) {
           // System.out.println("YES!!!!");
            //System.out.println("plan : " + Plan);
        	invokeCountersendPOST.incrementAndGet();
        }
        return super.sendPOST(string);
    }
    
    public int sendGET(String string) throws IOException {
    	if(string.equals(getURL)) {
        	invokeCountersendGET.incrementAndGet();
        }
        return super.sendGET(string);
    }
    };
            cmd.setValues( email, password);
           cmd.plan(Plan);
           Assert.assertEquals(11, invokeCountersendGET.get());

           System.out.println("invokeCountersendPOST.get() : "+ invokeCountersendPOST.get());

           Assert.assertEquals(1, invokeCountersendPOST.get());
}

@Test
public void testPlanUltimate() throws IOException {
	String email = "user2@ab.com";
	String password = "user";
	String Plan = "Ultimate";
	String postURL = "http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    String getURL = "http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
    final AtomicInteger invokeCountersendPOST = new AtomicInteger();
    final AtomicInteger invokeCountersendGET = new AtomicInteger();

    command cmd = new command() {
    
    public int sendPOST(String string) throws IOException {
    	if(string.equals(postURL)) {
        	invokeCountersendPOST.incrementAndGet();
        }
        return super.sendPOST(string);
    }
   
    public int sendGET(String string) throws IOException{
    	if(string.equals(getURL)) {
        	invokeCountersendGET.incrementAndGet();
        }
        return super.sendGET(string);
    }
    };
            cmd.setValues( email, password);
           cmd.plan(Plan);
           Assert.assertEquals(31, invokeCountersendGET.get());

           Assert.assertEquals(1, invokeCountersendPOST.get());
}

@Test
public void testPlanBusiness() throws IOException {
	String  email= "user2@ab.com";
	String password = "user";
	String Plan = "Business";
	String postURL = "http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
    String getURL = "http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
    final AtomicInteger invokeCountersendPOST = new AtomicInteger();
    final AtomicInteger invokeCountersendGET = new AtomicInteger();

    command cmd = new command() {
   
    public int sendPOST(String string) throws IOException{
    	if(string.equals(postURL)) {
        	invokeCountersendPOST.incrementAndGet();
        }
        return super.sendPOST(string);
    }
    
    public int sendGET(String string) throws IOException{
    	if(string.equals(getURL)) {
        	invokeCountersendGET.incrementAndGet();
        }
        return super.sendGET(string);
    }
    };
            cmd.setValues( email, password);
           cmd.plan(Plan);
           Assert.assertEquals(21, invokeCountersendGET.get());

           Assert.assertEquals(1, invokeCountersendPOST.get());
}

}