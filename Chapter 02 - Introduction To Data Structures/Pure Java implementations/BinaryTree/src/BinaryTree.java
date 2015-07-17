import java.util.Arrays;

public class BinaryTree<TKey extends Comparable<TKey>, TValue> {
    private TreeNode<TKey, TValue> root;

    public void add(TKey key, TValue value) {
        if (key == null) {
            throw new IllegalArgumentException("Ключът на елемента за добавяне не може да бъде null.");
        }

        if (value == null) {
            throw new IllegalArgumentException("Стойността на елемента за добавяне не може да бъде null.");
        }

        this.root = this.insert(key, value, null, this.root);
    }

    // Търсене в двоичното дърво
    public TreeNode<TKey, TValue> find(TKey key) {
        TreeNode<TKey, TValue> currentNode = this.root;

        while (currentNode != null) {
            if (key.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeftChild();
            } else if (key.compareTo(currentNode.getKey()) > 0) {
                currentNode = currentNode.getRightChild();
            } else {
                break;
            }
        }

        return currentNode;
    }

    // Изключване от двоичното дърво
    public void remove(TKey key) {
        TreeNode<TKey, TValue> nodeToDelete = this.find(key);

        if (nodeToDelete == null) {
            throw new IllegalStateException("Върхът, който трябва да се изключи, не беше намерен.");
        } else {
            this.remove(nodeToDelete);
        }
    }

    @Override
    public String toString() {
        return this.getTextRepresentation(this.root, 0);
    }

    private TreeNode<TKey, TValue> insert(
            TKey key,
            TValue value,
            TreeNode<TKey, TValue> parentNode,
            TreeNode<TKey, TValue> currentNode) {
        // Текущия елемент е листо или празно дърво
        if (currentNode == null) {
            currentNode = new TreeNode<>(key, value);
            currentNode.setParent(parentNode);
        } else {
            if (key.compareTo(currentNode.getKey()) < 0) {
                currentNode.setLeftChild(this.insert(key, value, currentNode, currentNode.getLeftChild()));
            } else if (key.compareTo(currentNode.getKey()) > 0) {
                currentNode.setRightChild(this.insert(key, value, currentNode, currentNode.getRightChild()));
            } else {
                currentNode.setValue(value);
            }
        }

        return currentNode;
    }

    // Намиране на минималния елемент в дърво
    private TreeNode<TKey, TValue> findMinimalElement(TreeNode<TKey, TValue> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }

        return node;
    }

    private void remove(TreeNode<TKey, TValue> nodeToDelete) {
        // Елементът за изключване има два наследника
        if (nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() != null) {
            // Намира се върхът за размяна. По дефиниция минималния елемент
            // от дясното поддърво заменя елементът, който трябва да бъде изтрит
            TreeNode<TKey, TValue> replacementNode = nodeToDelete.getRightChild();
            replacementNode = this.findMinimalElement(replacementNode);
            nodeToDelete.setKey(replacementNode.getKey());
            nodeToDelete.setValue(replacementNode.getValue());
            nodeToDelete = replacementNode;
        }

        // Елементът за изтриване има най-много едно дете
        TreeNode<TKey, TValue> tempNode;

        if (nodeToDelete.getLeftChild() != null) {
            tempNode = nodeToDelete.getLeftChild();
        } else {
            tempNode = nodeToDelete.getRightChild();
        }

        // Елементът за изтриване има едно дете
        if (tempNode != null) {
            tempNode.setParent(nodeToDelete.getParent());

            // Елементът е корена на дървото
            if (nodeToDelete.getParent() == null) {
                this.root = tempNode;
            } else {
                // Размяна на елемента за изтриване със неговите поддървета
                if (nodeToDelete.getParent().getLeftChild() == nodeToDelete) {
                    nodeToDelete.getParent().setLeftChild(tempNode);
                } else {
                    nodeToDelete.getParent().setRightChild(tempNode);
                }
            }
        } else {
            // Елементът има нула или едно поддървета
            // Елементът е коренa
            if (nodeToDelete.getParent() == null) {
                this.root = null;
            } else {
                // Елементът е листо
                if (nodeToDelete.getParent().getLeftChild() == nodeToDelete) {
                    nodeToDelete.getParent().setLeftChild(null);
                } else {
                    nodeToDelete.getParent().setRightChild(null);
                }
            }
        }
    }

    private String getTextRepresentation(TreeNode<TKey, TValue> node, int offset) {
        StringBuilder result = new StringBuilder();

        if (node != null) {
            char[] spaces = new char[offset];
            Arrays.fill(spaces, ' ');
            result.append(spaces);

            result.append(node.getKey());
            result.append("\n");

            result.append(this.getTextRepresentation(node.getLeftChild(), offset + 2));
            result.append(this.getTextRepresentation(node.getRightChild(), offset + 2));
        }

        return result.toString();
    }
}
