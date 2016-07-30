/**
  * @(#)tools.ExplorerUtil.java  2008-8-22  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: �˴��������˵��
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-22 	С��     		�½�
  **/
package tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

 /**
 * �˴���������ϸ˵��
 * 2008-8-22
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class ExplorerUtil {

	public static HashMap<Integer, String> getRelectInfo(File file) throws ClassNotFoundException, IOException{
		HashMap<Integer, String> map = null;
		try {
			map = new  HashMap<Integer, String>();
			
			ClassLoaderAny loader = new ClassLoaderAny();
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b,0,len);
			Class xclass = loader.getClass(null,b,0,len);
			Field[] fields = xclass.getDeclaredFields();
			int i = 0;
			map.put(i, "�ļ�"+file.getName()+"����������:");i++;
			if(fields.length>0){
				map.put(i, "����ֶ���������:");i++;
				for(Field field:fields){
					map.put(i, field.getAnnotations()+field.toString());
					i++;
				}
			}
			Constructor[] constructors = xclass.getDeclaredConstructors();
			if(constructors.length>0){
				map.put(i, "��Ĺ��캯����������:");i++;
				for(Constructor constructor:constructors){
					map.put(i, constructor.getAnnotations()+constructor.toString());
					i++;
				}
			}
			
			Method[] methods = xclass.getDeclaredMethods();
			if(methods.length>0){
				map.put(i, "��ķ�����������:");i++;
				for(Method method:methods){
					map.put(i,method.getAnnotations()+method.toString());
					i++;
				}
			}
		} catch (RuntimeException e) {
			System.out.println("����:"+e.getMessage());
		}
		return map;
	}
	
	public static Process excuteClass(File file) throws IOException{
		String type = FileInfo.getFileFormat(file);
	
		Runtime run = Runtime.getRuntime();
		Process process = null;
		if(type.equals(".jar")){
			process = run.exec("java -jar "+file.getAbsolutePath());
			//System.out.println("java -jar "+file.getAbsolutePath());
		}
		if(type.equals(".class")){
			process = run.exec("java -cp "+file.getParent()+" "+FileInfo.getFileName(file));
		}
		return process;
	}
	
	public static Enumeration getZipList(File file) throws ZipException, IOException{
		ZipFile zipFile = new ZipFile(file);
		//zipFile.close();
		return zipFile.entries();
		
	}
	
	public static Properties getMainInfo(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		Properties prop = new Properties();
		while((line=reader.readLine())!=null){
			int interval = line.indexOf(":");
			if(interval!=-1){
				String key = line.substring(0,interval);
				String value = line.substring(interval,line.length());
				prop.setProperty(key, value);
			}
		}
		reader.close();
		reader = null;
		return prop;
	}
	
	public static void getWarInfo(File file){
		
	}
	
	public static void getEarInfo(File file){
		
	}
	
	public static String getContent(File file) throws IOException{
		String s = "";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while((line=reader.readLine())!=null){
			s += line;
			s += "\n";
		}
		reader.close();
		reader = null;
		return s;
	}
	
	public static Properties getFileBaseInfo(File file){
		Properties prop = new Properties();
		prop.put("�ļ���:", file.getName());
		prop.put("�ļ�����:", new FileInfo().getFileType(file));
		prop.put("�ļ���ʽ:", FileInfo.getFileFormat(file));
		prop.put("λ��:", file.getParent());
		prop.put("��С:", (file.length()/1024>1?file.length()/1024.00:1)+" KB ("+file.length()+" �ֽ�)");
		prop.put("����޸�ʱ��:",new SimpleDateFormat("yyyy'��'MM'��'DD'��, ' hh:mm:ss").format(new Date(file.lastModified())));
		return prop;
	}
}
