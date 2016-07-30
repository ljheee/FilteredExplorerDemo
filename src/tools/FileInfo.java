/**
  * @(#)tools.FileInfo.java  2008-8-19  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 文件过滤器类。
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-19 	小猪     		新建
  **/
package tools;

import java.io.File;
import java.io.FileFilter;

 /**
 * 文件过滤器类。
 * 2008-8-19
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 */
public class FileInfo implements FileFilter {

	/** 文件类型的二维数组，分别表示文件类型和文件类型说明 */
	private String[][] fileType = null;
	
	/** 是否显示隐藏文件 */
	private boolean isShowHiden = false;
	
	/**
	 * 缺省文件类型的构造函数。<br>
	 * 文件类型：".txt",".java",".mf",".class",".jar",".war",".ear",".properties"。<br>
	 * 对应的文件类型说明:"文本文件","Java程序","Java清单文件","Java的运行文件","Java的打包文件","Web程序文件","JBoss等应用程序文件","Java属性文件"。<br>
	 */
	public FileInfo() {
		this(false);
	}
	
	/**
	 * 缺省文件类型的构造函数。<br>
	 * 文件类型：".txt",".java",".mf",".class",".jar",".war",".ear",".properties"。<br>
	 * 对应的文件类型说明:"文本文件","Java程序","Java清单文件","Java的运行文件","Java的打包文件","Web程序文件","JBoss等应用程序文件","Java属性文件"。<br>
	 * @param isShowHiden 是否显示隐藏文件。
	 */
	public FileInfo(boolean isShowHiden) {
		fileType = new String[][]{	{".txt",".java",".mf",".class",".jar",".war",".ear",".properties"},
									{"文本文件","Java程序","Java清单文件","Java编译后的文件","Java的打包文件","Web程序文件","JBoss等应用程序文件","Java属性文件"}};
		this.isShowHiden = isShowHiden;
	}
	/**
	 * 带参数类型的构造函数。<br>
	 * 必须保证传入的文件类型2数组长度相等。否则程序自动初始化默认数组。
	 * @param fileType 文件类型数组。前面数组表示过滤的数组的类型，如".txt"。<br>后面数组表示类型的说明，如".txt"表示"文本文件";
	 * @param isShowHiden 是否显示隐藏文件。
	 */
	public FileInfo(String[][] fileType,boolean isShowHiden) {
		this(isShowHiden);
		if(fileType[0].length==fileType[1].length)
			this.fileType = fileType;		
	}
	/**
	 * 测试指定抽象路径名是否应该包含在某个路径名列表中。
	 * （非 Javadoc）
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
	 * 获得某个类型的文件的类型文件说明。
	 * @param pathname 要获取类型文件说明的文件。
	 * @return 返回该文件对应的类型文件说明。未知的文件类型说明返回"未知文件类型";
	 */
	public String getFileType(File pathname){
		String fileName = pathname.getName();
		for(int i=0;i<fileType[0].length;i++)
			if(fileName.endsWith(fileType[0][i]))
				return fileType[1][i];
		return "未知文件类型";
	}

	/**
	 * 获得某个文件的文件格式，即文件的扩展名。
	 * @param pathname 要获取类型文件说明的文件。
	 * @return 返回该文件的扩展名，如.txt
	 */
	public static String getFileFormat(File pathname){
		String path = pathname.getName();
		return path.lastIndexOf(".")==-1?"未知格式":path.substring(path.lastIndexOf("."), path.length());
	}
	/**
	 * 获得某个文件的文件名。
	 * @param pathname 要获取类型文件说明的文件。
	 * @return 返回该文件的文件名，如Test.class返回Test
	 */
	public static String getFileName(File pathname){
		String name = pathname.getName();
		return name.substring(0,name.lastIndexOf("."));
	}
	
	/**
	 * 获得某个类型的文件的类型。
	 * @param pathname 要获取类型文件说明的文件。
	 * @return 返回该文件对应的类型。未知的文件类型说明返回-1.
	 */
	public int getFileIntegerType(File pathname){
		String fileName = pathname.getName();
		for(int i=0;i<fileType[0].length;i++)
			if(fileName.endsWith(fileType[0][i]))
				return i;
		return -1;
	}
	
	

	/**
	 * 获得是否显示隐藏文件。
	 * @return true:显示，false:不显示。
	 */
	public boolean isShowHiden() {
		return isShowHiden;
	}

	/**
	 * 设置是否显示隐藏文件。
	 * @param isShowHiden true:显示，false:不显示。
	 */
	public void setShowHiden(boolean isShowHiden) {
		this.isShowHiden = isShowHiden;
	}
}
