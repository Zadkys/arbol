import java.util.LinkedList;
import java.util.Queue;

class arbol {
    class arbolnode {
        int data, height;
        arbolnode left, right;

        public arbolnode(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    private arbolnode root;

    private arbolnode insert(arbolnode node, int data) {
        if (node == null)
            return new arbolnode(data);

        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);

        updateHeight(node);

        return balance(node);
    }

    private arbolnode rightRotate(arbolnode y) {
        arbolnode x = y.left;
        arbolnode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private arbolnode leftRotate(arbolnode x) {
        arbolnode y = x.right;
        arbolnode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private int getHeight(arbolnode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(arbolnode node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private void updateHeight(arbolnode node) {
        if (node != null)
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private arbolnode balance(arbolnode node) {
        
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0)
                return rightRotate(node);
            else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balance < -1) {
            if (getBalance(node.right) <= 0)
                return leftRotate(node);
            else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    public void printLevelOrder() {
        if (root == null)
            return;

        Queue<arbolnode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            arbolnode tempNode = queue.poll();
            System.out.print(tempNode.data + " ");

            if (tempNode.left != null)
                queue.add(tempNode.left);

            if (tempNode.right != null)
                queue.add(tempNode.right);
        }
    }

    public static void main(String[] args) {
        arbol tree = new arbol();

        int[] elements = {4, 11, 9, 2, 17, 5, 3, 1, 8, 0, 9};

        System.out.println("Árbol antes de balancear:");
        for (int element : elements) {
            tree.insert(element);
        }
        tree.printLevelOrder();

        System.out.println("\n\nÁrbol después de balancear:");
        tree.printLevelOrder();
    }
}