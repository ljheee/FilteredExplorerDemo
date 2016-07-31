/**
  * @(#)frm.DirTree.java  2008-8-18  
  * Copy Right Information	: Tarena
  * Project					: Explorer
  * JDK version used		: jdk1.6.4
  * Comments				: 资源管理器的树状显示类。
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-18 	小猪     		新建
  **/
package frm;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import main.ExplorerMain;

 /**
 * 资源管理器的树状显示类。<br>
 * 显示文件树。仿照MS的风格.
 * 为了速度：建议递归进行3层。<br>
 * 2008-8-18
 * @author		达内科技[Tarena Training Group]
 * @version	1.0
 * @since		JDK1.6(建议) 
 */
public class DirTree extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/** 根节点 */
	private DefaultMutableTreeNode node = null;
	/** 根 */
	private JTree tree = null;
	
	/** 是否显示隐藏文件 */
	private boolean isShowHiden = false;
	
	public DirTree() {
		
		node = new DefaultMutableTreeNode(getOsName());
		addRootNode();
		tree = new JTree(node);
		//tree.setCellRenderer(new DirRenderer(new ImageIcon("/images/open.gif"),new ImageIcon("images/close.gif"),new ImageIcon("images/leaf.ico")));
		
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(new ImageIcon("images/close.gif"));
		tree.setCellRenderer(renderer);
		
		tree.addTreeWillExpandListener(new DirTreeWillExpandListenerImpl());
		tree.addTreeSelectionListener(new DirTreeSelectionListenerImpl());
		setLayout(new BorderLayout());
		add(new JScrollPane(tree));
	}

	/**
	 * 显示是否显示隐藏文件。
	 * @return 返回true：显示隐藏文件，否则不显示隐藏文件。
	 */
	public boolean isShowHiden() {
		return isShowHiden;
	}

	/**
	 * 设置是否显示隐藏文件。
	 * @param isShowHiden true：显示隐藏文件。false：不显示。
	 */
	public void setShowHiden(boolean isShowHiden) {
		this.isShowHiden = isShowHiden;
	}
	
	/**
	 * 把file下目录添加到某节点上。
	 * @param file 文件目录。
	 * @param node 节点。
	 */
	private void addNodeToParentNode(File file,DefaultMutableTreeNode node){
		if(file.isDirectory()){
			node.removeAllChildren();
			File[] childFile = file.listFiles();
			if(childFile!=null)
				for(File f:childFile)
					if((isShowHiden  || !f.isHidden()) && f.isDirectory()){
						DefaultMutableTreeNode child = new DefaultMutableTreeNode(f.getName());
						node.add(child);
					}
		}	
	}
	
	/**
	 * 根结点添加子节点。（即为我的电脑添加各个盘符）.
	 *
	 */
	private void addRootNode(){
		File[] childFile = File.listRoots();
		for(File f:childFile){
			//if(isShowHiden || !f.isHidden()){
			//System.out.println(f.getName());
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
			node.add(child);
			if(f.canWrite())
			//	child.add(new DefaultMutableTreeNode("NoUse"));
			addNodeToParentNode(f, child);
			//}
		}
	}
	
	private class DirTreeWillExpandListenerImpl implements TreeWillExpandListener{
		/**
		 * 
		 */
		public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		}
		
		/**
		 * 节点即将展开的时候，对节点下的子节点操作，添加所有子节点的下级节点。
		 */
		public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
			TreePath tPath=event.getPath();
	    	DefaultMutableTreeNode td=(DefaultMutableTreeNode)tPath.getLastPathComponent();

	    	String nodePath=tPath.toString();
	    	String filePath = getFilePath(nodePath);
	    	
	    	Enumeration<DefaultMutableTreeNode> nodes = td.children();
	    	while(nodes.hasMoreElements()){
	    		DefaultMutableTreeNode node = nodes.nextElement();
	    		File file = new File(filePath+node.toString());
	    		addNodeToParentNode(file, node);
	    	}
	    	
		}
	}
	
	private class DirTreeSelectionListenerImpl implements TreeSelectionListener{
		public void valueChanged(TreeSelectionEvent e) {
			//System.out.println(e.getPath());
			TreePath tPath=e.getPath();
			String filePath = getFilePath(tPath.toString());
			File file = new File(filePath);
			File[] children = file.listFiles(ExplorerMain.fileInfo);
			//System.out.println("这边打印："+filePath);
			ExplorerMain.tableInfoPane.setFileList(children, filePath);
		}
	}
	
	/**
	 * 解析节点的字符串值得到节点的所代表的文件的路径。
	 * @param nodePath 节点的字符串值。
	 * @return 返回该节点所代表的文件的路径。
	 */
	private String getFilePath(String nodePath){
		String filePath = "";
		nodePath = nodePath.substring(1+getOsName().length(),nodePath.length()-1);
		StringTokenizer tokenizer = new StringTokenizer(nodePath,", ");
		while (tokenizer.hasMoreTokens()) {
			
			String s = tokenizer.nextToken();
			filePath += s.endsWith(File.separator)?s:s+"\\";
		}
		return filePath;
	}
	
	/**
	 * 获得操作系统的名称。
	 * @return 返回操作系统的名称。
	 */
	private String getOsName(){
		 return System.getProperty("os.name").trim();
	}
}
