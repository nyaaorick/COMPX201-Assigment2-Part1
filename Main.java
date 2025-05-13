public class Main {
    public static void main(String[] args) {

        // 创建树对象
        StrBST tree = new StrBST();

        // 定义 prettyPrint 方法
        Runnable prettyPrint = () -> {
            System.out.println("Tree:");
            StrBST.StrBSTPrinter.printNode(tree.root); // 调用 StrBSTPrinter 的打印方法
        };
        // 调用 prettyPrint
        prettyPrint.run();

        System.out.println("Testing BST\n");

        // 插入一些字母测试
        System.out.println("Inserting nodes C,B,A,E,D ...");
        tree.insert("C");
        tree.insert("B");
        tree.insert("A");
        tree.insert("E");
        tree.insert("D");

        // 调用 prettyPrint 打印树
        prettyPrint.run();

        System.out.println("A is in BST: " + tree.search("A")); // 这里得是 True
        System.out.println("F is in BST: " + tree.search("F")); // 这里得是 False

        // 打印整个树（中序遍历）
        System.out.println();
        tree.print();

        // 删除节点 B 并打印
        System.out.println("Removing node B !!!");
        tree.remove("B");
        prettyPrint.run();

        // 删除节点 C 并打印
        System.out.println("Removing node C !!!");
        tree.remove("C");
        prettyPrint.run();

        // 删除节点 A 并打印
        System.out.println("Removing node A ...");
        tree.remove("A");
        prettyPrint.run();
    }
}
