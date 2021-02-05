package advanced.spider;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class AdvancedPerformer {	
	public Map<String,String> callSecond(Map<String, String> hm,boolean cookieIsSet,String request_typ,String cookie,String location,String filename_part) throws UnknownHostException, IOException {
		String data=URLEncoder.encode("requestType","UTF-8")+"="+URLEncoder.encode("reqBuild","UTF-8")
		 		+"&"+URLEncoder.encode("pmid","UTF-8")+"="+URLEncoder.encode("ADMIN_LOGIN","UTF-8")
				+"&"+URLEncoder.encode("emailAddress","UTF-8")+"="+URLEncoder.encode("bala.bugatha@siliconmtn.com","UTF-8")
				+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode("Goldfish95@","UTF-8");
	
		SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		 SSLSocket socket=(SSLSocket)factory.createSocket("www.siliconmtn.com", 443);
		 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		            writer.println(request_typ+" "+location+" HTTP/1.1");
		            writer.println("Accept: */*");
		            writer.println("Host: " + "www.siliconmtn.com");
		            writer.println("Cookie: "+cookie);
		            if("POST".equals(request_typ)) {
						writer.println("Content-Type: application/x-www-form-urlencoded");	
						writer.println("Content-Length: " + data.length() + "");
					}
		            writer.println();
		            if(!cookieIsSet)
		            	writer.println(data);
		            
		            	String fileName = "/home/balabugatha/Desktop/Advanced_spider_html_files/bbg"+filename_part+".html";
		                File file = new File(fileName);
		                if (!file.exists()) {
		                    file.createNewFile();
		                }
		                FileWriter fw = new FileWriter(file.getAbsoluteFile());
		                BufferedWriter bw = new BufferedWriter(fw);
		            
		            String line = reader.readLine();
		            while(reader.ready() && line != null)
		            {
		            	if(cookieIsSet) {
		            		bw.write(line);
		            	}
		            	if(!cookieIsSet) {
		            		if(line.contains(":")) {
			            		String[] temp=line.split(": ");
			            		if(temp[0].equals("Set-Cookie") ){
			            			if(temp[1].startsWith("JSESSIONID")) {
			            				String[] temp1=temp[1].split(";");
			            				hm.put(temp[0],temp1[0]);
			            			}
			            		}
			            	}
		            	}
		                line = reader.readLine();
		            }
		            bw.close();
		            return hm;
	}
}
