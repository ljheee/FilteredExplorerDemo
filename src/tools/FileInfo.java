/**
  * @(#)tools.FileInfo.java  2008-8-19  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: �ļ��������ࡣ
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-19 	С��     		�½�
  **/
package tools;

import java.io.File;
import java.io.FileFilter;

 /**
 * �ļ��������ࡣ
 * 2008-8-19
 * @author		���ڿƼ�[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(����) 
 */
public class FileInfo implements FileFilter {

	/** �ļ����͵Ķ�ά���飬�ֱ��ʾ�ļ����ͺ��ļ�����˵�� */
	private String[][] fileType = null;
	
	/** �Ƿ���ʾ�����ļ� */
	private boolean isShowHiden = false;
	
	/**
	 * ȱʡ�ļ����͵Ĺ��캯����<br>
	 * �ļ����ͣ�".txt",".java",".mf",".class",".jar",".war",".ear",".properties"��<br>
	 * ��Ӧ���ļ�����˵��:"�ı��ļ�","Java����","Java�嵥�ļ�","Java�������ļ�","Java�Ĵ���ļ�","Web�����ļ�","JBoss��Ӧ�ó����ļ�","Java�����ļ�"��<br>
	 */
	public FileInfo() {
		this(false);
	}
	
	/**
	 * ȱʡ�ļ����͵Ĺ��캯����<br>
	 * �ļ����ͣ�".txt",".java",".mf",".class",".jar",".war",".ear",".properties"��<br>
	 * ��Ӧ���ļ�����˵��:"�ı��ļ�","Java����","Java�嵥�ļ�","Java�������ļ�","Java�Ĵ���ļ�","Web�����ļ�","JBoss��Ӧ�ó����ļ�","Java�����ļ�"��<br>
	 * @param isShowHiden �Ƿ���ʾ�����ļ���
	 */
	public FileInfo(boolean isShowHiden) {
		fileType = new String[][]{	{".txt",".java",".mf",".class",".jar",".war",".ear",".properties"},
									{"�ı��ļ�","Java����","Java�嵥�ļ�","Java�������ļ�","Java�Ĵ���ļ�","Web�����ļ�","JBoss��Ӧ�ó����ļ�","Java�����ļ�"}};
		this.isShowHiden = isShowHiden;
	}
	/**
	 * ���������͵Ĺ��캯����<br>
	 * ���뱣֤������ļ�����2���鳤����ȡ���������Զ���ʼ��Ĭ�����顣
	 * @param fileType �ļ��������顣ǰ�������ʾ���˵���������ͣ���".txt"��<br>���������ʾ���͵�˵������".txt"��ʾ"�ı��ļ�";
	 * @param isShowHiden �Ƿ���ʾ�����ļ���
	 */
	public FileInfo(String[][] fileType,boolean isShowHiden) {
		this(isShowHiden);
		if(fileType[0].length==fileType[1].length)
			this.fileType = fileType;		
	}
	/**
	 * ����ָ������·�����Ƿ�Ӧ�ð�����ĳ��·�����б��С�
	 * ���� Javadoc��
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File pathname) {
		String fileName = pathname.getName();
		for(String s:fileType[0])
			if(pathname.isFile() && (isShowHiden  || !pathname.isHidden()) && fileName.endsWith(s))
				return true;
		return false;
	}
	
	/**
	 * ���ĳ�����͵��ļ��������ļ�˵����
	 * @param pathname Ҫ��ȡ�����ļ�˵�����ļ���
	 * @return ���ظ��ļ���Ӧ�������ļ�˵����δ֪���ļ�����˵������"δ֪�ļ�����";
	 */
	public String getFileType(File pathname){
		String fileName = pathname.getName();
		for(int i=0;i<fileType[0].length;i++)
			if(fileName.endsWith(fileType[0][i]))
				return fileType[1][i];
		return "δ֪�ļ�����";
	}

	/**
	 * ���ĳ���ļ����ļ���ʽ�����ļ�����չ����
	 * @param pathname Ҫ��ȡ�����ļ�˵�����ļ���
	 * @return ���ظ��ļ�����չ������.txt
	 */
	public static String getFileFormat(File pathname){
		String path = pathname.getName();
		return path.lastIndexOf(".")==-1?"δ֪��ʽ":path.substring(path.lastIndexOf("."), path.length());
	}
	/**
	 * ���ĳ���ļ����ļ�����
	 * @param pathname Ҫ��ȡ�����ļ�˵�����ļ���
	 * @return ���ظ��ļ����ļ�������Test.class����Test
	 */
	public static String getFileName(File pathname){
		String name = pathname.getName();
		return name.substring(0,name.lastIndexOf("."));
	}
	
	/**
	 * ���ĳ�����͵��ļ������͡�
	 * @param pathname Ҫ��ȡ�����ļ�˵�����ļ���
	 * @return ���ظ��ļ���Ӧ�����͡�δ֪���ļ�����˵������-1.
	 */
	public int getFileIntegerType(File pathname){
		String fileName = pathname.getName();
		for(int i=0;i<fileType[0].length;i++)
			if(fileName.endsWith(fileType[0][i]))
				return i;
		return -1;
	}
	
	

	/**
	 * ����Ƿ���ʾ�����ļ���
	 * @return true:��ʾ��false:����ʾ��
	 */
	public boolean isShowHiden() {
		return isShowHiden;
	}

	/**
	 * �����Ƿ���ʾ�����ļ���
	 * @param isShowHiden true:��ʾ��false:����ʾ��
	 */
	public void setShowHiden(boolean isShowHiden) {
		this.isShowHiden = isShowHiden;
	}
}
