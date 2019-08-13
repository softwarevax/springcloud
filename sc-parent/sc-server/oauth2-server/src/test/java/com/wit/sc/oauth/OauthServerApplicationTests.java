package com.wit.sc.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OauthServerApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * 1、接口请求用户密码模式登陆
	 * 2、postman
	 * post方法
	 * 	grant_type ：password
	 * 	username ：admin
	 * 	password ：admin
	 * 	scope ：sc-portal
	 * 	basic auth
	 * 	username sc-portal(client_id)
	 * 	password 8000(client_secret)
	 *
	 * 	返回：
	 *        {
	 *     "access_token": "a30e295f-9de0-4c05-8a93-122346b4c4d6",
	 *     "token_type": "bearer",
	 *     "refresh_token": "da47981b-8db6-4699-ae54-f76321a14fb2",
	 *     "expires_in": 43199,
	 *     "scope": "sc-portal"
	 * }
	 */
	@Test
	public void token_password() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("username", "admin");
		params.add("password", "1111");
		params.add("scope", "sc-zuul");
		String response = restTemplate.withBasicAuth("sc-zuul", "8080").
				postForObject("/oauth/token", params, String.class);
		System.out.println(response);
	}

	/**
	 * 1、接口方式
	 * token客户端模式登陆(只需要客户端的username和password即可)
	 * 2、postman
	 * grant_type ：client_credentials
	 * basic auth
	 * 	 	username sc-portal(client_id)
	 * 	 	password 8000(client_secret)
	 *
	 * 	 返回：
	 *          {
	 *     "access_token": "969ce3a0-2645-4c01-80aa-e49a53d2a96a",
	 *     "token_type": "bearer",
	 *     "expires_in": 43199,
	 *     "scope": "sc-portal"
	 * }
	 */
	@Test
	public void token_client() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "client_credentials");
		String response = restTemplate.withBasicAuth("sc-portal", "8000").
				postForObject("/oauth/token", params, String.class);
		System.out.println(response);
	}

	/**
	 * 访问：http://127.0.0.1:9030/oauth/authorize?client_id=sc-portal&response_type=code&redirect_uri=https://www.csdn.net/
	 * 返回code https://www.csdn.net/?code=YZwXc6
	 * 通过授权码获(code)取token
	 *
	 * 返回：
	 * {
	 *     "access_token": "c9ad6322-d9cc-4e70-b5c6-650418c2de73",
	 *     "token_type": "bearer",
	 *     "refresh_token": "da47981b-8db6-4699-ae54-f76321a14fb2",
	 *     "expires_in": 42826,
	 *     "scope": "sc-portal"
	 * }
	 */
	@Test
	public void token_code() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("code", "u15P62");
		params.add("redirect_uri", "https://www.csdn.net/");
		String response = restTemplate.withBasicAuth("sc-portal", "8000").postForObject("/oauth/token", params, String.class);
		System.out.println(response);
	}

	/**
	 * 刷新tokan
	 *
	 * grant_type ：refresh_token
	 * refresh_token：da47981b-8db6-4699-ae54-f76321a14fb2
	 * 返回：
	 * {
	 *     "access_token": "fa041bfd-ce3e-4c73-92d4-223d19e2e3f9",
	 *     "token_type": "bearer",
	 *     "refresh_token": "da47981b-8db6-4699-ae54-f76321a14fb2",
	 *     "expires_in": 43199,
	 *     "scope": "sc-portal"
	 * }
	 */
	@Test
	public void token_refresh() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("refresh_token", "da47981b-8db6-4699-ae54-f76321a14fb2");
		String response = restTemplate.withBasicAuth("sc-portal", "8000").postForObject("/oauth/token", params, String.class);
		System.out.println(response);
	}
}
