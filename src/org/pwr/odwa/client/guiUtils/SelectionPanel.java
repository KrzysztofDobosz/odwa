package org.pwr.odwa.client.guiUtils;

import java.util.ArrayList;

import org.pwr.odwa.common.selection.SelectionLoader;

import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.NodeTraversalCallback;
import com.gwtext.client.dd.DragData;
import com.gwtext.client.dd.DragDrop;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.DropNodeCallback;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeEditor;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreePanelListenerAdapter;

public class SelectionPanel extends Panel
{
	private  final TreePanel treePanel;
	private  final TreePanel rowsTreePanel;

	private Menu menu;
	private TreeNode ctxNode;
	private TreeEditor treeEditor;
	private MetaXmlParser parser;

	public SelectionPanel()
	{

		parser = new MetaXmlParser();

		setLayout(new HorizontalLayout(20));
		setPaddings(15);

		treePanel = new TreePanel();
		treePanel.setTitle("Structure");
		treePanel.setAnimate(true);
		treePanel.setFrame(false);
		treePanel.setEnableDrag(true);
		treePanel.setContainerScroll(true);
		treePanel.setAutoScroll(true);
		treePanel.setRootVisible(true);
		treePanel.setWidth(300);
		treePanel.setHeight(400);
		treePanel.setSelectionModel(new MultiSelectionModel());

		TreeNode root = new TreeNode("DATA VIEW");
		//TreeNode root = parser.parse(xmlString);
		treePanel.setRootNode(root);
		root.expand();
		treePanel.expandAll();

		rowsTreePanel = new SelectionTreePanel();
		rowsTreePanel.setAnimate(true);
		rowsTreePanel.setFrame(false);
		rowsTreePanel.setContainerScroll(true);
		rowsTreePanel.setRootVisible(true);
		rowsTreePanel.setWidth(300);
		rowsTreePanel.setHeight(400);


		TextField field = new TextField();
		field.setSelectOnFocus(true);
		treeEditor = new TreeEditor(rowsTreePanel, field);

		rowsTreePanel.addListener(new TreePanelListenerAdapter()
		{
			public void onRender(Component component)
			{
				// rowsTreePanel.getRootNode().expand();
				// rowsTreePanel.expandAll();
			}

			public boolean doBeforeNodeDrop(TreePanel treePanel,
					TreeNode target, DragData dragData, String point,
					DragDrop source, TreeNode dropNode,
					DropNodeCallback dropDropNodeCallback)
			{
				if ("false".equals(target.getAttribute("allowDrop")))
				{
					return false;
				}

				else if ("false".equals(dropNode.getAttribute("allowDrag")))
				{
					return false;
				}
				else if("false".equals(target.getAttribute("multiple")))
				{
					if(target.getFirstChild() != null) return false;

				}
				else if("false".equals(target.getAttribute("allowDrop")))
				{
					return false;

				}
				else if(!(dropNode.getAttribute("metaType").equals(target.getAttribute("metaType"))))
				{

					return false;
				}

				else if(target.getDepth()> 1 && dropNode.getAttribute("metaType").equals("dimension")
						&& !(dropNode.getAttribute("uid").contains(target.getAttribute("uid"))))
					return false;

				TreeNode copyNode = dropNode.cloneNode();
				copyNode.setAttribute("allowDrag", "false");

				if(target.getDepth()> 1 || dropNode.getAttribute("metaType").equals("measure"))
						copyNode.setAttribute("allowDrop", "false");
				else copyNode.setAttribute("multiple", "true");

				if (dropNode.getAttribute("metaType").equals("measure"))
					copyNode.setAttribute("allowDrop", "false");

				Node[] children = copyNode.getChildNodes();
				for (int i = 0; i < children.length; i++)
				{
					Node child = children[i];
					copyNode.removeChild(child);
				}

				dropDropNodeCallback.setDropNode(copyNode);

				return true;
			}

			public void onContextMenu(TreeNode node, EventObject e)
			{
				int[] xy = e.getXY();
				showContextMenu(node, e);
			}
		});

		add(treePanel);
		add(rowsTreePanel);

	}

	class SelectionTreePanel extends TreePanel
	{

		public SelectionTreePanel()
		{

			TreeNode root = new TreeNode("User selection:");
			root.setExpanded(true);
			root.setAttribute("allowDrag", "false");
			root.setAttribute("allowDrop", "false");
			root.setAttribute("metaType", "false");


			TreeNode columns = new TreeNode("COLUMNS");
			columns.setAttribute("name", "COLUMNS");
			columns.setAttribute("allowDrag", "false");
			columns.setAttribute("multiple", "true");
			columns.setAttribute("metaType", "dimension");
			root.appendChild(columns);

			TreeNode rows = new TreeNode("ROWS");
			rows.setAttribute("name", "ROWS");
			rows.setAttribute("allowDrag", "false");
			rows.setAttribute("multiple", "true");
			rows.setAttribute("metaType", "dimension");
			root.appendChild(rows);

			TreeNode backg = new TreeNode("BACKGROUND");
			backg.setAttribute("name", "BACKGROUND");
			backg.setAttribute("allowDrag", "false");
			backg.setAttribute("multiple", "true");
			backg.setAttribute("metaType", "dimension");
			root.appendChild(backg);

			TreeNode measure = new TreeNode("MEASURE");
			measure.setAttribute("name", "MEASURE");
			measure.setAttribute("allowDrag", "false");
			measure.setAttribute("multiple", "false");
			measure.setAttribute("metaType", "measure");
			root.appendChild(measure);

			setTitle("Selection");
			setEnableDrop(true);
			setRootNode(root);
		}

	}

	public void loadXml(String xmlString)
	{

		TreeNode root = parser.parse(xmlString);
		treePanel.getRootNode().appendChild(root);
		treePanel.getRootNode().expand();
		//treePanel.expand();
		//treePanel.expandAll();
	}

	private void showContextMenu(final TreeNode node, EventObject e)
	{
		if (menu == null)
		{
			menu = new Menu();
			Item editItem = new Item("Edit", new BaseItemListenerAdapter()
			{
				public void onClick(BaseItem item, EventObject e)
				{
					treeEditor.startEdit(ctxNode);
				}
			});
			editItem.setId("edit-item");
			menu.addItem(editItem);

			Item disableItem = new Item("Disable",
					new BaseItemListenerAdapter()
					{
						public void onClick(BaseItem item, EventObject e)
						{
							ctxNode.disable();
							ctxNode.cascade(new NodeTraversalCallback()
							{
								public boolean execute(Node node)
								{
									((TreeNode) node).disable();
									return true;
								}
							});
						}
					});
			disableItem.setId("disable-item");
			menu.addItem(disableItem);

			Item enableItem = new Item("Enable", new BaseItemListenerAdapter()
			{
				public void onClick(BaseItem item, EventObject e)
				{
					ctxNode.enable();
					ctxNode.cascade(new NodeTraversalCallback()
					{
						public boolean execute(Node node)
						{
							((TreeNode) node).enable();
							return true;
						}
					});
				}
			});
			enableItem.setId("enable-item");
			menu.addItem(enableItem);

			Item removeItem = new Item("Remove", new BaseItemListenerAdapter()
			{
				public void onClick(BaseItem item, EventObject e)
				{

					ctxNode.cascade(new NodeTraversalCallback()
					{
						public boolean execute(Node node)
						{
							((TreeNode) node).remove();
							return true;
						}
					});
					ctxNode.remove();
				}
			});
			removeItem.setId("remove-item");
			menu.addItem(removeItem);

			Item cloneItem = new Item("Clone", new BaseItemListenerAdapter()
			{
				public void onClick(BaseItem item, EventObject e)
				{
					TreeNode clone = ctxNode.cloneNode();
					clone.setText("Copy of " + clone.getText());
					ctxNode.getParentNode().appendChild(clone);
					treeEditor.startEdit(clone);
				}
			});
			cloneItem.setId("clone-item");
			menu.addItem(cloneItem);

			Item newFolderItem = new Item("New Folder",
					new BaseItemListenerAdapter()
					{
						public void onClick(BaseItem item, EventObject e)
						{
							TreeNode newFolder = new TreeNode("New Folder");
							ctxNode.getParentNode().appendChild(newFolder);
							treeEditor.startEdit(newFolder);
						}
					});
			newFolderItem.setId("newfolder-item");
			menu.addItem(newFolderItem);
		}

		if (ctxNode != null)
		{
			ctxNode = null;
		}
		ctxNode = node;

		if (ctxNode.isDisabled())
		{
			menu.getItem("disable-item").disable();
			menu.getItem("enable-item").enable();
		} else
		{
			menu.getItem("disable-item").enable();
			menu.getItem("enable-item").disable();
		}
		menu.showAt(e.getXY());
	}

	public SelectionLoader loadSelection()
	{
		SelectionLoader result = new SelectionLoader();

		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> back = new ArrayList<ArrayList<String>>();
		String measure = "";

		TreeNode root = rowsTreePanel.getRootNode();

		Node[] nodes = root.getChildNodes();

		for (int i = 0; i< nodes.length; i++)
		{
			Node currentNode = nodes[i];
			if(currentNode.getAttribute("name").equals("ROWS"))
			{
				Node[] rowsNodes = currentNode.getChildNodes();
				ArrayList<String> rowsUid = new ArrayList<String>();
				for(Node row : rowsNodes)
				{
					Node[] subaxis = row.getChildNodes();
					for(Node subrow: subaxis)
					{
						rowsUid.add(subrow.getAttribute("uid"));
					}
					rows.add(rowsUid);
				}


			}
			else if(currentNode.getAttribute("name").equals("COLUMNS"))
			{
				Node[] colsNodes = currentNode.getChildNodes();

				for(Node row : colsNodes)
				{
					ArrayList<String> colsUid = new ArrayList<String>();
					Node[] subaxis = row.getChildNodes();
					for(Node subrow: subaxis)
					{
						colsUid.add(subrow.getAttribute("uid"));
					}
					cols.add(colsUid);
				}

			}
			else if(currentNode.getAttribute("name").equals("BACKGROUND"))
			{
				Node[] backNodes = currentNode.getChildNodes();

				for(Node row : backNodes)
				{
					ArrayList<String> backUid = new ArrayList<String>();
					Node[] subaxis = row.getChildNodes();
					for(Node subrow: subaxis)
					{
						backUid.add(subrow.getAttribute("uid"));
					}
					back.add(backUid);
				}

			}
			else if(currentNode.getAttribute("name").equals("MEASURE"))
			{
				measure = currentNode.getFirstChild().getAttribute("uid");
			}
		}

		result.setRows(rows);
		result.setCols(cols);
		//result.setBackground(back);
		result.setMeasure(measure);

		return result;
	}

}