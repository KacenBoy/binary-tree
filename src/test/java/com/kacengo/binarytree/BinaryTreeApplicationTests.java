package com.kacengo.binarytree;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BinaryTreeApplicationTests {
    
    private static final Logger logger = LoggerFactory.getLogger(BinaryTree.class);

    @Test
    void contextLoads() {

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.creatBinTree(binaryTree.root);
        logger.info("the size of the tree is " + binaryTree.size());
        logger.info("the height of the tree is " + binaryTree.height());

        logger.info("*******(前序遍历)遍历*****************");
        binaryTree.preOrder(binaryTree.root);

        logger.info("*******(中序遍历)遍历*****************");
        binaryTree.inOrder(binaryTree.root);

        logger.info("*******(后序遍历)遍历*****************");
        binaryTree.postOrder(binaryTree.root);

        logger.info("层次遍历*****************");
        binaryTree.LIterator(binaryTree.root);

        logger.info("***非递归实现****(前序遍历)遍历*****************");
        binaryTree.InRecPreOrder(binaryTree.root);

        logger.info("***非递归实现****(中序遍历)遍历*****************");
        binaryTree.InRecMidOrder(binaryTree.root);

        logger.info("***非递归实现****(后序遍历)遍历*****************");
        binaryTree.InRecPostOrder(binaryTree.root);
    }

}
