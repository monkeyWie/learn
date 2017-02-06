package protobuf;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Js {
	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		Reader jsbnReader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("protobuf/jsbn.min.js"));
		Reader sjclReader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("protobuf/sjcl.min.js"));
		try {
			engine.eval(jsbnReader);
			engine.eval(sjclReader);
			System.out.println(engine.eval("new jsbn.BigInteger('FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE65381FFFFFFFFFFFFFFFF',16).toString();"));
			System.out.println(engine.eval("sjcl.random.randomWords(8, 0)"));
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
