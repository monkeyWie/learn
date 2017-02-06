package excel;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelParse {
	public static void main(String[] args) throws Exception {
		InputStream input = new FileInputStream("d:/iclap.xls");
		HSSFWorkbook book = new HSSFWorkbook(input);
		HSSFSheet sheet = book.getSheetAt(0);
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		String[] pc = {"Java","PHP","C","C++","Android","ios","测试","前端开发","技术经理","技术总监","架构师","CTO","产品总监","产品经理","移动产品经理","游戏策划","设计总监","UI","UE","交互设计","数据分忻","运宫","新媒体运营","数据运营","运营总监","COO","编辑","市场推广","市场总监","市场策划","BD","销售总监"};
		int id = 1;
		int l1 = -1;
		int l2 = -1;
		String s5="";
		String s6="";
		for(int r=2;r<sheet.getLastRowNum();r++){
			HSSFRow row = sheet.getRow(r);
			if(row!=null){
				HSSFCell c1 = row.getCell(0);
				HSSFCell c2 = row.getCell(1);
				HSSFCell c3 = row.getCell(2);
				HSSFCell c4 = row.getCell(3);
				HSSFCell c5 = row.getCell(4);
				HSSFCell c6 = row.getCell(5);
				if(c1!=null&&c1.getStringCellValue().trim().length()>0){
					sql.append("insert into job_type values ("+id+",'"+c1.getStringCellValue().trim()+"',-1,1,sysdate(),-1,sysdate(),-1,1);\n");
					l1=id++;
				}
				if(c2!=null&&c2.getStringCellValue().trim().length()>0){
					sql.append("insert into job_type values ("+id+",'"+c2.getStringCellValue().trim()+"',"+l1+",2,sysdate(),-1,sysdate(),-1,1);\n");
					l2=id++;
				}
				if(c3!=null&&c3.getStringCellValue().trim().length()>0){
					sql.append("insert into job_type values ("+id+",'"+c3.getStringCellValue().trim()+"',"+l2+",3,sysdate(),-1,sysdate(),-1,1);\n");
					
					String exp = c4!=null?c4.getStringCellValue().trim():"";
					for(String e : exp.split("/")){
						int isHot = 0;
						for (String p : pc) {
							if(p.equalsIgnoreCase(c3.getStringCellValue().trim())||(c2!=null&&c2.getStringCellValue().trim().length()>0&&p.equalsIgnoreCase(c2.getStringCellValue().trim()))){
								isHot = 1;
								break;
							}
						}
						s5= c5!=null?c5.getStringCellValue().replaceAll("\n","<br>"):"";
						s6= c6!=null?c6.getStringCellValue().replaceAll("\n","<br>"):"";
						sql2.append("insert into job_temple (type_id,job_exp,job_request,is_hot,create_time,create_user_id,last_update_time,last_update_user_id,curr_type) " +
							"values("+id+",'"+e+"','岗位职责：<br>"+s6+"<br>岗位要求：<br>"+s5+"',"+isHot+",sysdate(),-1,sysdate(),-1,1);\n");
					}
					
					id++;
					
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/t.txt")));
		bw.write(sql.toString());
		bw.write(sql2.toString());
		bw.close();
	}
}
