/**
  * @(#)tools.ClassLoaderAny.java  2008-8-25  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-25 	小猪     		新建
  **/
package tools;

 /**
 * 此处加入类详细说明
 * 2008-8-25
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 * @author		Administrator
 */
public class ClassLoaderAny extends ClassLoader {

	public Class getClass(String name, byte[] b, int off, int len){
		return defineClass(name ,b, off, len);
	}
	
	
}
