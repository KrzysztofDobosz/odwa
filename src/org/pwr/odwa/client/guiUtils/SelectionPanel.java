package org.pwr.odwa.client.guiUtils;

import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.NodeTraversalCallback;
import com.gwtext.client.dd.DragData;
import com.gwtext.client.dd.DragDrop;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
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

	private  TreePanel treePanel;
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
		// treePanel.setAnimate(true);
		treePanel.setFrame(false);
		treePanel.setEnableDD(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(true);
		treePanel.setWidth(300);
		treePanel.setHeight(400);
		treePanel.setSelectionModel(new MultiSelectionModel());


		TreeNode root = new TreeNode("DATA VIEW");
		//TreeNode root = parser.parse(parser.getTekst());
		treePanel.setRootNode(root);
		root.expand();
		treePanel.expandAll();

		rowsTreePanel = new SelectionTreePanel();
		//rowsTreePanel.setAnimate(true);
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
				if ("true".equals(target.getAttribute("trip")))
				{
					TreeNode copyNode = dropNode.cloneNode();
					Node[] children = copyNode.getChildNodes();
					for (int i = 0; i < children.length; i++)
					{
						Node child = children[i];
						copyNode.removeChild(child);
					}
					dropDropNodeCallback.setDropNode(copyNode);

				}
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

			TreeNode columns = new TreeNode("COLUMNS");
			root.appendChild(columns);

			TreeNode rows = new TreeNode("ROWS");
			root.appendChild(rows);

			TreeNode backg = new TreeNode("BACKGROUND");
			root.appendChild(backg);

			setTitle("Selection");
			setEnableDD(true);
			setRootNode(root);
		}
	}

	public void loadXml(String xmlString)
	{
		removeAll();
		treePanel = new TreePanel();
		treePanel.setTitle("Structure");
		// treePanel.setAnimate(true);
		treePanel.setFrame(false);
		treePanel.setEnableDD(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(true);
		treePanel.setWidth(300);
		treePanel.setHeight(400);
		treePanel.setSelectionModel(new MultiSelectionModel());

		TreeNode root = parser.parse(xmlString);
		treePanel.setRootNode(root);

		root.expand();

		//treePanel.expandAll();

		MessageBox.alert(treePanel.getRootNode().getText());
		//add(treePanel);
		add(rowsTreePanel);

		show();

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

}
