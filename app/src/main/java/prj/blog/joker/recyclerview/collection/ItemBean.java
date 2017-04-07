package prj.blog.joker.recyclerview.collection;

/**
 * Created by XiaoYuLiu on 17/3/31.
 */

public class ItemBean {
    private String content;
    private boolean isSelected;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getContent() {
        return content;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
