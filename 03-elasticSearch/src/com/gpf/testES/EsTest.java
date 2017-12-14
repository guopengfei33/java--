package com.gpf.testES;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

public class EsTest {

	private String url = "http://127.0.0.1:9200/school/student/";
	private UUID id = UUID.randomUUID();

	// 进行索引的创建
	@Test
	public void EsInput() {
		/*
		 * //进行设置ES服务器的地址(school 表示index, student表示类型) String url =
		 * "http://127.0.0.1:9200/school/student/";
		 * 
		 * //进行id(唯一标识) UUID id = UUID.randomUUID();
		 */

		// 获取到httpClient
		HttpClient httpClient = new HttpClient();

		// 进行 添加方法的获取
		PutMethod method = new PutMethod(url + id);

		// 准备请求的参数
		String params = "{\"name\" : \"lisi\",\"age\" : 12,\"sex\":\"男\",\"address\" : \"wangwu\"}";
		try {
			StringRequestEntity requestEntity = new StringRequestEntity(params,
					"application/json", "UTF-8");
			// 发送请求
			method.setRequestEntity(requestEntity);

			// 执行方法
			int requestCode = httpClient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			System.out.println(response);

			JSONObject jsonObject = JSONObject.fromObject(response);
			boolean successFul = (boolean) jsonObject.get("created") ? (boolean) jsonObject
					.get("created") : false;
			if (successFul) {
				System.out.println("索引创建成功!");

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void EsQuery() throws Exception {

		List<Student> stus = new ArrayList<Student>();
		//String query = "{\"size\" : 100,\"query\" : {\"match_phrase\" : {\"name\" : \"wangwu\"}}}";// ,\"highlight\":{\"fields\":{\"name\"
																												// :
																												// {}}}
		String query = "{\"size\" : 100,\"query\" : {\"match\" : {\"age\" : \"12\"}}}";
		//与上边比较,是不会插入索引
		query = "{\"query\": {\"bool\": {\"filter\": {\"term\": {\"name\": \"wangwu\"}}}}}";
		//不支持多个字段联合起来查询(match_phrase)
		
		/*
		 * { "error": { "root_cause": [ { "type": "parsing_exception", "reason":
		 * "[match_phrase] query doesn't support multiple fields, found [name] and [age]"
		 * , "line": 1, "col": 70 } ], "type": "parsing_exception", "reason":
		 * "[match_phrase] query doesn't support multiple fields, found [name] and [age]"
		 * , "line": 1, "col": 70 }, "status": 400 }
		 */
		// 客户端
		HttpClient httpClient = new HttpClient();

//		query = "";
		// 获取方法
		PostMethod postMethod = new PostMethod(url + "_search");

		RequestEntity requestEntity = new StringRequestEntity(query,
				"application/json", "UTF-8");

		postMethod.setRequestEntity(requestEntity);

		httpClient.executeMethod(postMethod);

		String response = postMethod.getResponseBodyAsString();

		System.out.println(response);

		JSONObject jsonObject = JSONObject.fromObject(response);

		Map<String, Object> map = new HashMap<String, Object>();
		if(jsonObject.get("hits") != null){
			JSONObject hits = (JSONObject) jsonObject.get("hits");
			//获取到查询到的总数
			int total = (int) hits.get("total");
			//对查询到的数据进行分析
			List list =  (List) hits.get("hits");
			
			for(int i=0,len = list.size(); i < len; i++){
				JSONObject _source = (JSONObject) list.get(i);
				JSONObject stuJson = (JSONObject) _source.get("_source");
				Student student = new Student((String)stuJson.get("name"), (Integer)stuJson.get("age"), (String)stuJson.get("sex"), (String)stuJson.get("address"));
				stus.add(student);
				System.out.println(student);
			}
		}
	}
	
	@Test
	public void EsQuery2() throws Exception {

		List<Student> stus = new ArrayList<Student>();
																												// :
																												// {}}}
		// 客户端
		HttpClient httpClient = new HttpClient();

		// 获取方法
		GetMethod getMethod = new GetMethod(url + "_search");

		HttpMethodParams params = new HttpMethodParams();
		params.setParameter("q", "name : 'lisi'");
		getMethod.setParams(params);
		httpClient.executeMethod(getMethod);

		String response = getMethod.getResponseBodyAsString();

		System.out.println(response);

		JSONObject jsonObject = JSONObject.fromObject(response);

		Map<String, Object> map = new HashMap<String, Object>();
		if(jsonObject.get("hits") != null){
			JSONObject hits = (JSONObject) jsonObject.get("hits");
			//获取到查询到的总数
			int total = (int) hits.get("total");
			//对查询到的数据进行分析
			List list =  (List) hits.get("hits");
			
			for(int i=0,len = list.size(); i < len; i++){
				JSONObject _source = (JSONObject) list.get(i);
				JSONObject stuJson = (JSONObject) _source.get("_source");
				Student student = new Student((String)stuJson.get("name"), (Integer)stuJson.get("age"), (String)stuJson.get("sex"), (String)stuJson.get("address"));
				stus.add(student);
				System.out.println(student);
			}
		}
	}
	
	
	@Test
	public void EsDelete() throws Exception{
		
		//获取客户端
		HttpClient httpClient = new HttpClient();
		
		//获取方法  删除方法
		DeleteMethod deleteMethod = new DeleteMethod("http://127.0.0.1:9200/school?pretty");
		
		//
		int code = httpClient.executeMethod(deleteMethod);
		
		String str = deleteMethod.getResponseBodyAsString();
		
		System.out.println(str);
		
	}
}
