# zz549
unıque bınary tree
class Solution {
    public List<TreeNode> generateTrees(int n) {

        return buildBST(1, n);
    }
    public List<TreeNode> buildBST(int start, int end){

        List<TreeNode> li = new ArrayList<>();

        if(start > end){
            li.add(null);
            return li;
        }
        if(start == end){
            li.add(new TreeNode(start));
            return li;
        }

        for(int indx = start; indx<=end; indx++){
         List<TreeNode> leftNodeList = buildBST(start, indx-1);
         List<TreeNode> rightNodeList = buildBST(indx+1, end);
            for(TreeNode rightSide : rightNodeList){
                for(TreeNode leftSide : leftNodeList){
                    TreeNode root = new TreeNode(indx);
                    root.left = leftSide;
                    root.right = rightSide;
                    li.add(root);
                }
            }
        }
        return li;
    }
}
