/**
  * @(#)tools.ClassLoaderAny.java  2008-8-25  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: �˴��������˵��
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-25 	С��     		�½�
  **/
package tools;

 /**
 * �˴���������ϸ˵��
 * 2008-8-25
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 * @author		Administrator
 */
public class ClassLoaderAny extends ClassLoader {

	public Class getClass(String name, byte[] b, int off, int len){
		return defineClass(name ,b, off, len);
	}
	
	
}
