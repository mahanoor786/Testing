package pkg;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class command {

    

    
    private  String token = "";
    private String email="";
    private String password="";


    public void setValues(String email, String password) throws IOException{
        this.email=email;
        this.password=password;
    }

    

    public static void main(String[] args) throws IOException {

        
      //  plan("Standard");
        //plan("Business");
        //plan("Ultimate");

    }
 
    public int sendGET(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //System.out.println(token);
        con.setRequestProperty("Authorization", "Token " + token);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
             //System.out.println(response.toString());
            return 1;

            // print result
          
        } else {

            System.out.println("GET request not worked");
            return 2;
        }
    }

    public int sendPOST(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
       
        // For POST only - START
        con.setDoOutput(true);
        
        
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            String  s1 = response.toString();
            String[] words=s1.split(",");
            String token1=words[0].split(":")[1];

           // System.out.println(token1);
            
            token=token1.substring(1,token1.length()-1);
         //   System.out.println(s1);
            return 1;
        } else {
            System.out.println("POST request not worked");
            return 2;
        }
    }

    public void plan(String Plan) {
        System.out.println(Plan+"  ");
        String POSTURL="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/auth/login?email="+email+"&password="+password+"&api_name=plants&plan="+Plan;
        String GETURL="http://ec2-18-223-126-21.us-east-2.compute.amazonaws.com:3000/plants";
        try{sendPOST(POSTURL);}
        catch(IOException e){}
       
       int i=0;
       int count=0;
       while(i<50){ 
        try{
       if(sendGET(GETURL)==2){
            break;
       }
           }
        catch(IOException e){}
       count++;
       i++;
       System.out.print("Access number: "+i);

       }
       System.out.println("Successfull access till the count: "+count);


    } 

}