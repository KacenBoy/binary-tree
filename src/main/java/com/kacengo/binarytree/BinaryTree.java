package com.kacengo.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Kacen on 2020-08-27
 */
public class BinaryTree {

    private static final Logger logger = LoggerFactory.getLogger(BinaryTree.class);
    public TreeNode root = null;

    //这里先创建rootNode A,这里看不懂的先看TreeNode
    public BinaryTree() {
        root = new TreeNode(1,"rootNode(A)");
    }

    /*
    * 创建二叉树，结构如图
    *       A
    *       ｜
    *   ｜-------｜
    *   B        C
    *   ｜       ｜
    * ｜---｜   ｜-
    * D    E   F
    * ｜   ｜
    * -｜｜--｜
    *  X M   N
    */
    //一开始要传入顶端A
    public void creatBinTree(TreeNode root){
        //这里先创建两个节点，方便大家看，后续就直接new了
        TreeNode newNodeB = new TreeNode(2, "B");
        TreeNode newNodeC = new TreeNode(3, "C");
        //左右节点按上面结构来的
        root.leftNode = newNodeB;
        root.rightNode = newNodeC;
        root.leftNode.leftNode = new TreeNode(4, "D");
        root.leftNode.rightNode = new TreeNode(5, "E");
        root.rightNode.leftNode = new TreeNode(6, "F");
        root.leftNode.leftNode.rightNode = new TreeNode(7, "X");
        root.leftNode.rightNode.leftNode = new TreeNode(8, "M");
        root.leftNode.rightNode.rightNode = new TreeNode(9, "N");
    }

    public boolean isEmpty(){
        return root == null;
    }

    //这个树的高度
    public int height(){
        return height(root);
    }

    //这里的subTree是子树的意思
    public int height(TreeNode subTree){
        //这里会用到递归哦
        if (subTree == null){
            return 0;
        }else {
            //i是左，j是右
            int i = height(subTree.leftNode);
            int j = height(subTree.rightNode);
            //这里就是如果左高度小于右高度，那右边就要+1是不是，反则左加1
            return (i < j ) ? (j + 1) : (i + 1);
        }
    }

    //有树的高度那就得有节点个数
    public int size(){
        return size(root);
    }

    public int size(TreeNode subTree){
        //这里还是一样判断
        if (subTree == null){
            return 0;
        }else {
            //如果有节点存在就要添加
            return 1 + size(subTree.leftNode) + size(subTree.rightNode);
        }
    }

    //返回双亲节点
    public TreeNode parent(TreeNode element){
        return (root == null || root == element) ? null : parent(root , element);
    }

    public TreeNode parent(TreeNode subTree, TreeNode element){
        if (subTree == null){
            return null;
        }
        //返回父节点地址
        if (element == subTree.leftNode || element == subTree.rightNode){
            return subTree;
        }

        TreeNode s;

        //这里也是递归，左边找不到再找右边
        if ((s = parent(subTree.leftNode, element)) != null)
            return s;
        else
            return parent(subTree.rightNode, element);
    }

    //获取左子节点
    public TreeNode getLeftChildNode(TreeNode element){
        return element != null ? element.leftNode : null;
    }

    //获取右子节点
    public TreeNode getRightChildNoede(TreeNode element){
        return element != null ? element.rightNode : null;
    }

    //获取树
    public TreeNode getRoot(){
        return root;
    }

    /**
     * 在释放某一个节点时，它的左右节点的储存空间也已经释放，所以采用后续来释放空间（注意是在访问的时候才释放的哦）
     */
    public void destroy(TreeNode subTree){
        if (subTree != null){
            //删除左右结点
            destroy(subTree.leftNode);
            destroy(subTree.rightNode);
            //删除根节点
            subTree = null;
        }
    }

    //这个visited自己追踪看
    public void visited(TreeNode subTree){
        subTree.isVisited = false;
        logger.info("key: " + subTree.key + ", data: " + subTree.data);
    }

    //前序遍历 根结点 -> 左孩子 -> 右孩子
    public void preOrder(TreeNode subTree){
        if (null != subTree){
            visited(subTree);
            preOrder(subTree.leftNode);
            preOrder(subTree.rightNode);
        }
    }

    //中序遍历 左孩子 -> 根结点 -> 右孩子
    public void inOrder(TreeNode subTree){
        if (null != subTree){
            inOrder(subTree.leftNode);
            visited(subTree);
            inOrder(subTree.rightNode);
        }
    }

    //后续遍历 左孩子 -> 右孩子 -> 根结点
    public void postOrder(TreeNode subTree){
        if (null != subTree){
            postOrder(subTree.leftNode);
            postOrder(subTree.rightNode);
            visited(subTree);
        }
    }

    //前序遍历非递归实现
    public void InRecPreOrder(TreeNode s){
        //栈是Vector的一个子类，它实现了一个标准的后进先出的栈.不清楚的自行百度
        //它的方法直接追一下stack就知道了，而且他们都是加了synchronize的，至于原因自己明白，不明白百度
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = s;
        while (null != node || stack.size()>0){
            //存在左子树
            while (null != node){
                //把项压入堆栈顶部
                stack.push(node);
                visited(node);
                node = node.leftNode;
            }
            //栈非空
            if (stack.size() > 0){
                //移除堆栈顶部的对象，并作为此函数的值返回该对象。
                node = stack.pop();
                node = node.rightNode;
            }
        }
    }

    //中序遍历非递归实现
    public void InRecMidOrder(TreeNode s){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = s;
        while (null != node || stack.size() > 0){
            while (null != node) {
                stack.push(node);
                node = node.leftNode;
            }
            if (stack.size() > 0){
                node = stack.pop();
                visited(node);
                node = node.rightNode;
            }
        }

    }

    //后序遍历非递归实现
    public void InRecPostOrder(TreeNode s){
        Stack<TreeNode> stack = new Stack<>();
        //这里的node是用来记录节点的
        TreeNode node = s;
        //左子树入栈
        while (null != s) {
            for (; null != s.leftNode; s = s.leftNode)
                stack.push(s);
            //当节点无右子树或者右子树已经输出。
            if (stack.empty()) return;
            while (null != s && (s.rightNode == null || s.rightNode == node)) {
                visited(s);
                //记录前一个已经输出节点
                node = s;
                //栈空了就直接return了
                if (stack.empty()) return;
                s = stack.pop();
            }
            //处理右子树
            stack.push(s);
            assert s != null;
            s = s.rightNode;
        }
    }

    //广度/层次遍历
    public void LIterator(TreeNode node){
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.offer(node);
        while (!treeNodeQueue.isEmpty()){
            TreeNode node1 = treeNodeQueue.poll();
            if (null != node1) visited(node1);
            if (null != (node1 != null ? node1.leftNode : null)) treeNodeQueue.offer(node1.leftNode);
            if (null != (node1 != null ? node1.rightNode : null)) treeNodeQueue.offer(node1.rightNode);
        }
    }

}
