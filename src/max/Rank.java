package max;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Rank {
	
	public static void main(String[] args) throws HttpException, IOException {
		//130319302  88514142 
		Scanner s = new Scanner(System.in);
		String loginUrl = "http://api.maxjia.com/api/account/login/?phone_num=13631659657&pwd=263397478";
		HttpClient http = new HttpClient();
		HttpMethod method = new GetMethod(loginUrl);
		if(http.executeMethod(method)==200){
			JsonParser jp = new JsonParser();
			JsonObject json = jp.parse(method.getResponseBodyAsString()).getAsJsonObject();
			if("ok".equals(json.get("status").getAsString())){
				String pkey = json.get("result").getAsJsonObject().get("pkey").getAsString();
				while(true){
					System.out.print("请输入SteamID:");
					String steamId = s.next();//MTQ0NjI3OTA3My42NzEzNjMxNjU5NjU3XzFsZmJ4ZGV3emhuYXVxdWdw
					//http://api.maxjia.com/api/account/login/?phone_num=13631659657&pwd=263397478
					String url = String.format("http://api.maxjia.com/api/account/bind_steam_id/?steam_id=%s&pkey=%s&phone_num=13631659657",steamId,pkey);
					method = new GetMethod(url);
					method.setRequestHeader("Referer","http://api.maxjia.com/");
					if(http.executeMethod(method)==200){
						if("ok".equals(jp.parse(method.getResponseBodyAsString()).getAsJsonObject().get("status").getAsString())){
							method = new GetMethod("http://api.maxjia.com/api/activity/dota_and_me/?phone_num=13631659657");//web_id
							if(http.executeMethod(method)==200){
								String html = method.getResponseBodyAsString();
								Pattern mmrPattern = Pattern.compile("(\\d+)</span>单排积分\\n");
								Pattern rankPattern = Pattern.compile("天梯排名.*>(\\d+)<");
								Pattern datePattern = Pattern.compile("(更新至.*)\\)");
								Matcher mmrMatcher = mmrPattern.matcher(html);
								Matcher rankMatcher = rankPattern.matcher(html);
								Matcher dateMatcher = datePattern.matcher(html);
								if(mmrMatcher.find()){
									System.out.print("单排积分："+mmrMatcher.group(1));
								}else{
									System.out.print("暂无数据");
								}
								if(rankMatcher.find()){
									System.out.print("\t天梯排名："+rankMatcher.group(1));
								}else{
									rankPattern = Pattern.compile("击败了.*>(\\d+%)<");
									rankMatcher = rankPattern.matcher(html);
									if(rankMatcher.find()){
										System.out.print("\t击败了"+rankMatcher.group(1)+"天梯玩家");
									}else{
										System.out.print("\t天梯排名：0");
									}
								}
								if(dateMatcher.find()){
									System.out.println("\t"+dateMatcher.group(1));
								}
							}
						}else{
							System.out.println("steam id不存在");
						}
					}
				}
			}
		}
	}
	
}
