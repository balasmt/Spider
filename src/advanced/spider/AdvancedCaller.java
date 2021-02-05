package advanced.spider;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class AdvancedCaller {

	public static void main(String[] args) throws UnknownHostException, IOException {
		boolean flag=false;
		String cookie=null;
		Map<String,String> hm=new HashMap<>();
		AdvancedPerformer sc=new AdvancedPerformer();
		hm=sc.callSecond(hm,flag,"POST",cookie,"/sb/admintool","main");
	    if(hm.get("Set-Cookie")!=null) {
	    	cookie=hm.get("Set-Cookie");
	    }
	    flag=true;
	    System.out.println(cookie);
	    hm=sc.callSecond(hm,flag,"GET",cookie,"/sb/admintool?cPage=index&amp;actionId=SCHEDULE_JOB_INSTANCE&amp;organizationId=SMT","one");
	    hm=sc.callSecond(hm,flag,"GET",cookie,"/sb/admintool?cPage=index&amp;actionId=WEB_SOCKET&amp;organizationId=SMT","two");
	    hm=sc.callSecond(hm,flag,"GET",cookie,"/sb/admintool?cPage=index&amp;actionId=ERROR_LOG&amp;organizationId=SMT","three");
	    hm=sc.callSecond(hm,flag,"GET",cookie,"/sb/admintool?cPage=index&amp;actionId=FLUSH_CACHE","four");
	    hm=sc.callSecond(hm,flag,"GET",cookie,"/sb/admintool?cPage=stats&amp;actionId=FLUSH_CACHE","five");
	}
}
