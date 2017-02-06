package protobuf;

import java.math.BigInteger;

import com.google.protobuf.InvalidProtocolBufferException;

import util.ByteUtil;

public class Test {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		//000c080012026f6b
		//000c08910312056572726f72
		Xxzz.CommonResponse commonResponse = Xxzz.CommonResponse.parseFrom(ByteUtil.toByteArray("080012026f6b"));
		System.out.println(commonResponse.toString());
		
		//0014084510021a0261612210e69fb3e59f8ee58da1e6b49be584bf7328a40b30003800
		Xxzz.ChatMsgResponse msgResponse = Xxzz.ChatMsgResponse.parseFrom(ByteUtil.toByteArray("084510021a0261612210e69fb3e59f8ee58da1e6b49be584bf7328a40b30003800"));
		System.out.println(msgResponse.toString());
		//000b0886e85d123c3135333730333040313a705535337634715037454f505a567652594467466f383639385a706b2f7343526275342b424b55517a556b3d3a3230343039
		//000b0886e85d123c3135333730333040313a72367a6b35565739777169617a4f4a714b514d64457764785172666c464939464f62467332744d4c446f4d3d3a3230343039
		Xxzz.LoginRequest loginRequest = Xxzz.LoginRequest.parseFrom(ByteUtil.toByteArray("0886e85d123c3135333730333040313a68332f4739426e4c5876325a543857666134582b70633656333048585a2b6451554f6e7645476c356554383d3a3230343039"));
		System.out.println(loginRequest.toString());
		
		
		
		/*Xxzz.ChatMsgRequest chatMsgRequest = Xxzz.ChatMsgRequest.newBuilder().setChanId(2).setMsg("aa").build();
		System.out.println(ByteUtil.toByteString(chatMsgRequest.toByteArray()));*/
	}
}
