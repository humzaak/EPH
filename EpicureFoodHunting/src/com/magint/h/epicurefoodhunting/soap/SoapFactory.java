package com.magint.h.epicurefoodhunting.soap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import android.R;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

public class SoapFactory {

	/**
	 * @param args
	 */
	static final String url="http://epicureasia.com/app/webroot/webservice/index.php";
	static final String SOAP_ACTION = "http://epicureasia.com/webservice";
	static final String RECIPE_METHOD_NAME = "getRecipes";
	static final String NAMESPACE = " http://epicureasia.com/webservice";
	
		
	public static Document getRecipeResponseDocument(Context ApplicationContext) {
		 try {
	           
			  SoapObject request = new SoapObject(NAMESPACE, RECIPE_METHOD_NAME);
			    request.addProperty("recipesId","");
			    request.addProperty("orderingBy","");
			    request.addProperty("ordering","");
			    request.addProperty("limitFrom","");
			    request.addProperty("limitNumber","2");
			    //Create envelope
			    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
			            SoapEnvelope.VER11);
			    //envelope.dotNet = true;
			    //Set output SOAP object
			    envelope.setOutputSoapObject(request);
			    //Create HTTP call object
			    HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
				        //Invole web service
			        androidHttpTransport.call(SOAP_ACTION, envelope);
			        //String requestString =androidHttpTransport.requestDump;
			        //Get the response
			        Object result =envelope.getResponse();
			        //Assign it to fahren static variable
			    Document doc = Jsoup.parse( result.toString());
			       
			 return doc;

	        	      
	        } catch (Exception e) {
	            System.err.println("Error occurred while sending SOAP Request to Server");
	            e.printStackTrace();
	        }
		return null;
		
	}
	

	


}
