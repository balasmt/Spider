package basic.spider;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasicCaller {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Set<String> links=new HashSet<>();
		Set<String> seen=new HashSet<>();
		BasicPerformer c=new BasicPerformer(); 
		List<String> res=c.theDoer("/",seen);
		res.forEach(r->links.add(r));
		links.forEach(l->{
			List<String> temp=null;
			try {
				temp = c.theDoer(l,seen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp.forEach(t->links.add(t));
		});
	}
}
