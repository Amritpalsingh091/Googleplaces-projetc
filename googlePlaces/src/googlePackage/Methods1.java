


package googlePackage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Methods1 {


	private static final String GOOGLE_API_KEY = "AIzaSyARiki0HBLlyR7xH0K3e4eifaSLzx8b-7E";

	private final HttpClient client = HttpClientBuilder.create().build();;

	public void performSearch(final String origins, final String destinations, final String mode) throws ParseException, IOException,
	URISyntaxException{
		final URIBuilder builder = new URIBuilder().setScheme("https").setHost("maps.googleapis.com").setPath("/maps/api/distancematrix/json");


		builder.addParameter("origins",origins);
		builder.addParameter("destinations",destinations);


		builder.addParameter("mode",mode);
		builder.addParameter("key",GOOGLE_API_KEY);
		final HttpUriRequest request = new HttpGet(builder.build());
		//System.out.print(request);
		final HttpResponse execute = this.client.execute(request);
		final String response = EntityUtils.toString(execute.getEntity());
		//System.out.println(response);


		// JSONObject object_response = JSONObject.fromObject(response);

		JSONObject object = JSONObject.fromObject(response);

		JSONArray rows_array = object.getJSONArray("rows");
		//System.out.println(rows_array);

		for(int i = 0;i<rows_array.size();i++){
		

			JSONObject route = rows_array.getJSONObject(i);

			JSONArray elements_array = route.getJSONArray("elements");

			int j = 0;

			for(j =0;j<elements_array.size();j++){

				JSONObject legs = elements_array.getJSONObject(j);

				JSONObject start = legs.getJSONObject("distance");
				String value = start.getString("value");
				System.out.println("The good distance  is: "+value);
			   String array_distance[]= new String[3];
			   
					array_distance[j]= value;
					
					System.out.println(array_distance[j]);
					if(array_distance[j] >= array_distance[j+1])
					{
						
					}
					
			}
			
		
			

		} 


	}}