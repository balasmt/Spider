package basic.spider;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
public class BasicPerformer
{
    public List<String> theDoer(String url_depth, Set<String> seen) throws UnknownHostException, IOException
    {
    	List<String> li=new ArrayList<>();
    	SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		 
			 SSLSocket socket=(SSLSocket)factory.createSocket("www.siliconmtn.com", 443);
		     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        
            writer.println("GET "+url_depth+" HTTP/1.0");
            writer.println("Host: " + "www.siliconmtn.com");
            writer.println("");
            Pattern pattern = Pattern.compile("href=(\"|\')(.*?)(\"|\')");
            Matcher matcher;
            String line = reader.readLine();
            matcher = pattern.matcher(line);
            li.add(url_depth);
            if(!seen.contains(url_depth)) {
                String filename_part=url_depth;
                filename_part=filename_part.replaceAll("/", "");
                String fileName = "/home/balabugatha/Desktop/Basic_spider_html_files/bbg"+filename_part+".html";
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
            	while(reader.ready() && line != null)
                {
                   bw.write(line);
                    matcher = pattern.matcher(line);
                    if(matcher.find())
                    {
                        String str=matcher.group(2);
                        if(str.startsWith("/")&&!str.endsWith(".css")&&!str.endsWith(".png")&&!str.endsWith("/")) {
                        	li.add(str);	
                        }
                    }
                    line = reader.readLine();
                }
            	bw.close();
            }
            seen.add(url_depth);
		 return li;
    }
}